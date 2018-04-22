package app.brightpoint.com.ui.activity.bookawash

import android.Manifest
import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.View
import app.brightpoint.com.R
import app.brightpoint.com.session.DataSession
import app.brightpoint.com.ui.common.BaseActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.tedpark.tedpermission.rx2.TedRx2Permission
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_book_a_wash.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class BookAWashActivity: BaseActivity(),
        BookAWashContract.View,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener,
        LocationListener {

    @Inject
    lateinit var mPresenter: BookAWashPresenter

    @Inject
    lateinit var mDataSession: DataSession

    var mGoogleApiClient: GoogleApiClient? = null
    var mLocationRequest: LocationRequest? = null
    var mMap: GoogleMap? = null
    var mapFrag: SupportMapFragment? = null
    var latLng = LatLng("-6.185564".toDouble(),"106.823537".toDouble())
    var newLatLng : LatLng? = null
    var currLocationMarker : Marker? = null
    var mSearchArea: String? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_book_a_wash
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        setupUIListener()
        requestPermission()
    }

    override fun requestPermission() {
        TedRx2Permission.with(this)
                .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .request()
                .subscribe({ tedPermissionResult ->
                    if (tedPermissionResult.isGranted) {
                        //Permission Granted
                        mapFrag = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                        mapFrag?.getMapAsync(this)
                    } else {
                        //Denied by user
                    }
                }, { throwable -> throwable.message}, { })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun setupUIListener() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        btnFindBrightWash.setOnClickListener {
            searchLocation()
        }
    }

    override fun searchLocation() {
        showLoading()
        var addressList: List<Address>? = null
        val searchLocation = etSearchBrightWash.text

        if (searchLocation.isNotEmpty()) {
            mSearchArea = searchLocation.toString()
            val geocoder = Geocoder(this)
            try {
                addressList = geocoder.getFromLocationName(searchLocation.toString(), 1)
                mMap?.clear()
                val address = addressList.get(0)
                newLatLng = LatLng(address.latitude, address.longitude)
                addMarker(newLatLng!!.latitude, newLatLng!!.longitude, R.drawable.ic_marker, "Find Brightwash in Here")
                mMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                hideLoading()
                etSearchBrightWash.setText("")
                mMap!!.setOnMarkerClickListener(this)
            } catch (e: Exception) {
                hideLoading()
                toast("Error : $e")
            }
        } else {
            hideLoading()
            toast("Nothing to search")
        }
    }

    override fun showLoading() {
        pbBookAWash.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbBookAWash.visibility = View.GONE
    }

    override fun showError(msg: String) {
        toast("Error: ${msg}")
    }

    private fun setupGoogleClient(){
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()

        if (mGoogleApiClient != null) {
            mGoogleApiClient?.connect()
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
        mMap!!.mapType = GoogleMap.MAP_TYPE_TERRAIN
        mMap!!.isMyLocationEnabled = true
        setupGoogleClient()

        if(mGoogleApiClient != null) {
            mMap?.clear()
            addMarker(latLng.latitude, latLng.longitude, R.drawable.ic_marker, "Your Location")
            mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15F))
            hideLoading()
        }
    }

    override fun addMarker(lati: Double, longi: Double, marker: Int, titleMarker: String) {
        latLng = LatLng(lati, longi)
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)
        markerOptions.title(titleMarker)

        val bitmap = BitmapFactory.decodeResource(this.resources,marker)
        val markerDriver = BitmapDescriptorFactory.fromBitmap(bitmap)
        markerOptions.icon(markerDriver)

        mMap!!.addMarker(markerOptions)
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        if(marker != null && newLatLng != null) {
            mActivityNavigation.navigateToFindBrightwashPage(newLatLng!!, mSearchArea!!)
        }

        return false
    }

    override fun onConnected(p0: Bundle?) {

    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onLocationChanged(p0: Location?) {

    }
}
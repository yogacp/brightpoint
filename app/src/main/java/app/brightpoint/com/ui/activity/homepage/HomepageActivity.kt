package app.brightpoint.com.ui.activity.homepage

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import app.beslider.com.ui.Slider
import app.brightpoint.com.R
import app.brightpoint.com.adapters.BesliderAdapter
import app.brightpoint.com.helper.Helper
import app.brightpoint.com.session.DataSession
import app.brightpoint.com.ui.common.BaseActivity
import app.brightpoint.com.utils.ImageLoadingService
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.layout_homepage_banner.*
import kotlinx.android.synthetic.main.layout_homepage_content.*
import org.jetbrains.anko.toast
import javax.inject.Inject
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.tedpark.tedpermission.rx2.TedRx2Permission
import android.os.Looper
import android.support.v4.app.Fragment
import app.beslider.com.interfaces.OnSlideClickListener
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.maps.model.LatLng
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector


class HomepageActivity : BaseActivity(), HasSupportFragmentInjector, HomepageContract.View {

    @Inject
    lateinit var mFragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var mPresenter: HomepagePresenter

    @Inject
    lateinit var mDataSession: DataSession

    var mListImages = ArrayList<String?>()

    var currentLocation : Location? = null
    var mCurrentLatLng: LatLng? = LatLng("-6.185564".toDouble(), "106.823537".toDouble())
    var mLocationRequest: LocationRequest? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_homepage
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mPresenter.mView = this
        collectDefaultBanner()
        setupUIListener()
        getUserCurrentLocation()
        Log.d("Account","Data: ${mDataSession.getPhoneNumber()} - ${mDataSession.getLoginToken()}")
    }

    override fun collectDefaultBanner() {
        mListImages.add("https://firebasestorage.googleapis.com/v0/b/brightpoint-ac0ad.appspot.com/o/BP_BANNER%2Fbanner_microsite.webp?alt=media&token=6af63373-a197-4d9a-af5f-b714a3342953")
        mListImages.add("https://firebasestorage.googleapis.com/v0/b/brightpoint-ac0ad.appspot.com/o/BP_BANNER%2Fpertamina%20spbu.webp?alt=media&token=1c4a4c67-2871-41ba-9bdd-987f6178cd0c")
        mListImages.add("https://firebasestorage.googleapis.com/v0/b/brightpoint-ac0ad.appspot.com/o/BP_BANNER%2Fwash1.webp?alt=media&token=64d1d09e-1dfc-41f9-96c4-adc62fb10400")
        loadBanner()
    }

    override fun loadBanner() {
        Slider.init(ImageLoadingService())
        beslider.setAdapter(BesliderAdapter(mListImages))

        // If there is any click event on banner image
        beslider.setSlideClickListener(object : OnSlideClickListener {
            override fun onSlideClick(position: Int) {
                Log.d("Slider","Clicked")
            }
        })
    }

    override fun setupUIListener() {
        btnNearestSPBU.setOnClickListener {
            mActivityNavigation.navigateToNearestSPBUPage()
        }

        btnBookAWash.setOnClickListener {
            mActivityNavigation.navigateToBookAWashPage()
        }

        btnWashSchedule.setOnClickListener {
            mActivityNavigation.navigateToWashSchedulePage()
        }

        btnAccount.setOnClickListener {
            mActivityNavigation.navigateToAccountInformationDialog()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationPermission() {
        TedRx2Permission.with(this)
                .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE)
                .request()
                .subscribe({ tedPermissionResult ->
                    if (tedPermissionResult.isGranted) {
                        //Permission Granted
                        startLocationUpdates()
                    } else {
                        //Denied by user
                    }
                }, { throwable -> throwable.message}, { })
    }

    override fun onBackPressed() {
        showExitPopup()
    }

    override fun getUserCurrentLocation() {
//        if(mLocationRequest != null) {
//            startLocationUpdates()
//        } else {
//            requestLocationPermission()
//        }
        mDataSession.saveUserCurrentLocation("${mCurrentLatLng!!.latitude}, ${mCurrentLatLng!!.longitude}")
    }

    override fun showExitPopup() {
        val dialogSource = "2"
        val btnPositive = "Keluar"
        val btnNegative = "Batal"

        mHelper.showPopupDialog(this,
                "Konfirmasi",
                "Apakah Anda ingin keluar dari Aplikasi ?",
                dialogSource,
                btnPositive,
                btnNegative,
                object : Helper.CallbackDialog {
                    override fun onButtonPositiveClicked() {
                        exitApp()
                    }

                    override fun onButtonNegativeClicked() {

                    }

                })
    }

    override fun exitApp() {
        finishAffinity()
        System.exit(0)
    }

    @SuppressLint("MissingPermission")
    protected fun startLocationUpdates() {

        // Create the location request to start receiving updates
        mLocationRequest = LocationRequest()
        mLocationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest!!.interval = 10000
        mLocationRequest!!.fastestInterval = 5000

        // Create LocationSettingsRequest object using location request
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        val locationSettingsRequest = builder.build()

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(locationSettingsRequest)

        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                // do work here
                currentLocation = locationResult!!.lastLocation
                Log.d("UserLocation","Current Location: ${currentLocation!!.latitude}, ${currentLocation!!.longitude}")
            }
        }, Looper.myLooper())
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mFragmentDispatchingAndroidInjector
    }
}
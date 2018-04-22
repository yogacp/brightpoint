package app.brightpoint.com.ui.activity.bookawash

import com.google.android.gms.maps.model.LatLng
import io.reactivex.disposables.CompositeDisposable

interface BookAWashContract {
    interface View {
        fun requestPermission()
        fun setupUIListener()
        fun showLoading()
        fun hideLoading()
        fun showError(msg: String)
        fun addMarker(lati: Double, longi: Double, marker: Int, titleMarker: String)
        fun searchLocation()
    }

    interface UserActionListener {

    }
}
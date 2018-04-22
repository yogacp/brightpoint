package app.brightpoint.com.ui.activity.NearestSPBU

import app.brightpoint.com.vo.api.NearestSPBUData
import com.google.android.gms.maps.model.LatLng
import io.reactivex.disposables.CompositeDisposable

interface NearestSPBUContract {
    interface View {
        fun getUserCurrentLocation()
        fun setAdapter(spbuList: List<NearestSPBUData>)
        fun showLoading()
        fun hideLoading()
        fun showError(msg: String)
        fun getCompositeDisposable(): CompositeDisposable
    }

    interface UserActionListener {
        fun getListNearestSPBU(currentLatLng: LatLng)
    }
}
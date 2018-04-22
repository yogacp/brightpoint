package app.brightpoint.com.ui.activity.findbrightwash

import app.brightpoint.com.vo.api.FindBrightWashData
import com.google.android.gms.maps.model.LatLng
import io.reactivex.disposables.CompositeDisposable

interface FindBrightWashContract {
    interface View {
        fun setupUIListener()
        fun showLoading()
        fun hideLoading()
        fun showError(msg: String)
        fun setupAdapter(listData: List<FindBrightWashData>)
        fun getCompositeDisposable(): CompositeDisposable
    }

    interface UserActionListener {
        fun findBrightWash(latLng: LatLng)
    }
}
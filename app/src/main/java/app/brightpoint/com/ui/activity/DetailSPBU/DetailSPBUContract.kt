package app.brightpoint.com.ui.activity.DetailSPBU

import app.brightpoint.com.vo.api.*
import io.reactivex.disposables.CompositeDisposable

interface DetailSPBUContract {
    interface View {
        fun setDataToView(data: DetailSPBUData)
        fun setupUIListener()
        fun collectImages(data: List<DetailSPBUImagesData>)
        fun collectFacilities(data: DetailData)
        fun collectProducts(data: List<DetailSPBUProductData>)
        fun loadBanner()
        fun showLoading()
        fun hideLoading()
        fun showError(msg: String)
        fun getCompositeDisposable(): CompositeDisposable
    }

    interface UserActionListener {
        fun getData(spbu_id: Int)
    }
}
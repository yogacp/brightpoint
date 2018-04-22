package app.brightpoint.com.ui.activity.detailbrightwash

import app.brightpoint.com.vo.api.DetailBrightWashData
import app.brightpoint.com.vo.api.DetailSPBUData
import app.brightpoint.com.vo.api.DetailSPBUImagesData
import io.reactivex.disposables.CompositeDisposable

interface DetailBrightWashContract {
    interface View {
        fun setDataToView(data: DetailBrightWashData)
        fun setupUIListener()
        fun showLoading()
        fun hideLoading()
        fun showError(msg: String)
        fun getCompositeDisposable(): CompositeDisposable
        fun navigateToSuccessBooking(queue: Int)
    }

    interface UserActionListener{
        fun getBrightWashDetail(brightWashId: Int)
        fun bookingConfirmation(spbuid: Int)
    }
}
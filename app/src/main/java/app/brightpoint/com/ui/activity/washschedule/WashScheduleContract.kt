package app.brightpoint.com.ui.activity.washschedule

import app.brightpoint.com.vo.api.FindBrightWashData
import app.brightpoint.com.vo.api.WashScheduleData
import io.reactivex.disposables.CompositeDisposable

interface WashScheduleContract {
    interface View {
        fun setupUIListener()
        fun showLoading()
        fun hideLoading()
        fun showError(msg: String)
        fun setupAdapter(listData: List<WashScheduleData>)
        fun getCompositeDisposable(): CompositeDisposable
    }

    interface UserActionListener {
        fun getData()
    }
}
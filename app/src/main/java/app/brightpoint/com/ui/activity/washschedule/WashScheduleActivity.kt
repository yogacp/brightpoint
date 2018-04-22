package app.brightpoint.com.ui.activity.washschedule

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import app.brightpoint.com.R
import app.brightpoint.com.adapters.setUp
import app.brightpoint.com.ui.common.BaseActivity
import app.brightpoint.com.vo.api.WashScheduleData
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_wash_schedule.*
import kotlinx.android.synthetic.main.item_wash_schedule.view.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class WashScheduleActivity: BaseActivity(), WashScheduleContract.View {

    @Inject
    lateinit var mPresenter: WashSchedulePresenter

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mPresenter.mView = this
        setupUIListener()
        mPresenter.getData()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_wash_schedule
    }

    override fun setupUIListener() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun showLoading() {
        avWashScheduleIndicator.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        avWashScheduleIndicator.visibility = View.GONE
    }

    override fun showError(msg: String) {
        toast("Error $msg")
    }

    override fun setupAdapter(listData: List<WashScheduleData>) {
        rvWashScheduleList.setUp(listData, R.layout.item_wash_schedule, {
            tvName.text = it.spbuName
            tvCurrentQueue.text = "Current Queue: ${if(it.currentQueue.equals("-")) 0 else it.currentQueue.toString().toInt()}"
            tvDate.text = it.queueDate

            when(it.status) {
                "Cancelled" -> {
                    tvStatus.text = "Cancelled"
                    tvStatus.background = ContextCompat.getDrawable(context, R.drawable.button_red_rounded)
                }
                "Done" -> {
                    tvStatus.text = "Done"
                    tvStatus.background = ContextCompat.getDrawable(context, R.drawable.button_green_rounded)
                }
                "In-Progress" -> {
                    tvStatus.text = "In Progress"
                    tvStatus.background = ContextCompat.getDrawable(context, R.drawable.button_orange_rounded)
                }
            }
        })
    }

    override fun getCompositeDisposable(): CompositeDisposable {
        return mDisposables
    }

}
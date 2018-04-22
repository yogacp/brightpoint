package app.brightpoint.com.ui.activity.findbrightwash

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import app.brightpoint.com.R
import app.brightpoint.com.adapters.setUp
import app.brightpoint.com.ui.activity.DetailSPBU.DetailSPBUActivity
import app.brightpoint.com.ui.common.BaseActivity
import app.brightpoint.com.vo.api.FindBrightWashData
import com.google.android.gms.maps.model.LatLng
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_find_brightwash.*
import kotlinx.android.synthetic.main.item_find_brightwash.view.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class FindBrightWashActivity: BaseActivity(), FindBrightWashContract.View {

    companion object {
        val TAG_LAT_LONG = "tag_lat_long"
        val TAG_SEARCH_AREA = "tag_search_area"
    }

    @Inject
    lateinit var mPresenter: FindBrightWashPresenter
    var mSearchArea: String? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_find_brightwash
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mPresenter.mView = this
        setupUIListener()
    }

    override fun setupUIListener() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val bundle = intent.extras
        if (bundle != null) {
            val location = bundle.getString(TAG_LAT_LONG).split(",")
            val locationLatLng = LatLng(location[0].toDouble(), location[1].toDouble())
            mSearchArea = bundle.getString(TAG_SEARCH_AREA)
            mPresenter.findBrightWash(locationLatLng)
            tvSearchArea.text = mSearchArea
        }
    }

    override fun showLoading() {
        avFindBrightWashIndicator.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        avFindBrightWashIndicator.visibility = View.GONE
    }

    override fun showError(msg: String) {
        toast("Error: $msg")
    }

    override fun setupAdapter(listData: List<FindBrightWashData>) {
        rvFindBrightWashList.setUp(listData, R.layout.item_find_brightwash,{
            val carwashId = it.id
            tvItemCarwashName.text = it.name
            tvItemCarwashDistance.text = "${it.distance} Km"
            tvItemCarwashAddress.text = "${it.address.substring(0,30)} ..."
            tvItemCarwashCapacity.text = "Capacity: ${it.capacity} cars"
            tvItemCarwashQueue.text = "Total Queue: ${it.currentQueue}"

            layoutItem.setOnClickListener {
                mActivityNavigation.navigateToDetailBrightwashPage(carwashId)
            }
        })
    }

    override fun getCompositeDisposable(): CompositeDisposable {
        return mDisposables
    }
}
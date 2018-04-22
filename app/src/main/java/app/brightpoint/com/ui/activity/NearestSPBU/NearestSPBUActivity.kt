package app.brightpoint.com.ui.activity.NearestSPBU

import android.os.Bundle
import android.view.View
import app.brightpoint.com.R
import app.brightpoint.com.adapters.setUp
import app.brightpoint.com.session.DataSession
import app.brightpoint.com.ui.common.BaseActivity
import app.brightpoint.com.vo.api.NearestSPBUData
import com.google.android.gms.maps.model.LatLng
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_nearest_spbu.*
import kotlinx.android.synthetic.main.item_nearest_spbu.view.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class NearestSPBUActivity: BaseActivity(), NearestSPBUContract.View {

    @Inject
    lateinit var mPresenter: NearestSPBUPresenter

    @Inject
    lateinit var mDataSession: DataSession

    var mCurrentLatLng: LatLng? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_nearest_spbu
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mPresenter.mView = this
        getUserCurrentLocation()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun getUserCurrentLocation() {
        val userLocation = mDataSession.getUserCurrentLocation().split(",")
        mCurrentLatLng = LatLng(userLocation[0].toDouble(), userLocation[1].toDouble())

        if(mCurrentLatLng != null) {
            mPresenter.getListNearestSPBU(mCurrentLatLng!!)
        } else {
            hideLoading()
            toast("Jaringan sedang bermasalah, silahkan coba lagi.")
        }
    }

    override fun setAdapter(spbuList: List<NearestSPBUData>) {
        rvNearestSpbuList.setUp(spbuList, R.layout.item_nearest_spbu, {
            val spbuId = it.id
            tvItemSPBUName.text = it.name
            tvItemSPBUDistance.text = "${it.distance} Km"
            tvItemSPBUAddress.text = "${it.address.substring(0,30)} ..."

            if(it.isBrightwash  == 1) ivCarwashFacility.visibility = View.VISIBLE
            if(it.isOlimart     == 1) ivOilFacility.visibility = View.VISIBLE
            if(it.isSnackStore  == 1) ivCSFacility.visibility = View.VISIBLE
            if(it.isMosque      == 1) ivMosqueFacility.visibility = View.VISIBLE
            if(it.isToilet      == 1) ivToiletFacility.visibility = View.VISIBLE

            layoutItem.setOnClickListener {
                mActivityNavigation.navigateToDetailSPBUPage(spbuId)
            }
        })
    }

    override fun hideLoading() {
        avNearestSpbuIndicator.visibility = View.GONE
    }

    override fun showLoading() {
        avNearestSpbuIndicator.visibility = View.VISIBLE
    }

    override fun showError(msg: String) {
        toast("Error: $msg")
    }

    override fun getCompositeDisposable(): CompositeDisposable {
        return mDisposables
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
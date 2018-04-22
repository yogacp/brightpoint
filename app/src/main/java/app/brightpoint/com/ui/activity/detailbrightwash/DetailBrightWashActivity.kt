package app.brightpoint.com.ui.activity.detailbrightwash

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import app.brightpoint.com.R
import app.brightpoint.com.ui.activity.DetailSPBU.DetailSPBUActivity
import app.brightpoint.com.ui.common.BaseActivity
import app.brightpoint.com.vo.api.DetailBrightWashData
import com.google.android.gms.maps.model.LatLng
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_detail_brightwash.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class DetailBrightWashActivity: BaseActivity(), HasSupportFragmentInjector, DetailBrightWashContract.View {

    companion object {
        val TAG_BRIGHTWAS_ID = "tag_brightwas_id"
    }

    @Inject
    lateinit var mPresenter: DetailBrightWashPresenter

    @Inject
    lateinit var mFragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    var mLocation: LatLng? = null
    var mSpbuName: String? = null
    var mAddress: String? = null
    var mCapacity: String? = null
    var mCurrentQueue: String? = null
    var mImage: String? = null
    var mSpbuId: Int = 0

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mPresenter.mView = this
        setupUIListener()

        val bundle = intent.extras
        if (bundle != null) {
            mSpbuId = bundle.getInt(TAG_BRIGHTWAS_ID)
            mPresenter.getBrightWashDetail(mSpbuId)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_detail_brightwash
    }

    override fun setupUIListener() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        btnSeeLocation.setOnClickListener {
            mActivityNavigation.openMap(mLocation!!,mSpbuName)
        }

        btnConfirmBooking.setOnClickListener {
            mPresenter.bookingConfirmation(mSpbuId)
        }
    }

    override fun setDataToView(data: DetailBrightWashData) {
        mLocation = LatLng(data.latitude.toDouble(), data.longitude.toDouble())
        mSpbuName = data.name
        mAddress = data.address
        mCurrentQueue = data.currentQueue.toString()
        mImage = data.image
        mCapacity = data.capacity.toString()

        tvTitle.text = mSpbuName
        tvAddress.text = mAddress
        tvCurrentQueue.text = "Total Queue: ${mCurrentQueue}"
        tvCapacity.text = "Capacity ${mCapacity} Cars"
        Picasso.get().load(mImage).into(bannerImage)
    }

    override fun showLoading() {
        avDetailBwIndicator.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        avDetailBwIndicator.visibility = View.GONE
    }

    override fun showError(msg: String) {
        toast("Error: $msg")
    }

    override fun getCompositeDisposable(): CompositeDisposable {
        return mDisposables
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mFragmentDispatchingAndroidInjector
    }

    override fun navigateToSuccessBooking(queue: Int) {
        mActivityNavigation.navigateSuccessBookingDialog(queue)
    }
}
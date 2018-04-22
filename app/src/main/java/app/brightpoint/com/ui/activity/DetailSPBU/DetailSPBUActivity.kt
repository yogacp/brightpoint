package app.brightpoint.com.ui.activity.DetailSPBU

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import app.beslider.com.ui.Slider
import app.brightpoint.com.R
import app.brightpoint.com.adapters.BesliderAdapter
import app.brightpoint.com.adapters.setUp
import app.brightpoint.com.session.DataSession
import app.brightpoint.com.ui.common.BaseActivity
import app.brightpoint.com.utils.ImageLoadingService
import app.brightpoint.com.vo.api.DetailData
import app.brightpoint.com.vo.api.DetailSPBUData
import app.brightpoint.com.vo.api.DetailSPBUImagesData
import app.brightpoint.com.vo.api.DetailSPBUProductData
import com.google.android.gms.maps.model.LatLng
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_detail_spbu.*
import kotlinx.android.synthetic.main.item_detail_spbu_products.view.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class DetailSPBUActivity: BaseActivity(), DetailSPBUContract.View {

    companion object {
        val TAG_DETAIL_SPBUID = "TAG_SPBUID"
    }

    @Inject
    lateinit var mPresenter: DetailSPBUPresenter

    @Inject
    lateinit var mDataSession: DataSession

    var mListImages = ArrayList<String?>()
    var mLocation : LatLng? = null
    var mSpbuName: String? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_detail_spbu
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mPresenter.mView = this
        setupUIListener()

        val bundle = intent.extras
        if (bundle != null) {
            val spbuid = bundle.getInt(TAG_DETAIL_SPBUID)
            mPresenter.getData(spbuid)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun setupUIListener() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        btnSeeLocation.setOnClickListener {
            mActivityNavigation.openMap(mLocation!!,mSpbuName)
        }
    }

    override fun setDataToView(data: DetailSPBUData) {
        title = data.detail.name
        mSpbuName = data.detail.name
        collectImages(data.images)
        collectFacilities(data.detail)
        collectProducts(data.products)
        mLocation = LatLng(data.detail.latitude.toDouble(), data.detail.longitude.toDouble())
    }

    override fun collectProducts(data: List<DetailSPBUProductData>) {
        rvProductList.setUp(data, R.layout.item_detail_spbu_products, {
            tvProductTitle.text = it.name
            tvProductPrice.text = "Rp ${it.price}"

            if(it.isAvailable == 1) {
                imgAvailable.visibility = View.VISIBLE
                imgEmpty.visibility = View.GONE
            } else {
                tvProductTitle.typeface = Typeface.DEFAULT
                tvProductPrice.typeface = Typeface.DEFAULT
                imgAvailable.visibility = View.GONE
                imgEmpty.visibility = View.VISIBLE
            }
        })
    }

    override fun collectFacilities(data: DetailData) {
        if(data.isBrightwash  == 1) ivCarwashFacility.visibility = View.VISIBLE
        if(data.isOlimart     == 1) ivOilFacility.visibility = View.VISIBLE
        if(data.isSnackStore  == 1) ivCSFacility.visibility = View.VISIBLE
        if(data.isMosque      == 1) ivMosqueFacility.visibility = View.VISIBLE
        if(data.isToilet      == 1) ivToiletFacility.visibility = View.VISIBLE
    }

    override fun collectImages(data: List<DetailSPBUImagesData>) {
        for(image in data) {
            mListImages.add(image.filename)
        }
        loadBanner()
    }

    override fun loadBanner() {
        Slider.init(ImageLoadingService())
        besliderDetailSpbu.setAdapter(BesliderAdapter(mListImages))
    }

    override fun showLoading() {
        avDetailSpbuIndicator.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        avDetailSpbuIndicator.visibility = View.GONE
    }

    override fun showError(msg: String) {
        toast("Error: $msg")
    }

    override fun getCompositeDisposable(): CompositeDisposable {
        return mDisposables
    }
}
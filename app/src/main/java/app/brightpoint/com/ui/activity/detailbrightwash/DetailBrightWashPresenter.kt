package app.brightpoint.com.ui.activity.detailbrightwash

import app.brightpoint.com.api.responses.success.BaseApiResponse
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.session.DataSession
import app.brightpoint.com.vo.api.BookingConfirmedData
import app.brightpoint.com.vo.api.DefaultData
import app.brightpoint.com.vo.api.DetailBrightWashData
import app.brightpoint.com.vo.api.DetailSPBUData
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class DetailBrightWashPresenter @Inject constructor(
        val mGson : Gson,
        val mDataSession: DataSession,
        val mRepository: BrightPointRepository
): DetailBrightWashContract.UserActionListener {

    lateinit var mView: DetailBrightWashContract.View

    override fun getBrightWashDetail(brightWashId: Int) {
        mView.showLoading()

        val userInfo = HashMap<String, Any>()
        userInfo.put("phone",mDataSession.getPhoneNumber())
        userInfo.put("token", mDataSession.getLoginToken())

        val data = HashMap<String, Any>()
        data.put("spbu_id",brightWashId)

        mView.getCompositeDisposable()
                .add(mRepository.getDetailBrightwash(mGson.toJson(userInfo), mGson.toJson(data))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith (object : DisposableSubscriber<BaseApiResponse<DetailBrightWashData>>(){
                            override fun onNext(result: BaseApiResponse<DetailBrightWashData>?) {
                                mView.hideLoading()
                                if(result!!.resultCode == 1){
                                    mView.setDataToView(result.data)
                                }else{
                                    mView.showError(result.resultMessage)
                                }
                            }

                            override fun onComplete() {

                            }

                            override fun onError(t: Throwable?) {
                                mView.hideLoading()
                                mView.showError(t?.message!!)
                            }

                        })
                )
    }

    override fun bookingConfirmation(spbuid: Int) {
        mView.showLoading()

        val userInfo = HashMap<String, Any>()
        userInfo.put("phone",mDataSession.getPhoneNumber())
        userInfo.put("token", mDataSession.getLoginToken())

        val data = HashMap<String, Any>()
        data.put("spbu_id",spbuid)

        mView.getCompositeDisposable()
                .add(mRepository.bookConfirmation(mGson.toJson(userInfo), mGson.toJson(data))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith (object : DisposableSubscriber<BaseApiResponse<BookingConfirmedData>>(){
                            override fun onNext(result: BaseApiResponse<BookingConfirmedData>?) {
                                mView.hideLoading()
                                if(result!!.resultCode == 1){
                                    mView.navigateToSuccessBooking(result.data.queueNo)
                                }else{
                                    mView.showError(result.resultMessage)
                                }
                            }

                            override fun onComplete() {

                            }

                            override fun onError(t: Throwable?) {
                                mView.hideLoading()
                                mView.showError(t?.message!!)
                            }

                        })
                )
    }
}
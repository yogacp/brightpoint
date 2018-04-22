package app.brightpoint.com.ui.activity.washschedule

import app.brightpoint.com.api.responses.success.BaseApiResponse
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.session.DataSession
import app.brightpoint.com.vo.api.FindBrightWashData
import app.brightpoint.com.vo.api.WashScheduleData
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class WashSchedulePresenter @Inject constructor(
        val mGson : Gson,
        val mDataSession: DataSession,
        val mRepository: BrightPointRepository
): WashScheduleContract.UserActionListener {

    lateinit var mView: WashScheduleContract.View

    override fun getData() {
        mView.showLoading()

        val userInfo = HashMap<String, Any>()
        userInfo.put("phone",mDataSession.getPhoneNumber())
        userInfo.put("token", mDataSession.getLoginToken())

        mView.getCompositeDisposable()
                .add(mRepository.washSchedule(mGson.toJson(userInfo))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith (object : DisposableSubscriber<BaseApiResponse<List<WashScheduleData>>>(){
                            override fun onNext(result: BaseApiResponse<List<WashScheduleData>>?) {
                                mView.hideLoading()
                                if(result!!.resultCode == 1){
                                    mView.setupAdapter(result.data)
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
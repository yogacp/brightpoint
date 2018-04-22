package app.brightpoint.com.ui.activity.findbrightwash

import app.brightpoint.com.api.responses.success.BaseApiResponse
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.session.DataSession
import app.brightpoint.com.vo.api.FindBrightWashData
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class FindBrightWashPresenter @Inject constructor(
        val mGson : Gson,
        val mDataSession: DataSession,
        val mRepository: BrightPointRepository
): FindBrightWashContract.UserActionListener {

    lateinit var mView : FindBrightWashContract.View

    override fun findBrightWash(latLng: LatLng) {
        mView.showLoading()

        val userInfo = HashMap<String, Any>()
        userInfo.put("phone",mDataSession.getPhoneNumber())
        userInfo.put("token", mDataSession.getLoginToken())

        val data = HashMap<String, Any>()
        data.put("start_lat",latLng.latitude)
        data.put("start_longi",latLng.longitude)

        mView.getCompositeDisposable()
                .add(mRepository.findBrightWash(mGson.toJson(userInfo), mGson.toJson(data))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith (object : DisposableSubscriber<BaseApiResponse<List<FindBrightWashData>>>(){
                            override fun onNext(result: BaseApiResponse<List<FindBrightWashData>>?) {
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
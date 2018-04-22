package app.brightpoint.com.ui.activity.NearestSPBU

import app.brightpoint.com.api.responses.success.BaseApiResponse
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.session.DataSession
import app.brightpoint.com.vo.api.LoginData
import app.brightpoint.com.vo.api.NearestSPBUData
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class NearestSPBUPresenter @Inject constructor(
        val mGson : Gson,
        val mDataSession: DataSession,
        val mRepository: BrightPointRepository
): NearestSPBUContract.UserActionListener {

    lateinit var mView: NearestSPBUContract.View

    override fun getListNearestSPBU(currentLatLng: LatLng) {
        mView.showLoading()

        val userInfo = HashMap<String, Any>()
        userInfo.put("phone",mDataSession.getPhoneNumber())
        userInfo.put("token", mDataSession.getLoginToken())

        val data = HashMap<String, Any>()
        data.put("current_lat",currentLatLng.latitude)
        data.put("current_longi",currentLatLng.longitude)

        mView.getCompositeDisposable()
            .add(mRepository.searchNearestSPBU(mGson.toJson(userInfo), mGson.toJson(data))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith (object : DisposableSubscriber<BaseApiResponse<List<NearestSPBUData>>>(){
                        override fun onNext(result: BaseApiResponse<List<NearestSPBUData>>?) {
                            mView.hideLoading()
                            if(result!!.resultCode == 1){
                                mView.setAdapter(result.data)
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
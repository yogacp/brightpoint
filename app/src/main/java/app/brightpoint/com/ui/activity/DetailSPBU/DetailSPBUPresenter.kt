package app.brightpoint.com.ui.activity.DetailSPBU

import app.brightpoint.com.api.responses.success.BaseApiResponse
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.session.DataSession
import app.brightpoint.com.vo.api.DetailSPBUData
import app.brightpoint.com.vo.api.NearestSPBUData
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class DetailSPBUPresenter @Inject constructor(
        val mGson : Gson,
        val mDataSession: DataSession,
        val mRepository: BrightPointRepository
): DetailSPBUContract.UserActionListener {

    lateinit var mView: DetailSPBUContract.View

    override fun getData(spbu_id: Int) {
        mView.showLoading()

        val userInfo = HashMap<String, Any>()
        userInfo.put("phone",mDataSession.getPhoneNumber())
        userInfo.put("token", mDataSession.getLoginToken())

        val data = HashMap<String, Any>()
        data.put("spbu_id",spbu_id)

        mView.getCompositeDisposable()
                .add(mRepository.getDetailSPBU(mGson.toJson(userInfo), mGson.toJson(data))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith (object : DisposableSubscriber<BaseApiResponse<DetailSPBUData>>(){
                            override fun onNext(result: BaseApiResponse<DetailSPBUData>?) {
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
}
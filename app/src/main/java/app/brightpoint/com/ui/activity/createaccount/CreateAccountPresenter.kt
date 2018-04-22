package app.brightpoint.com.ui.activity.createaccount

import app.brightpoint.com.api.NetworkServices
import app.brightpoint.com.api.responses.success.BaseApiResponse
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.session.DataSession
import app.brightpoint.com.vo.api.DefaultData
import app.brightpoint.com.vo.api.LoginData
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class CreateAccountPresenter @Inject constructor(
        val mGson : Gson,
        val mLoginSession: DataSession,
        val mRepository: BrightPointRepository
): CreateAccountContract.UserActionListener {

    lateinit var mView: CreateAccountContract.View

    override fun register(fullname: String, noHp: String, email: String, password: String) {
        mView.showLoading()

        val data = HashMap<String, Any>()
        data.put("name", fullname)
        data.put("phone", noHp)
        data.put("email",email)
        data.put("password",password)

        mView.getCompositeDisposable()
                .add(mRepository.registerAccount(mGson.toJson(data))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith (object : DisposableSubscriber<BaseApiResponse<DefaultData>>(){
                            override fun onNext(result: BaseApiResponse<DefaultData>?) {
                                mView.hideLoading()
                                if(result?.resultCode == 1){
                                    mLoginSession.savePhoneNumber(noHp)
                                    mView.showSuccessCreateAccount()
                                }else{

                                }
                            }

                            override fun onComplete() {

                            }

                            override fun onError(t: Throwable?) {
                                mView.hideLoading()
                                mView.showError("Server sedang bermasalah. ${t?.message!!}")
                            }

                        })
        )
    }

}
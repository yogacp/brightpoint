package app.brightpoint.com.ui.activity.login

import android.content.Context
import app.brightpoint.com.api.responses.success.BaseApiResponse
import app.brightpoint.com.helper.Helper
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.session.DataSession
import app.brightpoint.com.vo.api.LoginData
import com.google.gson.Gson
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class LoginPresenter @Inject constructor(
        val mDataSession: DataSession,
        val mRepository: BrightPointRepository,
        val mGson : Gson
) : LoginContract.UserActionListener {

    lateinit var mView: LoginContract.View

    override fun checkIsLogin() {
        if(mDataSession.getLoginToken().isNotEmpty()){
            mView.navigateToHomePage()
        }
    }

    override fun login(phoneNo: String, password: String) {
        if(!isValidPhoneNo(phoneNo)){
            mView.showError("Invalid Phone No")
        }else if(!isValidPassword(password)){
            mView.showError("Invalid Password")
        }else if(!isValidPassword(password) && !isValidPhoneNo(phoneNo)){
            mView.showError("Invalid Phone No and Password")
        }else{
            mView.showLoading()
            val data = HashMap<String, Any>()
            data.put("phone",phoneNo)
            data.put("password", password)

            mView.getCompositeDisposable()
                    .add(mRepository.userLogin(mGson.toJson(data))
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith (object : DisposableSubscriber<BaseApiResponse<LoginData>>(){
                                override fun onNext(result: BaseApiResponse<LoginData>?) {
                                    mView.hideLoading()
                                    if(result?.resultCode == 1){
                                        mDataSession.savePhoneNumber(result.data.phone)
                                        mDataSession.saveToken(result.data.token)
                                        mView.navigateToHomePage()
                                    }else{

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

    override fun isValidPhoneNo(phoneNo: String): Boolean {
        return phoneNo.isNotEmpty()
    }

    override fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty()
    }
}
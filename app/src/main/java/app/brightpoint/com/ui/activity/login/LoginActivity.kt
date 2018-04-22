package app.brightpoint.com.ui.activity.login

import android.os.Bundle
import android.view.View
import app.brightpoint.com.R
import app.brightpoint.com.session.DataSession
import app.brightpoint.com.ui.common.BaseActivity
import com.google.firebase.iid.FirebaseInstanceId
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class LoginActivity: BaseActivity(), LoginContract.View {

    @Inject
    lateinit var mPresenter: LoginPresenter

    @Inject
    lateinit var mDataSession: DataSession

    private val mEtLoginUserName by lazy { et_login_username }

    private val mEtPassword by lazy { et_login_password }

    private val mTvSignUpHere by lazy { tv_sign_up_here }

    private val mBtnSignIn by lazy { btn_sign_in }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mPresenter.mView = this
        val fbToken =  FirebaseInstanceId.getInstance().token
        fbToken?.let {
            mDataSession.saveFirebaseToken(it)
        }
        mPresenter.checkIsLogin()
        setupUIListener()
    }

    override fun setupUIListener() {
        mBtnSignIn.setOnClickListener {
            val mUsername = mEtLoginUserName.text.toString()
            val mPassword = mEtPassword.text.toString()
            mPresenter.login(mUsername, mPassword)
        }
        mTvSignUpHere.setOnClickListener { mActivityNavigation.navigateToRegisterPage() }
    }

    override fun showError(content: String) {
        toast("Error: $content")
    }

    override fun navigateToHomePage() {
        mActivityNavigation.navigateToHomePage()
    }

    override fun showLoading() {
        pb_login.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_login.visibility = View.GONE
    }

    override fun getCompositeDisposable(): CompositeDisposable {
        return mDisposables
    }

}
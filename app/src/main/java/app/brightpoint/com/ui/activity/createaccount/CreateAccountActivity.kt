package app.brightpoint.com.ui.activity.createaccount

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import app.brightpoint.com.R
import app.brightpoint.com.ui.common.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class CreateAccountActivity: BaseActivity(), HasSupportFragmentInjector, CreateAccountContract.View {

    @Inject
    lateinit var mFragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var mPresenter: CreateAccountPresenter

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mPresenter.mView = this
        setupUIListener()
    }

    override fun onBackPressed() {
        mActivityNavigation.navigateToLoginPage()
        finish()
        super.onBackPressed()
    }

    override fun showLoading() {
        pb_register.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_register.visibility = View.GONE
    }

    override fun setupUIListener() {
        btnRegisterSignup.setOnClickListener {
            submitRegistration()
        }
    }

    override fun showError(content: String) {
        toast("Error: $content")
    }

    override fun showSuccessCreateAccount() {
        mActivityNavigation.navigateToRegisterSuccessDialog()
    }

    override fun getCompositeDisposable(): CompositeDisposable {
        return mDisposables
    }

    override fun submitRegistration() {
        val mFullName = et_register_fullname.text.toString()
        val mPhoneNo = et_register_no_hp.text.toString()
        val mEmail = et_register_email.text.toString()
        val mPassword = et_register_password.text.toString()
        val mConfirmPassword = et_register_confirm_password.text.toString()

        if(mPassword == mConfirmPassword){
            mPresenter.register(mFullName,mPhoneNo,mEmail,mPassword)
        }else{
            showError("Password dan Konfirmasi Password tidak sama, harap ulangi kembali")
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mFragmentDispatchingAndroidInjector
    }

}
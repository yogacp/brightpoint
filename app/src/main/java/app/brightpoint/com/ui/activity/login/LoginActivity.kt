package app.brightpoint.com.ui.activity.login

import android.os.Bundle
import app.brightpoint.com.R
import app.brightpoint.com.ui.common.BaseActivity
import javax.inject.Inject

class LoginActivity: BaseActivity(), LoginContract.View {

    @Inject
    lateinit var mPresenter: LoginPresenter

    override fun onActivityReady(savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

}
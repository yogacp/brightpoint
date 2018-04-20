package app.brightpoint.com.ui.activity.login

import android.content.Context
import app.brightpoint.com.helper.Helper
import com.google.gson.Gson
import javax.inject.Inject

class LoginPresenter @Inject constructor(
        val mHelper : Helper,
        val mContext : Context,
        val mGson : Gson
) : LoginContract.UserActionListener {

    lateinit var mView: LoginContract.View

    override fun setupView(view: LoginContract.View) {
        mView = view
    }
}
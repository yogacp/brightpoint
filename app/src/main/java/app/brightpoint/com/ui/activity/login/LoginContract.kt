package app.brightpoint.com.ui.activity.login

import io.reactivex.disposables.CompositeDisposable

interface LoginContract {

    interface View {
        fun setupUIListener()
        fun showError(content : String)
        fun navigateToHomePage()
        fun showLoading()
        fun hideLoading()
        fun getCompositeDisposable(): CompositeDisposable
    }
    interface UserActionListener{
        fun checkIsLogin()
        fun login(phoneNo : String, password :String)
        fun isValidPhoneNo(phoneNo: String) : Boolean
        fun isValidPassword(password: String) : Boolean
    }
}
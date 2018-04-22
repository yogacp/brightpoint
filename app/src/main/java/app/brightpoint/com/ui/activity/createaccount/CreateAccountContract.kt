package app.brightpoint.com.ui.activity.createaccount

import io.reactivex.disposables.CompositeDisposable

interface CreateAccountContract {

    interface View {
        fun setupUIListener()
        fun submitRegistration()
        fun showError(content :String)
        fun showLoading()
        fun hideLoading()
        fun showSuccessCreateAccount()
        fun getCompositeDisposable(): CompositeDisposable
    }

    interface UserActionListener{
        fun register(fullname :String, noHp :String,email :String, password:String)
    }

}
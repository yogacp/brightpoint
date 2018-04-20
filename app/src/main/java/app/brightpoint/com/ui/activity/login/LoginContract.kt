package app.brightpoint.com.ui.activity.login

interface LoginContract {
    interface View {

    }

    interface UserActionListener {
        fun setupView(view: View)
    }
}
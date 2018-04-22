package app.brightpoint.com.ui.activity.homepage

interface HomepageContract {

    interface View {
        fun collectDefaultBanner()
        fun loadBanner()
        fun showExitPopup()
        fun exitApp()
        fun setupUIListener()
        fun getUserCurrentLocation()
    }

    interface UserActionListener {

    }
}
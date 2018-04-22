package app.brightpoint.com.ui.activity.homepage

import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.session.DataSession
import com.google.gson.Gson
import javax.inject.Inject

class HomepagePresenter @Inject constructor(
        val mGson : Gson,
        val mDataSession: DataSession,
        val mRepository: BrightPointRepository
): HomepageContract.UserActionListener {

    lateinit var mView : HomepageContract.View

}
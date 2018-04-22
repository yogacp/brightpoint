package app.brightpoint.com.ui.activity.bookawash

import app.brightpoint.com.api.responses.success.BaseApiResponse
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.session.DataSession
import app.brightpoint.com.vo.api.DetailSPBUData
import app.brightpoint.com.vo.api.FindBrightWashData
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class BookAWashPresenter @Inject constructor(
        val mGson : Gson,
        val mDataSession: DataSession,
        val mRepository: BrightPointRepository
): BookAWashContract.UserActionListener {

    lateinit var mView: BookAWashContract.View

}
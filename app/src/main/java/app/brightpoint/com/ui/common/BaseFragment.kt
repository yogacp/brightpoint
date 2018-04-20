package app.brightpoint.com.ui.common

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.brightpoint.com.helper.Helper
import app.brightpoint.com.ui.common.navigation.ActivityNavigation
import com.google.gson.Gson
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var mActivityNavigation: ActivityNavigation

//    @Inject
//    lateinit var mFirebaseDB: FirebaseDBUtils

    @Inject
    lateinit var mGson: Gson

    @Inject
    lateinit var mHelper: Helper

    var mView: View? = null

    var mDisposables = CompositeDisposable()

    /**
     * Getting Layout ID from activity
     * */

    abstract fun getLayoutId () : Int

    /**
     * This method will be executed after view has been create in fragment
     */

    protected abstract fun onLoadFragment(saveInstance: Bundle?)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflater.let {
            mView  = inflater.inflate(getLayoutId(), container, false)
        }
        return mView
    }

    /**
     * This method will be executed after all views in fragment has rendered
     * */

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onLoadFragment(savedInstanceState)
    }

    fun getCurrentActivity() : FragmentActivity {
        return activity!!
    }
}
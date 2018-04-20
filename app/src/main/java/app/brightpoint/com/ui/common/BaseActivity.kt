package app.brightpoint.com.ui.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import app.brightpoint.com.helper.Helper
import app.brightpoint.com.ui.common.navigation.ActivityNavigation
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var mHelper: Helper

    @Inject
    lateinit var mActivityNavigation: ActivityNavigation

//    @Inject
//    lateinit var mFirebaseDB: FirebaseDBUtils

    var mDisposables = CompositeDisposable()

    /**
     * This function replace onCreate as main function run in activity
     * Auto Dependency Injection
     * @param Bundle
     * */

    abstract fun onActivityReady(savedInstanceState: Bundle?)

    /**
     * Getting Layout ID from activity
     * */

    abstract fun getLayoutId () : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        //Auto DI
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        onActivityReady(savedInstanceState)
    }

}
package app.brightpoint.com

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import app.brightpoint.com.di.component.AppComponent
import app.brightpoint.com.di.component.DaggerAppComponent
import app.brightpoint.com.di.module.FirebaseJobDispatcherModule
import app.brightpoint.com.di.module.NetworkModule
import app.brightpoint.com.helper.Helper
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BrightPointApp : Application(), HasActivityInjector {

    companion object {
        @JvmStatic lateinit var instance: BrightPointApp
        @JvmStatic lateinit var appComponent: AppComponent
    }

    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var mHelper: Helper

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //Initialize Multidex for prevent limit over 80k method
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        // Set Instance
        instance = this

        // Set App Component
        appComponent = createComponent()
        appComponent.inject(this)

        // Initialize Firebase App
        FirebaseApp.initializeApp(this)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return mActivityInjector
    }

    /**
     * Initialize Dependency Injection With Dagger 2
     * Level DI Application
     * */
    fun createComponent(): AppComponent {
        val appComponent = DaggerAppComponent.builder()
                .application(this)
                .networkModule(NetworkModule(this))
                .firebaseJobDispatcherModule(FirebaseJobDispatcherModule(this))
                .build()
        return appComponent
    }
}

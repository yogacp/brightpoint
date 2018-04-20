package app.brightpoint.com.di.module

import android.content.Context
import com.firebase.jobdispatcher.FirebaseJobDispatcher
import com.firebase.jobdispatcher.GooglePlayDriver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseJobDispatcherModule(val context: Context) {
    var mFirebaseJobDispatcher : FirebaseJobDispatcher?= null

    @Provides
    @Singleton
    fun provideFirebaseJobDispatcher() : FirebaseJobDispatcher {
        if(mFirebaseJobDispatcher == null){
            mFirebaseJobDispatcher = FirebaseJobDispatcher(GooglePlayDriver(context))
        }
        return mFirebaseJobDispatcher!!
    }
}
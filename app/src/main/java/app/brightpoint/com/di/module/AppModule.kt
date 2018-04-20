package app.brightpoint.com.di.module

import android.app.Application
import android.content.Context
import app.brightpoint.com.helper.Helper
import app.brightpoint.com.utils.RxBus
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideHelper(context: Context): Helper{
        return Helper(context)
    }

    @Provides
    @Singleton
    internal fun gson(): Gson {
        return Gson()
    }

    @Provides @Singleton
    internal fun rxBus(): RxBus {
        return RxBus()
    }

}
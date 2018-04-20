package app.brightpoint.com.di.component

import android.app.Application
import app.brightpoint.com.BrightPointApp
import app.brightpoint.com.di.module.AppModule
import app.brightpoint.com.di.module.FirebaseJobDispatcherModule
import app.brightpoint.com.di.module.NetworkModule
import app.brightpoint.com.di.module.builder.ActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(
                AndroidInjectionModule::class,
                ActivityBuilder::class,
                AppModule::class,
                NetworkModule::class,
                FirebaseJobDispatcherModule::class
        )
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun firebaseJobDispatcherModule(firebaseJobDispatcherModule: FirebaseJobDispatcherModule): Builder
        fun build(): AppComponent
    }

    fun inject(app: BrightPointApp)
}
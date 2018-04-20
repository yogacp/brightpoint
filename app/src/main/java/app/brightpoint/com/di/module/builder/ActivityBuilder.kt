package app.brightpoint.com.di.module.builder

import app.brightpoint.com.di.scope.ActivityScope
import app.brightpoint.com.ui.activity.login.LoginActivity
import app.brightpoint.com.ui.activity.login.LoginModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(LoginModule::class))
    internal abstract fun bindLoginActivity(): LoginActivity

}
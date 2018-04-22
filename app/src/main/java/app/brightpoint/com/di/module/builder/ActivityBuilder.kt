package app.brightpoint.com.di.module.builder

import app.brightpoint.com.di.scope.ActivityScope
import app.brightpoint.com.ui.activity.DetailSPBU.DetailSPBUActivity
import app.brightpoint.com.ui.activity.DetailSPBU.DetailSPBUModule
import app.brightpoint.com.ui.activity.NearestSPBU.NearestSPBUActivity
import app.brightpoint.com.ui.activity.NearestSPBU.NearestSPBUModule
import app.brightpoint.com.ui.activity.bookawash.BookAWashActivity
import app.brightpoint.com.ui.activity.bookawash.BookAWashModule
import app.brightpoint.com.ui.activity.createaccount.CreateAccountActivity
import app.brightpoint.com.ui.activity.createaccount.CreateAccountModule
import app.brightpoint.com.ui.activity.detailbrightwash.DetailBrightWashActivity
import app.brightpoint.com.ui.activity.detailbrightwash.DetailBrightWashModule
import app.brightpoint.com.ui.activity.findbrightwash.FindBrightWashActivity
import app.brightpoint.com.ui.activity.findbrightwash.FindBrightWashModule
import app.brightpoint.com.ui.activity.homepage.HomepageActivity
import app.brightpoint.com.ui.activity.homepage.HomepageModule
import app.brightpoint.com.ui.activity.login.LoginActivity
import app.brightpoint.com.ui.activity.login.LoginModule
import app.brightpoint.com.ui.activity.washschedule.WashScheduleActivity
import app.brightpoint.com.ui.activity.washschedule.WashScheduleModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(LoginModule::class))
    internal abstract fun bindLoginActivity(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(CreateAccountModule::class, CreateAccountFragmentBuilder::class))
    internal abstract fun bindCreateAccountActivity(): CreateAccountActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(HomepageModule::class, HomepageFragmentBuilder::class))
    internal abstract fun bindHomepageActivity(): HomepageActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(NearestSPBUModule::class))
    internal abstract fun bindNearestSPBUActivity(): NearestSPBUActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(DetailSPBUModule::class))
    internal abstract fun bindDetailSPBUActivity(): DetailSPBUActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(BookAWashModule::class))
    internal abstract fun bindBookAWashActivity(): BookAWashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(FindBrightWashModule::class))
    internal abstract fun bindFindBrightWashActivity(): FindBrightWashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(DetailBrightWashModule::class, DetailBrightwashFragmentBuilder::class))
    internal abstract fun bindDetailBrightWashActivity(): DetailBrightWashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(WashScheduleModule::class))
    internal abstract fun bindWashScheduleActivity(): WashScheduleActivity
}
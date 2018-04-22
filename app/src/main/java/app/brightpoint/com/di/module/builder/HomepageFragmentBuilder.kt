package app.brightpoint.com.di.module.builder

import app.brightpoint.com.ui.dialogfragment.UserAccountInformationDialog
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomepageFragmentBuilder {
    @ContributesAndroidInjector
    internal abstract fun contributeUserAccountInformation(): UserAccountInformationDialog
}
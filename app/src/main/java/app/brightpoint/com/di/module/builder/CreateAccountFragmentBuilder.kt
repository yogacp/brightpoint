package app.brightpoint.com.di.module.builder

import app.brightpoint.com.ui.dialogfragment.RegistrationSuccessDialog
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CreateAccountFragmentBuilder {
    @ContributesAndroidInjector
    internal abstract fun contributeSuccessRegistration(): RegistrationSuccessDialog
}
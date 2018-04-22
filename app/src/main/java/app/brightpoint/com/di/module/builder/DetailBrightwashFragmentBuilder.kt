package app.brightpoint.com.di.module.builder

import app.brightpoint.com.ui.dialogfragment.SuccessBookingDialog
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailBrightwashFragmentBuilder {
    @ContributesAndroidInjector
    internal abstract fun contributeSuccessBooking(): SuccessBookingDialog
}
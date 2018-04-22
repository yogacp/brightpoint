package app.brightpoint.com.ui.activity.washschedule

import app.brightpoint.com.api.NetworkServices
import app.brightpoint.com.di.scope.ActivityScope
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.ui.common.navigation.ActivityNavigation
import dagger.Module
import dagger.Provides

@Module
class WashScheduleModule {
    @Provides
    @ActivityScope
    internal fun provideActivityNavigation(washScheduleActivity: WashScheduleActivity): ActivityNavigation {
        return ActivityNavigation(washScheduleActivity)
    }

    @Provides @ActivityScope
    internal fun provideRepository(mNetworkServices: NetworkServices): BrightPointRepository {
        return BrightPointRepository(mNetworkServices)
    }

    @Provides @ActivityScope
    internal fun provideActivity(washScheduleActivity: WashScheduleActivity): WashScheduleContract.View {
        return washScheduleActivity
    }
}
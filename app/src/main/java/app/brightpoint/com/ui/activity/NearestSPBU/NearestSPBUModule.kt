package app.brightpoint.com.ui.activity.NearestSPBU

import app.brightpoint.com.api.NetworkServices
import app.brightpoint.com.di.scope.ActivityScope
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.ui.common.navigation.ActivityNavigation
import dagger.Module
import dagger.Provides

@Module
class NearestSPBUModule {

    @Provides @ActivityScope
    internal fun provideActivityNavigation(nearestSPBUActivity: NearestSPBUActivity): ActivityNavigation {
        return ActivityNavigation(nearestSPBUActivity)
    }

    @Provides @ActivityScope
    internal fun provideRepository(mNetworkServices: NetworkServices): BrightPointRepository {
        return BrightPointRepository(mNetworkServices)
    }

    @Provides @ActivityScope
    internal fun provideActivity(nearestSPBUActivity: NearestSPBUActivity): NearestSPBUContract.View {
        return nearestSPBUActivity
    }
}
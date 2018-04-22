package app.brightpoint.com.ui.activity.DetailSPBU

import app.brightpoint.com.api.NetworkServices
import app.brightpoint.com.di.scope.ActivityScope
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.ui.common.navigation.ActivityNavigation
import dagger.Module
import dagger.Provides

@Module
class DetailSPBUModule {

    @Provides @ActivityScope
    internal fun provideActivityNavigation(detailSPBUActivity: DetailSPBUActivity): ActivityNavigation {
        return ActivityNavigation(detailSPBUActivity)
    }

    @Provides @ActivityScope
    internal fun provideRepository(mNetworkServices: NetworkServices): BrightPointRepository {
        return BrightPointRepository(mNetworkServices)
    }

    @Provides @ActivityScope
    internal fun provideActivity(detailSPBUActivity: DetailSPBUActivity): DetailSPBUContract.View {
        return detailSPBUActivity
    }
}
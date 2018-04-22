package app.brightpoint.com.ui.activity.detailbrightwash

import app.brightpoint.com.api.NetworkServices
import app.brightpoint.com.di.scope.ActivityScope
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.ui.common.navigation.ActivityNavigation
import dagger.Module
import dagger.Provides

@Module
class DetailBrightWashModule {
    @Provides
    @ActivityScope
    internal fun provideActivityNavigation(detailBrightWashActivity: DetailBrightWashActivity): ActivityNavigation {
        return ActivityNavigation(detailBrightWashActivity)
    }

    @Provides @ActivityScope
    internal fun provideRepository(mNetworkServices: NetworkServices): BrightPointRepository {
        return BrightPointRepository(mNetworkServices)
    }

    @Provides @ActivityScope
    internal fun provideActivity(detailBrightWashActivity: DetailBrightWashActivity): DetailBrightWashContract.View {
        return detailBrightWashActivity
    }
}
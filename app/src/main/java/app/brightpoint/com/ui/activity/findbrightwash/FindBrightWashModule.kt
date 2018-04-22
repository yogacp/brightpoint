package app.brightpoint.com.ui.activity.findbrightwash

import app.brightpoint.com.api.NetworkServices
import app.brightpoint.com.di.scope.ActivityScope
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.ui.common.navigation.ActivityNavigation
import dagger.Module
import dagger.Provides

@Module
class FindBrightWashModule {
    @Provides
    @ActivityScope
    internal fun provideActivityNavigation(findBrightWashActivity: FindBrightWashActivity): ActivityNavigation {
        return ActivityNavigation(findBrightWashActivity)
    }

    @Provides @ActivityScope
    internal fun provideRepository(mNetworkServices: NetworkServices): BrightPointRepository {
        return BrightPointRepository(mNetworkServices)
    }

    @Provides @ActivityScope
    internal fun provideActivity(findBrightWashActivity: FindBrightWashActivity): FindBrightWashContract.View {
        return findBrightWashActivity
    }
}
package app.brightpoint.com.ui.activity.homepage

import app.brightpoint.com.api.NetworkServices
import app.brightpoint.com.di.scope.ActivityScope
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.ui.common.navigation.ActivityNavigation
import dagger.Module
import dagger.Provides

@Module
class HomepageModule {
    @Provides @ActivityScope
    internal fun provideNavigation(homepageActivity: HomepageActivity) : ActivityNavigation {
        return ActivityNavigation(homepageActivity)
    }

    @Provides @ActivityScope
    internal fun provideRepository(mNetworkServices: NetworkServices): BrightPointRepository {
        return BrightPointRepository(mNetworkServices)
    }

    @Provides @ActivityScope
    internal fun provideHomepageActivity(homepageActivity: HomepageActivity): HomepageContract.View {
        return homepageActivity
    }
}
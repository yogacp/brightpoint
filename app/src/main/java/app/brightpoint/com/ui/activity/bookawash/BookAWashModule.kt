package app.brightpoint.com.ui.activity.bookawash

import app.brightpoint.com.api.NetworkServices
import app.brightpoint.com.di.scope.ActivityScope
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.ui.common.navigation.ActivityNavigation
import dagger.Module
import dagger.Provides

@Module
class BookAWashModule {

    @Provides @ActivityScope
    internal fun provideActivityNavigation(bookAWashActivity: BookAWashActivity): ActivityNavigation {
        return ActivityNavigation(bookAWashActivity)
    }

    @Provides @ActivityScope
    internal fun provideRepository(mNetworkServices: NetworkServices): BrightPointRepository {
        return BrightPointRepository(mNetworkServices)
    }

    @Provides @ActivityScope
    internal fun provideActivity(bookAWashActivity: BookAWashActivity): BookAWashContract.View {
        return bookAWashActivity
    }
}
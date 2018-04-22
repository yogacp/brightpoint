package app.brightpoint.com.ui.activity.createaccount

import app.brightpoint.com.api.NetworkServices
import app.brightpoint.com.di.scope.ActivityScope
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.ui.common.navigation.ActivityNavigation
import dagger.Module
import dagger.Provides

@Module
class CreateAccountModule {
    @Provides @ActivityScope
    internal fun provideCreateAccountActivity(createAccountActivity: CreateAccountActivity): CreateAccountContract.View {
        return createAccountActivity
    }

    @Provides @ActivityScope
    internal fun provideBrightPointRepository(mNetworkServices: NetworkServices): BrightPointRepository {
        return BrightPointRepository(mNetworkServices)
    }

    @Provides @ActivityScope
    internal fun provideActivityNavigation(createAccountActivity: CreateAccountActivity): ActivityNavigation {
        return ActivityNavigation(createAccountActivity)
    }
}
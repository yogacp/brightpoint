package app.brightpoint.com.ui.activity.login

import app.brightpoint.com.api.NetworkServices
import app.brightpoint.com.di.scope.ActivityScope
import app.brightpoint.com.repository.BrightPointRepository
import app.brightpoint.com.ui.common.navigation.ActivityNavigation
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides @ActivityScope
    internal fun provideLoginView(loginActivity: LoginActivity): LoginContract.View {
        return loginActivity
    }

    @Provides @ActivityScope
    internal fun provideNavigation(loginActivity: LoginActivity): ActivityNavigation {
        return ActivityNavigation(loginActivity)
    }

    @Provides @ActivityScope
    internal fun provideRepository(mNetworkServices: NetworkServices): BrightPointRepository {
        return BrightPointRepository(mNetworkServices)
    }

}
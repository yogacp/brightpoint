package app.brightpoint.com.ui.activity.login

import app.brightpoint.com.di.scope.ActivityScope
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

}
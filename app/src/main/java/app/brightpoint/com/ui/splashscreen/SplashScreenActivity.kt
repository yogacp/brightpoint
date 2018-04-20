package app.brightpoint.com.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import app.brightpoint.com.R
import app.brightpoint.com.helper.Helper
import app.brightpoint.com.ui.activity.login.LoginActivity
import com.google.gson.Gson

class SplashScreenActivity: AppCompatActivity() {

    val LOG_TAG = "SplashScreen"
    val SPLASH_TIMEOUT = 1000 // 1 seconds timeout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        runSplashScreen(SPLASH_TIMEOUT.toLong())
    }

    fun runSplashScreen(timeout: Long) {
        Handler().postDelayed({
            kotlin.run {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, timeout)
    }
}
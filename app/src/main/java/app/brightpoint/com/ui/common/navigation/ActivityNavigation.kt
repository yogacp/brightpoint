package app.brightpoint.com.ui.common.navigation

import android.content.Context
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import app.brightpoint.com.ui.activity.DetailSPBU.DetailSPBUActivity
import app.brightpoint.com.ui.activity.NearestSPBU.NearestSPBUActivity
import app.brightpoint.com.ui.activity.bookawash.BookAWashActivity
import app.brightpoint.com.ui.activity.createaccount.CreateAccountActivity
import app.brightpoint.com.ui.activity.detailbrightwash.DetailBrightWashActivity
import app.brightpoint.com.ui.activity.findbrightwash.FindBrightWashActivity
import app.brightpoint.com.ui.activity.homepage.HomepageActivity
import app.brightpoint.com.ui.activity.login.LoginActivity
import app.brightpoint.com.ui.activity.washschedule.WashScheduleActivity
import app.brightpoint.com.ui.dialogfragment.RegistrationSuccessDialog
import app.brightpoint.com.ui.dialogfragment.SuccessBookingDialog
import app.brightpoint.com.ui.dialogfragment.UserAccountInformationDialog
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class ActivityNavigation @Inject constructor(val activity: AppCompatActivity) {

    /**
     * Intent to Login Page
     * */
    fun navigateToLoginPage() {
        activity.startActivity(newIntent(
                activity,
                LoginActivity::class.java
        )
        )
    }

    /**
     * Intent to Register Page
     * */
    fun navigateToRegisterPage() {
        activity.startActivity(newIntent(
                activity,
                CreateAccountActivity::class.java
        )
        )
    }

    /**
     * Intent to Home Page
     * */
    fun navigateToHomePage() {
        activity.startActivity(newIntent(
                activity,
                HomepageActivity::class.java
        )
        )
    }

    /**
     * Intent to Register Page
     * */
    fun navigateToNearestSPBUPage() {
        activity.startActivity(newIntent(
                activity,
                NearestSPBUActivity::class.java
        )
        )
    }

    /**
     * Intent to Register Page
     * */
    fun navigateToDetailSPBUPage(spbuId: Int) {
        val detailSpbu = newIntent(activity, DetailSPBUActivity::class.java)
        detailSpbu.apply {
            putExtra(DetailSPBUActivity.TAG_DETAIL_SPBUID, spbuId)
        }
        activity.startActivity(detailSpbu)
    }

    /**
     * Intent to Register Page
     * */
    fun navigateToBookAWashPage() {
        activity.startActivity(newIntent(
                activity,
                BookAWashActivity::class.java
        )
        )
    }

    /**
     * Intent to Register Page
     * */
    fun navigateToWashSchedulePage() {
        activity.startActivity(newIntent(
                activity,
                WashScheduleActivity::class.java
        )
        )
    }


    /**
     * Intent to Find Bright Wash
     * */
    fun navigateToFindBrightwashPage(latLng: LatLng, searchArea: String) {
        val findBrightwash = newIntent(activity, FindBrightWashActivity::class.java)
        findBrightwash.apply {
            putExtra(FindBrightWashActivity.TAG_LAT_LONG, "${latLng.latitude},${latLng.longitude}")
            putExtra(FindBrightWashActivity.TAG_SEARCH_AREA, searchArea)
        }
        activity.startActivity(findBrightwash)
    }


    /**
     * Intent to Detail Bright Wash
     * */
    fun navigateToDetailBrightwashPage(brightwasId: Int) {
        val findBrightwash = newIntent(activity, DetailBrightWashActivity::class.java)
        findBrightwash.apply {
            putExtra(DetailBrightWashActivity.TAG_BRIGHTWAS_ID, brightwasId)
        }
        activity.startActivity(findBrightwash)
    }

    /**
     * Register Success
     */
    fun navigateToRegisterSuccessDialog(){
        loadDialogFragment(
                RegistrationSuccessDialog(),
                RegistrationSuccessDialog.POPUP_REGISTER_SUCCESS_DIALOG
        )
    }

    /**
     * Register Success
     */
    fun navigateToAccountInformationDialog(){
        loadDialogFragment(
                UserAccountInformationDialog(),
                UserAccountInformationDialog.POPUP_USER_INFO_DIALOG
        )
    }

    /**
     * Navigate to Webview Menu Dialog Fragment
     * */
    fun navigateSuccessBookingDialog(queue: Int) {

        val args = Bundle().apply {
            putInt(SuccessBookingDialog.TAG_NO_ANTRIAN, queue)
        }

        val sFragment = SuccessBookingDialog().apply {
            arguments = args
        }

        loadDialogFragment(
                sFragment,
                SuccessBookingDialog.POPUP_SUCCESS_BOOKING_DIALOG
        )
    }

    /**
     * Open Map
     */
    fun openMap(location: LatLng, spbuName: String?) {
        val gmmIntentUri = Uri.parse("geo:${location.latitude},${location.longitude}?q=${location.latitude},${location.longitude}(Pertamina ${spbuName})")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.`package` = "com.google.android.apps.maps"
        activity.startActivity(mapIntent)
    }

    /**
     * Load dialog fragment
     * Handling all load dialog fragment navigation
     * */

    fun loadDialogFragment(mDialogFragment: DialogFragment, mTagFragment: String) {
        mDialogFragment.show(activity.supportFragmentManager, mTagFragment)
    }

    /**
     * Intent Common Function
     * Handling new intent
     * */

    fun <T> newIntent(context: Context, cls: Class<T>): Intent {
        return Intent(context, cls)
    }

    fun newIntentUri(label: String, uri: Uri): Intent {
        return Intent(label, uri)
    }
}
package app.brightpoint.com.ui.dialogfragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import app.brightpoint.com.R
import app.brightpoint.com.ui.common.BaseDialogFragment
import kotlinx.android.synthetic.main.df_register_success.*

class RegistrationSuccessDialog : BaseDialogFragment() {

    companion object {
        val POPUP_REGISTER_SUCCESS_DIALOG = "popup_success_register"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Disable onBackPressed dismiss
        isCancelable = false
    }

    override fun getLayoutId(): Int {
        return R.layout.df_register_success
    }

    override fun onLoadFragment(saveInstance: Bundle?) {
        //Set no toolbar
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)

        //Set no frame
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(android.app.DialogFragment.STYLE_NO_FRAME, android.R.style.Theme)

        //Disable dismiss outside dialog
        dialog.setCanceledOnTouchOutside(false)

        setViewClickListener()
    }

    fun setViewClickListener() {
        btnNextLogin.setOnClickListener {
            mActivityNavigation.navigateToLoginPage()
        }
    }

}
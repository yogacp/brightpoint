package app.brightpoint.com.ui.dialogfragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import app.brightpoint.com.R
import app.brightpoint.com.ui.common.BaseDialogFragment
import kotlinx.android.synthetic.main.df_success_booking.*

class SuccessBookingDialog: BaseDialogFragment() {

    companion object {
        val TAG_NO_ANTRIAN = "tag_no_antrian"
        val POPUP_SUCCESS_BOOKING_DIALOG = "popup_success_booking"
    }

    var mAntrian: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun getLayoutId(): Int {
        return R.layout.df_success_booking
    }

    override fun onLoadFragment(saveInstance: Bundle?) {
        //Set no toolbar
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)

        //Set no frame
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(android.app.DialogFragment.STYLE_NO_FRAME, android.R.style.Theme)

        //Disable dismiss outside dialog
        dialog.setCanceledOnTouchOutside(false)

        val args = arguments
        if (args != null) {
            mAntrian = args.getInt(TAG_NO_ANTRIAN)
            tvAntrian.text = mAntrian.toString()
        }

        setViewClickListener()
    }

    fun setViewClickListener() {
        btnClose.setOnClickListener {
            mActivityNavigation.navigateToHomePage()
        }
    }

}
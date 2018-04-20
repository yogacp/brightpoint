package app.brightpoint.com.ui.common.navigation

import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

class FragmentNavigation @Inject constructor(val activity: AppCompatActivity, val containerId: Int) {
    var mFragmentManager = activity.supportFragmentManager

    /**
     * Load Fragment
     * Handling all load fragment navigation
     * */
    fun loadFragment(fragment: Fragment) {
        mFragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .commitAllowingStateLoss()
    }

    fun loadDialogFragment(mDialogFragment: DialogFragment, mTagFragment: String) {
        mDialogFragment.show(mFragmentManager, mTagFragment)
    }

    /**
     * Get open current fragment
     * */
    fun getOpenFragment(): Fragment {
        return mFragmentManager.findFragmentById(containerId)
    }
}
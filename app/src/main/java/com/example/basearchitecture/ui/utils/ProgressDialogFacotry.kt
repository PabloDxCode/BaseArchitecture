package com.example.basearchitecture.ui.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.basearchitecture.R

/**
 * ProgressDialogFacotry
 *
 * Factory to create and show progress dialog
 */
class ProgressDialogFacotry {

    /**
     * Progress dialog object
     */
    private var mProgressDialog: ProgressDialog? = null

    /**
     * Method to create a custom progress dialog
     *
     * @param context context instance
     * @return progress dialog instance
     */
    fun createProgressDialog(context: Context): ProgressDialog {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(context, R.style.ProgressDialog)
        }
        mProgressDialog!!.show()
        mProgressDialog!!.setContentView(R.layout.custom_progress_dialog)
        mProgressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        mProgressDialog!!.setCancelable(false)
        return mProgressDialog!!
    }

    /**
     * Method to hide progress only if it was created
     */
    fun hideProgress() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
            mProgressDialog = null
        }
    }

}
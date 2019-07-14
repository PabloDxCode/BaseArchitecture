package com.example.basearchitecture.presentation.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.basearchitecture.R

/**
 * ProgressDialogFacotry
 */
class ProgressDialogFacotry {

    /**
     * Progress dialog object
     */
    private var mProgressDialog: ProgressDialog? = null

    /**
     * Method to create progress dialog
     *
     * @param context context instance
     * @return progress dialog
     */
    fun createProgressDialog(context: Context): ProgressDialog {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(context, R.style.ProgressDialog)
        }
        mProgressDialog!!.show()

        mProgressDialog!!.setContentView(R.layout.custom_progress_dialog)

        mProgressDialog!!.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        mProgressDialog!!.setCancelable(false)
        return mProgressDialog!!
    }

    /**
     * Method to hide progress
     */
    fun hideProgress() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
            mProgressDialog = null
        }
    }

}
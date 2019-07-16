package com.example.basearchitecture.ui.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import android.view.ContextThemeWrapper
import com.example.basearchitecture.R

data class AlertParams(val context: Context, var title: String?, var message: String?, var acceptTitle: String?, var onAccept : (() -> Unit?)?, var cancelTitle: String?, var onCancel : (() -> Unit?)?)

/**
 * AlertDialogUtils
 * Created by pablogutierrez on 31/12/18.
 */
class AlertDialogUtils private constructor(alertParams: AlertParams) {

    private val mAlertParams: AlertParams = alertParams

    init {
        showAlert()
    }

    @Suppress("UNCHECKED_CAST")
    private fun showAlert(){
        val builder = AlertDialog.Builder(ContextThemeWrapper(mAlertParams.context, R.style.AppTheme))
        builder.setTitle(mAlertParams.title)
        builder.setMessage(mAlertParams.message)
        if(mAlertParams.onAccept == null){
            builder.setNegativeButton(mAlertParams.acceptTitle) { dialogInterface, _ -> dialogInterface.dismiss()}
        } else {
            val onAccept : () -> Unit = (mAlertParams.onAccept as (() -> Unit)?)!!
            builder.setNegativeButton(mAlertParams.acceptTitle) { _, _ -> onAccept()}
        }

        if(mAlertParams.onCancel != null){
            val onCancel : () -> Unit = (mAlertParams.onCancel as (() -> Unit)?)!!
            builder.setNegativeButton(mAlertParams.acceptTitle) { _, _ -> onCancel()}
        }
        builder.setCancelable(false)
        builder.create().show()
    }

    class Builder(context: Context) {

        private val mContext: Context = context

        private var mTitle: String? = ""

        private var mMessage: String? = ""

        private var mAcceptTitle : String? = ""

        private var mCancelTitle : String? = ""

        private var onAcceptEvent : (() -> Unit?)? = null

        private var onCancelEvent : (() -> Unit?)? = null

        fun setTitle(title: String): Builder {
            this.mTitle = title
            return this
        }

        fun setMessage(message: String): Builder {
            this.mMessage = message
            return this
        }

        fun setAccept(title : String, onAccept : () -> Unit?): Builder {
            this.mAcceptTitle = title
            this.onAcceptEvent = onAccept
            return this
        }

        fun setCancel(title : String, onCancel : () -> Unit?): Builder {
            this.mCancelTitle = title
            this.onCancelEvent = onCancel
            return this
        }

        fun show() : AlertDialogUtils {
            val alertParams  = AlertParams(mContext, mTitle, mMessage, mAcceptTitle, onAcceptEvent, mCancelTitle, onCancelEvent)
            return AlertDialogUtils(alertParams)
        }

    }

}
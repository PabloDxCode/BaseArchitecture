package com.example.basearchitecture.ui.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.lang.Exception

/**
 * Utils
 */
class Utils {

    companion object {

        /**
         * Method to close keyboard
         *
         * @param activity activity instance
         */
        fun closeKeyboard(activity: Activity) {
            val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            //Find the currently focused view, so we can grab the correct window token from it.
            var view = activity.currentFocus
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        /**
         * Method to get error from strings
         *
         * @param message message or code
         * @param context context instance
         *
         * @return message from strings
         */
        fun getErrorFromStrings(message: String, context: Context): String {
            val validMessage = if (message === "") "generic_response" else message
            try {
                val packageName = context.packageName
                val resId =
                    context.resources.getIdentifier("error_${validMessage.toLowerCase()}", "string", packageName)
                return context.getString(resId)
            } catch (e: Exception) {
                return validMessage
            }
        }

    }

}
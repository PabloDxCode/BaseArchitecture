package com.example.basearchitecture.presentation.modules.commons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.basearchitecture.presentation.utils.ProgressDialogFacotry
import com.example.basearchitecture.presentation.utils.Utils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * BaseController
 */
open class BaseController : AppCompatActivity(), HasSupportFragmentInjector {

    /**
     * Dispatching android injector fragment
     */
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    /**
     * Progress dialog factory
     */
    private var mProgressDialogFacotry: ProgressDialogFacotry? = null

    /**
     * supportFragmentInjector
     *
     * @return dispatching android injector
     */
    override fun supportFragmentInjector(): AndroidInjector<Fragment>? = fragmentDispatchingAndroidInjector

    /**
     * onCreate
     *
     * @param savedInstanceState bundle instance
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mProgressDialogFacotry = ProgressDialogFacotry()
    }

    /**
     * Method to show or hide progress
     *
     * @param flag boolean
     */
    protected fun showHideProgress(flag: Boolean) {
        if (flag) {
            Utils.closeKeyboard(this)
            mProgressDialogFacotry!!.createProgressDialog(this)
        } else {
            mProgressDialogFacotry!!.hideProgress()
        }
    }

}
package com.example.basearchitecture.ui.modules.main.controllers

import android.os.Bundle
import com.example.basearchitecture.R
import com.example.basearchitecture.ui.modules.commons.BaseController

/**
 * MainController
 */
class MainController : BaseController() {

    /**
     * onCreate
     *
     * @param savedInstanceState bundle instance
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_controller)
    }

}

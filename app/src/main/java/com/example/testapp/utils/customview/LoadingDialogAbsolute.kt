package com.example.testapp.utils.customview

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import com.example.testapp.R

class LoadingDialogAbsolute(
    activity: Activity?
) : Dialog(activity!!) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_loading)
        setCancelable(false)

        this.window?.setBackgroundDrawableResource(R.color.transparent)
    }

}
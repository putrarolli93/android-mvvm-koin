package com.example.testapp.utils.base

import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.utils.customview.HorizontalProgressBar

abstract class BaseActivity : AppCompatActivity() {

    protected var horizontalProgressBar: HorizontalProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupWindowTransition()
        showHorizonTopProgressBar()
    }

    private fun setupWindowTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            with(window) {
                requestFeature(
                    Window.FEATURE_CONTENT_TRANSITIONS
                            or Window.FEATURE_ACTIVITY_TRANSITIONS)
                enterTransition = Fade()
                exitTransition = Fade()
            }
        }
    }

    fun showHorizonTopProgressBar() {
        if (horizontalProgressBar == null) {
            horizontalProgressBar = HorizontalProgressBar(this)
            decorViewGroup()?.addView(
                horizontalProgressBar,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        horizontalProgressBar?.visible()
    }

    fun goneHorizonTopProgressBar() {
        horizontalProgressBar?.gone()
    }

    private fun decorViewGroup(): ViewGroup? {
        return when (isDecorViewGroup()) {
            true -> window.decorView as ViewGroup
            else -> null
        }
    }

    private fun isDecorViewGroup(): Boolean {
        return window.decorView is ViewGroup
    }

}
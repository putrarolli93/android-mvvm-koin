package com.example.testapp.utils.customview

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.testapp.R

class HorizontalProgressBar : LinearLayout {

    constructor(context: Context) : super(context) {
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        val activity = (context as Activity)
        val view = LayoutInflater.from(context).inflate(
            R.layout.layout_progress_horizontal,
            null, false)
        val linearLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        linearLayoutParams.topMargin = activity.getStatusBarDimension()
        addView(view, linearLayoutParams)
    }

    fun Activity.getStatusBarDimension(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun View.goneIf(condition: Boolean) {
        if (condition) this.visibility = View.GONE else this.visibility = View.VISIBLE
    }

    fun gone() {
        goneIf(true)
    }

    fun visible() {
        goneIf(false)
    }
}
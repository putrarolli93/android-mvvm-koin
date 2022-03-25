package com.example.testapp.utils.base

import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.testapp.utils.customview.HorizontalProgressBar
import com.example.testapp.utils.customview.LoadingDialog
import com.example.testapp.utils.customview.LoadingDialogAbsolute

/**
 * Used for setting standard in declaring activities.
 * [bindingFactory] Declares lambda property to inflate the ViewBinding object.
 */
abstract class BaseActivity<B: ViewBinding>(private val bindingFactory: (LayoutInflater) -> B): AppCompatActivity(), BaseActivityView {

    private val loadingDialog by lazy {
        LoadingDialog(this)
    }

    private val loadingDialogAbsolute by lazy {
        LoadingDialogAbsolute(this)
    }

    /**
     * [binding] object to call views from layout.
     */
    protected lateinit var binding: B

    /**
     * This method functions to inflate the layout and calls methods such as:
     * initData()
     * initView(savedInstanceState)
     * initEvent()
     * loadingData()
     * observeData()
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        initActivityResultLauncher()
        initData()
        initView(savedInstanceState)
        initEvent()
        loadingData()
        observeData()
    }

    /**
     * This method is used to initialize all data like intent.
     */
    override fun initData() {}

    /**
     * This method is used to initialize all views like setting TextView's text, ImageView's resource, etc.
     */
    override fun initView(savedInstanceState: Bundle?) {}

    /**
     * Initializes the events like onClick.
     */
    override fun initEvent() {}

    /**
     * Initializes the onActivityResultCallback
     */
    override fun initActivityResultLauncher() {}

    /**
     * Put all the code that is used to load data.
     */
    override fun loadingData() {}

    /**
     * Used to observe the changes in the data through ViewModel.
     */
    override fun observeData() {}

    /**
     * Show the [loadingDialog] when starts loading.
     */
    override fun showLoadingDialog() {
        loadingDialog.show()
    }

    /**
     * Hide the [loadingDialog].
     */
    override fun hideLoadingDialog() {
        loadingDialog.hide()
    }

    /**
     * Dismiss the [loadingDialog].
     */
    override fun dismissLoadingDialog() {
        if (loadingDialog.isShowing){
            loadingDialog.dismiss()
        }
    }

    /**
     * Show the [loadingDialogAbsolute] when starts loading.
     */
    override fun showLoadingDialogAbsolute() {
        loadingDialogAbsolute.show()
    }

    /**
     * Hide the [loadingDialogAbsolute].
     */
    override fun hideLoadingDialogAbsolute() {
        loadingDialogAbsolute.hide()
    }

    /**
     * Dismiss the [loadingDialogAbsolute].
     */
    override fun dismissLoadingDialogAbsolute() {
        if (loadingDialogAbsolute.isShowing){
            loadingDialogAbsolute.dismiss()
        }
    }

    /**
     * Throws an exception when error.
     */
    override fun onError(throwable: Throwable?) {}

    /**
     * Throws an exception when connection to the internet has failed.
     */
    override fun onInternetError() {}

}
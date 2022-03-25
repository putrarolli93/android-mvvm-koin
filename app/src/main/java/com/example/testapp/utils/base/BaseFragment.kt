package com.example.testapp.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.testapp.utils.customview.LoadingDialog
import com.example.testapp.utils.customview.LoadingDialogAbsolute

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

/**
 * Used for setting standard in declaring fragments.
 * [bindingFactory] Declares lambda property to inflate the ViewBinding object.
 */
abstract class BaseFragment<B: ViewBinding>(private val inflate: Inflate<B>): Fragment(), BaseFragmentView {

    private val loadingDialog by lazy {
        LoadingDialog(requireActivity())
    }

    private val loadingDialogAbsolute by lazy {
        LoadingDialogAbsolute(requireActivity())
    }
    private var _binding: B? = null

    /**
     * [binding] object to call views from layout.
     */
    val binding get() = _binding

    /**
     * creates and returns the view hierarchy associated with the fragment.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView(savedInstanceState)
        loadingData()
        observeData()
        initActivityResultLauncher()
        initEvent()
    }

    /**
     * Setting [binding] object to null for memory efficiency.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
     * Throws an exception when error.
     */
    override fun onError(throwable: Throwable?) {}

    /**
     * Throws an exception when connection to the internet has failed.
     */
    override fun onInternetError() {}

}
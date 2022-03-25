package com.example.testapp.utils.base

import android.os.Bundle
import com.example.testapp.network.Resource
import com.example.testapp.network.Status

interface BaseFragmentView {

    fun initData()

    fun initView(savedInstanceState: Bundle?)

    fun initEvent()

    fun initActivityResultLauncher()

    fun loadingData()

    fun observeData()

    /**
     * Group function negative scenario
     */

    fun onError(throwable: Throwable? = null)

    fun onInternetError()

    fun <T> parseObserveData(
        resource: Resource<T>,
        resultLoading: (T?) -> Unit = { showLoadingDialog() },
        resultSuccess: (T) -> Unit = { _: T -> },
        resultNetworkFailed: (Throwable?) -> Unit = { onInternetError() },
        resultError: (Throwable?) -> Unit = { onError(it) }
    ) {
        when (resource.status) {
            Status.LOADING -> {
                resultLoading(resource.data)
            }
            Status.SUCCESS -> {
                dismissLoadingDialog()
                resource.data?.let { resultSuccess(it) }
            }
            Status.ERROR -> {
                dismissLoadingDialog()
                resultError(resource.throwable)
            }
            Status.NETWORK_FAILED -> {
                dismissLoadingDialog()
                resultNetworkFailed(resource.throwable)
            }
        }
    }

    /**
     * Group function show and gone UI view progress bar
     */

    fun showLoadingDialog()

    fun hideLoadingDialog()

    fun dismissLoadingDialog()

    fun showLoadingDialogAbsolute(){}

    fun hideLoadingDialogAbsolute(){}

    fun dismissLoadingDialogAbsolute(){}
}
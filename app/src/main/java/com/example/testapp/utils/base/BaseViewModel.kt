package com.example.testapp.utils.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers

open class BaseViewModel : ViewModel() {


    /*
    Transformers
    With a transformer, we can avoid repeating some code by applying the most commonly used chains among your Observable ,
    we’ll be chaining subscribeOn and observeOn to a couple of Observable below.

    It’s important to keep in mind that this example is for Observable , and if you’re working with other Emitters you need to change the type of the transformer, as follows.
    ObservableTransformer
    FlowableTransformer
    SingleTransformer
    MaybeTransformer
    CompletableTransformer
     */
    fun <T> applyObservableAsync(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
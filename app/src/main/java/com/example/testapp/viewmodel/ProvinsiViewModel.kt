package com.example.testapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.testapp.model.ProvinsiModel
import com.example.testapp.network.ProvinsiRepository
import com.example.testapp.network.Resource
import com.example.testapp.utils.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ProvinsiViewModel(val repository: ProvinsiRepository) : BaseViewModel() {

    var provinsiLiveData = MutableLiveData<Resource<ProvinsiModel>>()

    //RX way to call async task
    fun getProvinsi() {
        provinsiLiveData.postValue(Resource.loading())
        repository.getProvinsi()
                /*
                Scheduler.io() This is the most common types of Scheduler that are used. They’re generally used for IO related stuff,
                such as network requests, file system operations, and it’s backed by a thread pool.

                Scheduler.computation() This is quite similar to IO as it’s also backed up by the thread pool, however, the number of
                threads that can be used is fixed to the number of cores present in the device.

                Scheduler.newThread() The name here is self-explanatory, as it will create a new thread for each active Observable .

                Scheduler.single() This Scheduler is backed up by a single thread. No matter how many Observable there are, it will only run in a single thread.

                Scheduler.trampoline() This will run on whatever the current thread is. If it’s the main thread, it will run the code on the queue of the main thread.
                Similar to Immediate Scheduler, it also blocks the thread. The trampoline may be used when we have more than one Observable and we want them to execute in order.
                 */
            .subscribeOn(Schedulers.io())
                /*
                observeOn
                The method subscribeOn() will instruct the source Observable which thread to emit the items on and push the emissions on our Observer . But if it finds an observeOn() in the chain,
                it switches the emissions using the selected scheduler for the remaining operation.

                Usually, the observing thread in Android is the Main UI thread.
                 */
            .observeOn(AndroidSchedulers.mainThread())
                /*

                 */
//            .compose(applyObservableAsync())
            .subscribe({
                provinsiLiveData.postValue(Resource.success(it))
            }, {
                provinsiLiveData.postValue(Resource.error500(it))
            })
    }

}
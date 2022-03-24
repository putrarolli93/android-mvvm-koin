package com.example.testapp

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.network.Status
import com.example.testapp.ui.home.ProvinsiListAdapter
import com.example.testapp.utils.base.BaseActivity
import com.example.testapp.viewmodel.ProvinsiViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val provinsiViewModel: ProvinsiViewModel by viewModel()
    private lateinit var adapter: ProvinsiListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpAdapter()
        provinsiViewModel.getProvinsi()
        getProvinsiRxLiveData()

    }

    private fun getProvinsiRxLiveData() {
        provinsiViewModel.provinsiLiveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    goneHorizonTopProgressBar()
                    it.data?.provinsi?.let { data ->
                        adapter.updateList(data)
                    }
                }
                Status.ERROR_500 -> {
                    goneHorizonTopProgressBar()
                }
            }
        })
    }

    private fun setUpAdapter() {
        adapter = ProvinsiListAdapter()
        val layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        rvTopStory.layoutManager = layoutManager
        rvTopStory.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        rvTopStory.adapter = adapter
    }

}

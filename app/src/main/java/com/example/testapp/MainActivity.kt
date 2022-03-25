package com.example.testapp

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.ui.home.ProvinsiListAdapter
import com.example.testapp.utils.base.BaseActivity
import com.example.testapp.viewmodel.ProvinsiViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/*
Created By Putra Rolli
 */

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val provinsiViewModel: ProvinsiViewModel by viewModel()
    private lateinit var adapter: ProvinsiListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        setUpAdapter()
    }

    override fun initData() {
        super.initData()
    }

    override fun loadingData() {
        super.loadingData()
        provinsiViewModel.getProvinsi()
    }

    override fun observeData() {
        super.observeData()
        observeProvinsiRxLiveData()
    }

    override fun initEvent() {
        super.initEvent()
        binding.swiperefresh.setOnRefreshListener {
            provinsiViewModel.getProvinsi()
        }
    }

    private fun observeProvinsiRxLiveData() {
        provinsiViewModel.provinsiLiveData.observe(this, Observer {
            parseObserveData(it,
                resultSuccess = {
                    it?.provinsi?.let { data ->
                        adapter.updateList(data)
                    }
                    binding.swiperefresh.isRefreshing = false
                },
                resultEmpty = {
                    binding.swiperefresh.isRefreshing = false
                    //show page empty data
                },
                resultError = {
                    binding.swiperefresh.isRefreshing = false
                    //show page server error or anything
                })
        })
    }

    private fun setUpAdapter() {
        adapter = ProvinsiListAdapter()
        val layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.apply {
            rvTopStory.layoutManager = layoutManager
            rvTopStory.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    layoutManager.orientation
                )
            )
            rvTopStory.adapter = adapter
        }
    }

}

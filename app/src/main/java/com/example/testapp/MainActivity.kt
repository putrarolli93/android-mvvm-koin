package com.example.testapp

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.ui.home.ProvinsiListAdapter
import com.example.testapp.utils.base.BaseActivity
import com.example.testapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var adapter: ProvinsiListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpAdapter()
        mainViewModel.getProvinsi()
        mainViewModel.listProvinsi.observe(this, Observer {
            goneHorizonTopProgressBar()
            adapter.updateList(it.toMutableList())
        })
    }

    private fun setUpAdapter() {
        adapter = ProvinsiListAdapter()
        val layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, true)
        rvTopStory.layoutManager = layoutManager
        rvTopStory.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        rvTopStory.adapter = adapter
    }

}

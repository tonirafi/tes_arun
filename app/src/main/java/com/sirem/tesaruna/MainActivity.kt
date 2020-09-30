package com.sirem.tesaruna

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sirem.tesaruna.model.DataViewModel
import com.sirem.tesaruna.utils.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity () {

    @Inject
    lateinit var viewmodelProviderFactory: ViewModelProviderFactory
    lateinit var viewModel: DataViewModel

    internal lateinit var adapter: ListPostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setUi()
        setLiveData()
    }

    private fun setLiveData() {
        viewModel.liveDataPostApi.observe(this, Observer {
            swipe_refresh_layout.isRefreshing = false
            if (it?.size != 0 ) {
                adapter.addAll(it!!)
                viewModel.insertDataAll(it)
            } else {

            }

        })
        viewModel.liveDataLocal.observe(this, Observer {
            swipe_refresh_layout.isRefreshing = false

            if (it?.size != 0 ) {
                adapter.addAll(it!!)
            } else {
                getDataApi()
            }

        })

    }

    private fun setUi() {
        adapter = ListPostAdapter(this)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        getDataLocal()
        swipe_refresh_layout.setOnRefreshListener {
            getDataLocal()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this,viewmodelProviderFactory).get(DataViewModel::class.java)
    }

    fun getDataLocal(){
        swipe_refresh_layout.isRefreshing = true
        adapter.clear()
        viewModel.getLisDataLocal("")

    }

    fun getDataApi(){
        swipe_refresh_layout.isRefreshing = true
        adapter.clear()
        viewModel.getDataPost()

    }


}
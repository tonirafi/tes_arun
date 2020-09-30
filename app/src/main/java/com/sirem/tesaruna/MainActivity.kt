package com.sirem.tesaruna

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
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
        viewModel.liveDataPost.observe(this, Observer {
            swipe_refresh_layout.isRefreshing = false

            if (it?.size != 0 ) {
                adapter.addAll(it!!)
            } else {

            }

        })

    }

    private fun setUi() {
        adapter = ListPostAdapter(this)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        getData()
        swipe_refresh_layout.setOnRefreshListener {
            getData()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this,viewmodelProviderFactory).get(DataViewModel::class.java)
    }

    fun getData(){
        swipe_refresh_layout.isRefreshing = true
        adapter.clear()
        viewModel.getDataPost()

    }



}
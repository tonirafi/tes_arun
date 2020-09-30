package com.sirem.tesaruna

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sirem.tesaruna.model.DataViewModel
import com.sirem.tesaruna.utils.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
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
        getDataLocal("")
        swipe_refresh_layout.setOnRefreshListener {
            getDataLocal("")
        }

        tv_auto_complite.addTextChangedListener(
            object : TextWatcher {

                private var timer = Timer()
                private val DELAY: Long = 500 // milliseconds
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {
                    timer.cancel()
                    timer = Timer()
                    timer.schedule(
                        object : TimerTask() {
                            override fun run() {
                                runOnUiThread {
                                   getDataLocal(tv_auto_complite.text.toString())
                                }

                            }
                        },
                        DELAY
                    )
                }
            }
        )
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this,viewmodelProviderFactory).get(DataViewModel::class.java)
    }

    fun getDataLocal(title:String){
        swipe_refresh_layout.isRefreshing = true
        adapter.clear()
        viewModel.getLisDataLocal(title)

    }

    fun getDataApi(){
        swipe_refresh_layout.isRefreshing = true
        adapter.clear()
        viewModel.getDataPost()

    }


}
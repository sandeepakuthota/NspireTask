package com.sandeep.nspiretask.views

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ocrdemo.nspiretask.adapter.RepositaryListAdapter
import com.sandeep.nspiretask.common.UiState
import com.sandeep.nspiretask.databinding.ActivityMainBinding
import com.sandeep.nspiretask.entities.RepoData
import com.sandeep.nspiretask.utils.NetworkUtils
import com.sandeep.nspiretask.viewmodel.SearchRepoViewModel
import dagger.android.support.DaggerAppCompatActivity
import java.util.*
import javax.inject.Inject


class SearchRepoActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivityMainBinding
    val adapter = RepositaryListAdapter()
    var isLoading = false
    var vissibleThreshold = 10
    private var timer: Timer? = Timer()
    private val viewModel: SearchRepoViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[SearchRepoViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(NetworkUtils.isNetworkConnected(this)) {
            viewModel.getData()
        } else {
            viewModel.getOfflineData()
        }

        viewModel.data_model.observe(this, {
            when (it) {
                is UiState.Loading -> {
                    binding.progressCircularId.visibility = View.VISIBLE
                }
                is UiState.Success<*> -> {
                    binding.progressCircularId.visibility = View.GONE
                    isLoading = false
                    val data = it.result as List<RepoData>
                    binding.recyclerview.post {
                        adapter.updateList(data)
                        adapter.itemCount==0
                    }
                }
            }
        })
        val linearLayoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL, false)
        binding.recyclerview.layoutManager = linearLayoutManager
        binding.recyclerview.adapter = adapter

        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                hideKeyboard()
                val totalItemCOunt = linearLayoutManager.itemCount
                val lastVissibleItemPos = linearLayoutManager.findLastVisibleItemPosition()
                if (!isLoading && totalItemCOunt <= lastVissibleItemPos + vissibleThreshold) {
                    isLoading = true
                    adapter.loadMore()
                    viewModel.loadMore()
                }
            }
        })

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (timer != null) {
                    timer?.cancel()
                    timer = null
                }
                timer = Timer()
                timer?.schedule(object : TimerTask() {
                    override fun run() {
                        runOnUiThread {
                            adapter.reset()
                            viewModel.updateQueryString(binding.searchEditText.text.toString())
                        }
                    }
                }, 700)
            }
            override fun afterTextChanged(s: Editable) {}
        })

        binding.searchButton .setOnClickListener {
            if(binding.searchEditText.text.isEmpty()) {
                Toast.makeText(this,"Please Enter The Text",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            hideKeyboard()
            adapter.reset()
            viewModel.updateQueryString(binding.searchEditText.text.toString())
        }
    }

    fun hideKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

}
package com.sandeep.nspiretask.views

import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ocrdemo.nspiretask.adapter.ContributersAdapter
import com.sandeep.nspiretask.common.UiState
import com.sandeep.nspiretask.databinding.RepodetailsBinding
import com.sandeep.nspiretask.entities.ContributeDataN
import com.sandeep.nspiretask.entities.RepoData
import com.sandeep.nspiretask.viewmodel.RepoDetailsViewModel
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class RepoDetails : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: RepodetailsBinding
    val adapter = ContributersAdapter()

    private val viewModel: RepoDetailsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[RepoDetailsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RepodetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data_obj = intent.getParcelableExtra<RepoData>("data_val")

        binding.repoName.text = data_obj?.full_name
        binding.repoName.text = data_obj?.full_name
        binding.repoName.text = data_obj?.full_name

        binding.repoName.text = data_obj?.html_url
        binding.repoNameOneN.text = data_obj?.full_name
        binding.repoDescrption.text = data_obj?.description
        binding.repoComtributers.text = data_obj?.contributors_url
        Picasso.with(this).load(Uri.parse(data_obj?.owner?.avatar_url)).into(binding.img)

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerview.layoutManager = linearLayoutManager
        binding.recyclerview.adapter = adapter

        viewModel.getData(data_obj?.contributors_url.toString())

        viewModel.data_model.observe(this,{

            when (it) {
                is UiState.Loading -> {

                }
                is UiState.Success<*> -> {
                    val data = it.result as List<ContributeDataN>
                    binding.recyclerview.post {
                        adapter.setListData(data)
                    }
                }
            }
        })







        binding.repoName.setOnClickListener {

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data_obj?.html_url))
            startActivity(browserIntent)
           /* if (!data_obj?.html_url.toString().startsWith("http://") && !data_obj?.html_url.toString().startsWith("https://")){

            }else{
                Toast.makeText(this,"Link not valid",Toast.LENGTH_SHORT).show()
            }
*/


        }


    }


}
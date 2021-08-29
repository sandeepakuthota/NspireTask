package com.ocrdemo.nspiretask.adapter

import android.R
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sandeep.nspiretask.databinding.LoadmoreListitemBinding
import com.sandeep.nspiretask.databinding.RepositarylistListItemBinding
import com.sandeep.nspiretask.entities.RepoData
import com.sandeep.nspiretask.views.RepoDetails
import com.squareup.picasso.Picasso


class RepositaryListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = mutableListOf<RepoData?>()
    private val LOADING = 0
    private val ITEM = 1
    var isLoadingAdded = false

    fun loadMore() {
        isLoadingAdded = true
        data.apply {
            if(!contains(null)) {
                add(null)
                notifyItemInserted(this.size-1)
            }
        }
    }

    fun replaceList(newList: List<RepoData>) {
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }

    fun reset() {
        isLoadingAdded = false
        data.clear()
        notifyDataSetChanged()
    }

    fun updateList(newList: List<RepoData>) {
        isLoadingAdded = false
        data.apply {
            if(!isNullOrEmpty() && contains(null)) {
                remove(null)
            }
            if(!containsAll(newList)) {
                addAll(newList)
                notifyDataSetChanged()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if(viewType == ITEM) {
            val binding = RepositarylistListItemBinding.inflate(inflater, parent, false)
            return   MyViewHolder(binding)
        } else {
            val binding = LoadmoreListitemBinding.inflate(inflater, parent, false)
            return   LoadingViewHolder(binding)
/*            val viewLoading: View = inflater.inflate(R.layout.item_progress, parent, false)
            return LoadingViewHolder(viewLoading)*/
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == data.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            ITEM -> {
                val movieViewHolder: MyViewHolder = holder as MyViewHolder
                val asignment = data[position]
                holder.binding.repoName.text = asignment?.html_url
                holder.binding.repoNameOneN.text = asignment?.full_name
                Picasso.with(holder.itemView.context).load(Uri.parse(asignment?.owner?.avatar_url)).into(holder.binding.img)
                /* Picasso.load(Uri.parse(asignment?.owner?.avatar_url))
                     .into(holder.binding.img)*/
                //Glide.with(this).load(Uri.parse(asignment?.owner?.avatar_url)).into(holder.binding.img);


                holder.itemView.setOnClickListener {
                    val intent = Intent(holder.itemView.context, RepoDetails::class.java)
                    intent.putExtra("data_val",asignment)
                    holder.itemView.context.startActivity(intent)
                }
            }
            LOADING -> {
                val loadingViewHolder: LoadingViewHolder = holder as LoadingViewHolder
                loadingViewHolder.binding.progressCircularId.setVisibility(View.VISIBLE)
            }
        }


    }

    override fun getItemCount(): Int {
        return  data.size
    }
}

class MyViewHolder(val binding: RepositarylistListItemBinding) : RecyclerView.ViewHolder(binding.root) {

}
class LoadingViewHolder(val binding: LoadmoreListitemBinding) : RecyclerView.ViewHolder(binding.root) {

}
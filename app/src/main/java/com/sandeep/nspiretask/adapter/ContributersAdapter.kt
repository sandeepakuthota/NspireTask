package com.ocrdemo.nspiretask.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.nspiretask.databinding.ContributerListitemBinding
import com.sandeep.nspiretask.entities.ContributeDataN
import com.squareup.picasso.Picasso

class ContributersAdapter  : RecyclerView.Adapter<ViewHolder>() {

    var data = mutableListOf<ContributeDataN>()

    fun setListData(list: List<ContributeDataN>) {
        this.data = list.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContributerListitemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val asignment = data[position]
        holder.binding.repoName.text = asignment.login
        holder.binding.repoNameOneN.text = asignment.html_url
          Picasso.with(holder.itemView.context).load(Uri.parse(asignment?.avatar_url)).into(holder.binding.img)
        /* Picasso.load(Uri.parse(asignment?.owner?.avatar_url))
             .into(holder.binding.img)*/

        /*holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, RepoDetails::class.java)
            intent.putExtra("data_val",asignment)
            holder.itemView.context.startActivity(intent)
        }*/

    }

    override fun getItemCount(): Int {
        return  data.size
    }
}

class ViewHolder(val binding: ContributerListitemBinding) : RecyclerView.ViewHolder(binding.root) {

}
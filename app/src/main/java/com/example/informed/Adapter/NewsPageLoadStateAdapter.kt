package com.example.informed.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.informed.R

class NewsPageLoadStateAdapter:LoadStateAdapter<NewsPageLoadStateAdapter.LoaderViewHolder>() {



    inner  class LoaderViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val progressbar = itemView.findViewById<ProgressBar>(R.id.progressBar)

        fun bind(loadState:LoadState){

            progressbar.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {

        holder.bind(loadState)




    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {

        val view =  LayoutInflater.from(parent.context).inflate(R.layout.progress_bar,parent,false)
        return  LoaderViewHolder(view)

    }


}
package com.kontrakanprojects.al_kisah25nabidanrosul.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kontrakanprojects.al_kisah25nabidanrosul.R
import com.kontrakanprojects.al_kisah25nabidanrosul.databinding.ItemKisahNabiBinding
import com.kontrakanprojects.al_kisah25nabidanrosul.model.ResultsKisahNabi

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var onItemClickCallBack: OnItemClickCallBack? = null
    private val listKisahNabi = ArrayList<ResultsKisahNabi>()

    private val TAG = MainAdapter::class.simpleName

    fun setData(kisahNabi: List<ResultsKisahNabi>?) {
        if (kisahNabi == null) return
        listKisahNabi.clear()
        listKisahNabi.addAll(kisahNabi)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding =
            ItemKisahNabiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listKisahNabi[position])
    }

    override fun getItemCount() = listKisahNabi.size

    inner class MainViewHolder(private val binding: ItemKisahNabiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resultsKisahNabi: ResultsKisahNabi) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(resultsKisahNabi.imageUrl)
                    .placeholder(R.drawable.img_not_found)
                    .error(R.drawable.img_not_found)
                    .into(imgNabi)

                tvNama.text = resultsKisahNabi.name
                tvUsia.text = itemView.context.getString(R.string.usia, resultsKisahNabi.usia)
                tvDescription.text = resultsKisahNabi.description
            }

            itemView.setOnClickListener { onItemClickCallBack?.onItemClicked(resultsKisahNabi) }
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(resultsKisahNabi: ResultsKisahNabi)
    }
}
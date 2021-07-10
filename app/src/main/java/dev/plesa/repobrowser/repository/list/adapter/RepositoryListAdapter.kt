package dev.plesa.repobrowser.repository.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.plesa.repobrowser.R
import dev.plesa.repobrowser.model.GitHubRepositoryUI

class RepositoryListAdapter(
    var itemClickListener: RepositoryItemClickListener
) : RecyclerView.Adapter<RepositoryListViewHolder>() {

    private var repositories: ArrayList<GitHubRepositoryUI> = ArrayList()

    fun setData(data: List<GitHubRepositoryUI>) {
        repositories.clear()
        repositories.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(data: List<GitHubRepositoryUI>) {
        repositories.addAll(data)
        notifyItemRangeChanged(repositories.size - data.size, repositories.size)
    }

    fun clear() {
        repositories.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return RepositoryListViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: RepositoryListViewHolder, position: Int) {
        holder.setRepository(repositories[position])
    }

    override fun getItemCount() = repositories.size

}
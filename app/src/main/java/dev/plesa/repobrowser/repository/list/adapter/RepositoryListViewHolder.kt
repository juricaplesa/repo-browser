package dev.plesa.repobrowser.repository.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.plesa.repobrowser.R
import dev.plesa.repobrowser.model.GitHubRepositoryUI
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryListViewHolder(
    itemView: View,
    private val itemClickListener: RepositoryItemClickListener
) : RecyclerView.ViewHolder(itemView) {

    fun setRepository(repository: GitHubRepositoryUI) = with(itemView) {

        Glide.with(itemView.context)
            .load(repository.user.avatarUrl)
            .fallback(R.drawable.ic_missing_image)
            .into(imgOwner)

        txtRepository.text = repository.name
        txtOwner.text = repository.user.name
        txtWatchers.text = repository.watchersCount.toString()
        txtForks.text = repository.forksCount.toString()
        txtIssues.text = repository.issuesCount.toString()

        imgOwner.setOnClickListener { itemClickListener.onImageClicked(repository) }
        itemView.setOnClickListener { itemClickListener.onItemClicked(repository) }

    }

}
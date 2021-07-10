package dev.plesa.repobrowser.repository.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.plesa.repobrowser.model.GitHubRepositoryUI
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryListViewHolder(
    itemView: View,
    private val itemClickListener: RepositoryItemClickListener
) : RecyclerView.ViewHolder(itemView) {

    fun setRepository(repository: GitHubRepositoryUI) = with(itemView) {

        Glide.with(itemView.context).load(repository.ownerAvatarUrl).into(imgOwner)
        txtRepository.text = repository.name

        imgOwner.setOnClickListener { itemClickListener.onImageClicked(repository) }
        itemView.setOnClickListener { itemClickListener.onItemClicked(repository) }

    }

}
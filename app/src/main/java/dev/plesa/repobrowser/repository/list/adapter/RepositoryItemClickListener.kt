package dev.plesa.repobrowser.repository.list.adapter

import dev.plesa.repobrowser.common.ItemClickListener
import dev.plesa.repobrowser.model.GitHubRepositoryUI

interface RepositoryItemClickListener : ItemClickListener<GitHubRepositoryUI> {
    fun onImageClicked(item: GitHubRepositoryUI)
}
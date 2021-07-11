package dev.plesa.repobrowser.repository.details

import android.os.Bundle
import dev.plesa.repobrowser.R
import dev.plesa.repobrowser.common.EXTRA_REPOSITORY
import dev.plesa.repobrowser.common.base.BaseFragment
import dev.plesa.repobrowser.model.GitHubRepositoryUI
import org.koin.android.ext.android.inject

class RepositoryDetailsFragment :
    BaseFragment<RepositoryDetailsViewModel>(R.layout.fragment_repository_details) {

    companion object {
        fun newInstance(repository: GitHubRepositoryUI): RepositoryDetailsFragment {
            return RepositoryDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(EXTRA_REPOSITORY, repository)
                }
            }
        }
    }

    override val viewModel: RepositoryDetailsViewModel by inject()

    private val repository: GitHubRepositoryUI by lazy { arguments?.getSerializable(EXTRA_REPOSITORY) as GitHubRepositoryUI }



}
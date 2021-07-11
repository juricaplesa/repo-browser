package dev.plesa.repobrowser.repository.details

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import dev.plesa.repobrowser.R
import dev.plesa.repobrowser.common.EXTRA_REPOSITORY
import dev.plesa.repobrowser.common.base.BaseFragment
import dev.plesa.repobrowser.model.GitHubRepositoryUI
import dev.plesa.repobrowser.model.GitHubUserUI
import kotlinx.android.synthetic.main.fragment_repository_details.*
import kotlinx.android.synthetic.main.toolbar_with_title.*
import org.koin.android.ext.android.inject

class RepositoryDetailsFragment :
    BaseFragment<RepositoryDetailsViewModel>(R.layout.fragment_repository_details),
    View.OnClickListener {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar(toolbar, R.drawable.ic_back)

        displayRepositoryData(repository)

        viewModel.user.observe(viewLifecycleOwner, { user ->
            user?.let {
                displayUserData(it)
            }
        })

        viewModel.getUser(repository.user.detailsRequestUrl)

        btnRepository.setOnClickListener(this)
        btnOwner.setOnClickListener(this)
    }

    private fun displayRepositoryData(repository: GitHubRepositoryUI) {
        toolbarTitle.text = repository.name

        txtLanguage.text = repository.programmingLanguage
        txtCreatedAt.text = repository.createdAt
        txtUpdatedAt.text = repository.updatedAt
        txtWatchersCount.text = repository.watchersCount.toString()
        txtForksCount.text = repository.forksCount.toString()
        txtIssuesCount.text = repository.issuesCount.toString()

        Glide.with(this)
            .load(repository.user.avatarUrl)
            .fallback(R.drawable.ic_missing_image)
            .into(imgUser)

        txtOwnerName.text = repository.user.name
    }

    private fun displayUserData(user: GitHubUserUI) {
        Glide.with(this)
            .load(user.avatarUrl)
            .fallback(R.drawable.ic_missing_image)
            .into(imgUser)

        txtOwnerName.text = user.name
        txtOwnerCompany.text = user.company
        txtOwnerLocation.text = user.location
        txtOwnerEmail.text = user.email
        txtOwnerBio.text = user.bio
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            btnRepository.id -> viewModel.onRepositoryButtonClicked(repository)
            btnOwner.id -> viewModel.onUserButtonClicked(repository)
        }
    }

}
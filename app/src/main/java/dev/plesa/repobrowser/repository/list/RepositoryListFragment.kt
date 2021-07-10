package dev.plesa.repobrowser.repository.list

import android.os.Bundle
import android.view.View
import dev.plesa.repobrowser.R
import dev.plesa.repobrowser.common.base.BaseFragment
import org.koin.android.ext.android.inject

class RepositoryListFragment : BaseFragment<RepositoryListViewModel>(R.layout.fragment_repository_list) {

    companion object {
        fun newInstance(): RepositoryListFragment {
            return RepositoryListFragment()
        }
    }

    override val viewModel: RepositoryListViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}
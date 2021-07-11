package dev.plesa.repobrowser.repository.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dev.plesa.repobrowser.R
import dev.plesa.repobrowser.common.base.BaseFragment
import dev.plesa.repobrowser.common.getQueryTextChangeStateFlow
import dev.plesa.repobrowser.model.GitHubRepositoryUI
import dev.plesa.repobrowser.repository.list.adapter.RepositoryItemClickListener
import dev.plesa.repobrowser.repository.list.adapter.RepositoryListAdapter
import kotlinx.android.synthetic.main.fragment_repository_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class RepositoryListFragment :
    BaseFragment<RepositoryListViewModel>(R.layout.fragment_repository_list),
    RepositoryItemClickListener {

    companion object {
        fun newInstance(): RepositoryListFragment {
            return RepositoryListFragment()
        }
    }

    override val viewModel: RepositoryListViewModel by inject()

    private val adapter = RepositoryListAdapter(this)

    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearchView()
        setupRadioGroup()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    @FlowPreview
    private fun setupSearchView() {
        searchView.onActionViewExpanded()
        searchView.clearFocus()

        viewLifecycleOwner.lifecycleScope.launch {
            searchView.getQueryTextChangeStateFlow()
                .debounce(800)
                .distinctUntilChanged()
                .flowOn(Dispatchers.Default)
                .collect { query ->
                    viewModel.getRepositories(query)
                }
        }
    }

    private fun setupRadioGroup() {

    }

    override fun onImageClicked(item: GitHubRepositoryUI) {
        TODO("Not yet implemented")
    }

    override fun onItemClicked(item: GitHubRepositoryUI) {
        TODO("Not yet implemented")
    }

}
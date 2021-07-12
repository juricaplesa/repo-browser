package dev.plesa.repobrowser.repository.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.plesa.domain.model.RepositoriesSortOption
import dev.plesa.repobrowser.R
import dev.plesa.repobrowser.common.base.BaseFragment
import dev.plesa.repobrowser.common.getQueryTextChangeStateFlow
import dev.plesa.repobrowser.model.GitHubRepositoryUI
import dev.plesa.repobrowser.repository.RepositoryActivity
import dev.plesa.repobrowser.repository.details.RepositoryDetailsFragment
import dev.plesa.repobrowser.repository.list.adapter.RepositoryItemClickListener
import dev.plesa.repobrowser.repository.list.adapter.RepositoryListAdapter
import kotlinx.android.synthetic.main.fragment_repository_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
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

        viewModel.dataLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        viewModel.repositories.observe(viewLifecycleOwner, { data ->
            adapter.setData(data)
        })

        viewModel.nextRepositories.observe(viewLifecycleOwner, { data ->
            adapter.addData(data)
        })

        viewModel.showDetails.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let { data ->
                if (activity is RepositoryActivity) {
                    val activity = activity as RepositoryActivity
                    activity.addFragment(RepositoryDetailsFragment.newInstance(data))
                }
            }
        })

    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return
                val itemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                val isNearTheEnd = lastVisibleItemPosition + 2 >= itemCount
                if (itemCount > 0 && isNearTheEnd) {
                    viewModel.onScrolledNearEnd()
                }
            }
        })
    }
    
    @FlowPreview
    private fun setupSearchView() {
        searchView.onActionViewExpanded()
        searchView.clearFocus()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchView.getQueryTextChangeStateFlow()
                    .drop(1)
                    .debounce(800)
                    .distinctUntilChanged()
                    .flowOn(Dispatchers.Default)
                    .collect { query ->
                        viewModel.onQueryChanged(query)
                    }
            }
        }
    }

    private fun setupRadioGroup() {
        radioGroupSort.setOnCheckedChangeListener { _, checkedId ->
            val query = searchView.query.toString()
            when (checkedId) {
                sortDefault.id ->
                    viewModel.onSelectedSortChanged(query, RepositoriesSortOption.DEFAULT)
                sortStars.id ->
                    viewModel.onSelectedSortChanged(query, RepositoriesSortOption.STARS)
                sortForks.id ->
                    viewModel.onSelectedSortChanged(query, RepositoriesSortOption.FORKS)
                sortUpdated.id ->
                    viewModel.onSelectedSortChanged(query, RepositoriesSortOption.UPDATED)
            }
        }
    }

    override fun onImageClicked(item: GitHubRepositoryUI) {
        viewModel.onImageClicked(item)
    }

    override fun onItemClicked(item: GitHubRepositoryUI) {
        viewModel.onItemClicked(item)
    }

}
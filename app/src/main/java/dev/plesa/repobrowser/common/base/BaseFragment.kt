package dev.plesa.repobrowser.common.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

abstract class BaseFragment<viewModelClass : BaseViewModel>(
    private val layoutResource: Int
) : Fragment() {

    protected abstract val viewModel: viewModelClass

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResource, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.errorMessage.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let { stringResourceId ->
                showToastMessage(stringResourceId)
            }
        })

        viewModel.openExternalUrl.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let { externalUrl ->
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(externalUrl)))
            }
        })
    }

    private fun showToastMessage(stringResourceId: Int) {
        activity?.let {
            Toast.makeText(it, getString(stringResourceId), Toast.LENGTH_SHORT).show()
        }
    }

    fun setupActionBar(toolbar: Toolbar, homeIconResourceId: Int?) {
        setActionBar(toolbar)
        if (homeIconResourceId != null) {
            setHomeIcon(homeIconResourceId)
        }
    }

    private fun setActionBar(toolbar: Toolbar) {
        if (activity is AppCompatActivity) {
            val activity = activity as AppCompatActivity
            activity.setSupportActionBar(toolbar)
            activity.supportActionBar?.apply {
                setDisplayShowTitleEnabled(false)
            }
        }
    }

    private fun setHomeIcon(homeIconResourceId: Int) {
        if (activity is AppCompatActivity) {
            val activity = activity as AppCompatActivity
            activity.supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
                setHomeAsUpIndicator(homeIconResourceId)
            }
        }
    }


}
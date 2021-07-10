package dev.plesa.repobrowser.common.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

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

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { stringResourceId ->
            showToastMessage(stringResourceId)
        })

        viewModel.openExternalUrl.observe(viewLifecycleOwner, Observer {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        })
    }

    protected fun showToastMessage(stringResourceId: Int) {
        activity?.let {
            Toast.makeText(it, getString(stringResourceId), Toast.LENGTH_SHORT).show()
        }
    }

}
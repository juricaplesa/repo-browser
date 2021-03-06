package dev.plesa.repobrowser.repository

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import dev.plesa.repobrowser.R
import dev.plesa.repobrowser.common.base.BaseActivity
import dev.plesa.repobrowser.repository.list.RepositoryListFragment
import kotlinx.android.synthetic.main.activity_repository.*

class RepositoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        showFragment(fragmentContainer.id, RepositoryListFragment.newInstance())
    }

    fun addFragment(fragment: Fragment) {
        addFragment(fragmentContainer.id, fragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
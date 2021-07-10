package dev.plesa.repobrowser.common.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

open class BaseActivity : AppCompatActivity() {

    fun showFragment(containerViewId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(containerViewId, fragment)
            .commit()
    }

    fun addFragment(containerViewId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(containerViewId, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun setupActionBar(toolbar: Toolbar, homeIconResourceId: Int?) {
        setActionBar(toolbar)
        if (homeIconResourceId != null) {
            setHomeIcon(homeIconResourceId)
        }
    }

    private fun setActionBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
        }
    }

    private fun setHomeIcon(homeIconResourceId: Int) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setHomeAsUpIndicator(homeIconResourceId)
        }
    }

}
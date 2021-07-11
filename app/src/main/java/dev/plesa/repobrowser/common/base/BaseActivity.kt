package dev.plesa.repobrowser.common.base

import androidx.appcompat.app.AppCompatActivity
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

}
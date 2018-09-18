package com.moises.usersrandom.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast

open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    fun changeTitle(title: String) {
        actionBar()?.let {
            it.title = title
        }
    }

    fun showOrHideHomeBackButton(enabled: Boolean) {
        actionBar()?.let {
            it.setDisplayHomeAsUpEnabled(enabled)
        }
    }

    private fun actionBar(): ActionBar? {
        return (activity as AppCompatActivity).supportActionBar
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            activity!!.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun showMessageInToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
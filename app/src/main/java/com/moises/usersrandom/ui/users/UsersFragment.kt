package com.moises.usersrandom.ui.users

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.moises.usersrandom.R
import com.moises.usersrandom.model.User
import com.moises.usersrandom.ui.base.BaseFragment
import com.moises.usersrandom.ui.users.adapter.UsersAdapter
import com.moises.usersrandom.utils.explodeAndEpicenter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_users.*
import javax.inject.Inject

class UsersFragment @Inject constructor() : BaseFragment() {
    @Inject
    lateinit var viewModel: UsersViewModel
    @Inject
    lateinit var usersAdapter: UsersAdapter

    private var listenerUsers: OnUsersFragmentListener? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if (context !is OnUsersFragmentListener) {
            throw RuntimeException(context.toString() + " must implement OnUsersFragmentListener")
        }
        listenerUsers = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        changeTitle("Users")
        usersAdapter.addClickListener(this::animateViewClicked)
        recyclerView.run {
            layoutManager = GridLayoutManager(context, 3)
            adapter = usersAdapter
        }
        setUpViewModel()
    }

    private fun setUpViewModel() {
        viewModel.loading.observe(this, Observer { updateLoading(it!!)})
        viewModel.users.observe(this, Observer { showUsers(it!!) })
        viewModel.error.observe(this, Observer { showError(it!!) })
        viewModel.loadUsers()
    }

    override fun onDetach() {
        super.onDetach()
        listenerUsers = null
        viewModel.doDispose()
    }

    private fun updateLoading(status: Boolean) {
        recyclerView.run {
            visibility = if (status) View.GONE else View.VISIBLE
        }
        pbLoading.run {
            visibility = if (status) VISIBLE else GONE
        }
    }

    private fun showUsers(users: List<User>) {
        usersAdapter.addItems(users)
    }

    private fun showError(error: String) {
        showMessageInToast(error)
    }

    private fun animateViewClicked(user: User, clickedView: View) {
        recyclerView.explodeAndEpicenter(clickedView)
                .subscribe { listenerUsers?.onUserClicked(user) }
        recyclerView.adapter = null
    }
}

interface OnUsersFragmentListener {
    fun onUserClicked(user: User)
}

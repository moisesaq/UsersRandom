package com.moises.usersrandom.ui.users

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moises.usersrandom.R
import com.moises.usersrandom.model.User
import com.moises.usersrandom.ui.base.BaseFragment
import com.moises.usersrandom.ui.users.adapter.UsersAdapter
import com.moises.usersrandom.utils.explodeAndEpicenter
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_users.*
import javax.inject.Inject

class UsersFragment @Inject constructor() : BaseFragment(), UsersContract.View {
    @Inject
    lateinit var presenter: UsersContract.Presenter
    @Inject
    lateinit var usersAdapter: UsersAdapter

    private var listenerUsers: OnUsersFragmentListener? = null
    private var compositeDisposable = CompositeDisposable()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        presenter.addView(this)
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
        presenter.loadUsers()
    }

    override fun onDetach() {
        super.onDetach()
        listenerUsers = null
        compositeDisposable.dispose()
        presenter.doDispose()
    }

    override fun showLoading() {
        recyclerView.visibility = View.GONE
        pbLoading.visibility = View.VISIBLE
    }

    override fun showUsers(users: List<User>) {
        usersAdapter.addItems(users)
    }

    override fun hideLoading() {
        recyclerView.visibility = View.VISIBLE
        pbLoading.visibility = View.GONE
    }

    override fun showError(error: String) {
        showMessageInToast(error)
    }

    private fun animateViewClicked(user: User, clickedView: View) {
        recyclerView.explodeAndEpicenter(clickedView)
                .subscribe { listenerUsers?.onUserClicked(user) }
                .addTo(compositeDisposable)
        recyclerView.adapter = null
    }
}

interface OnUsersFragmentListener {
    fun onUserClicked(user: User)
}

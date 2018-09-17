package com.moises.usersrandom.ui.users

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.moises.usersrandom.R
import com.moises.usersrandom.model.User
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_users.*
import javax.inject.Inject

class UsersFragment @Inject constructor(): Fragment(), UsersContract.View {

    @Inject lateinit var presenter: UsersPresenter

    private var listenerUsers: OnUsersFragmentListener? = null

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

    private fun setUp() {
        button_test.setOnClickListener {
            Toast.makeText(context, "Test message", Toast.LENGTH_SHORT).show()
            presenter.loadUsers()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        //presenter.loadUsers()
    }

    override fun onDetach() {
        super.onDetach()
        listenerUsers = null
    }

    interface OnUsersFragmentListener {
        fun onUserClicked(userId: String)
    }

    override fun showUsers(users: List<User>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

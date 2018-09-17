package com.moises.usersrandom.ui.userInfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moises.usersrandom.R
import com.moises.usersrandom.model.User
import com.moises.usersrandom.ui.users.UsersFragment
import kotlinx.android.synthetic.main.fragment_info_user.*
import javax.inject.Inject

private const val ARG_PARAM1 = "userId"

class UserInfoFragment: Fragment(), UserInfoContract.View {

    private var userId: String? = null
    @Inject
    lateinit var presenter: UserInfoContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        userId?.let { presenter.findUser(it) }
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showUserInfo(user: User) {
        tv_name.text = user.name//String.("%s %s", user.name, user.lastname)
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
                UsersFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                    }
                }
    }
}
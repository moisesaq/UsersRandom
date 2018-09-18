package com.moises.usersrandom.ui.userInfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.moises.usersrandom.R
import com.moises.usersrandom.model.User
import com.moises.usersrandom.ui.base.BaseFragment
import com.moises.usersrandom.ui.users.UsersFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_info_user.*
import javax.inject.Inject

private const val ARG_PARAM1 = "user"

class UserInfoFragment : BaseFragment(), UserInfoContract.View {

    private var user: User? = null
    @Inject
    lateinit var presenter: UserInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        presenter.addView(this)
        arguments?.let {
            user = it.getParcelable(ARG_PARAM1) as User
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
        changeTitle("User info")
        showOrHideHomeBackButton(true)
        loadData()
    }

    private fun loadData() {
        user?.let  {
            if (!it.id.isEmpty())
                presenter.findUser(it.id)
            else
                showUserInfo(it)
        }
    }

    override fun showLoading() {
        pbLoading.visibility = View.VISIBLE
    }

    override fun showUserInfo(user: User) {
        loadPhoto(user.photo)
        tvName.text = user.name
        tvLastname.text = user.lastname
    }

    private fun loadPhoto(url: String) {
        Glide.with(ivPhoto.context)
                .load(url)
                .into(ivPhoto)
    }

    override fun hideLoading() {
        pbLoading.visibility = View.GONE
    }

    override fun showError(error: String) {
        showMessageInToast(error)
    }

    override fun onDetach() {
        super.onDetach()
        showOrHideHomeBackButton(false)
    }

    companion object {
        @JvmStatic
        fun newInstance(user: User) =
                UserInfoFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_PARAM1, user)
                    }
                }
    }
}
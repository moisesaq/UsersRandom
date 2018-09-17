package com.moises.usersrandom.ui.userInfo

import android.os.Bundle
import android.support.v4.app.Fragment
import com.moises.usersrandom.ui.users.UsersFragment

private const val ARG_PARAM1 = "param1"

class UserInfoFragment: Fragment() {

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
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
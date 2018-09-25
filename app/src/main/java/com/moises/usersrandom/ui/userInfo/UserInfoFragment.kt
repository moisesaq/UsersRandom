package com.moises.usersrandom.ui.userInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.moises.usersrandom.R
import com.moises.usersrandom.model.User
import com.moises.usersrandom.ui.base.BaseFragment
import com.moises.usersrandom.utils.appear
import com.moises.usersrandom.utils.rotationXBy
import com.moises.usersrandom.utils.rotationYBy
import com.moises.usersrandom.utils.translateX
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_info_user.*
import java.util.concurrent.TimeUnit
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
        tvName.text = "Name: " + user.name
        tvLastname.text = "Lastname: " + user.lastname
        animateViews()
        animateImageViewPhoto()
    }

    private fun animateViews() {
        ivPhoto.visibility = VISIBLE
        ivPhoto.appear(400)
                .doAfterTerminate {
                    cardViewInfo.visibility = VISIBLE
                    cardViewInfo.appear()
                }
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    animateTextView(tvName)
                }.doAfterTerminate {
                    animateTextView(tvLastname)
                }
                .subscribe()
    }

    private fun animateImageViewPhoto() {
        ivPhoto.setOnClickListener {
            animateTextView(tvName).subscribe()
        }
    }

    private fun animateTextView(textView: TextView): Completable {
        textView.visibility = VISIBLE
        return textView.appear()//textView.rotationYBy(y = 360f)
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
                    this.addTransition()
                    arguments = Bundle().apply {
                        putParcelable(ARG_PARAM1, user)
                    }
                }
    }
}
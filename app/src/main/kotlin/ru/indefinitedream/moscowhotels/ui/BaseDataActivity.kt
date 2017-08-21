package ru.indefinitedream.moscowhotels.ui

import android.animation.Animator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import ru.indefinitedream.moscowhotels.R
import ru.indefinitedream.moscowhotels.view.BaseView

const val ANIMATION_DURATION = 500L
const val VISIBLE_ALPHA = 1f
const val INVISIBLE_ALPHA = 0f

/**
 * Created by dmitry on 20.08.17.
 */
abstract class BaseDataActivity : AppCompatActivity(), BaseView {

    @BindView(R.id.progress)
    lateinit var progress : ProgressBar

    @BindView(R.id.error_message)
    lateinit var error : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        ButterKnife.bind(this)
    }

    abstract fun getLayoutId() : Int

    override fun showProgressBar(animated: Boolean) {
        setVisibleView(progress, animated)
    }

    override fun showError(animated: Boolean) {
        setVisibleView(error, animated)
    }

    protected open fun setVisibleView(view : View, animated : Boolean) {
        setVisibility(progress, view == progress, animated)
        setVisibility(error, view == error, animated)
    }

    protected fun setVisibility(view : View, visible: Boolean, animated : Boolean) {
        val alpha = if (visible) VISIBLE_ALPHA else INVISIBLE_ALPHA
        if (animated) {
            view.animate()
                    .alpha(alpha)
                    .setDuration(ANIMATION_DURATION)
                    .setListener(VisibilityAnimationListener(view, visible))
                    .start()
        } else {
            view.alpha = alpha
            view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
        }
    }

}

class VisibilityAnimationListener(private val view : View, private val toVisible : Boolean)
    : Animator.AnimatorListener {

    override fun onAnimationRepeat(animation: Animator?) { }

    override fun onAnimationEnd(animation: Animator?) {
        if (!toVisible) view.visibility = View.INVISIBLE
    }

    override fun onAnimationCancel(animation: Animator?) { }

    override fun onAnimationStart(animation: Animator?) {
        view.visibility = View.VISIBLE
    }

}
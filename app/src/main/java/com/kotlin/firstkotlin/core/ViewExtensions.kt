package com.kotlin.firstkotlin.core

import android.app.Activity
import android.graphics.Color
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.kotlin.firstkotlin.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeoutException

fun View.toggleVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

infix fun View.onClick(action: (() -> Unit)) {
    this.setOnClickListener { action.invoke() }
}

fun View.showSnackbar(message: String) {
    if (!TextUtils.isEmpty(message)) {
        val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        snackBar.setActionTextColor(Color.WHITE)
        val sbView = snackBar.view
        sbView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.colorAccent))
        snackBar.show()
    }
}

fun EditText.searchObservable(): Observable<String> {
    val subject = PublishSubject.create<String>()
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
            subject.onNext(text.toString())
        }

        override fun afterTextChanged(s: Editable) {
//            subject.onComplete()
        }
    })

    return subject
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

fun Fragment.showMessage(error: Throwable, isGuest: Boolean) {
    activity?.run {
        showMessage(error, isGuest)
    }
}

fun Activity.showMessage(error: Throwable, isGuest: Boolean) {
    when (error) {
        is ServerErrorException -> showShortToast("")
        is TimeoutException -> showShortToast("")
        is ServerUnreachableException -> showShortToast("")
        is UnAuthorizedException -> {
            if (!isGuest) {
                //clear cache
//                startActivity(Intent(this, LauncherActivity::class.java))
            }
            showShortToast("")
        }
    }
}

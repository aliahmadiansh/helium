package com.partsoftware.helium.core.ui

import android.util.Patterns
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.article.R
import com.article.core.common.gregorianToJalali
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputLayout


import java.util.regex.Pattern

@BindingAdapter("setImageView")
fun setImageView(view: AppCompatImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        view.visibility = View.VISIBLE
        view.load(imageUrl) {
            placeholder(R.drawable.ic_search)
            crossfade(true)
            crossfade(500)
        }
    } else {
        view.visibility = View.INVISIBLE
    }
}

@BindingAdapter("firstName", "lastName", "dateModify")
fun setSubTextView(view: TextView, firstName: String?, lastName: String?, date: String?) {
    if (!lastName.isNullOrEmpty() && !firstName.isNullOrEmpty() && !date.isNullOrEmpty()) {
        val dateSplit = date.split("T").toTypedArray()[0]
        val dateMonthName = gregorianToJalali(
            dateSplit.split("-").toTypedArray()[0].toInt(),
            dateSplit.split("-").toTypedArray()[1].toInt(),
            dateSplit.split("-").toTypedArray()[2].toInt()
        )
        val fullName = "$firstName $lastName"
        val text = "$fullName    •    $dateMonthName"
        view.text = text
    }
}

@BindingAdapter("userFirstName", "userLastName")
fun setUserTextView(view: TextView, firstName: String?, lastName: String?) {
    if (!firstName.isNullOrEmpty() && !lastName.isNullOrEmpty()) {
        val fullName = "$firstName  $lastName"
        view.text = fullName
    }
}

@BindingAdapter("readTime")
fun setReadTime(view: TextView, readTime: String?) {
    if (!readTime.isNullOrEmpty()) {
        val text = view.resources.getString(R.string.read_time_format, readTime)
        view.text = text
    }
}

@BindingAdapter("setBookmarked")
fun setStateBookmarked(view: ImageView, isBookmarked: Boolean) {
    if (isBookmarked) {
        view.setImageResource(R.drawable.ic_bookmarked)
    } else {
        view.setImageResource(R.drawable.ic_bookmark)
    }
}

@BindingAdapter("validationPersian")
fun setValidationPersian(view: TextInputLayout, text: String?) {
    view.error = if (text.isNullOrEmpty()) {
        null
    } else if (Pattern.compile("^[\\u0600-\\u06FF\\s]+\$").matcher(text).matches()) {
        null
    } else {
        view.resources.getString(R.string.error_english_letters)
    }
}

@BindingAdapter("validationEnglish")
fun setValidationEnglish(view: TextInputLayout, text: String?) {
    view.error = if (text.isNullOrEmpty()) {
        null
    } else if (Pattern.compile("^[a-zA-Z0-9_]*\$").matcher(text).matches()) {
        null
    } else {
        view.resources.getString(R.string.error_persian_letters)
    }
}

@BindingAdapter("validationEmail")
fun setValidationEmail(view: TextInputLayout, text: String?) {
    view.error = if (text.isNullOrEmpty()) {
        null
    } else if (Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
        null
    } else {
        view.resources.getString(R.string.error_email_address)
    }
}

@BindingAdapter("validationPhoneNumber")
fun setValidationPhoneNumber(view: TextInputLayout, text: String?) {
    view.error = if (text.isNullOrEmpty()) {
        null
    } else if (text.length < 11) {
        view.resources.getString(R.string.error_phone_number)
    } else {
        null
    }
}

@BindingAdapter("validationPassword")
fun setValidationPassword(view: TextInputLayout, text: String?) {
    view.error = if (text.isNullOrEmpty()) {
        null
    } else if (text.length < 6) {
        view.resources.getString(R.string.error_least_six_letters)
    } else {
        null
    }
}

@BindingAdapter("setProgressShow")
fun setProgressShow(view: CircularProgressIndicator, isShow: Boolean) {
    if (isShow) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}
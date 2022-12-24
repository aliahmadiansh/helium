package com.article.core.common
import android.os.Build
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.article.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.safeNavigate(@IdRes resId: Int) {
    try {
        findNavController().navigate(resId)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Fragment.safeNavigate(directions: NavDirections) {
    try {
        findNavController().navigate(directions)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}



fun View.appSnackBar(text: String) {
    Snackbar.make(this, text, Snackbar.ANIMATION_MODE_SLIDE)
        .setBackgroundTint(
            resources.getColor(
                R.color.background_bottom_sheet,
                resources.newTheme()
            )
        )
        .setTextColor(
            resources.getColor(
                R.color.white_light,
                resources.newTheme()
            )
        )
        .show()
}

fun gregorianToJalali(gy: Int, gm: Int, gd: Int): String {

    val gdm: IntArray = intArrayOf(0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334)
    val gy2: Int = if (gm > 2) (gy + 1) else gy
    var days: Int =
        355666 + (365 * gy) + ((gy2 + 3) / 4) - ((gy2 + 99) / 100) + ((gy2 + 399) / 400) + gd + gdm[gm - 1]
    var jy: Int = -1595 + (33 * (days / 12053))
    days %= 12053
    jy += 4 * (days / 1461)
    days %= 1461
    if (days > 365) {
        jy += ((days - 1) / 365)
        days = (days - 1) % 365
    }
    val jm: Int
    val jd: Int
    if (days < 186) {
        jm = 1 + (days / 31)
        jd = 1 + (days % 31)
    } else {
        jm = 7 + ((days - 186) / 30)
        jd = 1 + ((days - 186) % 30)
    }

    var monthName=""
    when (jm) {
        1 -> monthName = "فروردين"
        2 -> monthName = "ارديبهشت"
        3 -> monthName = "خرداد"
        4 -> monthName = "تير"
        5 -> monthName = "مرداد"
        6 -> monthName = "شهريور"
        7 -> monthName = "مهر"
        8 -> monthName = "آبان"
        9 -> monthName = "آذر"
        10 -> monthName = "دي"
        11 -> monthName = "بهمن"
        12 -> monthName = "اسفند"
    }
    return "$jy $monthName $jd"
}


package com.rikkeisoft.moviedb.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.add(id: Int, fragment: Fragment) {
    beginTransaction().add(id, fragment)
        .commit()
}

fun FragmentManager.replace(id: Int, fragment: Fragment) {
    beginTransaction().add(id, fragment)
        .commit()
}

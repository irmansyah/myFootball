package com.irmansyah.myfootball.utils.extension

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by irmansyah on 25/03/18.
 */

internal fun FragmentManager.replaceFragment(containerViewId: Int,
                                             fragment: Fragment,
                                             tag: String) {
    this.beginTransaction()
            .disallowAddToBackStack()
            .replace(containerViewId, fragment, tag)
            .commitNow()
}
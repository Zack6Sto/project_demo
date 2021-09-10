package com.example.project_demo.utils

import android.app.Activity
import android.content.Context
import android.view.View

/**
 * Class Related with Activity
 */
object ActivityUtil {
    /**
     * Can obtain current Context from Activity
     * @param Ac    Specific Activity
     * @return      Context of Activity
     */
    fun getCurrentContextFromActivity(Ac: Activity?): Context? {
        if (Ac != null) {
            var v = Ac.currentFocus
            if (v == null) {
                val w = Ac.window
                v = w.currentFocus
                if (v == null) {
                    v = w.decorView
                }
            }
            return v.context
        }
        return null
    }

    /**
     * Can obtain Current View which User See At Run-Time
     * @param Ac    Specific Activity
     * @return      View
     */
    fun getCurrentViewFromActivity(Ac: Activity?): View? {
        if (Ac != null) {
            var v = Ac.currentFocus
            if (v == null) {
                val w = Ac.window
                v = w.currentFocus
                if (v == null) {
                    v = w.decorView
                }
            }
            return v
        }
        return null
    }
}

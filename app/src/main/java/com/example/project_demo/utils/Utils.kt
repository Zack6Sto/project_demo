package com.example.project_demo.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.net.Uri
import android.os.Build
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.example.project_demo.R
import com.example.project_demo.data.Preferences
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import qiu.niorgai.StatusBarCompat

class Utils constructor(
    private val context: Context,
    private val mPreferences: Preferences
) {

//    fun generateModelScanQR(jsonString:String):ModelScanQR?{
//        return try {
//            val gson = Gson()
//            gson.fromJson(jsonString, ModelScanQR::class.java)
//        }catch (ex:Exception){
//            null
//        }
//    }

//    fun generateModelScanQRObject(jsonString:String): ModelScanQRObject?{
//        return try {
//            val gson = Gson()
//            gson.fromJson(jsonString, ModelScanQRObject::class.java)
//        }catch (ex:Exception){
//            null
//        }
//    }

    fun handleNestedFragmentBackStack(fragmentManager: FragmentManager): Boolean {
        val childFragmentList = fragmentManager.fragments
        if (childFragmentList.size > 0) {
            for (index in childFragmentList.size - 1 downTo 0) {
                val fragment = childFragmentList[index]
                val isPopped = handleNestedFragmentBackStack(fragment.childFragmentManager)
                return when {
                    isPopped -> true
                    fragmentManager.backStackEntryCount > 1 -> {
                        true
                    }
                    else -> false
                }
            }
        }
        return false
    }

    fun getFacebookPageURL(pageId: String) {
        val pageUrl = "https://www.facebook.com/$pageId"
        try {
            val applicationInfo: ApplicationInfo = context.packageManager
                .getApplicationInfo("com.facebook.katana", 0)
            if (applicationInfo.enabled) {
                val versionCode: Int = context.packageManager
                    .getPackageInfo("com.facebook.katana", 0).versionCode
                val url: String
                url = if (versionCode >= 3002850) {
                    "fb://facewebmodal/f?href=$pageUrl"
                } else {
                    "fb://page/$pageId"
                }
                val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                intentApp.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intentApp)
            } else {
                throw Exception("Facebook is disabled")
            }
        } catch (e: Exception) {
            val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl))
            intentApp.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intentApp)
        }
    }


    fun openAppLine(userId: String) {
        val uri = Uri.parse("http://line.me/ti/p/$userId")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(context.packageManager) != null) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        } else {
            val intentUrlGoogle = Intent(Intent.ACTION_VIEW)
            intentUrlGoogle.data = Uri.parse("market://details?id=" + "jp.naver.line.android&hl=th")
            context.startActivity(intentUrlGoogle)
        }
    }

    fun setImageAutoMetrics(context: Context, imageView: ImageView) {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width: Int = displayMetrics.widthPixels
        imageView.layoutParams.height = width / 3
    }

    fun setImageAutoMetrics(context: Context, imageView: View, number: Int) {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width: Int = displayMetrics.widthPixels
        imageView.layoutParams.height = width / number
    }

    fun eventStartAnimationIntent(activity: AppCompatActivity, isCheck: Boolean) {
        if (isCheck) {
            activity.overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)
        } else {
            activity.overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
        }
    }

    fun closeKeyborad(context: AppCompatActivity, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

//    @SuppressLint("ObsoleteSdkInt")
//    fun onSetStatusBar(context: Context, colorStatusBar: Boolean) {
//        if (colorStatusBar)
//            StatusBarCompat.setStatusBarColor(
//                context as Activity,
//                ContextCompat.getColor(context, R.color.textColorWhite)
//            )
//        else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//                StatusBarCompat.translucentStatusBar(context as Activity, true)
//            else
//                StatusBarCompat.translucentStatusBar(context as Activity)
//        }
//    }

//    fun showBadge(
//        context: Context,
//        bottomNavigationView: BottomNavigationView,
//        @IdRes itemId: Int,
//        value: String
//    ) {
//        val valueBadge: String
//
//        val itemView = bottomNavigationView.findViewById<BottomNavigationItemView>(itemId)
//        val badge = LayoutInflater.from(context)
//            .inflate(R.layout.notification_badge, bottomNavigationView, false)
//        val text = badge.findViewById<TextView>(R.id.notifications)
//
//        if (value != "0" && value.isNotEmpty()) {
//            valueBadge = if (value.toInt() > 999) {
//                "999+"
//            } else {
//                value
//            }
//            text.text = valueBadge
//            itemView.addView(badge)
//        } else {
//            removeBadge(bottomNavigationView, itemId)
//        }
//    }

    private fun removeBadge(bottomNavigationView: BottomNavigationView, @IdRes itemId: Int) {
        val itemView = bottomNavigationView.findViewById<BottomNavigationItemView>(itemId)
        if (itemView.childCount == 3) { // position menu
            itemView.removeViewAt(2)
        }
    }
}

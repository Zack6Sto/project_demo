package com.example.project_demo.utils.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.project_demo.R
import com.example.project_demo.databinding.*
import com.example.project_demo.utils.rxBus.EventRxBus
import com.example.project_demo.vo.RxEvent
import com.service.beneat.utils.extension.getDeviceMetrics
import java.util.ArrayList

class DialogPresenter constructor(
    var fragmentActivity: FragmentActivity
) {


    private val dialogMessage = getDialog()
    private val dialogMessageBtn = getDialog()

    fun dialogMessage(title: String, text: String?, clickCallback: ((Boolean) -> Unit)) {
        dialogMessage.setCanceledOnTouchOutside(false)
        val binding: DialogAlertMessageBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(fragmentActivity),
                R.layout.dialog_alert_message, null, false
            )
        dialogMessage.setContentView(binding.root)
        dialogMessage.window?.attributes!!.width =
            (fragmentActivity.getDeviceMetrics().widthPixels * 0.8).toInt()

        binding.title = title
        binding.text = text

        binding.tvOkey.setOnClickListener {
            dialogMessage.dismiss()
            if (text == "401") {
                EventRxBus.onAddEventRxBus(RxEvent("log out"))
            } else {
                clickCallback.invoke(true)
            }
        }

        if (dialogMessage.isShowing)
            dialogMessage.dismiss()

        dialogMessage.show()
    }

    fun dialogMessageTwoButton(title: String?, clickCallback: ((Boolean) -> Unit)) {
        val dialog = getDialog()
        dialog.setCanceledOnTouchOutside(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        val binding: DialogTwoButtonBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(fragmentActivity),
                R.layout.dialog_two_button, null, false
            )
        dialog.setContentView(binding.root)
        dialog.window?.attributes!!.width =
            (fragmentActivity.getDeviceMetrics().widthPixels * 0.8).toInt()

        binding.tvText.text = title

        binding.tvOkey.setOnClickListener {
            dialog.dismiss()
            clickCallback.invoke(true)
        }

        binding.tvCancel.setOnClickListener {
            dialog.dismiss()
            clickCallback.invoke(false)
        }

        dialog.show()
    }

//    fun dialogMessageIcon(
//        message: String,
//        isSuccess: Boolean,
//        clickCallback: ((Boolean) -> Unit)
//    ) {
//        val dialogMessageBtn = getDialog()
//        dialogMessageBtn.setCanceledOnTouchOutside(false)
//        dialogMessageBtn.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialogMessageBtn.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//        val binding: DialogMessageIconBinding =
//            DataBindingUtil.inflate(
//                LayoutInflater.from(fragmentActivity),
//                R.layout.dialog_message_icon, null, false
//            )
//        dialogMessageBtn.setContentView(binding.root)
//        dialogMessageBtn.window?.attributes!!.width =
//            (fragmentActivity.getDeviceMetrics().widthPixels * 0.9).toInt()
//
//        binding.text = message
//        binding.status = isSuccess
//
//        binding.tvOkey.setOnClickListener {
//            dialogMessageBtn.dismiss()
//            clickCallback.invoke(true)
//        }
//
//        dialogMessageBtn.show()
//    }

    fun dialogConfirmDelete(
        message: String,
        clickCallback: ((Boolean) -> Unit)
    ) {
        val dialogMessageBtn = getDialog()
        dialogMessageBtn.setCanceledOnTouchOutside(false)
        dialogMessageBtn.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogMessageBtn.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val binding: DialogConfirmDeleteBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(fragmentActivity),
                R.layout.dialog_confirm_delete, null, false
            )
        dialogMessageBtn.setContentView(binding.root)
        dialogMessageBtn.window?.attributes!!.width =
            (fragmentActivity.getDeviceMetrics().widthPixels * 0.8).toInt()

        binding.text = message

        binding.tvOkey.setOnClickListener {
            dialogMessageBtn.dismiss()
            clickCallback.invoke(true)
        }

        binding.tvCancel.setOnClickListener {
            dialogMessageBtn.dismiss()
        }

        dialogMessageBtn.show()
    }

//    fun dialogMessageNotitle(message: String?, clickCallback: ((Boolean) -> Unit)) {
//        val dialog = getDialog()
//        dialog.setCanceledOnTouchOutside(false)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        val binding: DialogMessageNoTitleBinding =
//            DataBindingUtil.inflate(
//                LayoutInflater.from(fragmentActivity),
//                R.layout.dialog_message_no_title, null, false
//            )
//        dialog.setContentView(binding.root)
//
//        dialog.window?.attributes!!.width =
//            (fragmentActivity.getDeviceMetrics().widthPixels * 0.8).toInt()
//        binding.message = message
//
//        binding.tvOkey.setOnClickListener {
//            dialog.dismiss()
//            clickCallback.invoke(true)
//        }
//
//        dialog.show()
//    }

//    fun dialogShowImageFullScreen(context: Context, mImageUrl: String) {
//        val dialog = getDialog()
//        dialog.setCanceledOnTouchOutside(false)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//        val binding: CustomImageFullscreenBinding =
//            DataBindingUtil.inflate(
//                LayoutInflater.from(fragmentActivity),
//                R.layout.custom_image_fullscreen, null, false
//            )
//        dialog.setContentView(binding.root)
//
//        binding.ivClose.setOnClickListener {
//            dialog.cancel()
//        }
//
//        binding.ivShow.setImageView(context, mImageUrl)
//
//        dialog.setCancelable(true)
//        dialog.show()
//    }

//    fun dialogSelectImage(clickCallback: ((Int) -> Unit)) {
//        val dialog = getDialog()
//        dialog.setCanceledOnTouchOutside(true)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        dialog.window?.setGravity(Gravity.BOTTOM)
//        val binding: DialogGalleryOrShootingBinding =
//            DataBindingUtil.inflate(
//                LayoutInflater.from(fragmentActivity),
//                R.layout.dialog_gallery_or_shooting, null, false
//            )
//        dialog.setContentView(binding.root)
//
//        binding.btnCamera.setOnClickListener {
//            clickCallback.invoke(1)
//            dialog.dismiss()
//        }
//
//        binding.btnGallery.setOnClickListener {
//            clickCallback.invoke(2)
//            dialog.dismiss()
//        }
//
//        binding.btnCancel.setOnClickListener {
//            dialog.dismiss()
//        }
//
//        dialog.show()
//    }
//
//    fun dialogBottom(
//        arrayList: ArrayList<String>,
//        mSelectPosition: Int,
//        mTitle: String,
//        mClickCallBack: (String) -> Unit
//    ) {
//
//        val dialog = getDialog()
//        dialog.setCanceledOnTouchOutside(false)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//        val binding: FragmentDialogBootomBinding = DataBindingUtil.inflate(
//            LayoutInflater.from(fragmentActivity),
//            R.layout.fragment_dialog_bootom,
//            null,
//            false
//        )
//        dialog.setContentView(binding.root)
//        dialog.window?.attributes!!.width =
//            (fragmentActivity.getDeviceMetrics().widthPixels * 0.8).toInt()
//
//        binding.tvTitleDialog.text = mTitle
//
//        binding.picker.setSelectorRoundedWrapPreferred(false)
//        binding.picker.setWheelItemCount(3)
//        binding.picker.setMin(0)
//        binding.picker.setMax(arrayList.size)
//        binding.picker.setAdapter(WPWeekDaysPickerAdapter(arrayList))
//        binding.picker.scrollTo(mSelectPosition)
//
//        binding.picker.setOnValueChangeListener(object : OnValueChangeListener {
//            override fun onValueChange(picker: WheelPicker, oldVal: String, newVal: String) {
//                mClickCallBack.invoke(picker.getCurrentItem())
//            }
//        })
//
//        binding.txtSelect.setOnClickListener {
//            dialog.cancel()
//        }

//        dialog.setCancelable(true)
//        dialog.show()
//    }


    fun showAlertDialog(activity: Activity?, msg: String?) {
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_layout)
        val text = dialog.findViewById<View>(R.id.text_dialog) as TextView
        text.text = msg
        val dialogButton: Button = dialog.findViewById<View>(R.id.btn_dialog) as Button
        dialogButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    fun getDialog(): Dialog = Dialog(fragmentActivity)

    fun getDialogFullScreen(): Dialog = Dialog(fragmentActivity, R.style.Dialog_FullScreen)
}

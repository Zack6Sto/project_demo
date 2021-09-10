package com.example.project_demo.view.contact

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.project_demo.R
import com.example.project_demo.databinding.ActivityContactBinding
import com.example.project_demo.view.base.BaseActivity
import com.example.project_demo.vo.enumClass.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactActivity : BaseActivity() {

    private lateinit var binding: ActivityContactBinding

    private val viewModel: ContactViewModel by viewModel()

    private var linkFaceBook = ""

    private var linkWeb = ""

    private var linkLineAdd = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initViewModel()
    }


    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact)
    }

    private fun initViewModel() {
        binding.dataViewModel = viewModel

        initOnClick()
        onSubScriptContacts()
    }

    private fun onSubScriptContacts() {
        viewModel.getContactUsCall.value = null
        viewModel.mResponseContactsUs.observe(this, Observer {
            binding.loadResource = it
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.data?.let {
                        it.forEachIndexed { index, dataContacts ->
                            when (dataContacts.type) {
                                "link" -> {
                                    linkWeb = dataContacts.link
                                }
                                "facebook" -> {
                                    linkFaceBook = dataContacts.link
                                }
                                "line" -> {
                                    linkLineAdd = dataContacts.link
                                }
                            }
                        }
                    }
                }
                Status.ERROR -> mDialogPresenter.dialogMessage(
                    resources.getString(R.string.message_alert_dialog),
                    it.message
                ) {}
            }
        })
    }

    private fun initOnClick() {
        viewModel.mOnClickListener.observe(this, Observer {
            when (it) {
                "faceBook" -> {
                    if (isCheckLinkUrl(linkFaceBook))
                        mUtils.getFacebookPageURL(linkFaceBook)
                    else
                        dialogInvalidUrl()
                }
                "line" -> {
                    if (isCheckLinkUrl(linkLineAdd))
                        mUtils.openAppLine(linkLineAdd)
                    else
                        dialogInvalidUrl()
                }
            }
        })

        toolbarViewModel.onClickToolbar.observe(this, Observer {
            when (it) {
                "intentBack" -> {
                    this.onBackPressed()
                }
            }
        })
    }

    private fun dialogInvalidUrl(){
        mDialogPresenter.dialogMessage(resources.getString(R.string.message_alert_dialog),"Invalid url"){
        }
    }

    private fun isCheckLinkUrl(link:String):Boolean{
        return link.isNotEmpty()
    }

    override fun onBackPressed() {
        startIntentAnimation(false)
        finish()
    }

}
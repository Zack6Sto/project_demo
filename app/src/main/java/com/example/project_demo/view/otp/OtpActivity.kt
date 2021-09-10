package com.example.project_demo.view.otp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.project_demo.R
import com.example.project_demo.databinding.ActivityOtpBinding
import com.example.project_demo.view.base.BaseActivity
import com.example.project_demo.view.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class OtpActivity : BaseActivity() {

    private val viewModel : OtpViewModel by viewModel()

    private lateinit var binding : ActivityOtpBinding
    private var mCountDownTimer: CountDownTimer? = null
    private var mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_otp)
        hideKeyboardWhenTouch()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setTimerCountDown(binding.tvCountdown,binding.txtCountdown,binding.txtMinutes)
        binding.tvPhone.text = viewModel.setPhone().toString()


//        mobile.length
//        val phone = mobile.toCharArray()
//        mobile = "" + phone[0] + phone[1] + phone[2] + "-XXX-X" + phone[7] + phone[8] + phone[9]
//        binding.tvPhone.text = mobile
    }


    private fun initViewModel() {
        binding.dataViewModel = viewModel
        onSubscriptOnClick()
    }

    private fun onSubscriptOnClick() {
        var intentApp:Intent
        viewModel.mOnClickListener.observe(this, Observer {
            when(it){
                "resend" ->{
                        mCountDownTimer?.start()
                        binding.tvRef.text = getRandomNumberString()
                        binding.tvResend.setTextColor(resources.getColor(R.color.dark_gray2))
                        binding.tvResend.isEnabled = false
                        binding.etPinEntry.text!!.clear()

                        mHandler.postDelayed(Runnable {
                            binding.tvResend.setTextColor(resources.getColor(R.color.dark_blue))
                            binding.tvResend.isEnabled = true
                        }, 15000)
                        binding.tvCountdown.visibility = View.VISIBLE
                        binding.txtMinutes.visibility = View.VISIBLE
                        binding.txtCountdown.text = "Your code will expire in "
                        binding.txtCountdown.setTextColor(resources.getColor(R.color.dark_gray))
                }
                "cancel" ->{
                    intentApp = Intent(this,LoginActivity::class.java)
                    startActivity(intentApp)
                }
                "ok" ->{
                    Toast.makeText(this,binding.etPinEntry.text,Toast.LENGTH_SHORT).show()
                    hideKeyboard()
                }
            }
        })

    }

    private fun setTimerCountDown(tvCountDown: TextView, txtCountDown: TextView, txtMinutes: TextView) {
        mCountDownTimer = object : CountDownTimer((60 * 300).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var seconds = (millisUntilFinished / 1000).toInt()
                val minutes = seconds / 60
                seconds %= 60
                val timeCountDownDelay =
                    String.format("%02d", minutes) + ":" + String.format("%02d", seconds)
                tvCountDown.text = timeCountDownDelay
            }

            override fun onFinish() {
                tvCountDown.visibility = View.GONE
                txtMinutes.visibility = View.GONE
                txtCountDown.text = "OTP has expire, Please Resend again."
                txtCountDown.setTextColor(resources.getColor(R.color.red))
            }
        }.start()
    }

}
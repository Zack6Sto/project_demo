package com.example.project_demo.view.calculator

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.project_demo.R
import com.example.project_demo.databinding.ActivityCalculatorBinding
import net.objecthunter.exp4j.ExpressionBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalculatorActivity : AppCompatActivity() {

    private val viewModel : CalculatorViewModel by viewModel()

    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_calculator)

        initView()
        initViewModel()
    }

    private fun initViewModel() {
        binding.dataViewModel = viewModel
    }

    private fun initView() {

        //Number listeners
        binding.btn00.setOnClickListener { appendOnClick(true, "00") }
        binding.btn0.setOnClickListener { appendOnClick(true, "0") }
        binding.btn1.setOnClickListener { appendOnClick(true, "1") }
        binding.btn2.setOnClickListener { appendOnClick(true, "2") }
        binding.btn3.setOnClickListener { appendOnClick(true, "3") }
        binding.btn4.setOnClickListener { appendOnClick(true, "4") }
        binding.btn5.setOnClickListener { appendOnClick(true, "5") }
        binding.btn6.setOnClickListener { appendOnClick(true, "6") }
        binding.btn7.setOnClickListener { appendOnClick(true, "7") }
        binding.btn8.setOnClickListener { appendOnClick(true, "8") }
        binding.btn9.setOnClickListener { appendOnClick(true, "9") }
        binding.btnDot.setOnClickListener { appendOnClick(true, ".") }

        //Operator Listeners
        binding.btnPlus.setOnClickListener { appendOnClick(false, "+") }
        binding.btnMinus.setOnClickListener { appendOnClick(false, "-") }
        binding.btnMultiply.setOnClickListener { appendOnClick(false, "*") }
        binding.btnDivide.setOnClickListener { appendOnClick(false, "/") }
        binding.btnLeftB.setOnClickListener { appendOnClick(false, "(") }
        binding.btnRightB.setOnClickListener { appendOnClick(false, ")") }

        initOnClickListener()
    }

    private fun initOnClickListener() {
        viewModel.mOnClickListener.observe(this, Observer {
            when(it){
                "clear" ->{
                    clear()
                    Log.e("mOnClickListener","clear")
                }

                "equal" ->{
                    calculate()
                    Log.e("mOnClickListener","equal")
                }
            }
        })

    }

    //now create methods
    private fun appendOnClick(clear: Boolean, string: String) {

        if (clear) {
            binding.tvOutput.text = ""
            binding.tvInput.append(string)
        } else {
            binding.tvInput.append(binding.tvOutput.text)
            binding.tvInput.append(string)
            binding.tvOutput.text = ""
        }
    }

    private fun clear() {
        binding.tvInput.text = ""
        binding.tvOutput.text = ""
    }

    private fun calculate() {

        try {
            val input = ExpressionBuilder(binding.tvInput.text.toString()).build()
            val output = input.evaluate()
            val longOutput = output.toLong()
            if (output == longOutput.toDouble()){
                binding.tvOutput.text = longOutput.toString()
            }else{
                binding.tvOutput.text = output.toString()
            }

        }catch (e:Exception){
            Toast.makeText(this,e.message, Toast.LENGTH_LONG).show()
        }
    }

}
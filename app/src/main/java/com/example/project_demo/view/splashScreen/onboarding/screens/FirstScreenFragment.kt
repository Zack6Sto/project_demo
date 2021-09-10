package com.example.project_demo.view.splashScreen.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.project_demo.R
import com.example.project_demo.databinding.FragmentFirstScreenBinding
import com.example.project_demo.databinding.FragmentViewPagerBinding


class FirstScreenFragment : Fragment() {

    private lateinit var binding: FragmentFirstScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_first_screen, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first_screen, container, false)
        return binding.root

        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.next.setOnClickListener {
            viewPager?.currentItem = 1
        }

        return view
    }


}
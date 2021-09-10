package com.example.project_demo.view.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.project_demo.R
import com.example.project_demo.databinding.ActivityMainBinding
import com.example.project_demo.view.base.BaseActivity
import com.example.project_demo.view.main.navHome.HomeFragment
import com.example.project_demo.view.main.navSetting.SettingFragment
import com.example.project_demo.view.main.navUsers.UsersFragment
import com.example.project_demo.vo.models.MenuModel
import java.util.*

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    var settingFragment: SettingFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initView()
        initViewBottomNav()
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun initViewBottomNav() {
        val homeFragment = HomeFragment()
        val usersFragment = UsersFragment()
        val settingFragment = SettingFragment()

        makeCurrentFragment(homeFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_user -> makeCurrentFragment(usersFragment)
                R.id.ic_setting -> makeCurrentFragment(settingFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
    }

//    fun favouriteMenuChanged(menuModelList: ArrayList<MenuModel?>?) {
//        settingFragment.addFavouriteMenu(menuModelList)
//    }
}
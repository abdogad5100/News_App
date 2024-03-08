package com.example.banana_news.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.banana_news.R
import com.example.banana_news.ui.category.CategoryFragment
import com.example.banana_news.ui.settings.SettingsFragment as SettingsFragment

class HomeActivity : AppCompatActivity() {
    lateinit var menuIcon : ImageView
    lateinit var drawerLayout: DrawerLayout
    lateinit var categoryTv : TextView
    lateinit var settingsTv : TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()

    }

    private fun initView() {
        menuIcon = findViewById(R.id.ic_menu)
        drawerLayout =findViewById(R.id.drawer_layout)
        categoryTv = findViewById(R.id.category_tv)
        settingsTv = findViewById(R.id.settings_tv)


        menuIcon.setOnClickListener{
            drawerLayout.open()
        }

        categoryTv.setOnClickListener{
            pushFragment(CategoryFragment())
            drawerLayout.close()
        }

        settingsTv.setOnClickListener{
            pushFragment(SettingsFragment())
            drawerLayout.close()

        }
        pushFragment(CategoryFragment())


    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
    }


}
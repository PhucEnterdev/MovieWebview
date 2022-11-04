package vn.com.enterdev.moviewebview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import vn.com.enterdev.moviewebview.R
import vn.com.enterdev.moviewebview.adapter.ViewPagerFragmentAdapter
import vn.com.enterdev.moviewebview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val fragmentAdapter: ViewPagerFragmentAdapter = ViewPagerFragmentAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPagerMain.adapter = fragmentAdapter
        onChangeViewPager()
        onClickBottomNav()
    }

    private fun onClickBottomNav() {
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> binding.viewPagerMain.currentItem = 0
                R.id.menu_favorite -> binding.viewPagerMain.currentItem = 1
                R.id.menu_history -> binding.viewPagerMain.currentItem = 2
            }
            true
        }
    }

    private fun onChangeViewPager() {
        binding.viewPagerMain.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.bottomNav.menu.findItem(R.id.menu_home).isChecked = true
                    1 -> binding.bottomNav.menu.findItem(R.id.menu_favorite).isChecked = true
                    2 -> binding.bottomNav.menu.findItem(R.id.menu_history).isChecked = true
                }
            }
        })
    }
}
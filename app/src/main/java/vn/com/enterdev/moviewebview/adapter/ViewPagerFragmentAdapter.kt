package vn.com.enterdev.moviewebview.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import vn.com.enterdev.moviewebview.fragments.FavoriteFragment
import vn.com.enterdev.moviewebview.fragments.HistoryFragment
import vn.com.enterdev.moviewebview.fragments.HomeFragment


class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> FavoriteFragment()
            2 -> HistoryFragment()
            else -> HomeFragment()
        }
    }
}
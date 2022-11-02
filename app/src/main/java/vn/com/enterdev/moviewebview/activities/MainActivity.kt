package vn.com.enterdev.moviewebview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import vn.com.enterdev.moviewebview.R
import vn.com.enterdev.moviewebview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
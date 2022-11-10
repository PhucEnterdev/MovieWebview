package vn.com.enterdev.moviewebview.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import vn.com.enterdev.moviewebview.databinding.ActivityWebViewBinding
import vn.com.enterdev.moviewebview.utils.Constants

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra(Constants.KEY_URL_MOVIE)

        // block not allow to run by chrome
        binding.webViewMovie.webViewClient = WebViewClient()
        binding.webViewMovie.settings.builtInZoomControls = true
        binding.webViewMovie.settings.allowContentAccess = true
        binding.webViewMovie.apply {
            loadUrl(url!!)
            settings.javaScriptEnabled = true
        }
    }
}
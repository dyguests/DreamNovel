package com.fanhl.dreamnovel.ui.guide

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.fanhl.dreamnovel.R
import com.fanhl.dreamnovel.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Example of a call to a native method
        sample_text.text = stringFromJNI()

        Handler().postDelayed({
            MainActivity.launch(this@WelcomeActivity)
        }, 2000)
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}

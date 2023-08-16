package com.project.myapplication

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun pxToDp(px: Int): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }

    private var checked = false

    fun setViewElevations() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)

        val barElevation = findViewById<BottomNavigationView>(R.id.navigation_content).elevation

        findViewById<ImageView>(R.id.bt_main_background).elevation = barElevation
        findViewById<ImageButton>(R.id.bt_main).elevation = barElevation + 1

        val overlay = findViewById<View>(R.id.overlay)
        overlay.elevation = barElevation + 1

        // findViewById<View>(R.id.action_panel_bottom_padding).elevation = barElevation

        findViewById<BottomNavigationView>(R.id.navigation_content).setOnNavigationItemSelectedListener {
            it.actionView?.bringToFront()
            it.actionView?.translationZ = 100f
            return@setOnNavigationItemSelectedListener true
        }

        findViewById<ImageButton>(R.id.bt_main).setOnClickListener {
            it as ImageButton
            checked = !checked
            if (checked) {
                // TODO: open bottomSheet here
                it.setImageResource(R.drawable.button_circle_close)

                // findViewById<View>(R.id.action_panel_bottom_padding).setBackgroundColor(Color.parseColor("#6Effffff"))

                //   findViewById<View>(R.id.action_panel_bottom_padding).elevation = barElevation + 1

                overlay.visibility = View.VISIBLE
                // it.bringToFront()
            } else {
                // TODO: close bottomSheet here
                it.setImageResource(R.drawable.button_circle_open)
                // findViewById<ImageButton>(R.id.bt_main).elevation--
                // findViewById<View>(R.id.action_panel_bottom_padding).setBackgroundColor(Color.parseColor("#ffffff"))
                // findViewById<View>(R.id.action_panel_bottom_padding).elevation = barElevation + 1
                overlay.visibility = View.GONE
            }
        }
        val displayMetrics: DisplayMetrics = this.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        Log.d("SCREENSSS", "screenWidth: $screenWidth screenHeight: $screenHeight")
        widthInInches()
    }

    override fun onResume() {
        super.onResume()

        Log.d("MainActivityxxx", "density: " + Resources.getSystem().displayMetrics.density)
        Log.d("MainActivityxxx", "bt elevation: " + findViewById<ImageButton>(R.id.bt_main).elevation.toString())
        Log.d(
            "MainActivityxxx",
            "bar elevation: " + findViewById<BottomNavigationView>(R.id.navigation_content).elevation.toString()
        )
        Log.d("MainActivityxxx", "overlay elevation: " + findViewById<View>(R.id.overlay).elevation.toString())
    }

    private fun widthInInches() : Float {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        // Screen width in pixels
        val widthPixels = displayMetrics.widthPixels

        // Screen density (scale factor)
        val densityDpi = displayMetrics.densityDpi.toFloat()

        // Calculate screen width in inches
        return widthPixels / densityDpi
    }
}
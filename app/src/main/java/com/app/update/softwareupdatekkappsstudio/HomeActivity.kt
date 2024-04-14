package com.app.update.softwareupdatekkappsstudio

import android.Manifest
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.app.update.softwareupdatekkappsstudio.fragments.AndroidUpdateFragment
import com.app.update.softwareupdatekkappsstudio.fragments.AppInstalledFragment
import com.app.update.softwareupdatekkappsstudio.fragments.AppsUninstallFragment
import com.app.update.softwareupdatekkappsstudio.fragments.DeviceInfoFragment
import com.app.update.softwareupdatekkappsstudio.fragments.ExitDialogFragment
import com.app.update.softwareupdatekkappsstudio.fragments.ExitDialogFragment.ExitDialogListener
import com.app.update.softwareupdatekkappsstudio.fragments.WifiFragment
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.ktx.BuildConfig
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission


class HomeActivity : AppCompatActivity(), ExitDialogListener {
    private var exitDialogFragment: ExitDialogFragment? = null
    private var bottomNavigationView: BottomNavigationView? = null
    private var drawerLayout: DrawerLayout? = null
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var navigationView: NavigationView? = null
    private var loadingDialog: Dialog? = null

    private var productIds: ArrayList<String> = ArrayList()
    private var subscriptionIds: ArrayList<String> = ArrayList()


    var btn_inapp: ImageView? = null
    var admobbannercontainer: LinearLayout? = null
    var adAdShow = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_try)
        loadingDialog = showDialog()
        onBackPressedDispatcher.addCallback(
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    exitDialogFragment?.show(supportFragmentManager, exitDialogFragment?.tag)
                }
            }
        )
        subscriptionIds.add("sub_weekly")//not using
        subscriptionIds.add("sub_month")//not using
        subscriptionIds.add("sub_year")//not using
        productIds.add(if (BuildConfig.DEBUG) "android.test.purchased" else "ads_free")//

        admobbannercontainer = findViewById(R.id.topBannerForHome)
        exitDialogFragment = ExitDialogFragment()

        btn_inapp = findViewById(R.id.btn_inapp)
        drawerLayout = findViewById(R.id.drawer_layout)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout?.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        navigationView = findViewById(R.id.navigation_view)
        setupDrawerContent(navigationView)
        bottomNavigationView = findViewById(R.id.bottomNavView)
        bottomNavigationView?.setOnNavigationItemSelectedListener(navListener)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, AppInstalledFragment()).commit()

        // Check if we have the required permission, if not request it.
        val settingsButton = findViewById<ImageView>(R.id.settingsbtn)
        settingsButton.setOnClickListener { v: View? ->
            if (drawerLayout != null) {
                drawerLayout?.openDrawer(GravityCompat.START)
            }
        }
        if (Constants.isRemovedAd) {
            btn_inapp?.visibility = View.GONE
        } else {
            btn_inapp?.visibility = View.VISIBLE
        }
        btn_inapp?.setOnClickListener { v: View? ->

        }
    }

    private fun permissionAccess(permissionListener: PermissionListener?) {
        TedPermission.create()
            .setRationaleTitle("Permission")
            .setRationaleMessage("Allow Wifi state")
            .setDeniedMessage("If you reject permission, you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
            .setPermissions(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .setPermissionListener(permissionListener)
            .check()
    }

    var selectedFragment: Fragment? = null
    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->

            try {
                when (item.itemId) {
                    R.id.nav_app -> {
                        selectedFragment = AppInstalledFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, selectedFragment!!).commit()
                    }

                    R.id.nav_device_info -> {
                        selectedFragment = DeviceInfoFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, selectedFragment!!).commit()
                    }

                    R.id.nav_android -> {
                        selectedFragment = AndroidUpdateFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, selectedFragment!!).commit()
                    }

                    R.id.nav_updates -> {
                        selectedFragment = AppsUninstallFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, selectedFragment!!).commit()
                    }

                    R.id.nav_wifi_details -> {
                        permissionAccess(object : PermissionListener {
                            override fun onPermissionGranted() {
                                // Permission granted, you can proceed with your logic here

                                try {
                                    selectedFragment = WifiFragment()
                                    supportFragmentManager.beginTransaction()
                                        .replace(R.id.fragmentContainer, selectedFragment!!)
                                        .commit()
                                } catch (e: java.lang.IllegalStateException) {
                                    e.printStackTrace()
                                }
                            }

                            override fun onPermissionDenied(deniedPermissions: List<String>) {
                                Toast.makeText(
                                    this@HomeActivity,
                                    "Permission Denied",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    }
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }


            true
        }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(
            item
        )
    }

    private fun setupDrawerContent(navigationView: NavigationView?) {
        navigationView?.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }
    }

    private fun selectDrawerItem(menuItem: MenuItem) {
        // Handle navigation menu item clicks here
        if (menuItem.itemId == R.id.menu_share_app) {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_SUBJECT, "App Update")

            // Create a shareable link to your app on the Google Play Store
            val playStoreLink = "https://play.google.com/store/apps/details?id=$packageName"
            share.putExtra(Intent.EXTRA_TEXT, playStoreLink)
            share.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(Intent.createChooser(share, "Share with"))
        } else if (menuItem.itemId == R.id.menu_rate_us) {
            showRateUsDialog()
        } else if (menuItem.itemId == R.id.menu_more_apps) {
            // Handle More Apps click
//                Toast.makeText(this, "More Apps clicked", Toast.LENGTH_SHORT).show();
            try {
                val uris =
                    Uri.parse("https://play.google.com/store/apps/developer?id=KK+Apps+Studio")
                startActivity(Intent(Intent.ACTION_VIEW, uris))
            } catch (anfe: ActivityNotFoundException) {
                Toast.makeText(
                    applicationContext,
                    "Google Play Store is not available.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else if (menuItem.itemId == R.id.menu_feedback) {
            // Handle Feedback click
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("videodownloader0786@gmail.com"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for App")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Write your feedback here:")
            try {
                startActivity(Intent.createChooser(emailIntent, "Send email using..."))
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    this@HomeActivity,
                    "There are no email clients installed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else if (menuItem.itemId == R.id.menu_privacy_policy) {
            // Handle Privacy Policy click
            startActivity(
                Intent(
                    "android.intent.action.VIEW",
                    Uri.parse("https://unseenmessage.blogspot.com/2023/09/app-updater-privacy-policy.html")
                )
            )
        }

        // Close the drawer
        drawerLayout!!.closeDrawers()
    }

    private fun showRateUsDialog() {
        // Create a custom dialog for rating
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_rate_us)
        dialog.window!!.setBackgroundDrawableResource(R.drawable.white_rounded_background)

        // Find views in the dialog layout
        val titleTextView = dialog.findViewById<TextView>(R.id.textViewTitle)
        val btnCancel = dialog.findViewById<Button>(R.id.btnCancel)
        val btnRateNow = dialog.findViewById<Button>(R.id.btnRateNow)
        val ratingBar = dialog.findViewById<AppCompatRatingBar>(R.id.ratingBar)

        // Set dialog title
        titleTextView.text = "How do you rate our app?"

        // Set onCancel listener to dismiss the dialog
        btnCancel.setOnClickListener { dialog.dismiss() }

        // Set onClick listener for Rate Now button
        btnRateNow.setOnClickListener { // Handle Rate Now click
            val rating = ratingBar.rating.toInt()

            val uri = Uri.parse("market://details?id=" + getPackageName())
            val rateIntent = Intent(Intent.ACTION_VIEW, uri)
            try {
                startActivity(rateIntent)
            } catch (e: ActivityNotFoundException) {
            }
            dialog.dismiss()
        }

        // Show the dialog
        dialog.show()
    }

    override fun onDestroy() {
        if (loadingDialog?.isShowing == true) {
            loadingDialog?.dismiss()
        }
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onExitConfirmed() {
        // Handle the exit confirmation (e.g., close the app)
        finishAffinity()
    }

    private fun showDialog(): Dialog {
        val dialog = Dialog(this)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.loading_ad)
        return dialog
    }

    companion object {
        private const val TAG = "purchase"
    }


}
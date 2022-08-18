package com.karam.easymvi.helpers.components

import android.Manifest
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.mohre.smartinspectionapp.R
import com.karam.easymvi.core.apiSettings.Const

open class ParentActivity  : AppCompatActivity() {
    // used to hold connection handlers that should be cancelled when destroyed
    var activity: AppCompatActivity? = null
    var rootView: FrameLayout? = null
    var toolbar: Toolbar? = null
    var menuId = 0
    var enableBack = false
    private var iconResId = 0
    val toolbarTitle: String? = null
    lateinit var progressHUD: SweetAlertDialog

    // -------------------- Camera Methods --------------------
    fun isPermissionsAllowed(): Boolean {
        var isGranted = true

        // prepare permissions granting result
        val writePermGranted =
            (checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED)
        val cameraPermGranted = (checkCallingOrSelfPermission(Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED)
        val fineLocationPermGranted =
            (checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)
        val coarseLocationPermGranted =
            (checkCallingOrSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)

        // check the permissions
        if (!writePermGranted && !cameraPermGranted && !fineLocationPermGranted && !coarseLocationPermGranted) {
            isGranted = false
            // request permissions from user
            val requiredPermissions: Array<String> = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
            ActivityCompat.requestPermissions(
                activity!!,
                requiredPermissions,
                Const.REQ_CAMERA_PERMISSIONS
            )
        }
        return isGranted
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        activity = this
//        rootView = findViewById(R.id.content)
        progressHUD = SweetAlertDialog(this)
    }

    fun showProgressDialog(label: String?) {
        progressHUD = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        progressHUD.confirmText = getString(R.string.ok)
        progressHUD.progressHelper.barColor = ContextCompat.getColor(this, R.color.primary)
        progressHUD.titleText = label
        progressHUD.setCancelable(false)
        progressHUD.show()
    }

    fun hideProgressDialog() {
        progressHUD.dismiss()
    }

    fun hideProgressDialog(dialog: SweetAlertDialog?, duration: Long) {
        if (dialog != null) {
            after(duration, dialog::dismissWithAnimation)
        }
    }

    fun getResColor(id: Int): Int {
        return ContextCompat.getColor(this, id)
    }

    fun loadFragment(container: Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(container, fragment)
            .commitAllowingStateLoss()
    }

    fun createOptionsMenu(menuId: Int) {
        this.menuId = menuId
        invalidateOptionsMenu()
    }

    fun removeOptionsMenu() {
        menuId = 0
        invalidateOptionsMenu()
    }

    fun enableBackButton() {
        enableBack = true
    }

    fun setToolbarIcon(iconResId: Int) {
        this.iconResId = iconResId
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (menuId != 0) {
            menuInflater.inflate(menuId, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return if (item.itemId == R.id.home && enableBack) {
//            onBackPressed()
//            true
//        } else {
//            super.onOptionsItemSelected(item)
//        }
//    }

    fun hasToolbar(): Boolean {
        return toolbar != null
    }

    companion object Run {
        fun after(delay: Long, process: () -> Unit) {
            Handler(Looper.getMainLooper()).postDelayed({
                process()
            }, delay)
        }
    }

}
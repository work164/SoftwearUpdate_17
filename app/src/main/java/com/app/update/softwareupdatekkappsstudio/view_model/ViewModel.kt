package com.app.update.softwareupdatekkappsstudio.view_model

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.update.softwareupdatekkappsstudio.model.AppModel
import java.io.File

class ViewModelclass(application: Application) : AndroidViewModel(application) {
    val infoList1 = MutableLiveData<ArrayList<AppModel>>()

    init {
        application.contentResolver
    }

    fun getInto(
        context: Activity,
        infoList: MutableList<ApplicationInfo>
    ): ArrayList<AppModel> {
        val List: ArrayList<AppModel> = ArrayList()
        val runable = Runnable {
            lateinit var model: AppModel

            for (info in infoList) {
                val packageName = info.packageName
                val appName = info.loadLabel(context.packageManager).toString();
                val file = File(info.sourceDir)
                val sizeInByte = file.length() // size in Byte
                // Log.e("sizeInByte",sizeInByte.toString())
                val size = (sizeInByte / 1024) / 1024
                // Log.e("size",size.toString())
                val versionName = context.packageManager.getPackageInfo(packageName, 0).versionName
                //  Log.d("TAG", versionName)

                val drawableIcon: Drawable = info.loadIcon(context.packageManager)
                model = AppModel(
                    icon = drawableIcon,
                    appName = appName,
                    appSize = "size: $size MB",
                    versionName = "version: $versionName ",
                    packageName = packageName
                )

                List.add(model)

            }
            context.runOnUiThread {
                infoList1.value = List

            }
        }
        val objBgThread = Thread(runable)
        objBgThread.start()
        return List
    }
}





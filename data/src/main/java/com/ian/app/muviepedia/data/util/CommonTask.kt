package com.ian.app.muviepedia.data.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.ian.app.muviepedia.data.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

/**
 *
Created by Ian Damping on 04/10/2019.
Github = https://github.com/iandamping
 */

fun FragmentActivity.saveImage(scope: CoroutineScope, views: View, imageUrl: String?) {
    scope.launch {
        if (imageUrl != null) {
            try {
                withContext(Dispatchers.IO) {
                    val bitmap = Glide.with(this@saveImage)
                            .asBitmap()
                            .load(imageUrl)
                            .submit(512, 512)
                            .get()
                    if (bitmap != null) {
                        saveImage(views, bitmap)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}

private fun saveImage(views: View, bitmap: Bitmap?) {
    val saveImagePath = "movieImage" + System.currentTimeMillis() + ".jpeg"
    val pictureDirectory = Environment.getExternalStorageDirectory()
    val imageFile = File(pictureDirectory, saveImagePath)
    val quality = 100
    try {
        val outputStream = FileOutputStream(imageFile)
        bitmap?.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        outputStream.flush()
        outputStream.close()
        openImageFromSnackbar(views, imageFile)
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    voidCustomMediaScannerConnection(views.context, saveImagePath)
}

private fun openImageFromSnackbar(views: View, imageFile: File) {
    val snackbar = Snackbar
            .make(views, "Image saved to gallery!", Snackbar.LENGTH_LONG)
            .setAction("OPEN") {
                val i = Intent(Intent.ACTION_VIEW)
                i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                val uri = FileProvider.getUriForFile(views.context, views.context.resources.getString(R.string.package_name), imageFile)
                i.setDataAndType(uri, "image/*")
                views.context?.startActivity(i)
            }
    snackbar.show()
}

fun voidCustomMediaScannerConnection(ctx: Context?, paths: String?) {
    if (paths != null) {
        val directory = Environment.getExternalStorageDirectory()
        val passingFile = File(directory, paths)
        MediaScannerConnection.scanFile(ctx, arrayOf(passingFile.toString()), null) { path, uri ->
            Log.i("ExternalStorage", "Scanned $path:")
            Log.i("ExternalStorage", "-> uri=$uri")
        }
    }

}
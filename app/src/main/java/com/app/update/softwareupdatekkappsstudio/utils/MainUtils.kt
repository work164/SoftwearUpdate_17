package com.app.update.softwareupdatekkappsstudio.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navigateFragment(currentId: Int, destinationActionId: Int) {
    if (findNavController().currentDestination?.id == currentId && isAdded) {
        findNavController().navigate(destinationActionId)
    }
}

fun Fragment.navigateFragment(currentId: Int, destinationActionId: Int, bundle: Bundle) {
    if (findNavController().currentDestination?.id == currentId && isAdded) {
        findNavController().navigate(destinationActionId, bundle)
    }
}

fun hasFragmentNotVisibleAndAdded(fragment: DialogFragment, onAction: () -> Unit) {
    if (!fragment.isVisible && !fragment.isAdded) {
        onAction()
    }
}

const val KB = 1024
const val MB = 1024 * 1024
const val GB = 1024 * 1024 * 1024


fun Long.toSizeString(): String {
    return when (this) {
        in 0 until KB -> String.format("%d B", this)
        in KB until MB -> String.format("%.2f KB", this.toDouble() / KB)
        in MB until GB -> String.format("%.2f MB", this.toDouble() / MB)
        in GB until Long.MAX_VALUE -> String.format("%.2f GB", this.toDouble() / GB)
        else -> ""
    }
}

fun shareAppURL(context: Context) {
    try {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "https://play.google.com/store/apps/details?id=" + context.packageName
        )
        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun rateUs(context: Context) {
    try {
        val rateUs = Intent(Intent.ACTION_VIEW)
        rateUs.data =
            Uri.parse("https://play.google.com/store/apps/details?id=" + context.packageName)
        context.startActivity(rateUs)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun openUrl(context: Context, url: String) {
    try {
        val shareIntent = Intent(Intent.ACTION_VIEW)
        shareIntent.data = Uri.parse(url)
        context.startActivity(shareIntent)
    } catch (e: Exception) {
        Toast.makeText(context, "No browser found", Toast.LENGTH_SHORT).show()
        e.printStackTrace()

    }
}

fun feedbackOnEmail(context: Context, email: String, subject: String) {
    try {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:$email")
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        context.startActivity(emailIntent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

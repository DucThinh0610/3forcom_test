package com.threeforcom.testexam.base

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.view.Window
import android.view.WindowManager
import com.threeforcom.testexam.R


class DialogProgress internal constructor(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window?.setBackgroundDrawable(ColorDrawable(context.resources.getColor(R.color.transparent)))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window?.statusBarColor = ContextCompat.getColor(context, R.color.transparent)
        }
        setContentView(R.layout.dialog_progress_bar)
        setCancelable(false)
    }
}

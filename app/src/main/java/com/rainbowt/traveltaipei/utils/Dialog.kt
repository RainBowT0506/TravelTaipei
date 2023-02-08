package com.rainbowt.traveltaipei.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rainbowt.traveltaipei.Constants.LANG


fun Context.showLangSelectionDialog(onClick: (String) -> Unit) {
    val builder = MaterialAlertDialogBuilder(this)
    builder.setTitle("Select a language")
    builder.setItems(LANG, DialogInterface.OnClickListener { dialog, langIndex ->
        onClick.invoke(LANG[langIndex])
    })

    val alert: AlertDialog = builder.create()
    alert.show()
}
package org.fcitx.fcitx5.android.utils

import android.content.Context
import android.content.Intent
import org.fcitx.fcitx5.android.ui.main.MainActivity

object AppUtil {
    fun launchMain(context: Context) {
        context.startActivity(
            Intent(
                context,
                MainActivity::class.java
            ).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })
    }

    fun launchMainToAddInputMethods(context: Context) {
        context.startActivity(Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(MainActivity.INTENT_DATA_ADD_IM, 0)
        })
    }

}
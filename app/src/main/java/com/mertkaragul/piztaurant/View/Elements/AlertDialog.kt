package com.mertkaragul.piztaurant.View.Elements

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizTAlertDialog(
    title : String,
    description : String,
    confirm : @Composable () -> Unit,
    dismiss : @Composable () -> Unit,
    dismissReq : () -> Unit,
) {
    AlertDialog(
        title = {
            Text(text = title)
        },

        text = {
            Text(text = description)
        },
        confirmButton = confirm,
        onDismissRequest = dismissReq,
        dismissButton = dismiss
    )
}
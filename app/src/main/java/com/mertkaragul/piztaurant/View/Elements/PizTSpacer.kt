package com.mertkaragul.piztaurant.View.Elements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

private const val DefaultSpacerVariables = 0.005

@Composable
fun PizTDefaultSpacerHeight() {
    val height = LocalConfiguration.current.screenHeightDp
    Spacer(modifier = Modifier.height((height * DefaultSpacerVariables).dp))
}

@Composable
fun PizTDefaultSpacerWidth() {
    val width = LocalConfiguration.current.screenWidthDp
    Spacer(modifier = Modifier.width((width * DefaultSpacerVariables).dp))
}

@Composable
fun PizTCustomSpacerHeight(size : Double = DefaultSpacerVariables) {
    val height = LocalConfiguration.current.screenHeightDp
    Spacer(modifier = Modifier.height((height * size).dp))
}

@Composable
fun PizTCustomSpacerWidth(size : Double = DefaultSpacerVariables) {
    val width = LocalConfiguration.current.screenWidthDp
    Spacer(modifier = Modifier.width((width * size).dp))
}
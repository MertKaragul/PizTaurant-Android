package com.mertkaragul.piztaurant.Model.BottomNav

import androidx.compose.ui.graphics.vector.ImageVector
import com.mertkaragul.piztaurant.Enum.ERoute

data class BottomNavModel(
    val id : Int,
    val name : String,
    val icon : ImageVector,
    val navigate : String
)

package com.mertkaragul.piztaurant.View.NavigationBottom

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.mertkaragul.piztaurant.Model.BottomNav.BottomNavModel


@Composable
fun NavigationBottom(items : List<BottomNavModel>, rememberNavController: NavController) {
    var enabledButton by remember { mutableIntStateOf(items.first().id)  }
    BottomAppBar {
        items.forEach {
            NavigationBarItem(
                selected = it.id == enabledButton,
                onClick = {
                    if (enabledButton != it.id) enabledButton = it.id
                    rememberNavController.navigate(it.navigate)
                },
                label = {
                   Text(text = it.name)
                },
                icon = {
                    Icon(
                       it.icon,
                       contentDescription = ""
                    )
                },

            )
        }
    }
}

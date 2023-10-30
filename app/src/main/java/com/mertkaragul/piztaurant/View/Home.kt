package com.mertkaragul.piztaurant.View

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mertkaragul.piztaurant.Enum.ERoute
import com.mertkaragul.piztaurant.R
import com.mertkaragul.piztaurant.Viewmodel.HomeViewModel
import com.mertkaragul.piztaurant.ui.theme.PizTaurantTheme

@Composable
fun Home(
    controller : NavHostController,
    homeViewModel: HomeViewModel = viewModel()
) {
    val width = LocalConfiguration.current.screenWidthDp

    val user by homeViewModel.user.observeAsState()
    if(user != null){
        controller.popBackStack()
        controller.navigate(ERoute.PIZZA_MENU_PAGE.toString())
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painterResource(id = R.drawable.home_page_background),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .border(0.dp, Color.Transparent)
                .blur(3.dp),
            contentDescription = "",
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "PizTaurant'a hoşgeldiniz ",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 25.sp
            )

            Text(
                text = "Lezzetli pizzalar burada",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 15.sp
            )

            if (user == null){
                Button(
                    onClick = {
                        controller.navigate(ERoute.REGISTER_PAGE.toString())
                    },
                    modifier = Modifier.width((width * .8).dp)
                ){
                    Text(text = "Devam et")
                }
            }else{

                Text(
                    text = "Az sonra yönlendirileceksiniz.",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 15.sp
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    PizTaurantTheme {
        Home(controller = rememberNavController())
    }
}

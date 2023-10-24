package com.mertkaragul.piztaurant.View.Elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertkaragul.piztaurant.ui.theme.PizTaurantTheme

@Composable
fun PizTDiscountText(
    price : Long,
    discountedPrice : Long,
    color : Color = MaterialTheme.colorScheme.primary
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "${discountedPrice}₺",
            color = color,
        )
        Text(
            text = "${price}₺",
            color = Color.Red,
            textDecoration = TextDecoration.LineThrough,
            fontSize = 10.sp,
            modifier = Modifier.padding(2.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPizTDiscountText() {
    PizTaurantTheme {
        PizTDiscountText(100, 90)
    }
}
package com.example.androiddevchallenge.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.App
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.CatBean

@Composable
fun CatDetailScreen(
    cat: CatBean,
    onBackPressed: () -> Unit
) {
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(300.dp)
        ) {
            val imageIdentity = App.context.resources.getIdentifier(
                cat.avatar, "drawable",
                App.context.packageName
            )
            val image: Painter = painterResource(imageIdentity)

            Image(
                painter = image,
                contentDescription = cat.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(300.dp),
                contentScale = ContentScale.Crop
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "back",
                    modifier = Modifier
                        .requiredSize(50.dp, 50.dp)
                        .padding(10.dp)
                        .clickable { onBackPressed() },
                )

                Text(
                    text = cat.name,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Text(
            text = cat.desc,
            modifier = Modifier.padding(10.dp),
            color = Color.Gray,
            fontSize = 20.sp
        )
    }
}
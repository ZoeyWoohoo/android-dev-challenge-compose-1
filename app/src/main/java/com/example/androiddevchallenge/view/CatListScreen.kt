package com.example.androiddevchallenge.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.App
import com.example.androiddevchallenge.model.CatBean
import com.example.androiddevchallenge.vm.CatListViewModel

@Composable
fun CatListScreen(
    catListViewModel: CatListViewModel,
    onClick: (cat: CatBean) -> Unit,
) {
    val cats: List<CatBean> by catListViewModel.cats.observeAsState(emptyList())
    Column(modifier = Modifier.fillMaxHeight()) {
        Log.d("TAG", "CatListScreen: cats -> $cats")
        CatList(cats, onClick = onClick)
    }
}

@Composable
fun CatList(
    cats: List<CatBean>,
    modifier: Modifier = Modifier,
    onClick: (cat: CatBean) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items = cats) { cat ->
            Card(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .requiredHeight(160.dp)
                    .clickable { onClick(cat) }
            ) {
                CardItem(avatar = cat.avatar, name = cat.name)
            }
        }
    }
}

@Composable
fun CardItem(avatar: String, name: String) {
    val imageIdentity = App.context.resources.getIdentifier(
        avatar, "drawable",
        App.context.packageName
    )
    val image: Painter = painterResource(imageIdentity)

    Row {
        Image(
            painter = image,
            contentDescription = name,
            modifier = Modifier
                .requiredSize(160.dp, 160.dp)
                .clip(shape = RoundedCornerShape(8.dp, 0.dp, 0.dp, 8.dp)),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(name, fontSize = 20.sp, color = Color.Black)
            Text("Help Wanted!!!",
                modifier = Modifier
                    .padding(top = 16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Red
            )
        }
    }
}
package com.example.image_card_state_ex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.image_card_state_ex.ui.theme.Image_card_state_exTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isFavorite by rememberSaveable {
                mutableStateOf(false)
            }
            ImageCard(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .fillMaxHeight(0.5f)
                .padding(16.dp),
                isFavorite = isFavorite){
                favorite -> isFavorite = favorite
            }
        }
    }
}

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onTabFavorite:(Boolean)-> Unit,) {
    // value를 사용하지 않기 위해 by
    // by 사용하려면 import androidx.compose.runtime.setValue, getValue import해야함
    // var isFavorite by rememberSaveable {
    //     mutableStateOf(false)
    // }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
    ){
        Box(modifier = Modifier.height(300.dp),
        ){
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.slamdunk),
                contentDescription = "Slamdunk",
                contentScale = ContentScale.Crop,
            )
        }
        Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopEnd,
        ){
            IconButton(onClick = {
                onTabFavorite.invoke(!isFavorite)
            }) {
                Icon(imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "favorite",
                    tint = Color.White)
            }
        }
    }
}
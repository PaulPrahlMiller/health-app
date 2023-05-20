package com.hkr.health.ui.screens.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.hkr.health.R
import com.hkr.health.ui.components.Heading

@Composable
fun Home(){
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        item { HomeTitle() }
        item { HomeDescription() }
        item { VideoList() }
    }
}

@Composable
fun HomeTitle(){
    Heading(
        text = stringResource(R.string.mental_health_title),
    )
}

@Composable
fun HomeDescription(){
    Text(
        text = stringResource(R.string.mental_health_description),
        color = Color.DarkGray,
        lineHeight = 32.sp,
        modifier = Modifier
            .padding(bottom = 20.dp)
    )
}

@Composable
fun VideoList(){
    Column {
        for (video in videos) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(video.uri))
            val context = LocalContext.current
            VideoListItem(video.title, context, intent)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun VideoListItem(title: String, context: Context, intent: Intent){
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(
                vertical = 8.dp,
                horizontal = 5.dp
            )
            .clickable(
                enabled = true,
                onClick = {
                    startActivity(context, intent, null)
                }
            )
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}


data class VideoData(
    val title: String,
    val uri: String
)

val videos = listOf(
    VideoData (
        "10 tips to maintain your mental health",
        "https://www.youtube.com/watch?v=-OAjfrhuwRk&t=5s"
    ),
    VideoData(
        "Understanding our sleep cycle: REM and non-REM sleep",
        "https://www.youtube.com/watch?v=98V1q5k8x5E&t=1s"
    ),
    VideoData(
        "How the food you eat affects your brain",
        "https://www.youtube.com/watch?v=xyQY8a-ng6g"
    ),
    VideoData(
        "We all have mental health",
        "https://www.youtube.com/watch?v=DxIDKZHW3-E"
    ),
    VideoData(
        "Alcohol and mental health",
        "https://www.youtube.com/watch?v=hzcZd08PqSQ"
    )
)



















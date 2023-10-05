package com.teegarcs.amifunny.android.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.teegarcs.amifunny.android.MyApplicationTheme


@Composable
fun LoaderWithMessage(message: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Text(text = message)
    }
}

@Composable
fun Loader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoaderWithMessage() {
    MyApplicationTheme {
        LoaderWithMessage("30:00")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoaderNoMessage() {
    MyApplicationTheme {
        Loader()
    }
}
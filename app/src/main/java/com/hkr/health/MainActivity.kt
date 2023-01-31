package com.hkr.health

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hkr.health.ui.theme.HealthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val classifier = MobileBertClassifier(this).init()

        setContent {
            HealthTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val owner = LocalViewModelStoreOwner.current

                    owner?.let {
                        val viewModel: HealthViewModel = viewModel(
                            it,
                            "HealthViewModel",
                            HealthViewModelFactory(
                                LocalContext.current.applicationContext as Application,
                                classifier
                            )
                        )
                        App(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun App(
    healthViewModel: HealthViewModel = viewModel()
) {
    HealthNavGraph(healthViewModel = healthViewModel)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App()
}
package com.example.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import com.example.weatherapp.presentation.screens.MainScreenCombinedView
import com.example.weatherapp.presentation.viewmodel.MainScreenVM
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ShowMe()
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBarWithMenu() {
        var expanded by remember { mutableStateOf(false) }

        TopAppBar(
            modifier = Modifier.statusBarsPadding(),
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            ),
            title = { Text(text = stringResource(R.string.app_name)) },
            actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }

                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Settings") },
                        onClick = {
                            expanded = false
                            // Handle Settings click
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Logout") },
                        onClick = {
                            expanded = false
                            // Handle Logout click
                        }
                    )
                }
            }
        )
    }

    @OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
    @Composable
    @Preview
    fun ShowMe() {
        WeatherAppTheme {
            Scaffold(
                topBar = {
                    TopBarWithMenu()
                },
            ) { innerPadding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    color = Color.Transparent
                ) {
                    val mainScreenVM = hiltViewModel<MainScreenVM>()
                    MainScreenCombinedView(mainScreenVM)
                }
            }
        }
    }
}

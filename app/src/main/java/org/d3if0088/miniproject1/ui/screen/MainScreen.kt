package org.d3if0088.miniproject1.ui.screen

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0088.miniproject1.R
import org.d3if0088.miniproject1.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.about),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}
@Composable
fun ScreenContent(modifier: Modifier) {
    var temperature by rememberSaveable { mutableStateOf("") }
    var temperatureError by rememberSaveable { mutableStateOf(false) }

    var selectedConversion by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_intro),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = temperature,
            onValueChange = {
                temperature = it
                temperatureError = false // Reset error when user types
            },
            label = { Text(text = stringResource(id = R.string.label), style = MaterialTheme.typography.bodySmall) },
            isError = temperatureError,
            trailingIcon = { IconPicker(temperatureError, "°C") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.padding(top = 6.dp)
        ) {
            RadioButton(
                selected = selectedConversion == "Fahrenheit",
                onClick = { selectedConversion = "Fahrenheit" },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
            Text(text = "Fahrenheit", modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp))

            RadioButton(
                selected = selectedConversion == "Kelvin",
                onClick = { selectedConversion = "Kelvin" },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
            Text(text = "Kelvin", modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp))
        }

        Button(
            onClick = {
                temperatureError = (temperature == "" || temperature == "0" || temperature.toFloatOrNull() == null)
                if (temperatureError) return@Button

                result = if (selectedConversion == "Fahrenheit") {
                    "${(temperature.toFloat() * 9/5 + 32)} °F"
                } else {
                    "${(temperature.toFloat() + 273.15f)} K"
                }
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.convert))
        }

        if (result.isNotEmpty()) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = result,
                style = MaterialTheme.typography.titleLarge
            )
        }
            Button(
                onClick = {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, result)
                    }
                    context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.share)))
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.share))
            }
        }
    }

@Composable
fun IconPicker(isError: Boolean, unit: String){
    if (isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else{
        Text(text = unit)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(rememberNavController())
}

package org.d3if0088.miniproject1.ui.screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import org.d3if0088.miniproject1.R
import org.d3if0088.miniproject1.model.Comic

@Composable
fun DeleteDialog(comic: Comic, onDismissRequest: () -> Unit, onConfirmation: (String) -> Unit, id: String) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = {
            Text(text = "Want to delete this ${comic.title} item ?")
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirmation(id)
            }) {
                Text(text = stringResource(id = R.string.delete))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = stringResource(id = R.string.decline))
            }
        }
    )
}
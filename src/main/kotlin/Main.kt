import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import de.joelneumann.ActionExtension
import org.pf4j.DefaultPluginManager
import java.nio.file.Paths

@Composable
@Preview
fun App(
    performActions: (Int) -> Unit
) {
    var inputValue by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Input field for integer
        IntNumberInputField(
            initialValue = inputValue,
            onValueChange = { value ->
                inputValue = value
            })

        Spacer(modifier = Modifier.height(8.dp))

        // Button to perform action
        Button(
            onClick = { performActions(inputValue) }
        ) {
            Text("Submit")
        }
    }
}

fun main() = application {
    val pluginManager = DefaultPluginManager(Paths.get("./plugins"))
    pluginManager.loadPlugins()
    pluginManager.startPlugins()

    val actions = pluginManager.getExtensions(ActionExtension::class.java)

    val performActions: (Int) -> Unit = { int ->
        actions.forEach { it.performAction(int) }
    }


    val onClose = {
        pluginManager.stopPlugins()
        exitApplication()
    }

    Window(onCloseRequest = onClose) {
        App(performActions)
    }
}

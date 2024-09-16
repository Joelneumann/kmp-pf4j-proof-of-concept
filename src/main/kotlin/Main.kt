import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import de.joelneumann.GreetingExtension
import org.pf4j.DefaultPluginManager
import java.nio.file.Paths

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Button(onClick = {
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }
    }
}

fun main() = application {
    val pluginManager = DefaultPluginManager(Paths.get("./plugins"))
    pluginManager.loadPlugins()
    pluginManager.startPlugins()

    val extensions = pluginManager.getExtensions(GreetingExtension::class.java)
    extensions.forEach { ext ->
        println(ext.greet("Kotlin Developer"))
    }


    val onClose = {
        pluginManager.stopPlugins()
        exitApplication()
    }

    Window(onCloseRequest = onClose) {
        App()
    }
}

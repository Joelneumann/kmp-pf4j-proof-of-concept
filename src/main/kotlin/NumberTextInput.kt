import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

typealias Validator<T> = (String) -> Boolean
typealias Converter<T> = (String) -> T?

@Composable
fun <T> GeneralNumberInputField(
    initialValue: T,
    label: String,
    validator: Validator<T>,
    converter: Converter<T>,
    onValueChange: (T) -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue(initialValue.toString())) }
    var isValid by remember { mutableStateOf(true) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                isValid = validator(it.text)
                if (isValid) {
                    converter(it.text)?.let(onValueChange)
                }
            },
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            isError = !isValid,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )
        if (!isValid) {
            Text(
                text = "Please enter a valid $label",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

fun validateByte(input: String): Boolean {
    val number = input.toIntOrNull()
    return number != null && number in 0..255
}

fun validateInt(input: String): Boolean {
    return input.toIntOrNull() != null
}

fun validateFloat(input: String): Boolean {
    return input.toFloatOrNull() != null
}

fun convertToInt(input: String): Int? {
    return input.toIntOrNull()
}

fun convertToFloat(input: String): Float? {
    return input.toFloatOrNull()
}

@Composable
fun ByteNumberInputField(initialValue: Int, onValueChange: (Int) -> Unit) {
    GeneralNumberInputField(
        initialValue,
        label = "Enter a number (0-255)",
        validator = ::validateByte,
        converter = ::convertToInt,
        onValueChange = onValueChange
    )
}

@Composable
fun IntNumberInputField(initialValue: Int, onValueChange: (Int) -> Unit) {
    GeneralNumberInputField(
        initialValue,
        label = "Enter an integer",
        validator = ::validateInt,
        converter = ::convertToInt,
        onValueChange = onValueChange
    )
}

@Composable
fun FloatNumberInputField(initialValue: Float, onValueChange: (Float) -> Unit) {
    GeneralNumberInputField(
        initialValue,
        label = "Enter a float",
        validator = ::validateFloat,
        converter = ::convertToFloat,
        onValueChange = onValueChange
    )
}

package id.bytezilla.hellojetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.bytezilla.hellojetpackcompose.ui.theme.HelloJetpackComposeTheme

private val sampleName = listOf(
    "Andre",
    "Desta",
    "Parto",
    "Rizki",
    "Komeng",
    "Raffi Ahmad",
    "Andhika Pratama",
    "Vincent Ryan Rompies"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloJetpackComposeTheme {
                HelloJetpackComposeApp()
            }
        }
    }
}

@Composable
fun HelloJetpackComposeApp() {
    Box(
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing
        )
    ) {
        GreetingList(sampleName)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun HelloJetpackComposeAppPreview() {
    HelloJetpackComposeTheme {
        HelloJetpackComposeApp()
    }
}

@Composable
fun GreetingList(names: List<String>) {
    if (names.isNotEmpty()) {
        LazyColumn {
            items(names) { name ->
                Greeting(name)
            }
        }
    } else {
        Box(contentAlignment = Alignment.Center) {
            Text("No people to greet :(")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    var isExpanded by remember { mutableStateOf(false) }
    val animatedSizeDp by animateDpAsState(
        targetValue = if (isExpanded) 120.dp else 80.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Row (
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Image(
            painter = painterResource(id = R.drawable.jetpack_compose),
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier.size(animatedSizeDp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column (modifier = Modifier.weight(1f)){
            Text(
                text = "Hello $name!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Welcome to Dicoding!")
        }
        IconButton(onClick = { isExpanded = !isExpanded }) {
            Icon(
                imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Outlined.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Show less" else "Show more"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloJetpackComposeTheme {
        Greeting("Android")
    }
}
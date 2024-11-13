package trpl.nim234311040.re_collection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import trpl.nim234311040.re_collection.ui.theme.RecollectionTheme

val Event.durationOfEvent: String
    get() = if (this.durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecollectionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EventSum(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun EventSum(modifier: Modifier = Modifier) {
    val events = listOf(
        Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0),
        Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15),
        Event(title = "Play some Minesweeper", description = "If you lose, you lose!", daypart = Daypart.MORNING, durationInMinutes = 30),
        Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30),
        Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60),
        Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10),
        Event(title = "Watch New Monster Hunter Video", description = "Damn those monsters are wild!",daypart = Daypart.AFTERNOON, durationInMinutes = 30),
        Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45),
        Event(title = "Go to sleep", description = "Sjleep!", daypart = Daypart.EVENING, durationInMinutes = 45)
    )

    val shortEvents = events.filter { it.durationInMinutes < 60 }

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "You have ${shortEvents.size} short events.\n")

        val groupedEvents = events.groupBy { it.daypart }
        groupedEvents.forEach { (daypart, events) ->
            Text(text = "$daypart: ${events.size} events")
            Text(text = "Duration of first event of the day: ${events[0].durationOfEvent}")
            Text(text = "Last event of the day: ${events.last().title}\n")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    RecollectionTheme {
        EventSum()
    }
}

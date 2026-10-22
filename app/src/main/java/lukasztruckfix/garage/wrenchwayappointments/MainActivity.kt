package lukasztruckfix.garage.wrenchwayappointments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import lukasztruckfix.garage.wrenchwayappointments.ui.composable.approot.AppRoot
import lukasztruckfix.garage.wrenchwayappointments.ui.theme.AJTHAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AJTHAppTheme {
                AppRoot()
            }
        }
    }
}
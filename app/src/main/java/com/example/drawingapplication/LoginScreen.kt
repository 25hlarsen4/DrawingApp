import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.drawingapplication.MainActivity
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen() {
    val context = LocalContext.current
    val navController = (context as MainActivity).navController

    val auth = FirebaseAuth.getInstance()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var isLogin by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (isLogin) {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                message = "Login successful!"
                                navController.navigate("drawingList")

                            } else {
                                message = "Login failed: ${task.exception?.message}"
                            }
                        }
                } else {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                message = "Sign-up successful!"
                                navController.navigate("drawingList")
                            } else {
                                message = "Sign-up failed: ${task.exception?.message}"
                            }
                        }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isLogin) "Login" else "Sign Up")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = {
                isLogin = !isLogin
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isLogin) "Don't have an account? Sign Up" else "Already have an account? Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message)
    }
}

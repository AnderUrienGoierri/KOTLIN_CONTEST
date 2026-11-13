package org.goierri.lecturesync.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val KotlinPurple = Color(0xFF7F52FF)
val DarkBackground = Color(0xFF181A1F)
val DarkSurface = Color(0xFF21252B)

@Composable
fun LectureSyncMobileApp() {
    var isRecording by remember { mutableStateOf(false) }
    var recordingSeconds by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = DarkBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "LectureSync Mobile",
                    color = Color.White,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "KMP Live Classroom Streamer",
                    color = Color(0xFF9DA5B4),
                    fontSize = 13.sp
                )
            }

            // Central Recording Widget
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurface)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = if (isRecording) "RECORDING IN PROGRESS" else "STANDBY",
                        color = if (isRecording) Color(0xFFE06C75) else Color(0xFF9DA5B4),
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = String.format("%02d:%02d", recordingSeconds / 60, recordingSeconds % 60),
                        color = Color.White,
                        fontSize = 44.sp,
                        style = MaterialTheme.typography.displayMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Sync Target: Desktop JavaFX (Ktor WebSocket)",
                        color = Color(0xFF61AFEF),
                        fontSize = 12.sp
                    )
                }
            }

            // Action Buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { isRecording = !isRecording },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isRecording) Color(0xFFE06C75) else KotlinPurple
                    )
                ) {
                    Text(
                        text = if (isRecording) "Stop & Send to AI" else "Start Classroom Recording",
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }

                OutlinedButton(
                    onClick = { /* Capture whiteboard */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Capture Whiteboard Photo",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

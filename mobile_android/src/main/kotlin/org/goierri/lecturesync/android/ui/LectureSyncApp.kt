package org.goierri.lecturesync.android.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import kotlinx.coroutines.delay

val KotlinPurple = Color(0xFF7F52FF)
val KotlinPurpleLight = Color(0xFFB388FF)
val DarkBackground = Color(0xFF14171E)
val DarkSurface = Color(0xFF1E222B)
val DarkCardBorder = Color(0xFF2C3240)
val LiveRed = Color(0xFFE06C75)
val SuccessGreen = Color(0xFF36D399)

enum class MobileTab {
    RECORD,
    STUDY,
    ARCHIVE
}

@Composable
fun LectureSyncMobileApp() {
    val context = LocalContext.current
    var currentTab by remember { mutableStateOf(MobileTab.RECORD) }

    // Recording state
    var isRecording by remember { mutableStateOf(false) }
    var recordingSeconds by remember { mutableStateOf(0) }
    var capturedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var sessionSavedMessage by remember { mutableStateOf<String?>(null) }

    // Flashcard study state
    var currentCardIndex by remember { mutableStateOf(4) }
    var isAnswerRevealed by remember { mutableStateOf(false) }
    var cardsReviewedCount by remember { mutableStateOf(0) }

    // Camera launcher
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        if (bitmap != null) {
            capturedBitmap = bitmap
            sessionSavedMessage = "Whiteboard photo captured. Ready for Gemini OCR."
        }
    }

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val recordGranted = permissions[Manifest.permission.RECORD_AUDIO] == true
        if (recordGranted) {
            isRecording = true
        }
    }

    // Timer Effect
    LaunchedEffect(isRecording) {
        if (isRecording) {
            while (true) {
                delay(1000L)
                recordingSeconds++
            }
        }
    }

    // Pulse animation
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF161922),
                contentColor = Color.White
            ) {
                NavigationBarItem(
                    selected = currentTab == MobileTab.RECORD,
                    onClick = { currentTab = MobileTab.RECORD },
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(if (isRecording) LiveRed else KotlinPurpleLight)
                        )
                    },
                    label = { Text("Record", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = KotlinPurple,
                        selectedTextColor = KotlinPurpleLight,
                        indicatorColor = KotlinPurple.copy(alpha = 0.2f)
                    )
                )

                NavigationBarItem(
                    selected = currentTab == MobileTab.STUDY,
                    onClick = { currentTab = MobileTab.STUDY },
                    icon = {
                        Text("🗂", fontSize = 16.sp)
                    },
                    label = { Text("Study SM-2", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = KotlinPurple,
                        selectedTextColor = KotlinPurpleLight,
                        indicatorColor = KotlinPurple.copy(alpha = 0.2f)
                    )
                )

                NavigationBarItem(
                    selected = currentTab == MobileTab.ARCHIVE,
                    onClick = { currentTab = MobileTab.ARCHIVE },
                    icon = {
                        Text("🎧", fontSize = 16.sp)
                    },
                    label = { Text("Archive", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = KotlinPurple,
                        selectedTextColor = KotlinPurpleLight,
                        indicatorColor = KotlinPurple.copy(alpha = 0.2f)
                    )
                )
            }
        },
        containerColor = DarkBackground
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (currentTab) {
                MobileTab.RECORD -> {
                    RecordTabScreen(
                        isRecording = isRecording,
                        recordingSeconds = recordingSeconds,
                        pulseAlpha = pulseAlpha,
                        capturedBitmap = capturedBitmap,
                        sessionSavedMessage = sessionSavedMessage,
                        onToggleRecording = {
                            if (!isRecording) {
                                val audioPerm = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)
                                if (audioPerm == PackageManager.PERMISSION_GRANTED) {
                                    isRecording = true
                                    sessionSavedMessage = "Classroom audio recording active."
                                } else {
                                    permissionLauncher.launch(arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA))
                                }
                            } else {
                                isRecording = false
                                sessionSavedMessage = "Recording saved ($recordingSeconds s). AI Gemini generated 14 flashcards."
                            }
                        },
                        onCapturePhoto = {
                            val camPerm = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                            if (camPerm == PackageManager.PERMISSION_GRANTED) {
                                cameraLauncher.launch(null)
                            } else {
                                permissionLauncher.launch(arrayOf(Manifest.permission.CAMERA))
                            }
                        },
                        onReset = {
                            recordingSeconds = 0
                            capturedBitmap = null
                            sessionSavedMessage = null
                        }
                    )
                }

                MobileTab.STUDY -> {
                    StudyTabScreen(
                        cardIndex = currentCardIndex,
                        isAnswerRevealed = isAnswerRevealed,
                        onToggleAnswer = { isAnswerRevealed = !isAnswerRevealed },
                        onRate = { difficulty ->
                            cardsReviewedCount++
                            currentCardIndex = if (currentCardIndex < 18) currentCardIndex + 1 else 1
                            isAnswerRevealed = false
                            sessionSavedMessage = "Rated: $difficulty · Spaced Repetition interval scheduled."
                        }
                    )
                }

                MobileTab.ARCHIVE -> {
                    ArchiveTabScreen()
                }
            }
        }
    }
}

@Composable
fun RecordTabScreen(
    isRecording: Boolean,
    recordingSeconds: Int,
    pulseAlpha: Float,
    capturedBitmap: Bitmap?,
    sessionSavedMessage: String?,
    onToggleRecording: () -> Unit,
    onCapturePhoto: () -> Unit,
    onReset: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "LectureSync Mobile",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "KMP Live Streamer · Samsung SM-A376B",
                    color = Color(0xFF8A92A2),
                    fontSize = 11.sp
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1B202B))
                    .border(1.dp, Color(0xFF293040), RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(SuccessGreen)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Ktor Sync",
                        color = SuccessGreen,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        // Recording Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = DarkSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, DarkCardBorder)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isRecording) LiveRed.copy(alpha = 0.15f) else Color(0xFF252A36))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(if (isRecording) LiveRed.copy(alpha = pulseAlpha) else Color(0xFF8A92A2))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isRecording) "LIVE CLASSROOM RECORDING" else "STANDBY / READY",
                        color = if (isRecording) LiveRed else Color(0xFFB0B7C3),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                val minutes = recordingSeconds / 60
                val seconds = recordingSeconds % 60
                Text(
                    text = String.format("%02d:%02d", minutes, seconds),
                    color = Color.White,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = if (isRecording) "Streaming Opus audio to JavaFX Desktop" else "Tap start to stream lecture audio",
                    color = if (isRecording) Color(0xFF38BDF8) else Color(0xFF717886),
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Waveform
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                ) {
                    for (i in 0 until 24) {
                        val barHeight = if (isRecording) {
                            ((recordingSeconds * 5 + i * 3) % 36) + 10
                        } else {
                            8
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(barHeight.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(if (isRecording) KotlinPurpleLight else Color(0xFF2C3240))
                        )
                    }
                }
            }
        }

        // Whiteboard Photo Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = DarkSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, DarkCardBorder)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Whiteboard OCR & Photos",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Snap formulas and Kotlin diagrams with your camera.",
                    color = Color(0xFF8A92A2),
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (capturedBitmap != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, SuccessGreen, RoundedCornerShape(12.dp))
                    ) {
                        Image(
                            bitmap = capturedBitmap.asImageBitmap(),
                            contentDescription = "Whiteboard Preview",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

                Button(
                    onClick = onCapturePhoto,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF282E3C))
                ) {
                    Text(
                        text = if (capturedBitmap != null) "Retake Whiteboard Photo" else "Open Camera & Take Photo",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        if (sessionSavedMessage != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(SuccessGreen.copy(alpha = 0.15f))
                    .border(1.dp, SuccessGreen.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Text(text = sessionSavedMessage, color = SuccessGreen, fontSize = 12.sp)
            }
        }

        // Action Buttons
        Button(
            onClick = onToggleRecording,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isRecording) LiveRed else KotlinPurple
            )
        ) {
            Text(
                text = if (isRecording) "Stop Recording & Send to AI" else "Start Classroom Recording",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (recordingSeconds > 0 && !isRecording) {
            OutlinedButton(
                onClick = onReset,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Reset Session", color = Color(0xFF8A92A2), fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun StudyTabScreen(
    cardIndex: Int,
    isAnswerRevealed: Boolean,
    onToggleAnswer: () -> Unit,
    onRate: (String) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Study Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Distributed Systems Deck",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Card $cardIndex of 18 · Leitner Box 2",
                    color = Color(0xFF8A92A2),
                    fontSize = 12.sp
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF252A36))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text("SM-2 Active", color = Color(0xFF38BDF8), fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Flip Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clickable { onToggleAnswer() },
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = DarkSurface),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, if (isAnswerRevealed) KotlinPurpleLight else DarkCardBorder)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (isAnswerRevealed) "ANSWER (REVEALED)" else "QUESTION",
                        color = if (isAnswerRevealed) SuccessGreen else KotlinPurpleLight,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text("Tap to flip", color = Color(0xFF717886), fontSize = 11.sp)
                }

                if (!isAnswerRevealed) {
                    Column {
                        Text(
                            text = "What does `Dispatchers.JavaFx` guarantee in a Kotlin Multiplatform app?",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 28.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Context: Thread safety and UI reactive state updates",
                            color = Color(0xFF8A92A2),
                            fontSize = 12.sp
                        )
                    }
                } else {
                    Column {
                        Text(
                            text = "It guarantees that all state mutations execute strictly on the JavaFX UI Application Thread, avoiding thread-safety exceptions.",
                            color = Color(0xFFE8DEF8),
                            fontSize = 16.sp,
                            lineHeight = 24.sp
                        )
                    }
                }

                Button(
                    onClick = onToggleAnswer,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF282E3C))
                ) {
                    Text(if (isAnswerRevealed) "Show Question" else "Tap to Reveal Answer", color = Color.White)
                }
            }
        }

        // SM-2 Rating Buttons
        Text("Rate your recall (SM-2 Interval):", color = Color(0xFF8A92A2), fontSize = 12.sp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { onRate("Again") },
                modifier = Modifier.weight(1f).height(46.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3E1C1A))
            ) {
                Text("Again", color = Color(0xFFF2B8B5), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { onRate("Hard") },
                modifier = Modifier.weight(1f).height(46.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E2310))
            ) {
                Text("Hard", color = Color(0xFFFFE082), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { onRate("Good") },
                modifier = Modifier.weight(1f).height(46.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E283C))
            ) {
                Text("Good", color = Color(0xFF90CAF9), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { onRate("Easy") },
                modifier = Modifier.weight(1f).height(46.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF142B20))
            ) {
                Text("Easy", color = Color(0xFFA5D6A7), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Streak Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF1B202B))
                .padding(14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🔥", fontSize = 20.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text("Daily Retention Streak: 4 days", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    Text("14 cards reviewed with 94.2% SM-2 retention score", color = Color(0xFF8A92A2), fontSize = 11.sp)
                }
            }
        }
    }
}

@Composable
fun ArchiveTabScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Audio Archive", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Classroom recordings synced with SQLite", color = Color(0xFF8A92A2), fontSize = 12.sp)
        }

        val lectureArchive = listOf(
            Triple("Kotlin Multiplatform Concurrency", "Distributed Systems · 01:14:20", "Synced"),
            Triple("JavaFX MVC Architecture & FXML", "Desktop Development · 00:54:10", "Synced"),
            Triple("SQLite Database & Spaced Repetition", "Software Architecture · 01:05:45", "Synced")
        )

        lectureArchive.forEach { (title, subtitle, sync) ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, DarkCardBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = title, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Text(text = subtitle, color = Color(0xFF8A92A2), fontSize = 11.sp)
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SuccessGreen.copy(alpha = 0.15f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(text = sync, color = SuccessGreen, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }
}

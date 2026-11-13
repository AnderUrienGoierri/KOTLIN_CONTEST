package org.goierri.lecturesync.data.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.goierri.lecturesync.domain.models.Flashcard
import org.goierri.lecturesync.domain.models.Lecture
import org.goierri.lecturesync.domain.models.TranscriptSegment

class LectureApiClient(
    private val baseUrl: String = "http://localhost:8080/api/v1",
    private val httpClient: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }
) {
    suspend fun getLectures(): List<Lecture> {
        return httpClient.get("$baseUrl/lectures").body()
    }

    suspend fun createLecture(lecture: Lecture): Lecture {
        return httpClient.post("$baseUrl/lectures") {
            contentType(ContentType.Application.Json)
            setBody(lecture)
        }.body()
    }

    suspend fun addTranscript(lectureId: Long, segment: TranscriptSegment): TranscriptSegment {
        return httpClient.post("$baseUrl/lectures/$lectureId/transcript") {
            contentType(ContentType.Application.Json)
            setBody(segment)
        }.body()
    }

    suspend fun generateFlashcards(lectureId: Long): List<Flashcard> {
        return httpClient.post("$baseUrl/lectures/$lectureId/ai/generate-flashcards").body()
    }
}

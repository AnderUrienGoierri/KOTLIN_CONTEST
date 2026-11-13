package org.goierri.lecturesync.backend.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.goierri.lecturesync.backend.repository.LectureRepository
import org.goierri.lecturesync.backend.service.GeminiService
import org.goierri.lecturesync.domain.models.Lecture
import org.goierri.lecturesync.domain.models.TranscriptSegment

fun Route.lectureRoutes(repository: LectureRepository, geminiService: GeminiService) {
    route("/api/v1/lectures") {
        get {
            val list = repository.getAllLectures()
            call.respond(list)
        }

        post {
            val lecture = call.receive<Lecture>()
            val created = repository.insertLecture(lecture)
            call.respond(HttpStatusCode.Created, created)
        }

        post("/{id}/transcript") {
            val segment = call.receive<TranscriptSegment>()
            val created = repository.addTranscriptSegment(segment)
            call.respond(HttpStatusCode.Created, created)
        }

        get("/{id}/flashcards") {
            val id = call.parameters["id"]?.toLongOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val cards = repository.getFlashcards(id)
            call.respond(cards)
        }

        post("/{id}/ai/generate-flashcards") {
            val id = call.parameters["id"]?.toLongOrNull() ?: return@post call.respond(HttpStatusCode.BadRequest)
            val generated = geminiService.generateFlashcardsFromTranscript(id, "StateFlow Concurrency Kotlin Multiplatform")
            repository.insertFlashcards(generated)
            call.respond(HttpStatusCode.OK, generated)
        }
    }
}

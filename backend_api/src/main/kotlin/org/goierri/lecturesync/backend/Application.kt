package org.goierri.lecturesync.backend

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.goierri.lecturesync.backend.database.DatabaseFactory
import org.goierri.lecturesync.backend.repository.LectureRepository
import org.goierri.lecturesync.backend.routes.lectureRoutes
import org.goierri.lecturesync.backend.service.GeminiService

fun main() {
    DatabaseFactory.init()
    val repository = LectureRepository()
    val geminiService = GeminiService()

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(CORS) {
            anyHost()
            allowHeader(io.ktor.http.HttpHeaders.ContentType)
        }
        routing {
            lectureRoutes(repository, geminiService)
        }
    }.start(wait = true)
}

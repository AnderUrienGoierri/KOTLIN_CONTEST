package org.goierri.lecturesync.backend.repository

import org.goierri.lecturesync.backend.database.DatabaseFactory
import org.goierri.lecturesync.domain.models.Flashcard
import org.goierri.lecturesync.domain.models.Lecture
import org.goierri.lecturesync.domain.models.LectureStatus
import org.goierri.lecturesync.domain.models.TranscriptSegment

class LectureRepository {

    fun getAllLectures(): List<Lecture> {
        val list = mutableListOf<Lecture>()
        DatabaseFactory.getConnection().use { conn ->
            val sql = """
                SELECT l.id, l.title, l.subject, l.lecturer, l.created_at, l.duration_seconds, 
                       l.audio_file_path, l.status, COUNT(f.id) as flashcard_count
                FROM lectures l
                LEFT JOIN flashcards f ON l.id = f.lecture_id
                GROUP BY l.id
                ORDER BY l.id DESC
            """.trimIndent()
            conn.createStatement().use { stmt ->
                val rs = stmt.executeQuery(sql)
                while (rs.next()) {
                    list.add(
                        Lecture(
                            id = rs.getLong("id"),
                            title = rs.getString("title"),
                            subject = rs.getString("subject"),
                            lecturer = rs.getString("lecturer"),
                            createdAt = rs.getString("created_at"),
                            durationSeconds = rs.getLong("duration_seconds"),
                            audioFilePath = rs.getString("audio_file_path"),
                            status = runCatching { LectureStatus.valueOf(rs.getString("status")) }.getOrDefault(LectureStatus.COMPLETED),
                            flashcardCount = rs.getInt("flashcard_count")
                        )
                    )
                }
            }
        }
        return list
    }

    fun insertLecture(lecture: Lecture): Lecture {
        DatabaseFactory.getConnection().use { conn ->
            val sql = "INSERT INTO lectures (title, subject, lecturer, status) VALUES (?, ?, ?, ?)"
            conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS).use { stmt ->
                stmt.setString(1, lecture.title)
                stmt.setString(2, lecture.subject)
                stmt.setString(3, lecture.lecturer)
                stmt.setString(4, lecture.status.name)
                stmt.executeUpdate()
                val keys = stmt.generatedKeys
                if (keys.next()) {
                    return lecture.copy(id = keys.getLong(1))
                }
            }
        }
        return lecture
    }

    fun addTranscriptSegment(segment: TranscriptSegment): TranscriptSegment {
        DatabaseFactory.getConnection().use { conn ->
            val sql = "INSERT INTO transcript_segments (lecture_id, timestamp_start_ms, timestamp_end_ms, speaker, text_content, is_key_point) VALUES (?, ?, ?, ?, ?, ?)"
            conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS).use { stmt ->
                stmt.setLong(1, segment.lectureId)
                stmt.setLong(2, segment.timestampStartMs)
                stmt.setLong(3, segment.timestampEndMs)
                stmt.setString(4, segment.speaker)
                stmt.setString(5, segment.textContent)
                stmt.setInt(6, if (segment.isKeyPoint) 1 else 0)
                stmt.executeUpdate()
                val keys = stmt.generatedKeys
                if (keys.next()) {
                    return segment.copy(id = keys.getLong(1))
                }
            }
        }
        return segment
    }

    fun getFlashcards(lectureId: Long): List<Flashcard> {
        val list = mutableListOf<Flashcard>()
        DatabaseFactory.getConnection().use { conn ->
            val sql = "SELECT * FROM flashcards WHERE lecture_id = ?"
            conn.prepareStatement(sql).use { stmt ->
                stmt.setLong(1, lectureId)
                val rs = stmt.executeQuery()
                while (rs.next()) {
                    list.add(
                        Flashcard(
                            id = rs.getLong("id"),
                            lectureId = rs.getLong("lecture_id"),
                            question = rs.getString("question"),
                            answer = rs.getString("answer"),
                            boxLevel = rs.getInt("box_level"),
                            nextReviewDate = rs.getString("next_review_date"),
                            easeFactor = rs.getDouble("ease_factor")
                        )
                    )
                }
            }
        }
        return list
    }

    fun insertFlashcards(flashcards: List<Flashcard>) {
        DatabaseFactory.getConnection().use { conn ->
            val sql = "INSERT INTO flashcards (lecture_id, question, answer, box_level) VALUES (?, ?, ?, ?)"
            conn.prepareStatement(sql).use { stmt ->
                for (card in flashcards) {
                    stmt.setLong(1, card.lectureId)
                    stmt.setString(2, card.question)
                    stmt.setString(3, card.answer)
                    stmt.setInt(4, card.boxLevel)
                    stmt.addBatch()
                }
                stmt.executeBatch()
            }
        }
    }
}

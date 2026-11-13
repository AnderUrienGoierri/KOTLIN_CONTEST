package org.goierri.lecturesync.backend.database

import java.sql.Connection
import java.sql.DriverManager

object DatabaseFactory {
    private const val DB_URL = "jdbc:sqlite:lecturesync.db"

    fun getConnection(): Connection {
        return DriverManager.getConnection(DB_URL)
    }

    fun init() {
        getConnection().use { conn ->
            conn.createStatement().use { stmt ->
                stmt.execute("PRAGMA foreign_keys = ON;")
                
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS lectures (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        title TEXT NOT NULL,
                        subject TEXT NOT NULL,
                        lecturer TEXT NOT NULL DEFAULT 'Irakaslea',
                        created_at TEXT NOT NULL DEFAULT (datetime('now', 'localtime')),
                        duration_seconds INTEGER NOT NULL DEFAULT 0 CHECK(duration_seconds >= 0),
                        audio_file_path TEXT,
                        status TEXT NOT NULL CHECK(status IN ('RECORDING', 'PROCESSING', 'COMPLETED', 'FAILED')) DEFAULT 'RECORDING'
                    );
                """.trimIndent())

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS transcript_segments (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        lecture_id INTEGER NOT NULL,
                        timestamp_start_ms INTEGER NOT NULL CHECK(timestamp_start_ms >= 0),
                        timestamp_end_ms INTEGER NOT NULL CHECK(timestamp_end_ms >= timestamp_start_ms),
                        speaker TEXT NOT NULL DEFAULT 'Speaker',
                        text_content TEXT NOT NULL,
                        is_key_point INTEGER NOT NULL DEFAULT 0 CHECK(is_key_point IN (0, 1)),
                        FOREIGN KEY (lecture_id) REFERENCES lectures(id) ON DELETE CASCADE
                    );
                """.trimIndent())

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS flashcards (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        lecture_id INTEGER NOT NULL,
                        question TEXT NOT NULL,
                        answer TEXT NOT NULL,
                        box_level INTEGER NOT NULL DEFAULT 1 CHECK(box_level BETWEEN 1 AND 5),
                        next_review_date TEXT NOT NULL DEFAULT (date('now')),
                        ease_factor REAL NOT NULL DEFAULT 2.5 CHECK(ease_factor >= 1.3),
                        FOREIGN KEY (lecture_id) REFERENCES lectures(id) ON DELETE CASCADE
                    );
                """.trimIndent())

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS mindmap_nodes (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        lecture_id INTEGER NOT NULL,
                        label TEXT NOT NULL,
                        category TEXT NOT NULL DEFAULT 'Concept',
                        parent_node_id INTEGER,
                        weight REAL NOT NULL DEFAULT 1.0 CHECK(weight > 0),
                        FOREIGN KEY (lecture_id) REFERENCES lectures(id) ON DELETE CASCADE
                    );
                """.trimIndent())
                
                // Seed demo lecture if empty
                val rs = stmt.executeQuery("SELECT COUNT(*) FROM lectures")
                if (rs.next() && rs.getInt(1) == 0) {
                    stmt.execute("""
                        INSERT INTO lectures (title, subject, lecturer, duration_seconds, status)
                        VALUES ('Kotlin Multiplatform Concurrency', 'Distributed Systems', 'Prof. Martinez', 4460, 'COMPLETED');
                    """.trimIndent())
                    
                    stmt.execute("""
                        INSERT INTO flashcards (lecture_id, question, answer, box_level)
                        VALUES (1, 'What is the role of Dispatchers.JavaFx in KMP?', 'It dispatches coroutine execution safely to the JavaFX Application Thread.', 2);
                    """.trimIndent())
                }
            }
        }
    }
}

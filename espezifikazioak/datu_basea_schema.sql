-- =============================================================================
-- LectureSync AI - Datu-Basearen Eskema (SQLite / Room KMP)
-- Estandarrak: UTF-8, Atzerriko gakoak (FOREIGN KEY) aktibatuta, CHECK murrizketak.
-- =============================================================================

PRAGMA foreign_keys = ON;

-- 1. Klase / Saio Nagusiak
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

-- 2. Transkripzio Segmentuak (Denbora-lerroa eta AI oharrak)
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

-- 3. Ikasteko Flashcard-ak (Spaced Repetition / Leitner Sistema)
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

-- 4. Kontzeptu-Mapeen Nodoak (Hierarkia eta Harremanak)
CREATE TABLE IF NOT EXISTS mindmap_nodes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    lecture_id INTEGER NOT NULL,
    label TEXT NOT NULL,
    category TEXT NOT NULL DEFAULT 'Concept',
    parent_node_id INTEGER,
    weight REAL NOT NULL DEFAULT 1.0 CHECK(weight > 0),
    FOREIGN KEY (lecture_id) REFERENCES lectures(id) ON DELETE CASCADE,
    FOREIGN KEY (parent_node_id) REFERENCES mindmap_nodes(id) ON DELETE SET NULL
);

-- 5. AI Laburpen Exekutiboak
CREATE TABLE IF NOT EXISTS ai_summaries (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    lecture_id INTEGER NOT NULL UNIQUE,
    executive_summary TEXT NOT NULL,
    key_concepts_json TEXT NOT NULL DEFAULT '[]',
    action_items_json TEXT NOT NULL DEFAULT '[]',
    generated_at TEXT NOT NULL DEFAULT (datetime('now', 'localtime')),
    FOREIGN KEY (lecture_id) REFERENCES lectures(id) ON DELETE CASCADE
);

-- Indizeak abiadura optimizatzeko
CREATE INDEX IF NOT EXISTS idx_transcripts_lecture ON transcript_segments(lecture_id);
CREATE INDEX IF NOT EXISTS idx_flashcards_review ON flashcards(next_review_date, box_level);
CREATE INDEX IF NOT EXISTS idx_mindmap_lecture ON mindmap_nodes(lecture_id);

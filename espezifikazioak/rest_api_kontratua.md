# REST API Kontratua (Ktor Server - Backend)

Backend REST APIak Kotlin Ktor Server erabiliko du, JSON estandarrean (`kotlinx.serialization`).

---

## 1. Oinarrizko Konfigurazioa
- **Base URL:** `http://localhost:8080/api/v1`
- **Headers:** `Content-Type: application/json`, `Accept: application/json`

---

## 2. Endpoints Nagusiak

### 2.1 Klaseak Kudeatzea (`/lectures`)

#### `GET /api/v1/lectures`
- **Deskribapena:** Gordetako klase guztien zerrenda eskuratu.
- **Erantzuna (200 OK):**
```json
[
  {
    "id": 1,
    "title": "Kotlin Multiplatform Concurrency",
    "subject": "Distributed Systems",
    "lecturer": "Prof. Martinez",
    "createdAt": "2026-11-13T10:00:00",
    "durationSeconds": 3600,
    "status": "COMPLETED",
    "flashcardCount": 8
  }
]
```

#### `POST /api/v1/lectures`
- **Deskribapena:** Saio berria hasi.
- **Eskaera (Body):**
```json
{
  "title": "Jetpack Compose Internals",
  "subject": "Mobile Architecture",
  "lecturer": "Prof. Martinez"
}
```
- **Erantzuna (201 Created):** Sortutako objektua IDarekin.

---

### 2.2 Transkripzioak eta Audio-stream (`/lectures/{id}/transcript`)

#### `POST /api/v1/lectures/{id}/transcript`
- **Deskribapena:** Transkripzio zati berri bat gehitzea.
- **Eskaera:**
```json
{
  "timestampStartMs": 14000,
  "timestampEndMs": 18500,
  "speaker": "Prof. Martinez",
  "textContent": "Dispatchers.Main handles UI thread mutations in JavaFX and Android."
}
```

---

### 2.3 AI Ekintzak (`/lectures/{id}/ai/...`)

#### `POST /api/v1/lectures/{id}/ai/generate-flashcards`
- **Deskribapena:** Gemini AI erabiliz transkripziotik flashcardak sortu.
- **Erantzuna (200 OK):**
```json
[
  {
    "id": 101,
    "lectureId": 1,
    "question": "What is the role of Dispatchers.JavaFx in KMP?",
    "answer": "It dispatches coroutine execution to the JavaFX Application Thread safely.",
    "boxLevel": 1,
    "nextReviewDate": "2026-11-14"
  }
]
```

#### `POST /api/v1/lectures/{id}/ai/summarize`
- **Deskribapena:** Klasearen laburpen exekutiboa eta kontzeptu-mapen nodoak sortzea.

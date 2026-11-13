# Figma Geruzen Arkitektura eta UI Diseinu Sistema Profesionala

Dokumentu honek Figma proiektuan erabiliko den geruzen hierarkia, Auto Layout arauak, osagaien egitura eta diseinu profesionalaren oinarriak zehazten ditu. Helburua interfaze artifizial edo amateurrak saihestea da, JetBrains eta KotlinConf mailako produktu fin bat lortuz.

---

## 1. Diseinu Profesionalaren Urrezko Arauak (Anti-Artificial Design)

1. **8px Grida (Spatial System):** Elementu guztien marjinak, espaziatzeak eta altuerak 8ren multiploak dira (4px salbuespen txikietarako, 8px, 16px, 24px, 32px).
2. **Auto Layout Zorrotza:** Figma-n ez da kaxa librerik erabiltzen. Elementu orok Auto Layout (Horizontal edo Vertical) izan behar du, `Fill container` edo `Hug contents` erabiliz erantzun sentikorra bermatzeko.
3. **Geruzen Izendapen Semantikoa:** `Frame 412` edo `Rectangle 12` bezalako izenak debekatuta daude. Izen semantikoak erabili behar dira: `Sidebar/Nav-Item-Active`, `Card/Lecture-Summary`, `Button/Primary-Accent`.
4. **Koloreen Hierarkia (60-30-10 Araua):**
   - %60 Oinarrizko atzealde ilun neutroa (`#181A1F` / `#21252B`).
   - %30 Egitura-koloreak, txartelak, ertz sotilak eta tipografia sekundarioa (`#2E333D`, `#9DA5B4`).
   - %10 Kotlin/JetBrains azentu biziak dei-ekintzetarako (`#7F52FF`, `#3574F0`).
5. **Tipografia Ponderatua:** Inter bururako eta gorputzerako, eta JetBrains Mono kode zein metriketarako.

---

## 2. Figma Fitxategiaren Orrialdeen Antolaketa (Pages)

1. **`00_Cover & Specs`**: Proiektuaren izena, bertsioa, egileak eta lehiaketaren informazio orokorra.
2. **`01_Design System & Tokens`**: Kolore paletak, tipografia eskala, ikonoak, botoiak eta txartel-txantiloiak.
3. **`02_Desktop UI (JavaFX Target)`**: Mahaigaineko bertsioaren pantailak (1280x800px eta 1440x900px markoak).
4. **`03_Mobile UI (Android Compose Target)`**: Mugikorreko pantailak (390x844px edo 412x915px).
5. **`04_Component States & Microinteractions`**: Botoien eta sarreren egoerak (Default, Hover, Active, Disabled, Loading).

---

## 3. Desktop UI Geruzen Hierarkia Zehatza (1280x800 Frame)

```text
[Frame] LectureSync-Desktop / 1280x800 / Background: #181A1F
  │
  ├── [AutoLayout - Horizontal] App-Shell / Fill Width, Fill Height
  │     │
  │     ├── [AutoLayout - Vertical] Sidebar-Navigation / Width: 260px, Fill Height / Bg: #14161A, Border-R: 1px #2E333D
  │     │     ├── [AutoLayout - Horizontal] Brand-Header / Pad: 24px 20px
  │     │     │     ├── [Vector] Kotlin-Brain-Logo / 28x28px
  │     │     │     └── [Text] "LectureSync AI" / 16px Semibold #F0F3F6
  │     │     │
  │     │     ├── [AutoLayout - Vertical] Nav-Menu / Gap: 4px, Pad: 0 12px
  │     │     │     ├── [Component] Nav-Item / State: Active / "Dashboard" (Icon + Label + Badge)
  │     │     │     ├── [Component] Nav-Item / State: Default / "Live Lecture" (Icon + Label)
  │     │     │     ├── [Component] Nav-Item / State: Default / "Flashcards & Quiz" (Icon + Label)
  │     │     │     ├── [Component] Nav-Item / State: Default / "Mind Maps" (Icon + Label)
  │     │     │     └── [Component] Nav-Item / State: Default / "Recordings Library" (Icon + Label)
  │     │     │
  │     │     ├── [Spacer]
  │     │     │
  │     │     └── [AutoLayout - Vertical] Sidebar-Footer / Pad: 16px 16px, Border-T: 1px #2E333D
  │     │           ├── [Component] Sync-Status-Pill / "Mobile Connected (Pixel 8)" / Dot: #28A745
  │     │           └── [Component] User-Profile-Card / Avatar + Name + Settings Icon
  │     │
  │     └── [AutoLayout - Vertical] Main-Workspace / Fill Width, Fill Height
  │           │
  │           ├── [AutoLayout - Horizontal] Top-ActionBar / Height: 64px, Fill Width / Bg: #181A1F, Border-B: 1px #2E333D
  │           │     ├── [AutoLayout - Horizontal] Breadcrumbs / "Lectures / Advanced Kotlin Concurrency"
  │           │     ├── [Spacer]
  │           │     ├── [AutoLayout - Horizontal] Global-Search-Input / Width: 280px / Bg: #21252B, Radius: 6px
  │           │     └── [Component] Button-Primary / "Start AI Live Session" / Bg: #7F52FF
  │           │
  │           └── [AutoLayout - Horizontal] Content-Body / Fill Width, Fill Height / Pad: 24px
  │                 │
  │                 ├── [AutoLayout - Vertical] Left-Panel-LectureNotes / Width: 60% / Gap: 16px
  │                 │     ├── [Component] Session-Header-Card / Title, Subject, Live Timer, AI Status Pill
  │                 │     ├── [Component] Audio-Visualizer-Card / Realtime waveform + Mic selector
  │                 │     └── [Component] Live-Transcript-Markdown / Auto-scrolling, Highlighted key points
  │                 │
  │                 └── [AutoLayout - Vertical] Right-Panel-AICopilot / Width: 40% / Gap: 16px
  │                       ├── [Component] Concept-Mindmap-Card / Interactive graphical node preview
  │                       ├── [Component] Generated-Flashcards-Card / Front/Back quick carousel
  │                       └── [Component] AI-Chat-Quick-Prompt / Ask Gemini about the current lecture
```

---

## 4. Component Library & Variants

### Botoi Osagaiak (Button Master Component):
- **Variants:**
  - `Variant`: `Primary` (Bg: `#7F52FF`, Text: White), `Secondary` (Bg: `#292D35`, Border: `#2E333D`), `Ghost` (Transparent, Hover Bg), `Danger` (`#E06C75`).
  - `State`: `Default`, `Hover`, `Pressed`, `Focused`, `Disabled`, `Loading`.
  - `Size`: `Small` (32px), `Medium` (40px), `Large` (48px).

### Txartel Osagaiak (Card Master Component):
- `Background`: `#21252B`
- `Border`: `1px solid #2E333D`
- `Radius`: `8px`
- `Padding`: `16px` edo `20px`
- `Shadow`: `0 4px 12px rgba(0, 0, 0, 0.25)`

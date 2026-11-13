# Agenteen Lan-Fluxua eta Komunikazio Protokoloa

Dokumentu honek agenteen arteko eskuz-eskuko entrega (handover), zereginen banaketa eta garapen-zikloa deskribatzen ditu.

---

## 1. Ziklo Errepikakor Garbia (Pipeline)

```text
[UI-UX-Designer-Agent]
   │ (Tokens & Figma Geruzen Egitura)
   ▼
[Desktop-JavaFX-Agent]  &  [Mobile-Android-Agent]
   │ (FXML / CSS / Compose UI)
   ▼
[KMP-Architect-Agent]
   │ (Shared Logic, Ktor Networking, StateFlow Models)
   ▼
[AI-Engine-Agent]
   │ (Gemini API Prompts & JSON Structured Outputs)
   ▼
[QA-DevSecOps-Agent]
   │ (GitHub Actions ci.yml, Detekt, Tests, Verifications)
   ▼
[Git Commit & Push Egonkorra]
```

---

## 2. Eskuz-Eskuko Protokoloa (Handover Guidelines)

1. **Diseinutik Kodera:**
   - `UI-UX-Designer-Agent`-ek ezaugarri berri baten tokenak eta geruzak zehazten dituenean, `.fxml` eta `.css` fitxategiak sortzen dira `scene_builder/` direktorioan.
   - `Desktop-JavaFX-Agent`-ek FXML hori hartu eta dagozkion `@FXML` id-ak eta `Controller` klaseak sortzen ditu.

2. **Datu-ereduen Definizioa:**
   - Datu-eredu guztiak (`LectureSession`, `TranscriptSnippet`, `Flashcard`, `MindMapNode`) `shared/commonMain` modulu zentralizatuan idatzi behar dira `@Serializable` erabiliz.
   - Desktop edo Mobile plataformek ezin dute bere kabuz bikoiztutako datu-eredurik sortu.

3. **AI Emaitzen Tratamendua:**
   - `AI-Engine-Agent`-ek Gemini erantzunak beti JSON balioztatu gisa egituratuko ditu `kotlinx.serialization` bidez zuzenean mapatu ahal izateko.
   - Errore edo deskonektatze kasuetan (adibidez API kuotak edo offline egoera), huts egite kontrolatuak eta cache lokalak emango dira.

---

## 3. Garapen Arau Zorrotzak

- **Emotikonorik Ez:** Proiektu osoan (fitxategiak, kodea, iruzkinak, erantzunak eta commit mezuak) ez da emotikonorik onartzen.
- **Aldaketa Txikien Commit-ak:** Funtzionalitate bakoitza probatu eta balioztatu ostean, berehala `git commit` eta `git push` egingo dira mezua ingelesez idatziz (adibidez: `feat(ui): implement dark theme for main desktop view`).
- **Pantailetan Proportzio Sentikorra:** Scene Builder-en ez dira posizio finko absolutuko osagaiak erabiliko; beti `HBox`, `VBox`, `BorderPane` edo `GridPane` erabiliko dira.

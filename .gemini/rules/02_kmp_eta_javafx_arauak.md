# KMP eta JavaFX Garapen Arauak

Dokumentu honek Kotlin Multiplatform eta JavaFX / OpenJFX integraziorako arau tekniko zorrotzak zehazten ditu.

---

## 1. JavaFX eta Kotlin Integrazioa

1. **FXML Lotura Segurua:**
   - Kontrolatzaileek `@FXML` oharpena erabili behar dute FXML-ko elementuak aldagai pribatuekin lotzeko:
     ```kotlin
     @FXML private lateinit var lecturesTable: TableView<LectureSession>
     ```
   - Kontrolatzaileen hasieratzea `initialize()` metodo estandarrean egingo da (`Initializable` interfazea).

2. **UI Hariaren Babesa (JavaFX Application Thread):**
   - Ezin da inoiz UI elementurik aldatu atzeko planoko hari batetik.
   - Kotlin coroutines erabiliko dira `Dispatchers.JavaFx` bideratzailearekin:
     ```kotlin
     scope.launch(Dispatchers.JavaFx) {
         statusLabel.text = "Session updated"
     }
     ```

3. **OpenJFX Bertsio Bateragarritasuna:**
   - Java 17 edo 21 LTS eta OpenJFX 21 erabiliko dira, Scene Builder bertsio berrienekin %100 bateragarri izateko.

---

## 2. KMP Common Kodearen Muga eta Arauak

- `commonMain` barruan ez da inoiz `java.*` edo `android.*` inportaziorik erabiliko.
- Plataforma-berezitasunak ebazteko `expect`/`actual` mekanismo estandarra erabiliko da.
- Serializazio guztia `kotlinx.serialization` bidez gauzatuko da.

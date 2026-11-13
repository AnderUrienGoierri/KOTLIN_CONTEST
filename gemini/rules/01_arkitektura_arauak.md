# Arkitektura Arauak (KMP + JavaFX + Android)

Proiektu honek Kotlin Multiplatform (KMP) arkitektura jarraitzen du Clean Architecture eta MVVM ereduekin bateratuta.

---

## 1. Moduluen Banaketa

```text
KOTLIN_CONTEST/
├── shared/                     # Kotlin Multiplatform Shared Module
│   ├── commonMain/             # Domeinu ereduak, UseCase-ak, Gemini AI bezeroa, SQLDelight DB
│   ├── jvmMain/                # JVM espezifikoa (Desktop JavaFX-erako moldatzaileak)
│   └── androidMain/            # Android espezifikoa (Audio grabaketa, Kamera APIak)
│
├── desktop_javafx/             # Mahaigaineko bezeroa (JavaFX + OpenJFX + Scene Builder FXML)
│   ├── src/main/kotlin/        # FXML kontrolatzaileak eta ViewModela
│   └── src/main/resources/     # FXML ikuspegiak eta styles.css
│
└── mobile_android/             # Android bezeroa (Jetpack Compose)
    └── src/main/kotlin/        # Compose UI eta Android ViewModelak
```

---

## 2. Datuen Fluxua (Unidirectional Data Flow - UDF)

1. **StateFlow:** ViewModel-ek UI egoera (UI State) bakar eta aldaezin bat erakutsiko dute `StateFlow<ViewState>` bidez.
2. **Intent/Event-ak:** Erabiltzailearen ekintzek metodo esplizituak deituko dituzte ViewModel-ean.
3. **Coroutines & Threading:**
   - I/O eta sare-eskaerak `Dispatchers.IO`-n burutuko dira.
   - JavaFX UI eguneratzeak soilik `Dispatchers.JavaFx` bidez bidaliko dira.
   - Android UI eguneratzeak `Dispatchers.Main`-en kokatuko dira.

---

## 3. Dependentzien Injekzioa eta Zerbitzuak

- Liburutegi arinak erabiliko dira (adib. Koin KMP edo eskuzko Service Locator eredua).
- API gakoak edo sekretuak ez dira inoiz git-era igoko; ingurune-aldagai bidez edo `.properties` lokaletatik kargatuko dira.

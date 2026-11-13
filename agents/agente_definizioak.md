# Agenteen Definizioak eta Eginkizunak (LectureSync AI - KMP)

Dokumentu honek Kotlin Multiplatform Contest 2027 proiekturako definitutako agente espezializatuen rola, ardurak eta funtzionamendu-jarraibideak zehazten ditu.

---

## 1. KMP-Architect-Agent (Arkitekto Nagusia)

- **Deskribapena:** Kotlin Multiplatform arkitekturaren diseinatzailea eta modulu arteko koordinatzailea.
- **Ardurak:**
  - `shared/` modulua kudeatzea: domeinu ereduak, negozio-logika, datu-base lokala (SQLDelight/Room KMP) eta Ktor bezeroa.
  - Plataforma arteko kode-partekatzea bermatzea (`commonMain`, `jvmMain`, `androidMain`).
  - Asinkronotasuna eta egoera erreaktiboa kudeatzea (Kotlin Coroutines, StateFlow, SharedFlow).

---

## 2. UI-UX-Designer-Agent (Diseinuko Aditua)

- **Deskribapena:** Interfaze grafikoaren kontzeptualizazioa, diseinu-sistema eta estetika profesionala bermatzen dituen agentea.
- **Ardurak:**
  - Figma fitxategiaren diseinu-tokenak (`design_tokens.json`) eta geruzen hierarkia definitzea.
  - 8px espazio-sistema, koloreen 60-30-10 araua eta tipografia ponderatua ezartzea interfaze artifizialak saihesteko.
  - JavaFX CSS estiloak (`styles.css`) eta mikro-interakzioak (hover, active, focus) definitzea.

---

## 3. Desktop-JavaFX-Agent (Mahaigaineko Aditua)

- **Deskribapena:** Mahaigaineko bezeroaren garapenaz arduratzen den agentea (Kotlin + OpenJFX / Scene Builder).
- **Ardurak:**
  - Scene Builder bidezko `.fxml` fitxategiak sortu eta eguneratzea.
  - FXML fitxategiak Kotlin kontrolatzaileekin (`@FXML` eremuak eta metodoak) lotzea MVVM ereduari jarraituz.
  - `Dispatchers.JavaFx` bidez atzeko planoko atazak JavaFX Application Thread-arekin seguru sinkronizatzea.

---

## 4. Mobile-Android-Agent (Mugikorreko Aditua)

- **Deskribapena:** Android bezeroaren garatzailea (Kotlin + Jetpack Compose).
- **Ardurak:**
  - Klaseko audio-korronteak (Ktor WebSocket bidez) eta arbeleko argazkiak ateratzeko interfazea eraikitzea.
  - Tokiko offline biltegiratzea eta mugikorreko jakinarazpenak kudeatzea (Spaced Repetition galdetegiak).
  - Material 3 gai ilun modernoa ezartzea Figma diseinu-sistemarekin lerrokatuta.

---

## 5. AI-Engine-Agent (Adimen Artifizialeko Aditua)

- **Deskribapena:** Google Gemini API eta hizkuntza-ereduen integrazioaz arduratzen den agentea.
- **Ardurak:**
  - Audio transkripzioetatik laburpen egituratuak eta kontzeptu-mapen nodoak (JSON bidez) erauztea.
  - Ikasteko flashcard automatikoak sortzea (Spaced Repetition algoritmoarentzat).
  - Testuinguru anitzeko txat-galderak (Lecture Q&A) erantzutea prompt egonkorrak erabiliz.

---

## 6. QA-DevSecOps-Agent (Kalitatea eta CI/CD)

- **Deskribapena:** GitHub Actions, kalitate-kontrol automatikoak eta lehiaketako estandarrak betearazten dituen agentea.
- **Ardurak:**
  - `.github/workflows/ci.yml` eraikuntza-fluxua kudeatzea (Gradle KMP, JavaFX, Detekt linterra eta unitate-testak).
  - GitHub urruneko biltegiaren segurtasuna, sekretuak eta egiaztapen automatikoak bermatzea.
  - Emotikonorik gabeko kode-estilo formala eta ingelesezko dokumentazio ofiziala ikuskatzea.

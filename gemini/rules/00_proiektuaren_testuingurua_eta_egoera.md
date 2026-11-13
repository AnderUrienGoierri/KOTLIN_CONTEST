# Proiektuaren Testuingurua eta Egoera Eguneratua

Dokumentu honek elkarrizketetan zehar hartutako erabaki, arkitektura, egiaztapen eta arau guztiak biltzen ditu.

---

## 1. Identifikazioa eta Errepositorioa
- **Proiektua:** `LectureSync AI` (Kotlin Multiplatform Contest 2027)
- **Lehiaketa:** JetBrains Kotlin Multiplatform Contest 2027 (KotlinConf 2027, Krakovia).
- **Entregatzeko azken eguna:** 2026ko abenduaren 7a.
- **Figma Fitxategi Ofiziala (Edu):** `https://www.figma.com/design/avrDxgETP3Snx7WyPo3YvC` (File Key: `avrDxgETP3Snx7WyPo3YvC`, Taldea: `2MG3`).
- **GitHub Konfigurazioa:** `.github/workflows/ci.yml` (KMP Gradle, JavaFX eta Android egiaztapenak).

---

## 2. Arau Profesionalak (Derrigorrezkoak)
- **Emotikonorik Ez:** Proiektu osoan (README, kodea, iruzkinak, commit mezuak eta erantzunak) emotikonoen erabilera erabat debekatuta dago.
- **Aldaketa Txikien Commit eta Push:** Funtzionalitate edo modulu bakoitza amaitzean berehala egingo dira `git commit` eta `git push` mezu estandarrekin (Conventional Commits, ingelesez).
- **Hizkuntza Estandarrak:**
  - Kodea, aldagaien izenak, iruzkinak, GitHub README eta CI fitxategiak: **Ingelesez**.
  - Barne dokumentazio eta jarraibideak: Euskaraz.
- **Arkitektura Partekatua (KMP):** Negozio-logika, AI komunikazioa eta datu-ereduak `shared` moduluan kokatuko dira. Desktop (JavaFX) eta Mobile (Android) bezeroek logika bera berrerabiliko dute.

---

## 3. Erabiltzaile Interfazea eta Diseinua
- **Tresnak:** Figma (kontzeptualizazioa, diseinu tokenak, geruzen hierarkia) eta Gluon Scene Builder (JavaFX FXML eta estilo-orriak).
- **Estetika:** Profesionala, modernoa, iluna (JetBrains / KotlinConf estilokoa), efektu artifizial edo amatoreak ekidinez.
- **Espazio Sistema:** 8px grida zorrotza eta diseinu erantzunkorra (responsive layout).

---

## 4. Egungo Egoera eta Hurrengo Urratsak
- [x] Lehiaketaren baldintzak eta informazioa bilduta (`kotlin_contest_info/kotlin_contest_oinarriak.md`).
- [x] Figma diseinu tokenak, geruzen arkitektura eta HTML aurrebista garatuta (`figma/`).
- [x] Scene Builder FXML proiektua eta CSS ilun profesionala eratuta (`scene_builder/`).
- [x] Agenteen definizioak eta lan-fluxua finkatuta (`agents/`).
- [ ] KMP proiektuaren egitura eta Gradle konfigurazioa osatzea.
- [ ] Gemini AI zerbitzuaren integrazioa probatzea.

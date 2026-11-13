# JavaFX Scene Builder Gida eta FXML Proiektua

Karpeta honek JavaFX eta Scene Builder bidez diseinatutako interfaze profesionalak biltzen ditu, KotlinConf mailako diseinu garbi eta modernoarekin.

---

## 1. Sarbidea eta Galderen Erantzunak

### G: Agenteak sarbiderik badu zure Scene Builder-era?
- **Gluon Scene Builder** zure ordenagailuan lokalean instalatutako aplikazio natiboa da (pantaila-argazkian ikusten den bezala irekita daukazuna).
- Agenteak disko lokalean (`c:\Dev\GoierriEskola\KOTLIN_CONTEST\scene_builder\`) dauden `.fxml` eta `.css` fitxategiak eraiki, eguneratu eta zuzentzen ditu zuzenean.
- Beraz, zuk Gluon Scene Builder-en **Open Project / Open File** sakatu eta hemengo `.fxml` fitxategiak ireki ditzakezu berehala. Ematen dituzun aldaketak edo guk egindakoak berehala sinkronizatuko dira.

### G: Localean (localhost nabigatzailean) izan al dezakegu Scene Builder?
- Scene Builder Mahaigaineko (Desktop) aplikazio grafiko natibo bat da; ez da web zerbitzari bat eta ez du localhost nabigatzailerik behar.
- Baina diseinua web nabigatzailean ikusi nahi baduzu, sortu dugun `../figma/preview_mockup.html` fitxategia ireki dezakezu nabigatzailean.

---

## 2. Fitxategien Egitura

1. **`styles.css`**: Dark Mode moderno profesionala JetBrains/KotlinConf estiloan (`#181A1F`, `#21252B`, `#7F52FF` akzentoak, ertz sotilak, botoi-egoerak eta tipografia dotorea).
2. **`main_view.fxml`**: Aplikazio nagusiaren leihoa (Sidebar nabigatzailea, TopBar bilaketarekin, eduki-panela eta beheko egoera-barra).
3. **`dashboard_view.fxml`**: Metrika txartelak eta klaseen taula biltegiratua.

---

## 3. Nola Ireki Scene Builder-en

1. Ireki zure ordenagailuan instalatuta duzun **Gluon Scene Builder**.
2. Egin klik **File -> Open...** (edo `Ctrl + O`).
3. Hautatu: `C:\Dev\GoierriEskola\KOTLIN_CONTEST\scene_builder\main_view.fxml`.
4. Scene Builder-en diseinu osoa kargatuko da bere estilo-orriarekin (`styles.css`).
5. Bertan osagai berriak gehitu, kokapenak aldatu edo kontrolatzaile berriak esleitu ditzakezu.

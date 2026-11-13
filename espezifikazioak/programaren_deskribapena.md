# Programaren Deskribapena eta Muga Funtzionalak

Dokumentu honek Goierri Eskolako 2. DAM metodologiaren araberako programaren definizio formala jasotzen du.

---

## 1. Helburu Nagusia
**LectureSync AI** ikasketa-esperientzia optimizatzeko plataforma bat da. Mugikorrak (Android) klaseko audioa eta arbeleko argazkiak biltzen ditu, eta mahaigaineko aplikazioak (Desktop JavaFX) denbora errealean transkripzioa, kontzeptu-mapak eta ikasteko flashcardak bistaratu eta kudeatzen ditu Google Gemini AI bidez.

---

## 2. Erabiltzaile Profilak
1. **Ikaslea (Student):** Klasean klaseko grabaketa hasi, oharrak sinkronizatu, flashcardak errepasatu eta azterketak prestatu.
2. **Irakaslea / Hizlaria (Presenter):** Saioaren materiala partekatu eta laburpen automatikoak balioztatu.

---

## 3. Pantaila Nagusiak eta Ekintzak

### Pantaila 1: Dashboard Nagusia (Mahaigaina)
- **Helburua:** Azken grabatutako klaseak, asteko estatistikak eta berrikusi beharreko flashcardak ikustea.
- **Elementuak:**
  - `btnNewLecture`: Saio berri bat sortzeko botoia.
  - `txtSearch`: Klaseak eta oharrak bilatzeko eremua.
  - `tblLectures`: Klaseen taula (Izenburua, Data, Iraupena, Egoera, Flashcard kopurua).
  - `cardMetrics`: AI metrika orokorrak (klase orduak, txartel kopurua, menderatze-ehunekoa).

### Pantaila 2: Live Lecture Studio (Mahaigaina)
- **Helburua:** Audioaren sarrera denbora errealean ikusi, transkripzio automatikoa irakurri eta AI bidezko laburpenak sortu.
- **Elementuak:**
  - `btnRecToggle`: Grabaketa eten / berrabiarazi botoia.
  - `lblLiveTimer`: Denbora-zenbatzailea.
  - `txtTranscript`: AI-k sortutako transkripzio etengabea (auto-scroll).
  - `btnGenerateSummary`: Gemini AI bidezko laburpena eskatzeko botoia.
  - `pnlMindMap`: Kontzeptu gakoen nodoak erakusteko gunea.

### Pantaila 3: Flashcards & Spaced Repetition (Mahaigaina eta Mugikorra)
- **Helburua:** Leitutako kontzeptuetatik sortutako galdera-erantzunak ikasi (Leitner sistema).
- **Elementuak:**
  - `cardQuestion`: Galderaren testua.
  - `btnFlipCard`: Txartelari buelta eman eta erantzuna ikusi.
  - `btnMarkKnown`: "Badakit" botoia (hurrengo berrikuspena atzeratu).
  - `btnMarkReview`: "Zaila / Berrikusi" botoia (bihar berriro galdetu).
  - `prgMastery`: Progresio-barra.

---

## 4. Zer EZ den Egingo (Irismena Mugatzea - Slide 3 & 9)
1. **EZ da kanpoko bideo-plataformarik (Zoom/Teams) integratuko:** Audioa zuzenean mikrofono lokaletik edo KMP WebSocket bidez jasoko da.
2. **EZ da ordainpeko transkripzio jabedunik erabiliko:** Google Gemini 2.5 Flash API eta audio-segmentazio arina erabiliko dira.
3. **EZ da erabiltzaile anitzeko baimen konplexurik (RBAC) ezarriko lehen fasean:** Erabiltzaile lokalaren esperientzian zentratuko da.

# Kotlin Multiplatform Contest 2027 - Oinarriak eta Informazioa

Dokumentu honek JetBrains-ek antolatutako Kotlin Multiplatform Contest 2027 lehiaketaren arau, baldintza, epe eta proposatutako ideien informazio osoa biltzen du.

Iturria: https://kotlinconf.com/contest/

---

## 1. Datu Nagusiak eta Saria

- **Kongresua:** KotlinConf 2027
- **Tokia:** Krakovia, Polonia
- **Datak:** 2027ko apirilaren 22tik 23ra
- **Saria:** 
  - Hiru proiektu onenentzat: KotlinConf 2027ra joateko bidaia eta hotela ordainduta.
  - Gainerako parte-hartzaile guztientzat: Kotlin oroigarri ofizialak.

---

## 2. Epeak

- **Proiektua bidaltzeko azken eguna:** 2026ko abenduaren 7a.
- **Irabazleen iragarpena:** 2026ko abenduaren 21a.

---

## 3. Baldintza Teknikoak eta Ebaluazio Kriterioak

- **Teknologia Nagusia:** Kotlin Multiplatform (KMP) erabili behar da derrigorrez.
- **Plataforma Kopurua:** Gutxienez bi plataformatan ibili behar du (adibidez: Desktop + Android, Android + iOS, Desktop + Web, edo Mobile + Ktor Server).
- **Proiektu Motaren Muga:** Ezin da txantiloi hutsa (template) edo liburutegi soil bat izan; amaitutako aplikazio funtzional bat izan behar du.
- **Adimen Artifiziala (AI):** AI gaitasunak integratzeak balio eta abantaila handia ematen du ebaluazioan.
- **Hizkuntza Baldintza:** Kodea, interfazearen testuak eta GitHub-eko README fitxategia **ingelesez** egon behar dute, instalazio- eta hedapen-argibide argiekin.

---

## 4. Parte-Hartzailearen Baldintzak

- Gutxienez 18 urte izatea.
- Azken 12 hilabeteetan ikasketa-programa ofizial batean matrikulatuta egon izana (ikasle edo graduatu berria).
- Lehiaketako Slack kanal ofizialean sartuta egotea.

---

## 5. Proposatutako Ideia Originalak (KMP + AI)

1. **LectureSync AI (Mugikorra + Desktop):**
   - Mugikorrarekin klaseko ahotsa grabatu eta arbeleko argazkiak atera.
   - Ordenagailuko mahaigainean (Desktop) denbora errealean transkripzioak, kontzeptu-mapak eta ikasteko flashcardak sortu AI bidez.
2. **KulturaLens (Android + iOS):**
   - Monumentu edo kartelei argazkiak atera eta ikusmen artifizialaren bidez azalpen historikoak zein tokiko hizkuntzen ahoskera-gidak eskaini, konexiorik gabeko datu-basearekin.
3. **DevPulse KMP (Desktop + Mugikorra):**
   - Mahaigainean garatzailearen Git commit-ak eta kode-aldaketak aztertu AI bidez; atsedenaldietan mugikorrera akats ohikoetan oinarritutako galdetegi laburrak bidali.
4. **PantryChef Multi (Mugikorra + Web/Desktop):**
   - Pisukideentzako elikagai-kudeatzailea; erosketa-tiketak eskaneatu eta hozkailuan dauden produktuekin soilik errezetak eta asteko menuak sortu.
5. **SoundScape Studio (Desktop + Mugikorra):**
   - Ikasketa-taldeentzako plataforma generatiboa; zereginaren arabera giro-soinuak, animazioak eta Pomodoro saio sinkronizatuak sortzen dituena.

---

## 6. Gure Hautua eta Egitura Estrategikoa

Aukeratutako norabidea: **LectureSync AI** (edo **DevPulse KMP**).
Gure diseinua JavaFX + Scene Builder erabiliz gauzatuko da Desktop moduluan, KMP arkitektura garbi batekin lotuta, eta mugikorrerako bezeroarekin bateragarri.

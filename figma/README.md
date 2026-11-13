# Figma Diseinurako Gida eta Integrazioa

Dokumentu honek Figma-n proiektua nola konfiguratu eta kudeatu azaltzen du, diseinu profesionala eta lehiaketarako aurkezpen bikaina lortzeko.

---

## 1. Konektatutako Proiektua Zure Figma Kontuan

Proiektua zuzenean sortu da zure Figma kontuan:
- **Fitxategia:** `LectureSync AI - KMP Contest 2027`
- **Esteka Zuzena:** https://www.figma.com/design/Bq0Trc8YwjlDprTnOjO1mf
- **File Key:** `Bq0Trc8YwjlDprTnOjO1mf`
- **Jabea:** Ander Urien Telleria (`and_uri_tel@goierrieskola.org`)

---

## 2. Sarbidea eta Galderen Erantzunak

### G: Agenteak ba al du sarbiderik nire Figma unitate pribatura?
Ez modu automatiko zuzenean, Figma-k autentifikazio pribatua (OAuth tokena edo Personal Access Token) eskatzen baitu hodeiko zure fitxategietara sartzeko. Dena den, bi modu daude konektatzeko:
1. **Figma REST API / Plugin:** Zure Figma kontuko `Personal Access Token` emanez gero, fitxategien informazioa eta geruzak irakurri ditzakegu.
2. **Diseinu Sistema eta Tokenak (Metodo gomendatua):** Guk sortutako `design_tokens.json` eta `ui_layer_architecture.md` erabiliz, egitura osoa definituta dago eta zuzenean Figma zein Scene Builder-era esportatzen da.

### G: Localean (localhost bidez) izan al dezakegu Figma nabigatzailean?
- **Figma:** Figma jabedun hodeiko plataforma bat da (`https://figma.com`). Ezin da bere motorra `localhost` batean instalatu zerbitzari gisa. Baina nabigatzailean erabil daiteke libreki edozein unetan.
- **Localhost Aurrebista:** Gure proiektuan HTML/CSS bidezko aurrebista interaktiboa sortu dugu (`preview_mockup.html`), eta hori bai ireki dezakezu zure nabigatzailean edo `localhost` zerbitzari lokal batean!

---

## 2. Nola Sortu Figma Proiektua Urratsik Urrats

1. Ireki zure nabigatzailean edo aplikazioan: `https://www.figma.com`.
2. Sortu **New Design File** berria eta izendatu: `LectureSync AI - KMP Contest 2027`.
3. Sortu ezkerreko panelan orrialde hauek:
   - `01_Design System & Tokens`
   - `02_Desktop App (1280x800)`
   - `03_Mobile App (390x844)`
4. Gehitu kolore-estilo lokalak `design_tokens.json` fitxategiko balioekin:
   - `Brand/Kotlin-Purple`: `#7F52FF`
   - `Brand/JetBrains-Blue`: `#3574F0`
   - `Background/Base`: `#181A1F`
   - `Background/Surface`: `#21252B`
   - `Border/Subtle`: `#2E333D`
   - `Text/Primary`: `#F0F3F6`
   - `Text/Secondary`: `#9DA5B4`
5. Eraiki osagaiak `ui_layer_architecture.md` fitxategian zehaztutako geruzen arabera, **Auto Layout** (Shift + A) beti aktibatuta mantenduz.

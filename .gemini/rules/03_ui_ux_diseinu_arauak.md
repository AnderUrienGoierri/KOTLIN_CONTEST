# UI/UX Diseinu Arauak (Figma & Scene Builder)

Dokumentu honek aplikazioak itxura artifizial edo amatorea izan ez dezan bete beharreko diseinu arau profesionalak zehazten ditu.

---

## 1. Diseinu Artifiziala Saihesteko Giltzak (Anti-AI & Anti-Amateur Look)

1. **Ez erabili aurrez zehaztutako estilo lehenetsiak:**
   - JavaFX-eko Modena estilo grisa guztiz ordezkatuko da `styles.css`-ko gure diseinu-tokenekin.
2. **Kontraste eta Ponderazio Zuzena:**
   - Testu primarioak: `#F0F3F6` (ez %100 txuri gordin orbangarria).
   - Testu sekundarioak: `#9DA5B4` (irakurgarritasun optimoa atzealde ilunaren gainean).
3. **8px Grida eta Banaketa Harmonikoa:**
   - Tarte guztiak 8px, 16px, 24px edo 32px izango dira. Elementuen ertzek arnasa hartu behar dute.
4. **Egitura Erantzunkorra (Responsive Containers):**
   - Scene Builder-en inoiz ez erabili koordenatu finkoak (`layoutX`, `layoutY`). Erabili `BorderPane`, `HBox`, `VBox` eta `Region` (hgrow="ALWAYS") tamaina dinamikoki egokitzeko.
5. **Mikro-interakzioak:**
   - Botoi orok izan behar ditu `:hover` eta `:pressed` egoera leunak, erabiltzaileari feedback argia emateko.

---

## 2. Figma eta Scene Builder Baliokidetza

- Figma-ko `Card` = JavaFX `VBox` estilo honekin: `.card-panel`
- Figma-ko `Primary Button` = JavaFX `Button` estilo honekin: `.btn-primary`
- Figma-ko `Badge` = JavaFX `Label` estilo honekin: `.badge-purple` edo `.badge-live`

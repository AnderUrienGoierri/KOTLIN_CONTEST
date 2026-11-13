# Git eta Egiaztapen Arauak (CI/CD)

Dokumentu honek Git kontrola, adarren kudeaketa eta automatizazio arauak biltzen ditu.

---

## 1. Commit Arauak

1. **Hizkuntza:** Commit mezu guztiak **ingelesez** idatzi behar dira.
2. **Formatua (Conventional Commits):**
   - `feat(scope): short description`
   - `fix(scope): fix description`
   - `docs(scope): documentation update`
   - `style(scope): css and design adjustments`
   - `refactor(scope): code structure without behavior change`
3. **Maiztasuna:** Urrats esanguratsu bakoitzaren ostean berehala egin `git commit` eta `git push`. Ez utzi aldaketa handiak metatzen.
4. **Emotikonorik Ez:** Commit mezuetan emotikonoak erabiltzea erabat debekatuta dago.

---

## 2. GitHub Actions CI/CD Egiaztapena

- Kodea `main` adarrera igotzean edo Pull Request irekitzean `.github/workflows/ci.yml` automatikoki exekutatuko da.
- CI pasatzeko:
  1. Gradle eraikuntzak arrakastatsua izan behar du (`./gradlew build`).
  2. Detekt eta Ktlint kode-analisiek ezin dute errore larririk eman.
  3. Proba automatikoek (unit tests) arrakastaz pasa behar dute.

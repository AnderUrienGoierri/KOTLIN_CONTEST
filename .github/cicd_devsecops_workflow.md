# CI/CD and DevSecOps Pipeline Documentation

This document explains the automated continuous integration, security checks, and code quality controls configured for the **LectureSync AI** project (Kotlin Multiplatform Contest 2027).

---

## 1. Workflow Architecture Overview

The CI pipeline runs via GitHub Actions defined in `.github/workflows/ci.yml`. It is triggered automatically on:
- Every `push` to the `main` branch.
- Every `pull_request` targeting `main`.

Concurrency groups are enabled to cancel redundant running workflows if newer commits are pushed in quick succession.

```text
Commit / Pull Request
   │
   ├── Job 1: Code Quality & Static Analysis (Linux, Temurin JDK 21)
   │     ├── Gradle dependency cache validation
   │     └── Detekt & Ktlint static analysis rules
   │
   ├── Job 2: KMP Shared Engine Tests (Linux, Temurin JDK 21)
   │     ├── Common domain tests
   │     └── Coroutine & serialization validation
   │
   └── Job 3: Desktop UI & JavaFX Validation (Windows, Temurin JDK 21)
         ├── Automated XML/FXML schema verification
         └── JavaFX desktop compilation and asset bundling
```

---

## 2. DevSecOps & Security Guidelines

1. **Zero Secret Leakage:**
   - No Gemini API keys, tokens, or credentials may ever be committed to git.
   - Use GitHub Repository Secrets (`GEMINI_API_KEY`) and load them into runtime configurations via environment variables.
2. **Deterministic Builds:**
   - All Gradle dependencies must specify exact or pinned version catalogs (`libs.versions.toml`).
   - The Gradle wrapper checksum is verified.
3. **Commit Standards:**
   - Conventional Commits enforced across all PR titles and messages (`feat:`, `fix:`, `refactor:`, `docs:`, `style:`).
   - Strict professional tone: no emojis or informal slang in commit histories.

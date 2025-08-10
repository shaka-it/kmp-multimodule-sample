# KMP Multimodule Sample

A fully modular Kotlin Multiplatform (KMP) project showcasing:
- **Shared business logic** and API definitions in `common` modules
- **Multiplatform Compose UI** targeting Android, iOS, Desktop (JVM) and Web (JS/WASM)
- **Ktor backend** with PostgreSQL persistence via JetBrains Exposed
- **Modular, feature-based architecture** with layered modules (api, data, presentation, compose)
- **Navigation** via Decompose and **Dependency Injection** via Koin

---

## Table of Contents
1. [Project Overview](#project-overview)
2. [Architecture & Modules](#architecture--modules)
3. [Tech Stack](#tech-stack)

---

## Project Overview

This repository demonstrates a complete Kotlin Multiplatform setup in 2025: one codebase, many platforms. You get:

- A **`common`** layer for business logic, models and feature wiring.
- A **Compose Multiplatform** client (`composeApp`) for Android, iOS, Desktop & Web.
- A **Ktor** server (`server`) using **PostgreSQL** and **Exposed** for data.
- **Feature modules** (e.g. `auth`, `posts`, `profile`) each split into `api`, `data`, `presentation`, and `compose` submodules as needed.

User flows, data access, UI and navigation are all implemented in Kotlin, maximizing code reuse and consistency.

---

## Architecture & Modules

### Root Modules
- **`build-logic`**  
  Custom Gradle plugins and shared build scripts (Conventions, ComposeSetup, ModuleSetup).
- **`convention`**  
  Internal convention plugin applying common settings across modules.

### Common Multiplatform Modules (`common`)
- **`common:core`**  
  Core configurations & utilities: Kotlin serialization, Coroutines, Ktor client setup, SQLDelight DB config (`AppDatabase`), and Koin DI modules.
- **`common:core-compose`**  
  Shared Compose UI toolkit: themes, components (AppToolbar, ThemedButton/TextField), desktop window settings.
- **`common:core-presentation`**  
  Presentation-layer abstractions: ViewModel and Decompose core interfaces.
- **Feature Modules** (e.g. `common:auth`, `common:posts`, `common:profile`):  
  1. **`api`**: Public interfaces and data models (repositories, DTOs).  
  2. **`data`**: Repository implementations and data sources (Ktor for remote, Settings/local storage).  
  3. **`presentation`**: UI logic components (Decompose flows, ViewModels) that depend on `api`.  
  4. **`compose`**: Compose screens/widgets for each feature, depending on `presentation` and `core-compose`.
- **`common:root`**  
  Root wiring of feature modules into a single application graph.
- **`common:umbrella`**  
  Aggregates all Koin modules and initializes `PlatformSDK` with `PlatformConfiguration`.

### Client Applications
- **`composeApp`**  
  Multiplatform Compose application targeting:
  - Android (`androidMain`)
  - iOS (`iosMain`)
  - Desktop JVM (`desktopMain`)
  - JS/WASM (`wasmJsMain`)  
  Uses Decompose for navigation and Koin for DI.
- **`iosApp`**  
  Xcode project to launch the iOS framework produced by `composeApp`.
- **`kotlin-js-store`**  
  Standalone JS/React-style web client (WASM/JS) built from Compose sources.

### Server
- **`server`**  
  Ktor-based backend exposing REST endpoints.  
  Persistence via PostgreSQL using JetBrains **Exposed** modules:
  - `exposed-core` for DSL
  - `exposed-dao` for Active-Record style
  - `exposed-jdbc` for JDBC integration

---

## Tech Stack
- **Language:** Kotlin 1.x, Kotlin Multiplatform
- **UI:** Compose Multiplatform, Decompose (navigation)
- **DI:** Koin
- **Networking:** Ktor client & server
- **Database:** SQLDelight (local), Exposed + PostgreSQL (server)
- **Build:** Gradle, custom convention plugins

---

## 📸 UX

**Desktop:**

<img width="717" height="591" alt="Desktop" src="https://github.com/user-attachments/assets/95cf47d5-0b36-4dd8-9dea-0b40c6405b5a" />

**Web:**

<img width="844" height="653" alt="Web" src="https://github.com/user-attachments/assets/fe1688a5-dad2-4784-8ce1-58c212b1df94" />

**Ios:**

<img width="430" height="893" alt="Ios" src="https://github.com/user-attachments/assets/82a37979-8881-4f12-bb39-adb26857e335" />

**Android:**

<img width="456" height="936" alt="Android" src="https://github.com/user-attachments/assets/e89a5f14-33c8-402d-94d0-531710f0e91d" />

**Server:**

<img width="1114" height="973" alt="Server" src="https://github.com/user-attachments/assets/5bee75bb-3ea1-4729-b633-8822d250d8bc" />

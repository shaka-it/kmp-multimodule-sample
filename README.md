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

**Android:**

<img width="456" height="936" alt="android" src="https://github.com/user-attachments/assets/9a6d641f-5892-4811-9e64-b61473f48fac" />

https://github.com/user-attachments/assets/a7770320-2bc3-41b1-8f3d-f9559dcd4aab

**Desktop:**

<img width="717" height="591" alt="desktop" src="https://github.com/user-attachments/assets/72aa5b65-0a4b-4a9f-a40c-8604dc523418" />

**Web:**

<img width="844" height="653" alt="web" src="https://github.com/user-attachments/assets/c4f3bfb5-5a14-4d57-b14f-547f7a5ccc68" />

**Ios:**

<img width="430" height="893" alt="ios" src="https://github.com/user-attachments/assets/0c1bf788-b6df-4130-9401-50905522a0d7" />

**Server:**

<img width="1114" height="973" alt="server" src="https://github.com/user-attachments/assets/f763245a-5946-488b-8682-87674545b948" />

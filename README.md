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
4. [Server Startup (Local)](#server-startup-local)

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
  Persistence via PostgreSQL using JetBrains **Exposed** modules

---

## Tech Stack
- **Language:** Kotlin
- **UI:** Compose Multiplatform, Decompose (navigation)
- **DI:** Koin
- **Networking:** Ktor client & server
- **Database:** SQLDelight (local), Exposed + PostgreSQL (server)
- **Build:** Gradle, custom convention plugins

---

## Server Startup (Local)

### Requirements
- Docker & Docker Compose installed

### 1) Create your env file
```bash
cp .env.example .env
```
Open `.env` and set the variables you need.  
You can point to **any** PostgreSQL instance (e.g., a managed service) or run a local Postgres via Compose.

> `.env` is automatically read by `docker-compose` at the project root.

### 2) Start the stack
**Console:**
```bash
docker compose up --build -d
# tail logs if needed
docker compose logs -f server
```

**IDE (Android Studio/IntelliJ):**
1. Open `docker-compose.yml` (root).
2. Click the green “Run” button to start services.

### 3) Stop / clean up
```bash
docker compose down         # stop & remove containers
# docker compose down -v    # also remove volumes (local DB data)
```

### Notes
- `Dockerfile` inside `server/` is used by Compose for local runs and can be used by **any** cloud that builds from a Dockerfile (examples: Koyeb, Render, Fly.io).
- If you prefer a local Postgres, keep the `db` service enabled in `docker-compose.yml` and fill the “Local DB” variables in `.env`.  
  If you use an external DB, you can run only the `server` service and set the external JDBC URL/user/password.

---

## 📸 UX

**Client:**

<img width="1920" height="1080" alt="KMP 001" src="https://github.com/user-attachments/assets/0c28e6e4-5287-444d-87d2-064f9f966a38" />

**Demo video:**

https://github.com/user-attachments/assets/a7770320-2bc3-41b1-8f3d-f9559dcd4aab

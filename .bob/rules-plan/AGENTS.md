# AGENTS.md

This file provides guidance to agents when working with code in this repository.

## Plan Mode Architecture (Non-Obvious Only)

- **NOT a monorepo**: Each `topics/` subdirectory is COMPLETELY INDEPENDENT Spring Boot app
  - No parent POM at root - each has own Spring Boot parent
  - Cannot share code between projects - intentional workshop isolation
  
- **Intentionally incomplete**: Features marked "待實作" are BY DESIGN for workshop exercises

- **Different H2 databases**: Each app uses different DB name to prevent data sharing
  - transaction-monitor: `uitc_db`
  - journey-analysis: `krtc_db`
  - station-system: `krtc_db`

- **Package names = different "companies"**: Simulates multi-client scenarios
  - `com.uitc` = 聯邦網通科技 (financial services)
  - `com.krtc` = 高雄捷運 (transportation)

- **Static resources in classpath**: `src/main/resources/static/` not `src/main/webapp/`
  - Spring Boot serves directly from classpath (no WEB-INF/JSP)

## Architectural Constraints

- All apps use port 8080 - cannot run simultaneously
- Maven wrapper MUST run from project directory, not root
- No code sharing - copy-paste if needed (workshop design)
- Lombok required - affects compilation
# AGENTS.md

This file provides guidance to agents when working with code in this repository.

## Advanced Mode Rules (Non-Obvious Only)

- **Different packages per app**: `com.uitc` (transaction-monitor) vs `com.krtc` (others) - not a typo
- **Constructor injection only**: No `@Autowired` field injection - required for workshop testability pattern
- **API prefix convention**: All controllers use `/api/` prefix (project standard, not Spring default)
- **H2 database names differ**: Check `application.yml` - `uitc_db` vs `krtc_db`
- **Static resources location**: `src/main/resources/static/` not `src/main/webapp/` (Spring Boot serving)
- **No code sharing**: Each app in `topics/` is independent - copy-paste if needed (workshop design)
- **Browser/MCP tools available**: Can test web UIs at `http://localhost:8080` after starting app

## Critical Gotchas

- Lombok required - compilation fails without IDE plugin
- Maven wrapper must run from project directory, not root
- All apps use port 8080 - cannot run simultaneously
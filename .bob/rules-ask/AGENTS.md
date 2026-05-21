# AGENTS.md

This file provides guidance to agents when working with code in this repository.

## Ask Mode Context (Non-Obvious Only)

- **Root is NOT the app**: HTML/CSS/JS in root are workshop landing page, NOT Spring Boot apps
- **"topics/" = independent apps**: Each subdirectory is a COMPLETE standalone Spring Boot project
- **Intentionally incomplete code**: Features marked "待實作" (to be implemented) are BY DESIGN for workshop
- **Multi-language docs**: `design-docs/` in Traditional Chinese (繁體中文), code/comments in English
- **Different packages = different "companies"**:
  - `com.uitc` = 聯邦網通科技 (financial services)
  - `com.krtc` = 高雄捷運 (transportation)
  - Not a typo - simulates multi-client scenarios

## Critical Context

- H2 database names differ per project - check `application.yml`, don't assume
- Package name differences are intentional, not errors
- Missing features are learning exercises, not bugs
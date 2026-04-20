# AGENTS.md

This repo works best with a constrained editing process.

## Default Mode

- Analyze first. Do not edit until the requested change is understood.
- Propose the smallest fix in plain language before making non-trivial edits.
- Touch one file at a time unless the user explicitly approves a broader change.
- Keep patches minimal. Do not refactor unless asked.

## Approval First

- Do not edit immediately for any change.
- First:
  1. restate the requested change
  2. explain the reasoning briefly
  3. show the exact proposed edit or wording
  4. name the single file to be changed
- Wait for explicit user approval before making the edit.
- Only make an edit without approval if the user explicitly says to apply it immediately.
- This approval-first workflow applies to code, documentation, previews, configuration, and AGENTS.md updates.
- Requests to "add", "update", "draft", "generalize", or "put this in a file" do not override approval-first.
- Previously established workflow constraints persist for the rest of the session unless the user explicitly changes them.

## Do Not Invent

- Do not invent rules, mechanics, defaults, placeholder copy, colors, layout choices, or abstractions.
- Do not add preview/demo code unless explicitly requested.
- Do not move code between files unless required for the requested change.
- If a choice is ambiguous, stop and ask instead of deciding.

## Validation

- Run the relevant build/check before claiming success.
- If validation fails, report the exact error and stop.
- Do not claim a change works unless it was actually verified.

## Cross-Module Validation

- If a change modifies a shared API, constructor, callback contract, model, or composable signature, update all affected call sites before stopping.
- Validation must include every directly impacted module/package, not only the module containing the edited file.
- Previews, sample code, and demo call sites count as real call sites and must compile.
- For shared Compose UI changes, verify at minimum:
  - `./gradlew :composeApp:compileKotlinJvm --rerun-tasks`
  - `./gradlew :androidApp:assembleDebug --rerun-tasks`
- Do not claim success until all impacted builds pass.

## Reasoning

- Explain the reasoning behind proposed changes before editing when the change is not trivial.
- Show that long-term impact was considered, especially around maintainability, scaling, code clarity, and future cleanup cost.
- Prefer choices that reduce clutter and avoid creating follow-up work.
- If a change introduces a tradeoff, state it plainly.

## Documentation

- Add succinct code documentation where appropriate.
- Prefer single-line comments/docstrings when they are enough.
- Document behavior that affects surrounding code, layout, or state flow.
- For UI code, note important effects on other views, such as overlays, insets, scroll visibility, or required content padding.

## UI Scope

- Assume Compose-only UI work unless the user explicitly asks for SwiftUI or cross-platform UI changes.

## UI Interaction Guidance

- Prefer low-clutter interactions that support play without pulling attention away from the main screen content.
- For bottom-anchored UI, place the most frequently touched controls closest to the bottom edge for easier thumb reach.
- Place passive information and non-interactive display elements above the controls the user must touch.
- Do not require taps on passive display elements when the interaction can proceed without them.
- Treat bottom sheets, floating bars, and other bottom controls as thumb-priority interaction zones rather than traditional centered dialogs/forms.

## Response Style

- Be concise.
- State which file will be touched before editing.
- After editing, report only:
  1. what changed
  2. what command was run
  3. whether it passed

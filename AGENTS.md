# Wrenchway Appointments

This project was generated from a donor template (`/home/codex-agent/constructor-white-projects/assets/service-template`) by
TemplateForge. Application type: **Service**.

- Package: `lukasztruckfix.garage.wrenchwayappointments`
- Project name: `Wrenchway Appointments`

## Generated content structure

- `../data/model/ServiceCategory` — category definitions; values must
  reuse the string resources created for categories, not new literals.
- `../data/repository/ServiceRepository.kt` — catalog of service
  objects (8-15 total), evenly distributed across categories, each with
  a 3-5 sentence description, a realistic price, and an image link
  pointing at an Unsplash or Pexels photo.
- `../data/model/Benefit.kt` (`serviceBenefits`) — 3 short benefit
  statements (quality, warranty, necessity) with icons matching the text.
- Category string resources follow the naming convention
  `service_category_<category_name>_title`.

## Working in this repo

- Keep changes scoped to what the current task asks for — this is a
  freshly generated project, not a hand-maintained one, so don't
  "clean up" unrelated files.
- Preserve the existing package structure and naming conventions found
  in the generated code instead of introducing new patterns.
- Do not modify build/gradle configuration, project name, package name,
  or app id — those were set intentionally during generation.
- Do not run Gradle builds, dependency downloads, Android lint, or emulator
  tests. The project is published as-is after your work. Validate only the
  modified data and resource files with lightweight local checks.
- Do not change business logic or UI code — limit changes strictly to
  the placeholder data described above (categories, resources,
  repository objects, benefits).
- When filling in placeholder data (strings, repository objects, etc.),
  match the style and format already used elsewhere in the file.
- All generated text (category names, string resources, descriptions,
  benefit text) must be written in English, even if the task's input
  (e.g. the topic) is given in another language.

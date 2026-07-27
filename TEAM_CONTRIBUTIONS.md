# Solo Project Contributions

**Student:** Shacquile Bray-Telesford  
**Project role:** Sole developer

This is an individual project. Shacquile Bray-Telesford is responsible for the design, implementation, testing, documentation, and presentation of all three deliverables.

| Deliverable | Work completed | Main files and areas |
|---|---|---|
| Deliverable 1 — Front-End & Database | Designed the SkillSwap Academy domain; created the Thymeleaf and Bootstrap pages; implemented workshop creation, validation, persistence, details, catalogue search, category/level filters, sorting, pagination, and startup sample data. | `src/main/resources/templates/`, `static/css/`, `Workshop.java`, `WorkshopController.java`, `WorkshopService.java`, `WorkshopRepository.java`, `data.sql` |
| Deliverable 2 — Security & Users | Implemented registration, BCrypt password encoding, database-backed `UserDetails`, Student/Instructor/Admin roles, protected routes, custom login/logout, user dashboard, and the admin workshop management area. | `AppUser.java`, `Role.java`, `RegistrationController.java`, `SecurityConfig.java`, `CustomUserDetailsService.java`, `SeedUsersConfig.java`, `templates/auth/`, `templates/admin/` |
| Deliverable 3 — Microservices & DevOps | Built the separate Skill Verification service with its own database and business logic; implemented full CRUD REST endpoints, multi-parameter search, HTTP Basic Auth, H2 dev and PostgreSQL QA profiles, Docker Compose, `RestTemplate` integration, error handling, and the combined admin dashboard. | `skill-verification-service/`, `VerificationServiceClient.java`, `VerificationRequest.java`, `VerificationRecordDto.java`, `ApplicationConfig.java`, `docker-compose.yml` |
| Documentation & demonstration | Wrote the README, architecture notes, run instructions, QA/Docker instructions, submission checklist, and demonstration script. | `README.md`, `SUBMISSION_CHECKLIST.md`, `DEMO_SCRIPT.md`, `TEAM_CONTRIBUTIONS.md` |

## Presentation ownership

- Deliverable 1 presenter: Shacquile Bray-Telesford
- Deliverable 2 presenter: Shacquile Bray-Telesford
- Deliverable 3 presenter: Shacquile Bray-Telesford
- Docker/QA demonstration: Shacquile Bray-Telesford

## Commit evidence

Because this is a solo project, all legitimate commits should be authored by Shacquile Bray-Telesford. Before submission, confirm that the GitHub repository shows incremental commits for the work completed. Do not invent commit hashes or claim work that cannot be explained during the presentation.

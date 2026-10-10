**Project: Smart Wildlife Conservation & Protected Area Management** 🌿

We're building a web application to help forest officers manage wildlife, protected areas, patrols, incidents, and conservation activities in one place.

**Main Features:**
- **Dashboard:** Overview of animals, incidents, patrols, rescues, and high-priority zones.
- **Wildlife Management:** Register animals, track species, health status, and locations.
- **Protected Area Management:** Manage forest zones, habitats, and protected areas.
- **Patrol Management:** Schedule patrols, assign officers, and track patrol status.
- **Incident Reporting:** Report poaching, forest fires, illegal logging, and human-wildlife conflicts.
- **Rescue Management:** Track injured animals, rescue teams, and rehabilitation.
- **Staff & Resources:** Manage officers, equipment, and assignments.
- **Conservation Programmes:** Track habitat restoration and other conservation initiatives.
- **Smart Priority Scoring:** Rank zones based on incident severity, unresolved threats, and time since the last patrol.
- **Reports & Analytics:** Search records, view statistics, and compare incidents across zones.

**Technology Stack:** React, Java, Spring Boot, REST APIs, Spring Data JPA, and PostgreSQL.

**Architecture:** Three independent microservices:
1. Wildlife Management — animal and species records.
2. Operations Management — patrols, incidents, rescues, staff, and resources.
3. Conservation Management — protected areas, programmes, and priority scoring.

Each service has its own PostgreSQL database, and the frontend communicates directly with the services.

**Our main differentiator:** A smart priority system that identifies which protected areas need attention most and helps officers decide where to send patrol teams.
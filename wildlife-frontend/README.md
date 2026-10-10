# WildGuard React frontend

Run `npm install` and then `npm run dev`. Open the Vite URL, normally http://localhost:5173.

Backend defaults:
- Wildlife: http://localhost:8081
- Operations: http://localhost:8082
- Conservation: http://localhost:8083

Make sure all Spring Boot services are running. If the browser reports CORS errors, allow `http://localhost:5173` in the relevant controller.

The generic CRUD pages use the endpoint and field names in `src/config.js`. Check those against your current backend models before using create/update operations.

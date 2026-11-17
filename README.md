# Hierarchical Client-Car Management System

A fullstack web application that implements hierarchical CRUD operations for Categories (Clients) and Elements (Cars), developed as part of laboratory assignments.

## About the Project

This project started as a practical implementation of modern web application concepts using modern JavaScript frameworks and backend microservices/gateways.

The application establishes a parent-child relationship between Clients and their respective Cars, featuring dynamic nested routing, REST API integration, form validations, and a consistent custom design system.

The entire project, from the initial architectural setup to the fully functional solution, was developed iteratively with the help of AI.

## Features

- **Client Management (Categories)** – View, create, edit, and delete clients with a user-friendly interface.
- **Car Management (Elements)** – View, add, edit, and delete cars associated with specific clients.
- **Hierarchical Navigation** – Seamless routing structure handling nested paths (e.g., `/clients/:clientId/cars/:carId`).
- **Resource Transfer & Updates** – Ability to manage element attributes, including reassigning ownership between clients.
- **Form Validation** – Input validation for models, types, production years, and client details.
- **Custom UI/UX ("Bordello-style")** – Consistent, custom-styled dark/red/white theme applied across all components, forms, and detail views.

## Tech Stack

### Backend:
- Java
- Spring Boot
- REST API (Hierarchical endpoints: `/api/clients/:clientId/cars/:carId`)

### Frontend:
- Angular (Standalone Components, Server-Side Rendering / SSR)
- TypeScript
- RxJS (Reactive programming & asynchronous data handling)
- Angular Router (Nested routes with optimized route ordering/priority)
- HTML5, CSS3

## Installation & Running

### 1. Backend (Spring Boot)
1. Clone or download the backend repository.
2. Open the project in your preferred IDE or terminal.
3. Run the Spring Boot application using Maven:
   ```bash
   ./mvnw spring-boot:run
cat << 'EOF' > README.md
# Algorithmic Chess Matchmaking API ♟️

A high-performance, Spring Boot-based chess engine and matchmaking API designed to demonstrate advanced **Object-Oriented Programming (OOP)**, **Data Structures**, and **Concurrent System Design**.

## 🚀 Key Technical Highlights

### 1. Advanced Core Java & OOP (Polymorphism)
- **Abstract Domain Modeling:** Utilized an abstract `Piece` class to define core behaviors, leveraging **Polymorphism** to allow specific pieces (Knight, Rook, Queen, etc.) to resolve their own movement logic at runtime.
- **Encapsulated State:** Employed a modular `Board` and `Position` model to manage the 8x8 grid, ensuring strict data integrity and separation of concerns.

### 2. Algorithmic Rules Engine (DSA)
- **Sliding Window Logic:** Implemented ray-casting algorithms for "sliding" pieces (Rook, Bishop, Queen) to calculate valid trajectories until obstructed by board boundaries or other pieces.
- **Backtracking Checkmate Detector:** Developed a sophisticated $O(1)$ space-complexity backtracking algorithm to simulate "hypothetical" moves, verifying if any legal move can escape a **Check** state.
- **Check Detection:** Engineered a board-wide threat-assessment algorithm that scans enemy attack vectors to protect the King's coordinate state.

### 3. Scalable Backend Architecture (SDE Focus)
- **Concurrent Game Management:** Utilized `ConcurrentHashMap` within the Service layer to manage thousands of isolated, thread-safe game sessions simultaneously.
- **RESTful API Design:** Designed a "Headless" backend with DTO-based request handling for creating matches and submitting moves via JSON payloads.
- **UUID Matchmaking:** Implemented unique session tracking using `UUID` to ensure secure and distinct game identification.

## 🛠 Tech Stack
- **Language:** Java 17
- **Framework:** Spring Boot 3.x
- **Build Tool:** Gradle
- **Tools:** VS Code, Postman/cURL (for API testing), Lombok

## 🚦 Getting Started

### Prerequisites
- Java 17 or higher
- Gradle (included via `./gradlew`)

### Running the Server
```bash
./gradlew bootRun
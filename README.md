# Database Data Processing System

This project, titled Database Data Processing System, is an application designed to handle data from databases efficiently. It aims to implement SOLID principles and GRASP patterns, emphasizing high cohesion and low coupling. The system interacts with relational databases using JDBC and facilitates various data processing tasks.

## Table of Contents

- [Overview](#overview)
- [Task](#task)
- [Database Schema](#database-schema)
- [Implementation](#implementation)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Overview

The Database Data Processing System focuses on managing data from databases effectively. It operates without a user interface but is extensively tested using dedicated test classes. The system aims to adhere to SOLID and GRASP principles, although the use of architectural patterns like DAO is optional but recommended. CRUD operations are implemented for each entity, supporting any relational database, with a preference for local file storage options like H2 or SQLite.

## Task

- Implement SOLID and GRASP principles in the design and implementation of the Database Data Processing System.
- Develop a persistence layer for managing data.
- Design a database schema comprising at least four tables, including one-to-many and many-to-many relationships.
- Conduct functional testing using dedicated test classes with appropriate test data.

## Database Schema

The database schema for the Database Data Processing System consists of the following tables:
1. **Animals**: Contains information about various animals.
2. **Enclosures**: Stores details about enclosures where animals are housed.
3. **Employees**: Holds data related to employees managing the database.
4. **Visitors**: Stores information about visitors accessing the database.

## Implementation

The implementation of the Database Data Processing System follows these guidelines:
- Each entity has CRUD operations implemented.
- Data validation and error handling mechanisms are integrated into the system.
- The project adheres to a three-layer architecture, separating data access, business logic, and presentation layers.
- Code organization maintains high cohesion and low coupling for improved maintainability and scalability.

## Testing

Functional testing of the Database Data Processing System is conducted using dedicated test classes. Test data ensures the correctness of CRUD operations and data integrity.

## Contributing

Contributions to the development of the Database Data Processing System are encouraged. Follow these steps to contribute:
1. Fork the repository.
2. Create a new branch for your feature or bug fix: `git checkout -b feature/new-feature`.
3. Make changes and commit them: `git commit -m "Add new feature"`.
4. Push changes to your fork: `git push origin feature/new-feature`.
5. Open a pull request.

## License

This project is licensed under the [MIT License](LICENSE). Refer to the LICENSE file for details.

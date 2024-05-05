# What is SkillSync?
SkillSync is an open-source peer-to-peer learning platform.

## Features

Yet to be discussed.

## Tech Stack

SkillSync uses Next (TypeScript) for the frontend website and uses Spring (Java) for the backend.


## Contribution

### Workflow

This project uses a 3 legged branching strategy with `main` being the production version of the code; the main branch reflects the current version that is currently live to the public. The `development` branch contains the active changes that are made by the engineers to implement new features or fixes; pull requests are needed to contribute to `development` with 2 approvals from other engineers.

When you are contributing to the project, you will need to create a new branch based on the `development` branch in a fork of your own. Please format the branch with a prefix and a name that best describes the type of change being implemented these contain:

- `feature/`(New feature branches)
- `fix/` (Fixes for branches)
- `task/`(Generic branches for mundane tasks, such as updating a lang file or dependencies)

Once you have selected the branch type please create a branch name as a suffix. For example, if you had a branch that implemented a login button on the home page you would call it `feature/home-page-login-button`

Upon completing your changes **that are tested**, please raise a pull request that merges **into development**, fill out the template and drop a link to the pull request in the "Ship-In-90" [discussion](https://discord.com/channels/368853404723707914/1073307477405335592) channel. You should also move the original issue into the `Code Review` column on the board. Upon review and approval, your changes will be submitted to the development branch. Then once the version is ready, it is released into production. 

### Tasks

Within this project, contributors can help to work on tasks. Tasks will be split into different issues. To claim a task, find the issue that includes your chosen task and comment that you would like to work on a specific part of the issue. It's key that we communicate with each other to create a more productive and efficient development environment. You can also mention that you've claimed a task in the [discussion](https://discord.com/channels/368853404723707914/1073307477405335592) channel. 

Make sure to read all the discussion within the issue to know what needs to be completed, there will be a checklist of tasks and who's working on them in the issue description to help things flow smoothly.

If you would like to unclaim a task, make sure to comment on the relevant issue to let everybody else know that the issue is open for contribution. 

You can also collaborate on a task with another contributor, just let the rest of the team know in the issue discussion.

### Spring Boot Backend Guidelines

1. **Project Structure:**
```
backend
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.skillsync.backend
│   │   │       ├── config           // Configuration classes
│   │   │       ├── controller       // REST API controllers
│   │   │       ├── exception        // Custom exception classes
│   │   │       ├── model            // Entity classes
│   │   │       ├── payload          // Data Transfer Objects and request/response objects
│   │   │       ├── repository       // Data access layer
│   │   │       ├── security         // Application security (authentication, etc.)
│   │   │       ├── service          // Business logic layer
│   │   │       ├── utils            // Utilities
│   │   │       └── BackendApplication.java   // Main class
│   │   │
│   │   └── resources
│   │       ├── static              // Static resources (css, js, etc.)
│   │       ├── templates           // HTML templates
│   │       ├── applicationConfigTemplate.yaml  // Application properties example
│   │       └── application.yaml    // Application properties
│   │
│   └── test
│       └── java
            └── com.skillsync.backend // Test classes
│
├── .gitignore                      // Git ignore file
├── pom.xml                         // Maven dependencies
└── etc.
```
2. **Coding Conventions:**

    - Class names should be nouns in UpperCamelCase.
    - Method names should be verbs in lowerCamelCase.
    - Variable names should be in lowerCamelCase.
    - Constant names should be in uppercase with underscores separating words.
    - Use 4 spaces for indentation. Follow standard Java formatting practices.
    - ???
    - etc.

3. **Usage:**
    - install (and use) Java 17 (and *optionally* Maven)
    - before running the app set up a MySQL database and create the appropriate configuration file in the project's resources
    - `.\mvnw spring-boot:run` to run the app (or just `mvn` if you have Maven)
    - `.\mvnw test` to run the tests (or `mvn`)
    - access the app at **http://localhost:8080**
    - access endpoint documentation at **http://localhost:8080/swagger-ui/index.html** (you can also try the endpoints here)
4. **???:**
5. **etc.:**
---

![alt text](https://images-ext-1.discordapp.net/external/ZodgpNW25bKMHly3yapNdxjUcH8s__4xR5pqVxXy1dA/https/cdn-longterm.mee6.xyz/plugins/embeds/images/368853404723707914/668c5398d4a84cfbd3475ae6d201c456e398f819dcbf513c719b6d00aac67756.png?width=848&height=676)

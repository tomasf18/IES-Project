---
id: github-workflow
title: GitHub Workflow
sidebar_label: GitHub Workflow
---

# GitHub Workflow

This document outlines the branch naming conventions, issue naming conventions, and commit message guidelines for the **Smart Training System** project to ensure a clean, organized, and easily navigable codebase.

## Branch Naming Convention

To maintain a well-organized repository, we follow the following branch naming conventions:

### Feature Branch
`feature/<feature_name>`  
- **Purpose**: Used for developing new features or functionality.
- **Example**: `feature/user-authentication`

### Bugfix Branch
`bugfix/<bug_name>`  
- **Purpose**: Used for fixing bugs or issues identified in the code.
- **Example**: `bugfix/login-error`

### Hotfix Branch
`hotfix/<critical_bug_name>`  
- **Purpose**: Reserved for critical fixes, often in production environments.
- **Example**: `hotfix/critical-login-failure`

### Experimental Branch
`experiment/<experiment_name>`  
- **Purpose**: Used for testing new ideas, experimental features, or concepts.
- **Example**: `experiment/new-algorithm`

### Documentation Branch
`docs/<section>`  
- **Purpose**: Used exclusively for updating project documentation.
- **Example**: `docs/architecture-section`

### Release Branch
`release/<version_number>`  
- **Purpose**: Used to prepare a specific version of the project for release.
- **Example**: `release/v1.0.0`

---

## Issue Naming Convention

When creating issues in GitHub, we follow these naming conventions to clearly identify the type of issue and its purpose:

### Feature Request
`[Feature]: <short description>`  
- **Purpose**: For requesting new features or functionality.
- **Example**: `[Feature]: Add User Authentication`

### Bug Report
`[Bug]: <short description>`  
- **Purpose**: For reporting bugs or errors in the system.
- **Example**: `[Bug]: Fix login error on mobile devices`

### Task / Improvement
`[Task]: <short description>`  
- **Purpose**: For general tasks, improvements, or refactoring.
- **Example**: `[Task]: Refactor database connection logic`

---

## Commit Message Guidelines

Commit messages should be clear and descriptive to make it easier to track changes over time. Follow this format:

### Commit Format:
`[Task]: <short description>`

**Example**:
- `docs: Project Overview, Introduction, Product Concept, Architecture, Information Perspective`

This format helps maintain a clean and understandable commit history.

---

By following these conventions, the project remains organized, making collaboration more efficient and ensuring that the repository is easy to navigate for all contributors.


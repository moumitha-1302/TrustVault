# TrustVault — AI Prompts and Development Log

## 1. Project Overview

TrustVault is a secure document management web application built using Java, Spring Boot, Spring Security, Maven, and PostgreSQL.

The application provides a login page, dashboard, document upload, document listing, download, deletion, access-control information, and a security-status page.

## 2. AI-Assisted Development

AI assistance was used to support implementation, troubleshooting, and documentation.

The following prompts summarize the main development tasks:

### Task 1: Project Setup
**Prompt summary:** Help set up and run a Spring Boot application using Maven and Java.

**Work completed:** Configured the project and started the application locally.

### Task 2: Database Configuration
**Prompt summary:** Help configure PostgreSQL and troubleshoot database connection settings.

**Work completed:** Configured the PostgreSQL connection using application properties and environment variables for credentials.

### Task 3: Authentication
**Prompt summary:** Help configure Spring Security login and require authentication for application pages.

**Work completed:** Configured form-based login and authentication requirements.

### Task 4: Document Management
**Prompt summary:** Help implement document upload, listing, download, and deletion.

**Work completed:** Added local file storage, a 10 MB upload limit, allowed-extension checks, and file-path validation.

### Task 5: Access Control
**Prompt summary:** Help display the authenticated user and authorities and configure administrator-only document deletion.

**Work completed:** Added an Access Control page and configured a `ROLE_ADMIN` account with a server-side authorization rule.

### Task 6: Security Status
**Prompt summary:** Help display the security protections configured in the application.

**Work completed:** Added a Security Status page summarizing selected protections and outstanding limitations.

### Task 7: Troubleshooting
**Prompt summary:** Help diagnose application startup, browser connection, and page-routing problems.

**Work completed:** Restarted the application and verified that the Access Control and Security Status pages opened.

## 3. Testing and Corrections

- Restarted the Spring Boot application after code changes.
- Verified that login works with the configured account.
- Verified that the Access Control page displays the account and authorities.
- Verified that the Security Status page opens from the dashboard.
- Identified that document deletion authorization still requires direct testing.

## 4. Responsible AI Use

AI-generated code was reviewed and tested during development. AI assistance does not replace security testing, code review, or independent verification.

This log summarizes the development tasks; it is not a verbatim transcript of every prompt.

## 5. Remaining Work

- Test upload, download, and deletion with temporary files.
- Test authorization using an account without administrator privileges.
- Add proper per-user document ownership and permissions.
- Improve file-content validation and error handling.
- Review deployment security and secret management.
- Complete final documentation and submission checks.
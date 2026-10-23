# TrustVault — Threat Model

## 1. System Overview

TrustVault is a Java Spring Boot web application for managing documents. It uses Spring Security for authentication and authorization, PostgreSQL for database connectivity, and local file storage for uploaded documents.

## 2. Assets to Protect

- User login credentials.
- Uploaded documents.
- Document integrity and availability.
- Access-control rules.
- Database credentials and application secrets.

## 3. Potential Threats and Mitigations

| Threat | Existing protection | Remaining limitation |
|---|---|---|
| Unauthenticated access | Spring Security requires authentication for application routes. | Authentication configuration must be reviewed before deployment. |
| Unauthorized document deletion | Deletion route requires `ROLE_ADMIN`. | The current configuration has one administrator account; non-admin authorization testing is still needed. |
| Malicious file uploads | Upload size and filename-extension restrictions are implemented. | Extension checks alone do not prove that file contents are safe. |
| Path traversal | Normalized paths are checked against the upload directory. | File handling still requires security testing. |
| Cross-site request forgery (CSRF) | Spring Security's default CSRF protection remains enabled. | All state-changing routes and forms must be tested. |
| Credential exposure | Database and application passwords are supplied through environment variables. | Environment variables must be protected, and secrets must never be committed to source control. |
| Unauthorized document downloads | Download routes require authentication. | Per-user document ownership and download permissions are not implemented. |
| Data loss | Documents are stored locally. | Backups and recovery procedures are not yet established. |
| Data exposure from a compromised server | Access requires authentication. | Encryption at rest and production-grade server hardening have not been implemented. |
| Lack of accountability | The application exposes account information. | A persistent audit log for uploads, downloads, and deletions is not implemented. |

## 4. Security Controls Currently Configured

- Spring Security form-based authentication.
- Authenticated access to application pages.
- An administrator role for the configured account.
- Server-side authorization for document deletion.
- Default CSRF protection.
- HTTP response-header protections configured in the security configuration.
- Upload size and extension restrictions.
- File-path validation for download and deletion.
- Environment-variable-based database and application credentials.

These controls have not all undergone independent security testing.

## 5. Recommended Future Improvements

1. Implement separate user accounts and roles.
2. Associate each document with its owner and enforce ownership checks on every document operation.
3. Validate actual file content and consider malware scanning.
4. Add audit logging without recording passwords or sensitive document contents.
5. Use HTTPS and secure production cookie settings.
6. Encrypt sensitive stored documents and establish secure backups.
7. Add automated tests for authentication, authorization, file uploads, downloads, and deletion.
8. Review dependency vulnerabilities and production configuration.
9. Verify that secrets and uploaded files are excluded from public source control.

## 6. Scope and Limitations

TrustVault is a development-stage project. The security-status page is informational and is not a security audit or certification. The application should not be represented as production-ready until the remaining controls have been implemented and tested.

## 7. Conclusion

TrustVault includes foundational authentication, upload validation, path checks, and administrator authorization. Further work is required to support secure multi-user document ownership, comprehensive testing, auditing, and production deployment.
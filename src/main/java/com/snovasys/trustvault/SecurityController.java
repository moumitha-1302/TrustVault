package com.snovasys.trustvault;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {

    @GetMapping("/security")
    @ResponseBody
    public String securityStatus() {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <title>TrustVault | Security Status</title>
                <style>
                    body {
                        margin: 0;
                        padding: 30px;
                        font-family: Arial, sans-serif;
                        background: #f4f6fb;
                        color: #202945;
                    }
                    .card {
                        max-width: 650px;
                        margin: 30px auto;
                        padding: 28px;
                        background: white;
                        border-radius: 14px;
                        box-shadow: 0 4px 15px #00000012;
                    }
                    h1 { color: #172554; }
                    .item {
                        padding: 14px;
                        margin: 12px 0;
                        background: #f1f5f9;
                        border-radius: 8px;
                    }
                    .status {
                        color: #166534;
                        font-weight: bold;
                    }
                    .notice {
                        color: #92400e;
                        background: #fef3c7;
                        padding: 14px;
                        border-radius: 8px;
                    }
                    a { color: #1d4ed8; }
                </style>
            </head>
            <body>
                <div class="card">
                    <h1>TrustVault Security</h1>
                    <p>Security features currently configured in the application.</p>

                    <div class="item">
                        <strong>Login protection</strong>
                        <p class="status">Enabled</p>
                        <p>Authentication is required to access application pages.</p>
                    </div>

                    <div class="item">
                        <strong>CSRF protection</strong>
                        <p class="status">Enabled by default</p>
                        <p>Spring Security protects supported state-changing requests.</p>
                    </div>

                    <div class="item">
                        <strong>File upload restrictions</strong>
                        <p class="status">Configured</p>
                        <p>Uploads are limited to 10 MB and selected file extensions.</p>
                    </div>

                    <div class="item">
                        <strong>File path validation</strong>
                        <p class="status">Configured</p>
                        <p>Download and delete paths are checked before use.</p>
                    </div>

                    <div class="notice">
                        <strong>Important:</strong>
                        This is a summary of configured protections, not a security audit.
                        Multi-user permissions and production hardening still need work.
                    </div>

                    <p><a href="/">Back to Dashboard</a></p>
                </div>
            </body>
            </html>
            """;
    }
}
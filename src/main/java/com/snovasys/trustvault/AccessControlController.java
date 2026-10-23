
package com.snovasys.trustvault;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.Authentication;

@Controller
public class AccessControlController {

    @GetMapping("/access-control")
    @ResponseBody
    public String accessControl(Authentication authentication) {
        String username = authentication.getName();
        String permissions = authentication.getAuthorities().toString();

        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <title>TrustVault | Access Control</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background: #f4f6fb;
                        color: #202945;
                        padding: 30px;
                    }
                    .card {
                        max-width: 600px;
                        margin: 40px auto;
                        padding: 28px;
                        background: white;
                        border-radius: 14px;
                        box-shadow: 0 4px 15px #00000012;
                    }
                    h1 { color: #172554; }
                    .item {
                        background: #eff6ff;
                        padding: 14px;
                        margin: 12px 0;
                        border-radius: 8px;
                    }
                    a { color: #1d4ed8; }
                </style>
            </head>
            <body>
                <div class="card">
                    <h1>Access Control</h1>
                    <p>View your TrustVault account access details.</p>
                    <div class="item">
                        <strong>Signed-in user:</strong>
                        <span>%s</span>
                    </div>
                    <div class="item">
                        <strong>Assigned authorities:</strong>
                        <span>%s</span>
                    </div>
                    <p>Access is restricted to authenticated users.</p>
                    <a href="/">Back to Dashboard</a>
                </div>
            </body>
            </html>
            """.formatted(escapeHtml(username), escapeHtml(permissions));
    }

    private String escapeHtml(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}

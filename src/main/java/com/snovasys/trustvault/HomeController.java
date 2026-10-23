package com.snovasys.trustvault;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>TrustVault Dashboard</title>
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <style>
                    body {
                        margin: 0;
                        font-family: Arial, sans-serif;
                        background: #f4f6fb;
                        color: #202945;
                    }
                    header {
                        background: #172554;
                        color: white;
                        padding: 22px 7%;
                    }
                    header h1 { margin: 0; }
                    header p { margin-bottom: 0; color: #dbeafe; }
                    main { padding: 35px 7%; }
                    .welcome { margin-bottom: 28px; }
                    .cards {
                        display: grid;
                        grid-template-columns:
                            repeat(auto-fit, minmax(220px, 1fr));
                        gap: 20px;
                    }
                    .card {
                        background: white;
                        padding: 24px;
                        border-radius: 14px;
                        box-shadow: 0 4px 15px #0000000c;
                    }
                    .card h2 { font-size: 20px; }
                    .card p { color: #64748b; line-height: 1.5; }
                    .tag {
                        display: inline-block;
                        background: #dbeafe;
                        color: #1d4ed8;
                        padding: 6px 10px;
                        border-radius: 20px;
                        font-size: 12px;
                    }
                    footer {
                        text-align: center;
                        padding: 25px;
                        color: #64748b;
                    }
                </style>
            </head>
            <body>
                <header>
                    <h1>TrustVault</h1>
                    <p>Your secure document management system</p>
                </header>
                <main>
                    <section class="welcome">
                        <h2>Welcome to your Dashboard!</h2>
                        <p>Manage your documents with confidence.</p>
                    </section>
                    <section class="cards">
                        <div class="card">
                            <h2>My Documents</h2>
                            <p>View and manage your uploaded documents.</p>
                            <a href="/documents">Open My Documents</a>
                        </div>
                        <div class="card">
    <h2>Upload Documents</h2>
    <p>Upload and manage files in your vault.</p>
    <a href="/documents">Upload a Document</a>
</div>
                        <div class="card">
                            <h2>Access Control</h2>
<p>View your account and assigned permissions.</p>
<a href="/access-control">Open Access Control</a>
                        </div>
                        <div class="card">
                            <h2>Security</h2>
<p>Review the security protections enabled in TrustVault.</p>
<a href="/security">View Security Status</a>
                        </div>
                    </section>
                </main>
                <footer>TrustVault | Secure Document Management</footer>
            </body>
            </html>
            """;
    }
}
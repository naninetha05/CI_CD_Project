# Java Application - Place in: java-app/src/main/java/com/cicdproject/HelloServlet.java

package com.cicdproject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>CICD Java Application</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 50px; }");
            out.println("h1 { color: #333; }");
            out.println(".info { background-color: #f0f0f0; padding: 20px; border-radius: 5px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Welcome to CICD Java Application</h1>");
            out.println("<div class='info'>");
            out.println("<p><strong>Application Status:</strong> Running Successfully ✓</p>");
            out.println("<p><strong>Deployment Time:</strong> " + getCurrentDateTime() + "</p>");
            out.println("<p><strong>Server:</strong> " + request.getServerName() + "</p>");
            out.println("<p><strong>Java Version:</strong> " + System.getProperty("java.version") + "</p>");
            out.println("<p><strong>OS:</strong> " + System.getProperty("os.name") + "</p>");
            out.println("</div>");
            out.println("<p>This application was deployed using:</p>");
            out.println("<ul>");
            out.println("<li>Terraform for Infrastructure Provisioning</li>");
            out.println("<li>Ansible for Server Configuration</li>");
            out.println("<li>Jenkins for CI/CD Orchestration</li>");
            out.println("<li>Maven for Application Build</li>");
            out.println("<li>Tomcat for Application Hosting</li>");
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}

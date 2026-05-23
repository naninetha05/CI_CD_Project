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
            out.println("<head><title>CICD Java Application</title></head>");
            out.println("<body>");
            out.println("<h1>Welcome to CICD Java Application</h1>");
            out.println("<p><strong>Status:</strong> Running Successfully</p>");
            out.println("<p><strong>Deployed:</strong> " + getCurrentDateTime() + "</p>");
            out.println("<p><strong>Server:</strong> " + request.getServerName() + "</p>");
            out.println("<p><strong>Java:</strong> " + System.getProperty("java.version") + "</p>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}

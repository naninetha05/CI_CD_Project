package com.cicdproject;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for HelloServlet.
 * Uses Mockito to mock HttpServletRequest and HttpServletResponse
 * so the servlet can be tested without a running container.
 */
public class HelloServletTest {

    private HelloServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new HelloServlet();
    }

    @Test
    public void doGet_shouldReturnValidHtmlResponse() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(request.getServerName()).thenReturn("localhost");
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        String output = stringWriter.toString();
        assertTrue("Response should be valid HTML", output.contains("<html>"));
        assertTrue("Response should contain closing HTML tag", output.contains("</html>"));
    }

    @Test
    public void doGet_shouldIncludeApplicationTitle() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(request.getServerName()).thenReturn("localhost");
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        String output = stringWriter.toString();
        assertTrue("Response should contain application title",
                output.contains("CICD Java Application"));
    }

    @Test
    public void doGet_shouldSetHtmlContentType() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(request.getServerName()).thenReturn("localhost");
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        verify(response).setContentType("text/html;charset=UTF-8");
    }

    @Test
    public void doGet_shouldIncludeServerNameInResponse() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(request.getServerName()).thenReturn("app-server.example.com");
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        String output = stringWriter.toString();
        assertTrue("Response should include the server name from the request",
                output.contains("app-server.example.com"));
    }

    @Test
    public void doGet_shouldIncludeDeploymentTimestamp() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(request.getServerName()).thenReturn("localhost");
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        String output = stringWriter.toString();
        assertTrue("Response should include a deployment timestamp",
                output.contains("Deployed"));
    }

    @Test
    public void doPost_shouldProduceSameOutputAsDoGet() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(request.getServerName()).thenReturn("localhost");
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        String output = stringWriter.toString();
        assertTrue("POST handler should delegate to GET and return HTML",
                output.contains("<html>"));
    }

    @Test
    public void environment_javaVersionShouldBeAvailable() {
        String javaVersion = System.getProperty("java.version");
        assertNotNull("java.version system property should be set", javaVersion);
        assertFalse("java.version should not be empty", javaVersion.isEmpty());
    }

    @Test
    public void environment_shouldRunOnSupportedJavaVersion() {
        String javaVersion = System.getProperty("java.version");
        int majorVersion = parseMajorVersion(javaVersion);
        assertTrue("Application requires Java 11 or higher, found: " + javaVersion,
                majorVersion >= 11);
    }

    /**
     * Parses the major version from a Java version string.
     * Handles both legacy (1.8.x) and modern (11.x, 17.x) formats.
     */
    private int parseMajorVersion(String version) {
        if (version.startsWith("1.")) {
            return Integer.parseInt(version.split("\\.")[1]);
        }
        return Integer.parseInt(version.split("[.\\-]")[0]);
    }
}

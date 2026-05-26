package com.cicdproject;

import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
    public void testDoGetReturnsHtml() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(request.getServerName()).thenReturn("localhost");
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        String output = stringWriter.toString();
        assertTrue("Response should contain HTML", output.contains("<html>"));
        assertTrue("Response should contain app title", output.contains("CICD Java Application"));
    }

    @Test
    public void testDoGetSetsContentType() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(request.getServerName()).thenReturn("localhost");
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        verify(response).setContentType("text/html;charset=UTF-8");
    }

    @Test
    public void testDoGetContainsServerName() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(request.getServerName()).thenReturn("13.207.110.172");
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        String output = stringWriter.toString();
        assertTrue("Response should contain server name", output.contains("13.207.110.172"));
    }

    @Test
    public void testDoPostDelegatesToDoGet() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(request.getServerName()).thenReturn("localhost");
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        String output = stringWriter.toString();
        assertTrue("POST should return same HTML as GET", output.contains("<html>"));
    }

    @Test
    public void testJavaVersionIsAvailable() {
        String javaVersion = System.getProperty("java.version");
        assertNotNull("Java version should not be null", javaVersion);
        assertFalse("Java version should not be empty", javaVersion.isEmpty());
    }

    @Test
    public void testRunningOnCorrectJavaVersion() {
        String javaVersion = System.getProperty("java.version");
        boolean isJava11OrHigher = javaVersion.startsWith("11") ||
                                   javaVersion.startsWith("17") ||
                                   javaVersion.startsWith("21");
        assertTrue("Should run on Java 11 or higher", isJava11OrHigher);
    }
}

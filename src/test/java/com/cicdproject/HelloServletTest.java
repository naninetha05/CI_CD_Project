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
    public void testServletReturnsHtml() throws Exception {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(request.getServerName()).thenReturn("localhost");
        when(response.getWriter()).thenReturn(pw);

        servlet.doGet(request, response);

        String output = sw.toString();
        assertTrue(output.contains("<html>"));
        assertTrue(output.contains("CICD Java Application"));
    }

    @Test
    public void testContentTypeIsSet() throws Exception {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(request.getServerName()).thenReturn("localhost");
        when(response.getWriter()).thenReturn(pw);

        servlet.doGet(request, response);

        verify(response).setContentType("text/html;charset=UTF-8");
    }

    @Test
    public void testServerNameAppearsInResponse() throws Exception {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(request.getServerName()).thenReturn("my-test-server");
        when(response.getWriter()).thenReturn(pw);

        servlet.doGet(request, response);

        assertTrue(sw.toString().contains("my-test-server"));
    }

    @Test
    public void testPostCallsGet() throws Exception {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(request.getServerName()).thenReturn("localhost");
        when(response.getWriter()).thenReturn(pw);

        servlet.doPost(request, response);

        assertTrue(sw.toString().contains("<html>"));
    }

    @Test
    public void testJavaVersionExists() {
        String version = System.getProperty("java.version");
        assertNotNull(version);
        assertFalse(version.isEmpty());
    }

    @Test
    public void testJavaVersionIsSupported() {
        String version = System.getProperty("java.version");
        int major = version.startsWith("1.") ?
            Integer.parseInt(version.split("\\.")[1]) :
            Integer.parseInt(version.split("[.\\-]")[0]);
        assertTrue("Need Java 11 or higher", major >= 11);
    }
}

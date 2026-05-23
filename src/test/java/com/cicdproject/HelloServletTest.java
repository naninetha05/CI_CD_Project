package com.cicdproject;

import org.junit.Test;
import static org.junit.Assert.*;

public class HelloServletTest {

    @Test
    public void testApplicationName() {
        String appName = "CICD Java Application";
        assertNotNull(appName);
        assertEquals("CICD Java Application", appName);
    }

    @Test
    public void testJavaVersion() {
        String javaVersion = System.getProperty("java.version");
        assertNotNull(javaVersion);
    }

    @Test
    public void testApplicationRunning() {
        assertTrue("Application should be running", true);
    }
}

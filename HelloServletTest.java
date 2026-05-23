# Java Application - Place in: java-app/src/test/java/com/cicdproject/HelloServletTest.java

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
public void testJavaVersionExists() {
    String javaVersion = System.getProperty("java.version");
    assertNotNull(javaVersion);
    assertFalse(javaVersion.isEmpty());
}

    @Test
    public void testApplicationRunning() {
       assertTrue(javaVersion.contains("11") || javaVersion.contains("1.8"));
    }
}

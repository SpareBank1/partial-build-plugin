package com.lesfurets.maven.partial.core;

import com.lesfurets.maven.partial.mocks.MavenSessionMock;
import com.lesfurets.maven.partial.mocks.ModuleMock;
import org.apache.maven.execution.MavenSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ConfigurationTest {

    @Test
    public void exemplifyAll() {
        System.out.println(Property.exemplifyAll());
    }

    @Test
    public void userProperties() throws Exception {
        MavenSession mavenSession = MavenSessionMock.get();
        Properties properties = new Properties();
        properties.setProperty(Property.referenceBranch.fullName(), "refs/test/branch");
        when(mavenSession.getUserProperties()).thenReturn(properties);
        ModuleMock module = ModuleMock.module(mavenSession);
        Configuration arguments = module.arguments();
        assertEquals("refs/test/branch", arguments.referenceBranch);
    }

    @Test
    public void badProperty() throws Exception {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            MavenSession mavenSession = MavenSessionMock.get();
            Properties properties = new Properties();
            properties.setProperty("partial.badProperty", "refs/test/branch");
            when(mavenSession.getUserProperties()).thenReturn(properties);
            ModuleMock module = ModuleMock.module(mavenSession);
            Configuration arguments = module.arguments();
        });
    }

}

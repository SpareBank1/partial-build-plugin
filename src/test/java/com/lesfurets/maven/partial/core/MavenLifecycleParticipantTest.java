package com.lesfurets.maven.partial.core;

import com.lesfurets.maven.partial.extension.MavenLifecycleParticipant;
import com.lesfurets.maven.partial.mocks.MavenSessionMock;
import org.apache.maven.execution.MavenSession;
import org.codehaus.plexus.logging.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

public class MavenLifecycleParticipantTest {

    @Test
    public void disabled() throws Exception {
        Property.enabled.setValue("false");
        MavenLifecycleParticipant participant = new MavenLifecycleParticipant();
        Field loggerField = participant.getClass().getDeclaredField("logger");
        loggerField.setAccessible(true);
        loggerField.set(participant, Mockito.mock(Logger.class));
        MavenSession mavenSession = MavenSessionMock.get();
        participant.afterProjectsRead(mavenSession);
    }

    @Test
    public void configured() throws Exception {
        MavenLifecycleParticipant participant = new MavenLifecycleParticipant();
        Field loggerField = participant.getClass().getDeclaredField("logger");
        loggerField.setAccessible(true);
        loggerField.set(participant, Mockito.mock(Logger.class));
        MavenSession mavenSession = MavenSessionMock.get();
        participant.afterProjectsRead(mavenSession);
    }

}

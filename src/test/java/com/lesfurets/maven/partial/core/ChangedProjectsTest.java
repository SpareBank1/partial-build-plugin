package com.lesfurets.maven.partial.core;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.logging.console.ConsoleLogger;
import org.junit.Test;

import com.google.inject.Guice;
import com.lesfurets.maven.partial.mocks.MavenSessionMock;
import com.lesfurets.maven.partial.mocks.RepoTest;

public class ChangedProjectsTest extends RepoTest {

    @Test
    public void list() throws Exception {
        final Set<Path> expected = new HashSet<>(Arrays.asList(
                        Paths.get("child2/subchild2"),
                        Paths.get("child3"),
                        Paths.get("child4")
        ));
        final Set<Path> actual = Guice.createInjector(new GuiceModule(new ConsoleLogger(), MavenSessionMock.get()))
                        .getInstance(ChangedProjects.class).get().stream()
                        .map(MavenProject::getBasedir)
                        .map(File::toPath)
                        .map(RepoTest.LOCAL_DIR.resolve(".")::relativize)
                        .collect(Collectors.toSet());
        assertEquals(expected, actual);
    }

}

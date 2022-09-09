package com.lesfurets.maven.partial.mocks;

import com.lesfurets.maven.partial.core.Property;
import org.codehaus.plexus.logging.console.ConsoleLoggerManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.impl.StaticLoggerBinder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class RepoTest {

    public static final String TEMPLATE_ZIP = "/project.zip";
    public static final Path TEST_WORK_DIR = Paths.get(System.getProperty("buildDir"));
    public static final Path LOCAL_DIR = TEST_WORK_DIR.resolve("tmp").resolve("repo");
    public static final Path REMOTE_DIR = TEST_WORK_DIR.resolve("tmp").resolve("remote");

    protected LocalRepoMock localRepoMock;
    public StaticLoggerBinder staticLoggerBinder;
    protected ByteArrayOutputStream consoleOut;
    protected String pluginVersion;
    private final PrintStream normalOut;

    public RepoTest() {
        this.normalOut = System.out;
    }

    @BeforeAll
    public void before() throws Exception {
        init();
        localRepoMock = new LocalRepoMock(false);
        pluginVersion = getPluginVersion();
    }

    protected void init() {
        staticLoggerBinder = new StaticLoggerBinder(new ConsoleLoggerManager().getLoggerForComponent("Test"));
        resetConsoleOut();
        resetProperties();
    }

    protected LocalRepoMock getLocalRepoMock() {
        return localRepoMock;
    }

    private void resetConsoleOut() {
        consoleOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOut));
    }

    private void resetProperties() {
        for (Property property : Property.values()) {
            property.setValue(property.getDefaultValue());
        }
        Property.uncommited.setValue("false");
        Property.referenceBranch.setValue("refs/heads/develop");
        Property.compareToMergeBase.setValue("false");
    }

    private String getPluginVersion() throws IOException {
        return System.getProperty("projectVersion");
    }

    @AfterAll
    public void after() throws Exception {
        localRepoMock.close();
        System.setOut(normalOut);
        normalOut.print(consoleOut.toString());
    }

}

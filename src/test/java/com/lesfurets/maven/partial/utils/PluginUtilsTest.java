package com.lesfurets.maven.partial.utils;

import org.codehaus.plexus.logging.console.ConsoleLoggerManager;
import org.junit.jupiter.api.Test;
import org.slf4j.impl.StaticLoggerBinder;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PluginUtilsTest {

    List<String> strings = Arrays.asList("something", "something");

    StaticLoggerBinder staticLoggerBinder = new StaticLoggerBinder(
                    new ConsoleLoggerManager().getLoggerForComponent("Test"));

    @Test
    public void separatePattern() throws Exception {

        List<String> patterns = PluginUtils.separatePattern("something, something");
        assertThat(patterns).isEqualTo(strings);
    }

    @Test
    public void patternEscapeCharacters() throws Exception {
        List<String> patterns = PluginUtils.separatePattern("something,\nsomething");
        assertThat(patterns).isEqualTo(this.strings);
    }

}
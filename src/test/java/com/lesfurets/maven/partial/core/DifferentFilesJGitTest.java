package com.lesfurets.maven.partial.core;

import com.google.inject.Guice;
import com.lesfurets.maven.partial.mocks.ModuleMock;

public class DifferentFilesJGitTest extends DifferentFilesTest {

    protected DifferentFiles getInstance() throws Exception {
        return Guice.createInjector(ModuleMock.module()).getInstance(DifferentFilesJGit.class);
    }

}

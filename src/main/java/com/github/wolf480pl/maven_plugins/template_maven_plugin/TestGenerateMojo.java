/*
 * This file is part of Template Maven Plugin.
 *
 * Copyright (c) 2013 Wolf480pl <wolf480@interia.pl>
 * Template Maven Plugin is licensed under the GNU Lesser General Public License.
 *
 * Template Maven Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Template Maven Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.wolf480pl.maven_plugins.template_maven_plugin;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 *
 */
@Mojo(name = "test-generate", defaultPhase = LifecyclePhase.GENERATE_TEST_SOURCES)
public class TestGenerateMojo extends AbstractGenerateMojo {
    /**
     * Directory where generated sources should be placed.
     */
    @Parameter(alias = "outputDir", defaultValue = "${project.build.directory}/generated-test-sources/template", required = true)
    private File outputDirectory;

    /**
     * Directory where to look for templates.
     */
    @Parameter(alias = "templateDir", defaultValue = "${basedir}/src/test/template", required = true)
    private File templateDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        generate(this.templateDirectory, this.outputDirectory);

        if (!this.project.getTestCompileSourceRoots().contains(this.outputDirectory.getAbsolutePath())) {
            this.project.addTestCompileSourceRoot(this.outputDirectory.getAbsolutePath());
        }
    }

}

/*
 * This file is part of Maven Plugin For Trove Templates.
 *
 * Copyright (c) 2013 Wolf480pl <wolf480@interia.pl>
 * Maven Plugin For Trove Templates is licensed under the GNU Lesser General Public License.
 *
 * Maven Plugin For Trove Templates is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Maven Plugin For Trove Templates is distributed in the hope that it will be useful,
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
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.DirectoryScanner;

import com.github.wolf480pl.maven_plugins.template_maven_plugin.Generator.WrapperInfo;

/**
 *
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class GenerateMojo extends AbstractMojo {
    @Component
    private MavenProject project;

    /**
     * Directory where generated sources should be placed.
     */
    @Parameter(alias = "outputDir", defaultValue = "${project.build.directory}/generated-sources/template", required = true)
    private File outputDirectory;

    /**
     * Directory where to look for templates.
     */
    @Parameter(alias = "templateDir", defaultValue = "${basedir}/src/main/template", required = true)
    private File templateDirectory;

    /**
     * Whether or not clean the outputDirectory before generating sources.
     */
    @Parameter(alias = "cleanOutput")
    private boolean clean;

    /**
     * Types for which generate classes from templates.
     */
    @Parameter
    private WrapperInfo[] types;

    /**
     * Prefix to add to generated files.
     */
    @Parameter(defaultValue = "")
    private String prefix;

    @Parameter
    private String[] includes;

    @Parameter
    private String[] excludes;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (!this.templateDirectory.exists()) {
            getLog().error("Template directory \"" + this.templateDirectory + "\" not found");
            throw new MojoExecutionException("Template directory \"" + this.templateDirectory + "\" not found");
        }
        if (!this.templateDirectory.isDirectory()) {
            getLog().error("Template directory \"" + this.templateDirectory + "\" is not a directory");
            throw new MojoExecutionException("Template directory \"" + this.templateDirectory + "\" is not a directory");
        }

        if (!this.outputDirectory.exists()) {
            if (!this.outputDirectory.mkdirs()) {
                getLog().error("contentCould not create output directory \"" + this.outputDirectory + "\"");
                throw new MojoExecutionException("Could not create output directory \"" + this.outputDirectory + "\"");
            } else {
                getLog().info("Created output directory \"" + this.outputDirectory + "\"");
            }
        }
        if (this.prefix == null) {
            this.prefix = "";
        }

        Generator generator;
        Logging log = new MavenLog(getLog());
        if (this.types != null) {
            generator = new Generator(log, this.outputDirectory, this.prefix, this.types);
        } else {
            generator = new Generator(log, this.outputDirectory, this.prefix);
        }
        if (this.clean) {
            generator.clean();
        }

        if (this.includes != null || this.excludes != null) {
            DirectoryScanner scanner = new DirectoryScanner();
            scanner.addDefaultExcludes();
            scanner.setBasedir(this.templateDirectory);
            scanner.setIncludes(this.includes);
            scanner.setExcludes(this.excludes);
            scanner.scan();
            for (String path : scanner.getIncludedFiles()) {
                File in = new File(this.templateDirectory, path);
                File outDir = new File(this.outputDirectory, path).getParentFile();
                try {
                    generator.processFile(in, outDir);
                } catch (IOException e) {
                    getLog().error("IOException ocurred while processing file: " + in, e);
                }
            }

        } else {
            generator.generate(this.templateDirectory);
        }

        if (!this.project.getCompileSourceRoots().contains(this.outputDirectory.getAbsolutePath())) {
            this.project.addCompileSourceRoot(this.outputDirectory.getAbsolutePath());
        }
    }
}

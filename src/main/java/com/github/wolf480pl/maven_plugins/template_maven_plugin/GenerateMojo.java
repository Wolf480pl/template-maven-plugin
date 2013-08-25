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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 *
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class GenerateMojo extends AbstractMojo {
    /**
     * Location of the file.
     */
    @Parameter(alias = "outputDir", defaultValue = "${project.build.directory}/generated-source", required = true)
    private File outputDirectory;

    @Parameter(alias = "templateDir", defaultValue = "${basedir}/src/main/templates", required = true)
    private File templateDirectory;

    @Parameter(alias = "cleanOutput")
    private boolean clean;

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

        Generator generator = new Generator(this.outputDirectory);
        if (this.clean) {
            generator.clean();
        }
        generator.generate(this.templateDirectory);
    }
}

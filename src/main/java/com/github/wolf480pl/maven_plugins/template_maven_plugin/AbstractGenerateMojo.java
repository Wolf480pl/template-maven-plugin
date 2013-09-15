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

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.DirectoryScanner;

import com.github.wolf480pl.maven_plugins.template_maven_plugin.Generator.WrapperInfo;

public abstract class AbstractGenerateMojo extends AbstractMojo {

    @Component
    protected MavenProject project;
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

    public AbstractGenerateMojo() {
        super();
    }

    protected void generate(File templateDirectory, File outputDirectory) throws MojoExecutionException, MojoFailureException {
        if (!templateDirectory.exists()) {
            getLog().error("Template directory \"" + templateDirectory + "\" not found");
            throw new MojoExecutionException("Template directory \"" + templateDirectory + "\" not found");
        }
        if (!templateDirectory.isDirectory()) {
            getLog().error("Template directory \"" + templateDirectory + "\" is not a directory");
            throw new MojoExecutionException("Template directory \"" + templateDirectory + "\" is not a directory");
        }

        if (!outputDirectory.exists()) {
            if (!outputDirectory.mkdirs()) {
                getLog().error("contentCould not create output directory \"" + outputDirectory + "\"");
                throw new MojoExecutionException("Could not create output directory \"" + outputDirectory + "\"");
            } else {
                getLog().info("Created output directory \"" + outputDirectory + "\"");
            }
        }
        if (this.prefix == null) {
            this.prefix = "";
        }

        Generator generator;
        Logging log = new MavenLog(getLog());
        if (this.types != null) {
            generator = new Generator(log, outputDirectory, this.prefix, this.types);
        } else {
            generator = new Generator(log, outputDirectory, this.prefix);
        }
        if (this.clean) {
            generator.clean();
        }

        if (this.includes != null || this.excludes != null) {
            DirectoryScanner scanner = new DirectoryScanner();
            scanner.addDefaultExcludes();
            scanner.setBasedir(templateDirectory);
            scanner.setIncludes(this.includes);
            scanner.setExcludes(this.excludes);
            scanner.scan();
            for (String path : scanner.getIncludedFiles()) {
                File in = new File(templateDirectory, path);
                File outDir = new File(outputDirectory, path).getParentFile();
                try {
                    generator.processFile(in, outDir);
                } catch (IOException e) {
                    getLog().error("IOException ocurred while processing file: " + in, e);
                }
            }

        } else {
            generator.generate(templateDirectory);
        }
    }

}
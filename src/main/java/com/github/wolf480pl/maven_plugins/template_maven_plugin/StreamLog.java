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

import java.io.PrintStream;

public class StreamLog implements Logging {
    private final boolean debug;
    private final PrintStream out;
    private final PrintStream err;

    public StreamLog(boolean debug) {
        this(debug, System.out, System.err);
    }

    public StreamLog(boolean debug, PrintStream out) {
        this(debug, out, out);
    }

    public StreamLog(boolean debug, PrintStream out, PrintStream err) {
        this.debug = debug;
        this.out = out;
        this.err = err;
    }

    @Override
    public void info(CharSequence content) {
        this.err.println("INFO: " + content);
    }

    @Override
    public void warn(CharSequence content) {
        this.err.println("WARNING: " + content);
    }

    @Override
    public void error(CharSequence content, Throwable error) {
        this.err.println("ERROR: " + content);
        error.printStackTrace(this.err);
    }

}

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


public interface Logging {

    void info(CharSequence message);

    void error(CharSequence message, Throwable t);

    void warn(CharSequence message);

}

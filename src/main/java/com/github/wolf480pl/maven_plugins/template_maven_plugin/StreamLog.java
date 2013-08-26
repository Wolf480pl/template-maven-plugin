package com.github.wolf480pl.maven_plugins.template_maven_plugin;

import java.io.PrintStream;

import org.apache.maven.plugin.logging.Log;

public class StreamLog implements Log {
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

    public boolean isDebugEnabled() {
        return this.debug;
    }

    public void debug(CharSequence content) {
        if (!this.debug) {
            return;
        }
        this.err.println("DEBUG: " + content);
    }

    public void debug(CharSequence content, Throwable error) {
        if (!this.debug) {
            return;
        }
        this.err.println("DEBUG: " + content);
        error.printStackTrace(this.err);
    }

    public void debug(Throwable error) {
        if (!this.debug) {
            return;
        }
        this.err.print("DEBUG: ");
        error.printStackTrace(this.err);
    }

    public boolean isInfoEnabled() {
        return true;
    }

    public void info(CharSequence content) {
        this.err.println("INFO: " + content);
    }

    public void info(CharSequence content, Throwable error) {
        this.err.println("INFO: " + content);
        error.printStackTrace(this.err);
    }

    public void info(Throwable error) {
        this.err.print("INFO: ");
        error.printStackTrace(this.err);
    }

    public boolean isWarnEnabled() {
        return true;
    }

    public void warn(CharSequence content) {
        this.err.println("WARNING: " + content);
    }

    public void warn(CharSequence content, Throwable error) {
        this.err.println("WARNING: " + content);
        error.printStackTrace(this.err);
    }

    public void warn(Throwable error) {
        this.err.print("WARNING: ");
        error.printStackTrace(this.err);
    }

    public boolean isErrorEnabled() {
        return true;
    }

    public void error(CharSequence content) {
        this.err.println("ERROR: " + content);
    }

    public void error(CharSequence content, Throwable error) {
        this.err.println("ERROR: " + content);
        error.printStackTrace(this.err);
    }

    public void error(Throwable error) {
        this.err.print("ERROR: ");
        error.printStackTrace(this.err);
    }
}

package org.example.method;

import java.nio.file.Path;

@FunctionalInterface
public interface FileCondition {
    boolean condition(Path path);
}

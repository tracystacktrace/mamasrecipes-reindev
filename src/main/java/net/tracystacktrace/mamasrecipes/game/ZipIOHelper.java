package net.tracystacktrace.mamasrecipes.game;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ZipIOHelper {
    public static @NotNull File @NotNull [] collectZipFiles(@NotNull File folder) {
        if (!folder.exists()) {
            folder.mkdirs();
            return new File[0];
        }

        if (!folder.isDirectory()) {
            throw new IllegalStateException("The directory appears to be a file! What?");
        }

        try {
            return Files.walk(folder.toPath())
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().toLowerCase().endsWith(".zip"))
                    .map(Path::toFile)
                    .toArray(File[]::new);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to walk through files!", e);
        }
    }
}

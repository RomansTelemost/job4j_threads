package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile {

    private final File file;

    public WriteFile(File file) {
        this.file = file;
    }

    public void saveContent(String content) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] buffer = new byte[content.length()];
            for (int i = 0; i < content.length(); i++) {
                buffer[i] = (byte) content.charAt(i);
            }
            bos.write(buffer);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}

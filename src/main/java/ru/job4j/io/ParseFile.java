package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Predicate;

public final class ParseFile {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() {
        return parseFile(character -> true);
    }

    public String getContentWithoutUnicode() {
        return parseFile(character -> character < 0x80);
    }

    private String parseFile(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buff = new byte[bis.available()];
            while (bis.read(buff) > 0) {
                for (int i = 0; buff.length > i; i++) {
                    if (filter.test((char)buff[i])) {
                        output.append((char)buff[i]);
                    }
                }
            }
        } catch (IOException io) {

        }
        return output.toString();
    }

    public static void main(String[] args) throws Exception {
        ParseFile parseFile = new ParseFile(new File("data/files/inputParseFile.txt"));
        System.out.println(parseFile.getContent());

        WriteFile writeFile = new WriteFile(new File("data/files/outputParseFile.txt"));
        writeFile.saveContent(parseFile.getContent());
    }
}

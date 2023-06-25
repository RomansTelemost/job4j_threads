package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownloadWithDelay {

    public static void main(String[] args) throws Exception {
        String url = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        try (BufferedInputStream bis = new BufferedInputStream(new URL(url).openStream())) {
            FileOutputStream fos = new FileOutputStream("data/files/test1.txt");
            byte[] fileBuff = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(fileBuff)) != -1) {
                fos.write(fileBuff, 0, bytesRead);
                Thread.sleep(1000);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

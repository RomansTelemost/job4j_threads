package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class WFileGet implements Runnable {

    private final String url;
    private final int speed;
    private final String fileName;

    public WFileGet(String url, int speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (BufferedInputStream bis = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fos = new FileOutputStream("data/files/" + fileName)) {

            int alreadyDownloadBytes = 0;
            byte[] buff = new byte[1024];
            int readBytes;

            LocalDateTime dateOfStartLoading = LocalDateTime.now();
            while ((readBytes = bis.read(buff)) != -1) {
                int secondPass = LocalDateTime.now().getSecond() - dateOfStartLoading.getSecond();

                fos.write(buff, 0, readBytes);
                alreadyDownloadBytes += readBytes;

                if (secondPass > 0
                        && alreadyDownloadBytes / secondPass > 0
                        && (alreadyDownloadBytes / secondPass) > (speed * secondPass)) {
                    int mustDownload = alreadyDownloadBytes - (speed * secondPass);
                    int delaySeconds = (mustDownload / speed) - secondPass;
                    Thread.sleep(delaySeconds * 1000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String fileName = args[2];

        Thread wget = new Thread(new WFileGet(url, speed, fileName));
        wget.start();
        wget.join();
    }
}

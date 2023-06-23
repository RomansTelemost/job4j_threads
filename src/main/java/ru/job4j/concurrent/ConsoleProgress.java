package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    public static void main(String[] args) throws Exception {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000); /* симулируем выполнение параллельной задачи в течение 5 секунд. */
        progress.interrupt();
    }

    @Override
    public void run() {
        var process = new char[] {'-', '\\', '|', '/'};
        var progressIndex = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                progressIndex = progressIndex == process.length ? progressIndex = 0: progressIndex;
                System.out.print("\r load: " + process[progressIndex]);
                progressIndex++;
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }



    }
}

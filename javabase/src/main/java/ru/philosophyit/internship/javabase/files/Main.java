package ru.philosophyit.internship.javabase.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String ...args) throws IOException {
        System.out.println(Files.readString(Path.of("/home/stuffing/Documents/Yarovenko_AG_Java_2021_Liga/javabase/src/main/resources/hello.txt")));


        String dirPath = "/home/stuffing/Documents/Yarovenko_AG_Java_2021_Liga/javabase/src";

        FileScanner fileScanner = new FileScanner();
        fileScanner.run(dirPath);


            // Отобразите рекурсивно дерево директорий src/main/java/ru/philosophyit/internship/javabase
        // например 
        // src/main/java/ru/philosophyit/internship/javabase/files/Main.java
        // src/main/java/ru/philosophyit/internship/javabase/locks/DeadLock.java
        // src/main/java/ru/philosophyit/internship/javabase/locks/LiveLock.java
        // src/main/java/ru/philosophyit/internship/javabase/threads/Completable.java
        // и т.д.
        /// Более удачные оформления вывода в консоль приветствуются
    }
}

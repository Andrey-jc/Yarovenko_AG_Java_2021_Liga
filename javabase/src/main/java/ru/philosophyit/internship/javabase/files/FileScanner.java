package ru.philosophyit.internship.javabase.files;

import java.io.File;
import java.util.Objects;

public class FileScanner {


    public void run(String dirPath) {
        File file = new File(dirPath);
        String[] sDirList = file.list();

        int i;
        for(i = 0; i < Objects.requireNonNull(sDirList).length; i++)
        {
            File f1 = new File(dirPath +
                    File.separator + sDirList[i]);

            if (f1.isFile()) {

                System.out.println(dirPath +
                        File.separator + sDirList[i]);
                System.out.println();
            } else {
                run(dirPath +
                        File.separator + sDirList[i]);
            }
        }

    }

}

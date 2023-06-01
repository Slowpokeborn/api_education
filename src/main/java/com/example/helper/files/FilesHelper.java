package com.example.helper.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.example.helper.AllureAttachment.attachToAllureZipFile;
import static com.example.helper.date.DateTimeUtil.getStringFromDateTimeForGenerator;
import static com.example.helper.files.zip.Zipper.archiveDirectoryToZip;
import static com.example.helper.logger.MessageFormatter.format;

public class FilesHelper {

    /**
     * Архивировать лог файл и прикрепить к Allure отчету
     * запускать после выполнения всех тестовых активностей
     */
    public static void archiveLogFileAndAttachToAllure() {
        String logDirPath = "target/log";
        String zipFilePath = format("target/buildlog_{}.zip", getStringFromDateTimeForGenerator());

        try {
            archiveDirectoryToZip(logDirPath, zipFilePath);
            System.out.println("Create file " + zipFilePath);
            attachToAllureZipFile(zipFilePath);
        } catch (Exception e) {
            System.out.println("Failed to archive and attach log file to Allure report\n" + e.getMessage());
        }
    }


    public static InputStream readFileToInputStream(String filePath) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return inputStream;
    }
}
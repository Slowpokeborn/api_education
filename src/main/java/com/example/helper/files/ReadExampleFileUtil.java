package com.example.helper.files;

import java.io.File;

public class ReadExampleFileUtil {
    private final static String FOLDER = "/example/";


    /**
     * Получить объект File из штатного каталога src/main/resources/example
     * применять для вычитки файлов с образцами сообщений
     *
     * @param filePath - имя файла
     * @return - File
     */
    public static File getFileByFileNameFromExampleFolder(String filePath) {
        return new File(ReadExampleFileUtil.class.getResource(FOLDER + filePath).getPath());
    }
}

package ru.ya.olganow;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFileText {
//    для файлов без href
//    static {
//        Configuration.fileDownload = FileDownloadMode.PROXY;
//    }


    @Test
    void selenideFileDownloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("#raw-url").download(); //путь к файлу
        //System.out.println("");

        InputStream is = new FileInputStream(downloadedFile);//Read a file
        try {
            byte[] fileSource = is.readAllBytes(); // все содержимое файла
            // получаем содержимое в байтах и преобразовываем в строку
            String fileContent = new String(fileSource, StandardCharsets.UTF_8);
            // проверяем содержимое
            assertThat(fileContent).contains("This repository is the home of the next generation of JUnit, JUnit 5.");
        } finally {
            is.close();
        }

    }

    @Test
    void selenideFileDownloadTestSecond() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("#raw-url").download();
        //Конструкция Try with resources, она автоматически закрывает ресурсы которые
        // в скобках после try вне зависимоти от результата в {}
        try (InputStream is = new FileInputStream(downloadedFile)) {
            byte[] fileSource = is.readAllBytes();
            String fileContent = new String(fileSource, StandardCharsets.UTF_8);
            assertThat(fileContent).contains("This repository is the home of the next generation of JUnit");

        }
    }
//   Можно и так, что идентично второму варианту
//    import org.apache.commons.io.FileUtils;
//    @Test
//    void selenideFileDownloadTestThird() throws Exception {
//        open("https://github.com/junit-team/junit5/blob/main/README.md");
//        File downloadedFile = $("#raw-url").download();
//        String contents = FileUtils.readFileToString(downloadedFile, StandardCharsets.UTF_8));
//    }


    @Test
    void uploadFile() throws Exception {
        open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("testcat.png");
        $("div.qq-file-info").shouldHave(text("testcat.png"));
    }

}
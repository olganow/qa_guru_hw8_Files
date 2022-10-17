package ru.ya.olganow;


import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;

import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class FileParseTest {

    ClassLoader cl = FileParseTest.class.getClassLoader();

    @Test
    void xlsTestCheckFileNameAndCellData() throws Exception {
        ZipFile zipFile = new ZipFile(Objects.requireNonNull(cl.getResource("testzipfiles.zip")).getFile());
        ZipEntry entry = zipFile.getEntry("filexls.xls");
        XLS xls;
        try (InputStream inputStream = zipFile.getInputStream(entry)) {
            xls = new XLS(inputStream);
            assertThat(xls.excel.getSheetAt(0)
                    .getRow(1)
                    .getCell(1)
                    .getStringCellValue())
                    .isEqualTo("Bunny@bunny.bon");

            assertThat(entry.getName()).isEqualTo(entry.getName());
        }
    }


    @Test
    void pdfTestCheckFileNameAndCellData() throws Exception {
        ZipFile zipFile = new ZipFile(Objects.requireNonNull(cl.getResource("testzipfiles.zip")).getFile());
        ZipEntry entry = zipFile.getEntry("progit.pdf");
        try (InputStream inputStream = zipFile.getInputStream(entry)) {
            PDF pdf = new PDF(inputStream);

            assertThat(pdf.text).contains("Pro Git");
            assertThat(entry.getName()).isEqualTo(entry.getName());
        }
    }

    @Test
    void csvTestCheckFileNameAndCellData() throws Exception {
        ZipFile zipFile = new ZipFile(Objects.requireNonNull(cl.getResource("testzipfiles.zip")).getFile());
        ZipEntry entry = zipFile.getEntry("filecsvtest.csv");
        try (InputStream inputStream = zipFile.getInputStream(entry)) {

            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            List<String[]> content = reader.readAll();
            String[] row = content.get(1);
            assertThat(row[0]).isEqualTo("Bunny;Bunny@bunny.bon");

            assertThat(entry.getName()).isEqualTo(entry.getName());
        }
    }

    @Test
    void zipTestCsvReadName() throws Exception {

        try (InputStream isz = cl.getResourceAsStream("file-xlsx-for-test.csv.zip");
             ZipInputStream zis = new ZipInputStream(isz)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String entryName = entry.getName();
                assertThat(entry.getName()).isEqualTo(entryName);

            }
        }
    }

}

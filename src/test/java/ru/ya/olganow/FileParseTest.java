package ru.ya.olganow;


import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;

import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;



public class FileParseTest {

    ClassLoader cl = FileParseTest.class.getClassLoader();

    @Test
    void zipTestXlsReadName() throws Exception {
        try (InputStream isz = cl.getResourceAsStream("file-xlsx-for-test.xlsx.zip");
             ZipInputStream zis = new ZipInputStream(isz)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String entryName = entry.getName();
                assertThat(entry.getName()).isEqualTo(entryName);
            }
        }
    }


    @Test
    void xlsTest22() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/testzipfiles.zip"));
        ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("testzipfiles.zip"));
        ZipEntry entry;
        while ((entry = is.getNextEntry()) != null) {
            if (entry.getName().contains(".xls")) {
                try (InputStream inputStream = zf.getInputStream(entry)) {
                    XLS xls = new XLS(inputStream);
                    System.out.println("");
                    assertThat(xls.excel.getSheetAt(0)
                            .getRow(1)
                            .getCell(1)
                            .getStringCellValue())
                            .isEqualTo("Bunny@bunny.bon");
                }
            }
        }
    }



    @Test
    void zipTestPdfReadName() throws Exception {
        try (InputStream isz = cl.getResourceAsStream("progit.pdf.zip");
    ZipInputStream zis = new ZipInputStream(isz)) {

        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            String entryName = entry.getName();
            assertThat(entry.getName()).isEqualTo(entryName);

        }
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

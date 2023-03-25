package net.jyou.poidemo;

import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author Joey Chen
 * @created 2023/3/9 18:41
 */
public class HssfDemo {

    @SneakyThrows
    public void newWordbook() {
        Workbook wbHssf = new HSSFWorkbook();
        try (OutputStream fileOut = new FileOutputStream("hssf_workbook.xls")) {
            wbHssf.write(fileOut);
        }

        Workbook wbXssf = new XSSFWorkbook();
        try (OutputStream fileOut = new FileOutputStream("xssf_workbook.xlsx")) {
            wbXssf.write(fileOut);
        }
    }

}

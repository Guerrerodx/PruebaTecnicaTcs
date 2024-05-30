package cucumber.pages;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {

    public void generateExcelFromTable(WebElement tableElement) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Summary");

        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Specifications Summary");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, getMaxColumns(tableElement) - 1));
        titleCell.setCellStyle(createTitleCellStyle(workbook));

        List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
        int rowCount = 1;
        for (WebElement rowElement : rows) {
            Row row = sheet.createRow(rowCount++);
            List<WebElement> headers = rowElement.findElements(By.tagName("th"));
            List<WebElement> cols = rowElement.findElements(By.tagName("td"));
            int colCount = 0;

            for (WebElement header : headers) {
                Cell cell = row.createCell(colCount++);
                cell.setCellValue(header.getText());
                cell.setCellStyle(createDataCellStyle(workbook));
            }

            for (WebElement colElement : cols) {
                Cell cell = row.createCell(colCount++);
                cell.setCellValue(colElement.getText());
                cell.setCellStyle(createDataCellStyle(workbook));
            }
        }

        for (int i = 0; i < getMaxColumns(tableElement); i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream("summary.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Archivo Excel creado con éxito.");
    }

    private int getMaxColumns(WebElement tableElement) {
        List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
        int maxColumns = 0;
        for (WebElement rowElement : rows) {
            int columns = rowElement.findElements(By.tagName("td")).size();
            if (columns > maxColumns) {
                maxColumns = columns;
            }
        }
        return maxColumns;
    }

    private CellStyle createTitleCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private CellStyle createDataCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        return style;
    }
}

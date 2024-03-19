/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component.sms;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import models.SMSTemplate;

public class ExcelTemplateReader {
    private File file;

    public ExcelTemplateReader(File file) {
        this.file = file;
    }

    public List<SMSTemplate> readTemplates() throws IOException {
        List<SMSTemplate> templates = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Read header row for replaceable text fields
            Row headerRow = rowIterator.next();
            List<String> fields = readFields(headerRow);

            // Read data rows for template content
            while (rowIterator.hasNext()) {
                Row dataRow = rowIterator.next();
                SMSTemplate template = readTemplate(fields, dataRow);
                templates.add(template);
            }
        }

        return templates;
    }

    private List<String> readFields(Row headerRow) {
        List<String> fields = new ArrayList<>();
        Iterator<Cell> cellIterator = headerRow.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            fields.add(cell.getStringCellValue());
        }
        return fields;
    }

    private SMSTemplate readTemplate(List<String> fields, Row dataRow) {
        SMSTemplate template = new SMSTemplate();
        for (int i = 0; i < fields.size(); i++) {
            String fieldName = fields.get(i);
            String fieldValue = dataRow.getCell(i).getStringCellValue();
            switch (i) {
                case 0:
                    // First column assumed to be title
                    template.setTitle(fieldValue);
                    break;
                case 1:
                    // Second column assumed to be ID
                    template.setId(fieldValue);
                    break;
                default:
                    // Other columns assumed to be replaceable fields
                    template.addReplaceableField(fieldName, fieldValue);
                    break;
            }
        }
        return template;
    }

    public static void main(String[] args) {
        File excelFile = new File("template_data.xlsx");
        ExcelTemplateReader reader = new ExcelTemplateReader(excelFile);
        try {
            List<SMSTemplate> templates = reader.readTemplates();
            // Use generated templates
            for (SMSTemplate template : templates) {
                System.out.println("Title: " + template.getTitle());
                System.out.println("ID: " + template.getId());
                System.out.println("Content: " + template.getContent());
                System.out.println("Replaceable Fields: " + template.getReplaceableFields());
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

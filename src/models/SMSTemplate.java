/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Codepal
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SMSTemplate {
    private String title;
    private String id;
    private String content;
    private Map<String, String> replaceableFields;
    
    private List<Map<String, String>> dataValuesList; //from excel

    public SMSTemplate(String title, String id, String content) {
        this.title = title;
        this.id = id;
        this.content = content;
        this.replaceableFields = new HashMap<>();
        this.dataValuesList = new ArrayList<>();
    }

    public SMSTemplate() {
       
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Map<String, String> getReplaceableFields() {
        return replaceableFields;
    }

    public void setReplaceableFields(Map<String, String> replaceableFields) {
        this.replaceableFields = replaceableFields;
    }
    

    public void addReplaceableField(String fieldName, String fieldValue) {
        replaceableFields.put(fieldName, fieldValue);
    }

    public List<Map<String, String>> getDataValuesList() {
        return dataValuesList;
    }

    public void setDataValuesList(List<Map<String, String>> dataValuesList) {
        this.dataValuesList = dataValuesList;
    }

    
    

    public String generateText() {
        String generatedText = content;
        for (Map.Entry<String, String> entry : replaceableFields.entrySet()) {
            String fieldName = entry.getKey();
            String fieldValue = entry.getValue();
            generatedText = generatedText.replace("{" + fieldName + "}", fieldValue);
        }
        return generatedText;
    }
    

    public static void main(String[] args) {
        // Example usage
        SMSTemplate template = new SMSTemplate("Invoice Template", "INV001",
                "Dear {customerName},\n\nPlease find attached the invoice for the amount of {amount}.\n\nRegards,\n{companyName}");
        
        // Add replaceable fields
        template.addReplaceableField("customerName", "John Doe");
        template.addReplaceableField("amount", " KES 1000");
        template.addReplaceableField("companyName", "ABC Company");

        // Generate text content
        String generatedText = template.generateText();
        System.out.println(generatedText);
    }
}

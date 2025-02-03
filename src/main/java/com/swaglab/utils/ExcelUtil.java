package com.swaglab.utils;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.Cleanup;
import lombok.Data;
import lombok.ToString;

public class ExcelUtil {

    public static <T,V> List<T> getRowsIntoPojoList(String filePath, Class<T> pojoClass){
        XSSFSheet sheet = getFirstSheet(filePath);
        Map<String, Integer> headerMap = getHeaderMap(sheet.getRow(0));

        List<T> rowList = new ArrayList<>();
        sheet.forEach(row -> {
            if (row.getRowNum() != 0) {
                Map<String, String> dataMap = getRowDataInMap(row, headerMap);
                T object = convertMapDataToPojo(dataMap, pojoClass);
                rowList.add(object);
            }
        });

        return rowList;
        
    }

    public static <T, V> List<T> getRowsIntoPojoList(String filePath, Class<T> pojoClass, 
            String fieldName, String fieldValue) {

        XSSFSheet sheet = getFirstSheet(filePath);
        Map<String, Integer> headerMap = getHeaderMap(sheet.getRow(0));

        List<T> rowList = new ArrayList<>(); 
        sheet.forEach(row -> {
            if (row.getRowNum() != 0 
                && getCellValueInString(row.getCell(headerMap.get(fieldName))).equals(fieldValue)) {
                
                Map<String, String> dataMap = getRowDataInMap(row, headerMap);
                T object = convertMapDataToPojo(dataMap, pojoClass);
                rowList.add(object);
            }
        });

        return rowList;
    }

    public static <T, V> T getRowIntoPojo(String filePath, Class<T> pojoClass, 
        String fieldName, String fieldValue) {
        
        XSSFSheet sheet = getFirstSheet(filePath);
        Map<String, Integer> headerMap = getHeaderMap(sheet.getRow(0));
        
        Iterator<Row> iterator = sheet.iterator();
        iterator.next();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            if(getCellValueInString(row.getCell(headerMap.get(fieldName))).equals(fieldValue)){
                Map<String, String> dataMap = getRowDataInMap(row, headerMap);
                return convertMapDataToPojo(dataMap, pojoClass);
            }
        }
        System.err.println("Row not found for field: " + fieldName + " ,value: " + fieldValue);
        return null;
    }

    private static String getCellValueInString(Cell cell){
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
            default:
                return "";
        }  
    }

    private static XSSFSheet getFirstSheet(String filePath){
        try {
            @Cleanup 
            FileInputStream excelFile = new FileInputStream(new File(filePath));
            @Cleanup 
            XSSFWorkbook wb = new XSSFWorkbook(excelFile);
            return wb.getSheetAt(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    private static Map<String, Integer> getHeaderMap(XSSFRow headerRow) {
        Map<String, Integer> headerMap = new HashMap<>();
        for (Cell cell : headerRow) {
            headerMap.put(cell.getStringCellValue(), cell.getColumnIndex());
        }
        return headerMap;
    }

    private static <T, V> Map<String, String> getRowDataInMap(Row row, Map<String, Integer> headerMap){
        Map<String, String> dataMap = new HashMap<>();
        headerMap.entrySet().forEach(entry->{
            Integer columnIndex = headerMap.get(entry.getKey());
            if (columnIndex != null) {
                Cell cell = row.getCell(columnIndex);
                if (cell != null) {
                    dataMap.put(entry.getKey().toUpperCase(), getCellValueInString(cell));
                }
            }
        });

        return dataMap;
    }

    private static <T, V> T convertMapDataToPojo(Map<String, String> dataMap, Class<T> pojoClass) {
        try {
            T object = pojoClass.getDeclaredConstructor().newInstance();
            Arrays.asList(pojoClass.getDeclaredFields()).forEach(field -> {
                try {
                    String fieldName = field.getName();
                    String methodName = "set" + fieldName.replaceFirst( (fieldName.charAt(0)+""), 
                        (fieldName.charAt(0)+"").toUpperCase());
                    Method seterMethod = pojoClass.getDeclaredMethod(methodName, String.class);
                    seterMethod.invoke(object, dataMap.getOrDefault(field.getName().toUpperCase(), null));
                    // field.set(object, dataMap.getOrDefault(field.getName().toUpperCase(), null));
                } catch (Exception e) {
                    System.out.println("Setter Method not found for field: " + field.getName());
                }
            });
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

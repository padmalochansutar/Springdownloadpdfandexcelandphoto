package com.csmtech.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.csmtech.entity.Registration;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
	
	 public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = { "registrationId", "applicantName", "email", "mobileNo","age","idProof","course","college" ,"branch","isDelete"};
	  static String SHEET = "Registration";
	  
       
	  public static ByteArrayInputStream tutorialsToExcel(Registration tutorials) {
//        System.out.println("the tutorialss is:"+tutorialss);
//	    try (Workbook workbook = new XSSFWorkbook(); ) {
//	      Sheet sheet = workbook.createSheet(SHEET);
//
//	      // Header
//	      Row headerRow = sheet.createRow(0);
//          CellStyle headerCellStyle=workbook.createCellStyle();
//          headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
//          headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//          //creating header cells
//          Cell cell=headerRow.createCell(0);
//          cell.setCellValue("id");
//          cell.setCellStyle(headerCellStyle);
//          cell=headerRow.createCell(1);
//          cell.setCellValue("title");
//          cell.setCellStyle(headerCellStyle);
//          
//          cell=headerRow.createCell(2);
//          cell.setCellValue("description");
//          cell.setCellStyle(headerCellStyle);
//          
//          cell=headerRow.createCell(3);
//          cell.setCellValue("published");
//          cell.setCellStyle(headerCellStyle);
//          
//          //creating data row for each of tutorials object
//          System.out.println("the size is:"+tutorialss.size());
//          Row dataRow=null;
////          int count = 1;
////          for (tutorials tCell : tutorialss) {
////        	  dataRow=sheet.createRow(count);
////        	  dataRow.createCell(1).setCellValue(tCell.getId());
////        	  count++;
////		}
////          System.out.println(dataRow);
//          tutorials datatutorials=null;
//          for (tutorials cell2 : tutorialss) {
//		
//        	  datatutorials=cell2;
//        	  System.out.println("the data tutorials is:"+datatutorials);
//        	  
//		}
//         
//          
//          for(int i=1; i <= tutorialss.size();i++) {
//        	  System.out.println("the integer is"+i);
//        	  System.out.println(tutorialss.get(1).getId());
//        	  dataRow=sheet.createRow(i);//plus one to exclude the header row..
//        	  dataRow.createCell(1).setCellValue(tutorialss.get(1).getId());
//        	  dataRow.createCell(2).setCellValue(tutorialss.get(2).getTitle());
//        	  dataRow.createCell(3).setCellValue(tutorialss.get(3).getDescription());
//        	  dataRow.createCell(4).setCellValue(tutorialss.get(4).getPublished());
//          }
//         
//          System.out.println("the row data is:"+dataRow);
//          //making sure the size of excel cell auto resize to fit the data
//          sheet.autoSizeColumn(0);
//          sheet.autoSizeColumn(1);
//          sheet.autoSizeColumn(2);
//          sheet.autoSizeColumn(3);
//          ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
//          workbook.write(outputStream);
//          
//          return new ByteArrayInputStream(outputStream.toByteArray());
//	    
//	    } catch (IOException e) {
//	      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
//	    }
//	  }

		    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
		      Sheet sheet = workbook.createSheet(SHEET);

		      // Header
		      Row headerRow = sheet.createRow(0);
              System.out.println("the header length is:"+HEADERs.length);
		      for (int col = 0; col < HEADERs.length; col++) {
		        Cell cell = headerRow.createCell(col);
		        cell.setCellValue(HEADERs[col]);
		      }

		      int rowIdx = 1;
		     
		        Row row = sheet.createRow(rowIdx++);

		        row.createCell(0).setCellValue(tutorials.getRegistrationId());
		        row.createCell(1).setCellValue(tutorials.getApplicantName());
		        row.createCell(2).setCellValue(tutorials.getEmail());
		        row.createCell(3).setCellValue(tutorials.getMobileNo());
		        row.createCell(4).setCellValue(tutorials.getAge());
		        row.createCell(5).setCellValue(tutorials.getIdProof());
		        row.createCell(6).setCellValue(tutorials.getCourse());
		        row.createCell(7).setCellValue(tutorials.getCollege().getCollegeName());
		        row.createCell(8).setCellValue(tutorials.getBranch().getBranchName());
		        row.createCell(9).setCellValue(tutorials.getIsDelete());
		        //The java string valueOf() method converts different types of values into string. By the help of string valueOf() method, you can convert int to string, long to string, boolean to string, character to string, float to string, double to string, object to string and char array to string.
		        //row.createCell(3).setCellValue(String.valueOf(tutorials.getPublished()));//String.valueOf:-it will convert number to boolean
		      

		      workbook.write(out);
		      return new ByteArrayInputStream(out.toByteArray());
		    } catch (IOException e) {
		      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		    }
	}
}

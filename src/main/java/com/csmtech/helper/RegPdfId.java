package com.csmtech.helper;

import java.awt.Color;
import java.io.IOException;
import java.util.List;


import javax.servlet.http.HttpServletResponse;

import com.csmtech.entity.Registration;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class RegPdfId {
	
	 private Registration listUsers;
     
	    public RegPdfId(Registration listUsers) {
	        this.listUsers = listUsers;
	    }
	 
	    private void writeTableHeader(PdfPTable table) {
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLUE);
	        cell.setPadding(5);
	        String[] HEADERs = { "registrationId", "applicantName", "email", "mobileNo","age","idProof","course","college" ,"branch","isDelete"};
	        Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(Color.WHITE);
	         
//	        cell.setPhrase(new Phrase("User ID", font));
//	         
//	        table.addCell(cell);
//	         
//	        cell.setPhrase(new Phrase("E-mail", font));
//	        table.addCell(cell);
//	         
//	        cell.setPhrase(new Phrase("Full Name", font));
//	        table.addCell(cell);
//	         
//	        cell.setPhrase(new Phrase("Roles", font));
//	        table.addCell(cell);
//	         
//	        cell.setPhrase(new Phrase("Enabled", font));
//	        table.addCell(cell);      
	        
	        for(int col=0;col<HEADERs.length;col++) {
	        	 
	        	cell.setPhrase(new Phrase(HEADERs[col], font));
	        	table.addCell(cell);
	        }
	    }
	     
	    private void writeTableData(PdfPTable table) {
	    	int i=0;
	       
	            table.addCell(String.valueOf(listUsers.getRegistrationId()));
	            table.addCell(listUsers.getApplicantName());
	            table.addCell(listUsers.getEmail());
	            table.addCell(listUsers.getMobileNo().toString());
	            table.addCell(listUsers.getAge().toString());
	            table.addCell(listUsers.getIdProof());
	            table.addCell(listUsers.getCourse());
	            table.addCell(listUsers.getCollege().getCollegeName());
	            table.addCell(listUsers.getBranch().getBranchName());
	            table.addCell(listUsers.getIsDelete());
	           
	            //table.addCell(user.getRoles().get(i).getName().toString());
//	            String string = "";
//	           if(user.getRoles().size()>1) {
//	        	   for(Role role : user.getRoles()) {
//	        		   //System.out.println("the before string is:"+string);
//	        		   string += role.getName()+" ,";
//	        		   
//	        		   //System.out.println("the After string is:"+string);
//	        	   }
//	        	   table.addCell(string.substring(0, 17));
//	           }else {
//	        	   table.addCell(user.getRoles().get(i).getName().toString());
//	           }
//	            String string="";
//      for(Role roles:user.getRoles()) {
//           //table.addCell(user.getRoles().get(i).getName().toString());
//    	  string +=roles.getName()+",";
//            	
//            	 i++;
//           }
//      table.addCell(string);
	            	
	            	
	           // table.addCell(String.valueOf(user.isEnabled()));
	           
	        }
	
	     
	    public void export(HttpServletResponse response) throws DocumentException, IOException {
	        Document document = new Document(PageSize.B4);
	        PdfWriter.getInstance(document, response.getOutputStream());
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	        font.setColor(Color.BLUE);
	         
	        Paragraph p = new Paragraph("List of Users", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        document.add(p);
	         
	        PdfPTable table = new PdfPTable(10);
	       table.setWidthPercentage(100f);
	       // table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
	        table.setSpacingBefore(10);
	         
	        writeTableHeader(table);
	        writeTableData(table);
	         
	        document.add(table);
	         
	        document.close();
	         
	    }

}

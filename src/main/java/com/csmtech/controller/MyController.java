package com.csmtech.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.csmtech.entity.Branch;
import com.csmtech.entity.College;
import com.csmtech.entity.Registration;
import com.csmtech.helper.ExcelHelper;
import com.csmtech.helper.ExcelhelperAll;
import com.csmtech.helper.RegPdfId;
import com.csmtech.helper.UserPDFExporter;
import com.csmtech.repository.BranchRepository;
import com.csmtech.repository.CollegeRepository;
import com.csmtech.repository.FeesRepository;
import com.csmtech.repository.RegistrationRepository;
import com.csmtech.utils.CommonFileUpload;
import com.lowagie.text.DocumentException;


@Controller
public class MyController {
	
	@Autowired
	private BranchRepository branchRepo;
	
	@Autowired
	private CollegeRepository collegeRepo;
	@Autowired
	private FeesRepository feesRepo;
	
	@Autowired
	private RegistrationRepository regRepo;
	@RequestMapping("/test")
	public String test(Model model) {
		
		model.addAttribute("collegeList",collegeRepo.findAll());
		return "register";
	}
	String data="";
	@GetMapping("/getByCollegeId")
	public void getCollegeId(@RequestParam("cId") Integer cId, HttpServletResponse resp) throws IOException {
		PrintWriter pw = resp.getWriter();
		System.out.println(cId);
		//College college=collegeRepo.findById(cId).get();
		List<Branch> branch = branchRepo.findbyCollegeId(cId);
		System.out.println("branch is:"+branch);

		data += "<option value='" + 0 + "'>" + "--SELECT--" + "</option>";

		for (Branch bh : branch) {
			data += "<option value='" + bh.getBranchId() + "'>" + bh.getBranchName() + "</option>";

		}
		pw.print(data);
		data = null;
	}
	@GetMapping("/getByBranchId")
	public void getBranchId(@RequestParam("bId") Integer bId, HttpServletResponse resp) throws IOException {
		System.out.println("the branch id:"+bId);
		PrintWriter pw = resp.getWriter();
		Branch branch =branchRepo.findById(bId).get();
		Long fees = feesRepo.findByBId(branch);
		
			pw.print(fees);
		System.out.println("the fees Is:"+fees);
		
		
	}
	@GetMapping("/addData")
	public void addData(@RequestParam("cName") String name) {
		System.out.println("the data is:"+name);
		
	}
	
	@GetMapping("/getByCollegename")
	public void getByCollegename(@RequestParam("cName") String cName, HttpServletResponse resp) throws IOException {
		PrintWriter pw = resp.getWriter();
		String data1 = "";
	
		System.out.println(cName);
		//College college=collegeRepo.findById(cId).get();
		College college = collegeRepo.findbyCollegename(cName);
		System.out.println("college is:"+college);

		System.out.print(data1);
//		data1 += "<option value='" + 0 + "'>" + "--SELECT--" + "</option>";
//		data1 += "<option value='" + college.getCollegeId() + "'>" + college.getCollegeName() + "</option>";
		data1="<a href='./updateUser?userId="+cName+" class='btn btn-success'>UPDATE</a>";
		
		System.out.println(data1);
		
		pw.print(data1);
	}
	
	
	
	@PostMapping("/saveData")
	public String saveAllData(@RequestParam("cName") College cId, @RequestParam("bName") Branch bId,
			@RequestParam("applicantName") String name, @RequestParam("email") String email,
			@RequestParam("mobileNo") Long number, @RequestParam("dob") String date,
			@RequestParam("gender") String gender,@RequestParam("course") String course ,@RequestParam("idProof") MultipartFile img,Model model) throws Exception {
		System.out.println("the College is:"+cId);
		LocalDate today = LocalDate.now();
		System.out.println("today date is :"+today);
		LocalDate birthday = LocalDate.parse(date);
		System.out.println("th form date is:"+birthday);
		
		Period period = Period.between( birthday,today);
		int years = period.getYears();
		
		System.out.println("the age is:"+years);
		System.out.println("the college is");
		File f = new File("E:/FileUpload/" + img.getOriginalFilename());
		BufferedOutputStream bf = null;
		try {
			byte[] bytes = img.getBytes();
			bf = new BufferedOutputStream(new FileOutputStream(f));
			bf.write(bytes);
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Registration rg = new Registration();
		rg.setCollege(cId);
		rg.setBranch(bId);
		
		rg.setApplicantName(name);
		rg.setEmail(email);
		rg.setMobileNo(number);
		rg.setAge(years);
		rg.setIdProof(img.getOriginalFilename());
		rg.setCourse(course);
		rg.setIsDelete("No");
		
		if(rg.getAge()>=15) {
			
			regRepo.save(rg);
			}
		else if(rg.getAge()<=14) {
			String msg="age must be greater than 18";
			model.addAttribute("msg",msg);
			return "register";
		}

		
		return "redirect:./test";

	}
	@GetMapping("/allData")
	public String allData(Model model) {
		List<Registration> findAll = regRepo.findAll();
		System.out.println("the data is:"+findAll);
		model.addAttribute("allBookingList",findAll);
		return "viewPage";
		
	}
	
	@GetMapping("/downloadFile")
	public void downloadFile(@RequestParam("id") Integer id, HttpServletResponse response) throws IOException {
		System.out.println("Inside Download File--------->>");
		Registration reg = regRepo.getApplicantByapplicantId(id);
		System.out.println("the reg is:"+reg);
		CommonFileUpload.downloadFile(response, reg.getIdProof());
	}
	Integer rId=0;
	@GetMapping("/download/tut/{rid}")
	  public void downloadExcelFile(@PathVariable("rid") String id,HttpServletResponse response) throws IOException {
			System.out.println("the id is:"+id);
			//rId=Integer.parseInt(id);// For input string: "rid=1":-output//solution below code
			rId=Integer.parseInt(id.split("=")[1]);//rid and 1 both are store in array bcz rid store in 0 position and 1 is store in 1 position and equal to split ...
			System.out.println("the Rid is:"+rId);
			response.setContentType("application/octet-stream");
			response.setHeader("content-Disposition", "attachment;filename=Tutorial.xlsx");
			
			ByteArrayInputStream inputStream=ExcelHelper.tutorialsToExcel(createTestData());
			System.out.println("the input stream is:"+inputStream);
			IOUtils.copy(inputStream, response.getOutputStream());
			System.out.println("the outputStrem is:"+response.getOutputStream());
		  
	  }
		
		private Registration createTestData(){
			//creating list of tutorials data for testing
//			List<Tutorial> tutorials=new ArrayList<Tutorial>();
//			
//			tutorials.add(new Tutorial(1,"c","dennis",false));
//			tutorials.add(new Tutorial(2,"c++","dennis",true));
//			tutorials.add(new Tutorial(3,"java","james",true));
			//repository.saveAll(tutorials);
			System.out.println("the rId is:"+rId);
			return regRepo.findById(rId).get();
			
		}
		
		
		@GetMapping("/downl/tutorial.xlsx")
		  public void downloadExcelFile(HttpServletResponse response) throws IOException {
				
				response.setContentType("application/octet-stream");
				response.setHeader("content-Disposition", "attachment;filename=Tutorial.xlsx");
				
				ByteArrayInputStream inputStream=ExcelhelperAll.tutoToExcel(TestData());
				System.out.println("the input stream is:"+inputStream);
				IOUtils.copy(inputStream, response.getOutputStream());
				System.out.println("the outputStrem is:"+response.getOutputStream());
			  
		  }
			
			private List<Registration> TestData(){
				//creating list of tutorials data for testing
//				List<Tutorial> tutorials=new ArrayList<Tutorial>();
//				
//				tutorials.add(new Tutorial(1,"c","dennis",false));
//				tutorials.add(new Tutorial(2,"c++","dennis",true));
//				tutorials.add(new Tutorial(3,"java","james",true));
				//repository.saveAll(tutorials);
				return regRepo.findAll();
				
			}
			
			  @GetMapping("/users/export/pdf")
			    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
			        response.setContentType("application/pdf");
			        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			        String currentDateTime = dateFormatter.format(new Date());
			         
			        String headerKey = "Content-Disposition";
			        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
			        response.setHeader(headerKey, headerValue);
			         
			        List<Registration> listUsers = regRepo.findAll();
			        System.out.println("the list of user is:"+listUsers);
			        UserPDFExporter exporter = new UserPDFExporter(listUsers);
			        
			        exporter.export(response);
			         
			    }
			  Integer pId;
			  @GetMapping("/use/export/pdf/{pId}")
			    public void exportToPDFId(@PathVariable("pId") String id,HttpServletResponse response) throws DocumentException, IOException {
			        System.out.println("the pid is:"+id);
			        pId=Integer.parseInt(id.split("=")[1]);
				  response.setContentType("application/pdf");
			        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			        String currentDateTime = dateFormatter.format(new Date());
			         
			        String headerKey = "Content-Disposition";
			        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
			        response.setHeader(headerKey, headerValue);
			         
			        Registration listUsers = regRepo.findById(pId).get();
			        System.out.println("the list of user is:"+listUsers);
			        RegPdfId exporter = new RegPdfId(listUsers);
			        
			        exporter.export(response);
			         
			    }
			
}

package com.ats.webapi.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ats.webapi.model.Info;

import javassist.bytecode.stackmap.BasicBlock.Catch;


@RestController
public class ImageUploadController {

	private static String SUGGESTION_URL = "/opt/tomcat-latest/webapps/uploads/SUGGESTION/";
	private static String COMPLAINT_URL = "/opt/tomcat-latest/webapps/uploads/COMPLAINT/";
	private static String NOTIFICATION_URL = "/opt/tomcat-latest/webapps/uploads/NOTIFICATION/";
	private static String FEEDBACK_URL = "/opt/tomcat-latest/webapps/uploads/FEEDBACK/";

	//private static String BILL_FOLDER ="/home/maxadmin/Desktop/photos/";
	
	

	@RequestMapping(value = { "/photoUpload" }, method = RequestMethod.POST)
	public @ResponseBody Info getFarmerContract(@RequestParam("file") MultipartFile uploadfile , @RequestParam("imageName") String imageName,@RequestParam("type") String type) {

		Info info = new Info();

		System.out.println("File Name " + uploadfile.getOriginalFilename());

		try {

			saveUploadedFiles(Arrays.asList(uploadfile) ,  imageName,type);

			info.setError(false);
			info.setMessage("File uploaded successfully");

		} catch (IOException e) {

			e.printStackTrace();
			info.setError(true);
			info.setMessage("File upload failed");
		}

		return info;
	}

	// save file
	private void saveUploadedFiles(List<MultipartFile> files,  String imageName,String type) throws IOException {

		try {
		for (MultipartFile file : files) {
			Path path=null;
			if (file.isEmpty()) {
				continue; 
			}
            if(type.equalsIgnoreCase("s"))
            {
			 path =Paths.get(SUGGESTION_URL + imageName);
            }
            else if(type.equalsIgnoreCase("c"))
            {
   			 path =Paths.get(COMPLAINT_URL + imageName);

            }
            else if(type.equalsIgnoreCase("nf"))	
            {
   			 path =Paths.get(NOTIFICATION_URL + imageName);

            }
            else if(type.equalsIgnoreCase("f"))
            {
   			 path =Paths.get(FEEDBACK_URL + imageName);

            }
			byte[] bytes = file.getBytes();
			
			Files.write(path, bytes);

		}

	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

}
}

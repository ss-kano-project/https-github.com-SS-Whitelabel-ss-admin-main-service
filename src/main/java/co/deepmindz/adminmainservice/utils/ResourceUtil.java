package co.deepmindz.adminmainservice.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import co.deepmindz.adminmainservice.resources.CustomHttpResponse;

@Service
public class ResourceUtil {

	public ResponseEntity<Object> fileUploadFuction(MultipartFile file, String type) throws IOException {
		if (type.contains("splash") || type.contains("logo")) {
			String imageDirectory = "src/main/resources/static/";
			String currentDirectory = System.getProperty("user.dir");
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			String fullImageDirectory = currentDirectory + "/" + imageDirectory;
			File directory = new File(fullImageDirectory);
			// Create the directory if it doesn't exist
			if (!directory.exists()) {
				if (directory.mkdirs()) {
					System.out.println("Directory created successfully.");
				} else {
					return ResponseEntity.badRequest().body("Failed to create directory.");
				}
			}
			File destinationFile = new File(fullImageDirectory, fileName);
			file.transferTo(destinationFile);
			String fileStatus = "File Transfer success";

			return CustomHttpResponse.responseBuilder(type + " uploaded successfully", HttpStatus.CREATED, fileStatus);
		} else {
			return ResponseEntity.badRequest().body("Invalid file type.");
		}
	}

}
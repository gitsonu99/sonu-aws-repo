package com.digivive.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digivive.api.service.AwsBucketService;

@RestController
@RequestMapping("/bucket")
public class S3BucketController {
	@Autowired 
	private AwsBucketService bucketService;

@PostMapping("/save")
public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file)
{
 return ResponseEntity.ok(bucketService.uploadFile(file));
}
	

@DeleteMapping("/delete")
public ResponseEntity<String> deleteFile(@RequestParam("file") String file)
{
 return ResponseEntity.ok(bucketService.deleteFile(file));
}
@GetMapping("/download")
public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam("file") String file)throws Exception
{
 			
return ResponseEntity
		 .status(HttpStatus.OK)
		 .header("contentType", "ByteArrayStream")
		 .body(new ByteArrayResource(bucketService.downlaodFile(file)));   	 
}	


}

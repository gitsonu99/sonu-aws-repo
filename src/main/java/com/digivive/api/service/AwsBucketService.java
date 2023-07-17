package com.digivive.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
@Service
public class AwsBucketService {
	@Value("${aws.bucket.name}")
	private String bucketName;
	@Autowired
	private AmazonS3  clientAmazonS3;
	
	
	
	/**
	 *
	 * @param file of type MultipartFile 
	 * @return of type File
	 */
    public  File convertMultipartFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
	try{	
 FileOutputStream outputStream=new FileOutputStream(convertedFile);
 outputStream.write(file.getBytes());
	}
	catch(IOException exception) {
   exception.printStackTrace();
	}
return convertedFile;
	
	}
	
	/**
	 * 
	 * @param file of type MultipartFile.
	 * @return of type string.
	 */
	public String uploadFile(MultipartFile file) {
	File fileobject= convertMultipartFile(file);
	
	clientAmazonS3.putObject(bucketName, file.getOriginalFilename()+":"+System.currentTimeMillis(), fileobject);
	fileobject.delete();
	return "FileObject has been saved to aws s3 bucket!";
	}
	

	/**
	 * 
	 * @param file of type string.
	 * @return  of type string.
	 */
	
	public String deleteFile(String file) { 
    clientAmazonS3.deleteObject(bucketName, file);
    return "File has been deleted successfully!";
	}
	/**
	 * 
	 * @param file of string
	 * @return type byte[]
	 * @throws Exception
	 */
public byte[] downlaodFile(String file)throws Exception {
	S3Object object=clientAmazonS3.getObject(bucketName, file);
	S3ObjectInputStream stream= object.getObjectContent();
  return com.amazonaws.util.IOUtils.toByteArray(stream);
	
}
	
}

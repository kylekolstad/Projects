package com.campfire.utilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class AWS {
	
	private static AWSCredentials credentials = new BasicAWSCredentials(
			  "AKIATLO777W4NDLQ3TUD", 
			  "pUmIp8huN5EBT7V1ty9BaJcjea4GFqir1KlwREDP"	
			);
	private static AmazonS3 s3client = AmazonS3ClientBuilder
			  .standard()
			  .withCredentials(new AWSStaticCredentialsProvider(credentials))
			  .withRegion(Regions.US_EAST_2)
			  .build();
	
	private static String bucketName = "campfire-project2";
	
	
	public static String uploadToS3(String filenameToBeStored, File file) {
		
		s3client.putObject(
				  bucketName, 
				  filenameToBeStored, 
				  file			  
				);
		
		S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, filenameToBeStored));
		String url = s3object.getObjectContent().getHttpRequest().getURI().toString();
		
		return url;
	}
	
	public static String uploadToS3Inputsream(String filenameToBeStored, MultipartFile file) throws IOException {

		ObjectMetadata metadata = new ObjectMetadata();

		metadata.setContentLength(file.getSize());

		InputStream inputStream;

		inputStream = new BufferedInputStream(file.getInputStream());
		PutObjectRequest request = new PutObjectRequest(bucketName, filenameToBeStored, inputStream, metadata);

		s3client.putObject(request);

		S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, filenameToBeStored));
		String url = s3object.getObjectContent().getHttpRequest().getURI().toString();

		return url;
	}
}

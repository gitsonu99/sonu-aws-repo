package com.digivive.api.configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class DynamoDbConfiguration {
@Value("${aws.accessKey}")
private String accessKey;
@Value("${aws.secretId}")
private String secretId;
@Value("${aws.region}")
private String region;
@Value("${aws.endpoint}")
private String endpoint;


@Bean
public DynamoDBMapper dynamoDbMapper() {
    return new  DynamoDBMapper(amazonDynamoDB());
}

@Bean
public AmazonDynamoDB   amazonDynamoDB() {
    return  AmazonDynamoDBClientBuilder.standard()
    		.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
    		.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey,secretId)))
    		.build();
		
}

@Bean 
public AmazonS3 getAmazonS3() {
	return  AmazonS3ClientBuilder.standard()
		   .withRegion(Regions.EU_NORTH_1)
		   .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretId)))
		   .build();
		   	     
}

}

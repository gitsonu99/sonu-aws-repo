package com.digivive.api.configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

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

}

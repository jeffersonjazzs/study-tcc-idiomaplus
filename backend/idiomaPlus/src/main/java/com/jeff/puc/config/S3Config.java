/*
 * 
 */
package com.jeff.puc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class S3Config.
 */
@Configuration
public class S3Config {

	/** The aws id. */
	@Value("${aws.access_key_id}")
	private String awsId;

	/** The aws key. */
	@Value("${aws.secret_access_key}")
	private String awsKey;

	/** The region. */
	@Value("${s3.region}")
	private String region;

	/**
	 * S 3 client.
	 *
	 * @return the amazon S 3
	 */
	@Bean
	public AmazonS3 s3client() {
		BasicAWSCredentials awsCred = new BasicAWSCredentials(awsId, awsKey);
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(awsCred)).build();
		return s3client;
	}
}

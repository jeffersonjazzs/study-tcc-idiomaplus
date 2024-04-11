/*
 * 
 */
package com.jeff.puc.services.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.jeff.puc.services.exceptions.FileException;

// TODO: Auto-generated Javadoc
/**
 * The Class S3Service.
 */
@Service
public class S3Service {

	/** The log. */
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	/** The s 3 client. */
	@Autowired
	private AmazonS3 s3client;

	/** The bucket name. */
	@Value("${s3.bucket}")
	private String bucketName;

	/**
	 * Upload file.
	 *
	 * @param multipartFile the multipart file
	 * @return the uri
	 */
	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream is = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
			throw new FileException("Erro de IO: " + e.getMessage());
		}
	}

	/**
	 * Upload file.
	 *
	 * @param is the is
	 * @param fileName the file name
	 * @param contentType the content type
	 * @return the uri
	 */
	public URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			LOG.info("Iniciando upload");
			s3client.putObject(bucketName, fileName, is, meta);
			LOG.info("Upload finalizado");
			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URL para URI");
		}
	}

	/**
	 * Upload file.
	 *
	 * @param localFilePath the local file path
	 */
	public void uploadFile(String localFilePath) {
		try {
			File file = new File(localFilePath);
			LOG.info("Iniciando upload");
			s3client.putObject(new PutObjectRequest(bucketName, "cp3.jpg", file));
			LOG.info("Upload finalizado");
		} catch (AmazonServiceException e) {
			LOG.info("AmazonServiceException: " + e.getErrorMessage());
			LOG.info("Status code: " + e.getErrorCode());
			LOG.info("Status code: " + e.getErrorType());
		} catch (AmazonClientException e) {
			LOG.info("AmazonClientException: " + e.getMessage());
			LOG.info("AmazonClientException: " + e.getStackTrace());
		}
	}
}

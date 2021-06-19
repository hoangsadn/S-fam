package com.example.demo.amazon;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.ecs.model.Resource;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {
    private final AmazonS3 s3;

    @Autowired
    public FileStore(AmazonS3 s3){
        this.s3 = s3;
    }

    public void save(String path,
                     String fileName,
                     Optional<Map<String,String>> optionalMetaData,
                     InputStream inputStream) {
        ObjectMetadata metadata = new ObjectMetadata();
        optionalMetaData.ifPresent(map->{
            if (!map.isEmpty()){
                map.forEach((key, value) -> metadata.addUserMetadata(key, value));
            }
        });
        try {
            s3.putObject(path,fileName,inputStream,metadata);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to store file to s3");
        }
    }
    public void save(String path,
                     String fileName,
                     InputStream inputStream) {
        ObjectMetadata metadata = new ObjectMetadata();

        try {
            s3.putObject(path,fileName,inputStream,metadata);

        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to store file to s3");
        }
    }
//    public List<byte[]> downloads(String path, String key) {
//        List<Resource> resourceList = new ArrayList<>();
//
//        String s3ResponseFilePath = "s3://bucket/path/"; //put your s3 path here
//
//        try {
//            List<S3ObjectSummary> s3Objects = s3.listObjects("bucket", s3ResponseFilePath).getObjectSummaries();
//            return IOUtils.toByteArray(object.getObjectContent());
//        } catch (AmazonServiceException | IOException e) {
//            throw new IllegalStateException("Failed to download file to s3", e);
//        }
//    }
    public byte[] download(String path, String key) {
        try {
            S3Object object = s3.getObject(path, key);
            return IOUtils.toByteArray(object.getObjectContent());
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download file to s3", e);
        }
    }
}

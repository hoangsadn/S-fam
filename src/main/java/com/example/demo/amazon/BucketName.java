package com.example.demo.amazon;

public enum BucketName {

    PROFILE_IMAGE("s-fam-bucket");

    private final String bucketName;

    BucketName(String bucketName){
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;

    }
}

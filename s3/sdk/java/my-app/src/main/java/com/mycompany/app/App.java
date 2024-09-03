package com.mycompany.app;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.Random;


public class App 
{
    public static void main( String[] args )
    {
        // Get the bucket name from environment variables
        String bucketName = System.getenv("BUCKET_NAME");
        String region = "ca-central-1";

        // Initialize the S3 client
        S3Client s3 = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();

        // Print the bucket name for confirmation
        System.out.println("Bucket name: " + bucketName);

        try {
            // Create the S3 bucket
            CreateBucketRequest bucketRequest = CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .createBucketConfiguration(cb -> cb.locationConstraint(region))
                    .build();

            CreateBucketResponse bucketResponse = s3.createBucket(bucketRequest);
            System.out.println("Bucket created: " + bucketResponse.location());

            // Generate a random number of files to create and upload
            Random random = new Random();
            int numberOfFiles = 1 + random.nextInt(6);
            System.out.println("Number of files: " + numberOfFiles);

            for (int i = 0; i < numberOfFiles; i++) {
                String filename = "file_" + i + ".txt";
                File tempFile = createTempFile(filename);

                // Upload the file to the S3 bucket
                s3.putObject(PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(filename)
                        .build(), tempFile.toPath());

                System.out.println("Uploaded: " + filename);
            }

        } catch (S3Exception | IOException e) {
            e.printStackTrace();
        } finally {
            s3.close();
        }
    }

    // Method to create a temporary file with random content (UUID)
    private static File createTempFile(String filename) throws IOException {
        File tempFile = new File(System.getProperty("java.io.tmpdir"), filename);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            String content = UUID.randomUUID().toString();
            fos.write(content.getBytes(StandardCharsets.UTF_8));
        }
        return tempFile;
    }
}

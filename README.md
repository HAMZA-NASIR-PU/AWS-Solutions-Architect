# AWS Solutions Architect Learning

=> Video lecture that I am following => https://www.youtube.com/watch?v=c3Cn4xYfxJY&t=41074s

## AWS Setup on Windows

This guide will help you set up AWS on Windows, create a user with the necessary permissions on AWS, configure it on your Windows machine, and verify the setup.

### Prerequisites

- A valid AWS account.
- Admin access to install software on your Windows machine.

### 1. Create an AWS Free Tier Account

If you don't have an AWS account, create one by following these steps:

1. Go to the [AWS Free Tier Sign-Up Page](https://aws.amazon.com/free).
2. Complete the registration process.

### 2. Set Up AWS CLI on Windows

#### Step 1: Download and Install AWS CLI

1. Download the AWS CLI installer for Windows from [this link](https://aws.amazon.com/cli/).
2. Run the installer and follow the on-screen instructions.
3. Verify the installation by opening the Command Prompt (`cmd`) and typing:
    ```bash
    aws --version
    ```
   You should see the version of the AWS CLI installed.

#### Step 2: Configure AWS CLI

After setting up the CLI, you’ll need to configure it with your AWS credentials.

### 3. Create a New IAM User in AWS

#### Step 1: Log into the AWS Console

1. Visit the [AWS Management Console](https://aws.amazon.com/console/) and log in with your credentials.

#### Step 2: Create an IAM User

1. Go to the **IAM Dashboard**.
2. Select **Users** from the sidebar and click on **Add User**.
3. Choose a username (e.g., `my-windows-user`).
4. Enable **Programmatic Access** to get access keys for the CLI.
5. Click **Next** and assign permissions. You can either:
   - Attach an existing policy such as `AdministratorAccess`, or
   - Create a custom policy as needed.
6. Click through the remaining steps and **Create User**.
7. Save the **Access Key ID** and **Secret Access Key** as you will need them to configure the AWS CLI.

### 4. Configure AWS on Windows

Now that you have the IAM user's access keys, you can configure the AWS CLI on your Windows machine.

#### Step 1: Open Command Prompt

Open `cmd` on your Windows machine.

#### Step 2: Run the AWS Configure Command

Run the following command to configure the AWS CLI:

```bash
aws configure


### Create a user in AWS using IAM.

### set environment variables

`Dummy Values`

```cmd
setx AWS_ACCESS_KEY_ID AKIAIOSFODNN7EXAMPLE
setx AWS_SECRET_ACCESS_KEY wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY
setx AWS_DEFAULT_REGION us-west-2
```

If above does not work, then use `aws configure` command.

> aws sts get-caller-identity
> aws s3 ls


## System Environment Variables in CMD

This guide covers the basic commands to view and set environment variables in the Windows Command Prompt (CMD).

### Viewing System Variables

#### 1. Viewing All System Variables Using `set`
The `set` command displays all environment variables and their values currently set in the system.

##### Command:
```batch
set
```

##### Steps:
1. Open Command Prompt (`cmd`).
2. Type `set` and press `Enter`.

This will display a list of all environment variables and their corresponding values.

#### 2. Viewing a Specific Environment Variable Using `echo`
You can view the value of a specific environment variable using the `echo` command. To do so, wrap the environment variable name in `%` symbols.

##### Command:
```batch
echo %VARIABLE_NAME%
```

##### Example:
To see the value of the `PATH` environment variable:
```batch
echo %PATH%
```

This command will output the value stored in the `PATH` variable.

### Setting Environment Variables

#### 1. Temporarily Setting an Environment Variable Using `set`
You can use the `set` command to temporarily set a new environment variable for the current CMD session.

##### Command:
```batch
set VARIABLE_NAME=Value
```

##### Example:
To set a variable named `MY_VAR` with the value `HelloWorld`:
```batch
set MY_VAR=HelloWorld
```

You can verify it using the `echo` command:
```batch
echo %MY_VAR%
```

This variable will only be available for the duration of the current CMD session.


## AWS S3 Bucket Management using AWS CLI

This guide demonstrates how to manage AWS S3 buckets using the AWS CLI. It includes commands to list, create, delete, and empty S3 buckets.

### Prerequisites

- AWS CLI must be installed and configured on your machine.
- You must have the necessary IAM permissions to manage S3 buckets and objects.

### Commands

#### 1. List all S3 Buckets

To list all the S3 buckets in your AWS account, use the following command:

```bash
aws s3api list-buckets
```

This command will return the names of all S3 buckets.

#### 2. Delete an S3 Bucket

Before deleting a bucket, ensure that it is empty. Once the bucket is empty, you can delete it using the following command:

```bash
aws s3api delete-bucket --bucket your-bucket-name
```

Replace `your-bucket-name` with the actual name of the S3 bucket you want to delete.

#### 3. List Objects in a Specific S3 Bucket

To view all the objects inside a specific S3 bucket, use the following command:

```bash
aws s3api list-objects --bucket your-bucket-name --query "Contents[].{Key: Key}"
```

This command will display the keys (names) of all the objects stored in the specified S3 bucket.

#### 4. Remove All Objects from an S3 Bucket

If you need to delete all objects from a bucket before deleting the bucket itself, use this command:

```bash
aws s3 rm s3://your-bucket-name --recursive
```

This will delete all objects and subdirectories inside the S3 bucket.

#### Example Usage

1. **List all buckets:**
   ```bash
   aws s3api list-buckets
   ```

2. **Delete a bucket:**
   ```bash
   aws s3api delete-bucket --bucket my-example-bucket-dd2
   ```

3. **List objects in a bucket:**
   ```bash
   aws s3api list-objects --bucket my-example-bucket-dd2 --query "Contents[].{Key: Key}"
   ```

4. **Remove all objects from a bucket:**
   ```bash
   aws s3 rm s3://my-example-bucket-dd --recursive
   ```

### Important Notes

- Before you delete an S3 bucket, you must ensure that the bucket is completely empty, or the delete operation will fail.
- Always make sure you have the necessary IAM permissions to perform operations on S3.



## AWS S3 Storage Classes Explained

Amazon S3 offers a range of storage classes to help you manage costs while meeting different data access and durability needs. Each storage class is designed for specific use cases, providing varying levels of availability, durability, and pricing. This guide explains each storage class in detail.

### 1. S3 Standard Storage Class

#### Description
The **S3 Standard** storage class is designed for frequently accessed data. It provides high durability, availability, and low latency. This is the default storage class if none is specified when uploading objects to S3.

- **Use Case**: Suitable for general-purpose storage of frequently accessed data.
- **Durability**: 99.999999999% (11 9's) across multiple Availability Zones.
- **Availability**: 99.99%.
- **Retrieval Cost**: No retrieval costs.

### 2. S3 Intelligent-Tiering Storage Class

#### Description
The **S3 Intelligent-Tiering** storage class automatically moves data between two access tiers (frequent and infrequent) when access patterns change, optimizing storage costs.

- **Use Case**: Ideal for data with unknown or changing access patterns.
- **Durability**: 99.999999999% (11 9's).
- **Availability**: 99.9%.
- **Retrieval Cost**: No retrieval fee for frequent tier; retrieval fee for infrequent tier.

### 3. S3 Standard-IA (Infrequent Access) Storage Class

#### Description
The **S3 Standard-IA** storage class is for data that is accessed less frequently but still requires rapid access when needed. It offers the same high durability and availability as S3 Standard but at a lower storage cost.

- **Use Case**: Suitable for infrequently accessed, long-lived data.
- **Durability**: 99.999999999% (11 9's).
- **Availability**: 99.9%.
- **Retrieval Cost**: Charged per GB retrieved.

### 4. S3 One Zone-IA Storage Class

#### Description
The **S3 One Zone-IA** storage class stores data in a single Availability Zone, making it more cost-effective than Standard-IA while offering the same infrequent access performance.

- **Use Case**: Ideal for infrequently accessed data that can be easily reproduced in case of availability zone failure.
- **Durability**: 99.999999999% (11 9's) within a single Availability Zone.
- **Availability**: 99.5%.
- **Retrieval Cost**: Charged per GB retrieved.

### 5. S3 Glacier Storage Class

#### Description
The **S3 Glacier** storage class is used for archival storage of data that is rarely accessed and requires long-term storage at low cost. Retrieval times range from minutes to hours.

- **Use Case**: Suitable for long-term backups, digital archives, and disaster recovery.
- **Durability**: 99.999999999% (11 9's).
- **Availability**: Designed for 99.99% availability.
- **Retrieval Cost**: Retrieval time options range from expedited (minutes) to standard (hours).

### 6. S3 Glacier Deep Archive Storage Class

#### Description
The **S3 Glacier Deep Archive** storage class offers the lowest-cost storage for long-term archival data that is rarely accessed and can tolerate longer retrieval times (up to 12 hours).

- **Use Case**: Ideal for data that needs to be retained for regulatory or compliance purposes but is rarely accessed.
- **Durability**: 99.999999999% (11 9's).
- **Availability**: Designed for 99.99% availability.
- **Retrieval Cost**: Retrieval time is 12 hours.

### How to Specify Storage Class

When you upload an object to S3, you can specify the storage class by using the following command in the AWS CLI:

```bash
aws s3 cp file.txt s3://your-bucket-name/ --storage-class STANDARD_IA
```

Replace `STANDARD_IA` with the desired storage class.

### Conclusion

Choosing the right storage class can optimize your costs and performance for different use cases. Understanding the trade-offs between availability, durability, and cost will help you decide the most suitable class for your data.



## AWS S3 CLI Practice Task

This practice task is designed to help you get hands-on experience with key AWS S3 CLI commands as you prepare for your AWS certification. The tasks simulate real-world scenarios you may encounter when working with S3 in the cloud.

### Task 1: Listing Buckets and Objects

1. **List all S3 buckets in your account**:
   - Use the AWS CLI to list all the buckets that currently exist in your AWS account.
   ```bash
   aws s3api list-buckets --query "Buckets[*].Name"
   ```

2. **List all objects in a specific bucket**:
   - Select a bucket from the list and use the CLI to list all the objects stored in that bucket.
   ```bash
   aws s3api list-objects --bucket my-example-bucket-pak --query "Contents[*].Key"
   ```

### Task 2: File Manipulation and Upload to S3

3. **Create a new text file**:
   - Create a new file on your local machine named `abc.txt`.
   ```bash
   touch abc.txt
   ```

4. **Write to the file**:
   - Write the text "HELLO WORLD" into the `abc.txt` file.
   ```bash
   echo "HELLO WORLD" > abc.txt
   ```

5. **Verify the file contents**:
   - Display the contents of the file to ensure the text was written correctly.
   ```bash
   cat abc.txt
   ```

6. **Upload the file to S3**:
   - Use the AWS CLI to upload the `abc.txt` file to an S3 bucket (`my-example-bucket-pak2`). Assign it the same key (`abc.txt`) and specify the content type.
   ```bash
   aws s3api put-object --bucket my-example-bucket-pak2 --key abc.txt --content-type plain/txt --body abc.txt
   ```

### Task 3: Syncing Directories with S3

7. **Synchronize a local directory with S3**:
   - Assume you have a local directory named `images/`. Synchronize the contents of this directory with an S3 bucket.
   ```bash
   aws s3 sync images/ s3://my-example-bucket-pak2
   ```

### Task 4: Downloading and Managing Files

8. **Download an object from S3**:
   - Download the `abc.txt` file from your S3 bucket (`my-example-bucket-pak2`) and save it locally as `abc2.txt`.
   ```bash
   aws s3api get-object --bucket my-example-bucket-pak2 --key abc.txt abc2.txt
   ```

9. **Verify the downloaded file**:
   - Ensure that the file was downloaded correctly by listing the files in the directory and inspecting `abc2.txt`.
   ```bash
   ls -la
   ```

### Task 5: Cleanup

10. **Remove local files**:
    - After completing the tasks, clean up by removing the local `abc.txt` and `abc2.txt` files.
    ```bash
    rm abc.txt abc2.txt
    ```

11. **Verify cleanup**:
    - List the contents of the directory to ensure the files were deleted.
    ```bash
    ls -la
    ```

### Bonus Task: Automate the Workflow

- Create a shell script named `s3Practice.sh` that automates the entire workflow from file creation to file download and cleanup. This will help you practice automation, which is key to effective cloud management.

### Learning Outcomes

By completing this practice task, you will have demonstrated:
- Listing and interacting with S3 buckets and objects.
- Basic file manipulation and management on your local machine.
- Uploading, syncing, and downloading objects from S3.
- File cleanup and workflow automation with shell scripts.

This practice task simulates real-world scenarios and gives you hands-on experience with the AWS S3 CLI commands.



### S3 Bucket

> S3 API
> S3 CLI
> aws s3 ls
> aws s3 rb s3://my-example-bucket
> aws s3api create-bucket --bucket my-bucket-name --region us-east-1
> aws s3api list-buckets
> aws s3api list-objects --bucket my-bucket-name
> aws s3api delete-object --bucket my-bucket-name --key my-file.txt
> aws s3api delete-bucket --bucket my-bucket-name
> aws s3api get-bucket-acl --bucket my-bucket-name
> aws s3api list-buckets --query "Buckets[].Name" --output text
> aws s3api list-buckets --query "Buckets[].{Name: Name, CreationDate: CreationDate}" --output json7
> aws s3api list-buckets --query "Buckets[?starts_with(Name, 'my-')]" --output table
> aws s3api list-buckets --query "Buckets[?starts_with(Name, 'my-')]" --output yaml
> aws s3api list-buckets --query "Buckets[?starts_with(Name, 'my-')]" --output json

1. List All Buckets Created in the Last 7 Days

```bash
# Store the date 7 days ago in a variable
seven_days_ago=$(date -u -d '7 days ago' +'%Y-%m-%dT%H:%M:%SZ')
echo $seven_days_ago

# Use the stored date in the query
aws s3api list-buckets --query "Buckets[?CreationDate > \`'$seven_days_ago'\`]" --output table

# Second way
aws s3api list-buckets --query "Buckets[?CreationDate > '2024-08-01T00:00:00Z']" --output table
```

2. Find Buckets That Have Names Starting with a Specific Prefix
> aws s3api list-buckets --query "Buckets[?starts_with(Name, 'prod-')]" --output table

3. Identify Buckets That Do Not Have Versioning Enabled
```bash
for bucket in $(aws s3api list-buckets --query "Buckets[].Name" --output text); do
  status=$(aws s3api get-bucket-versioning --bucket $bucket --query "Status" --output text)
  if [ "$status" != "Enabled" ]; then
    echo $bucket
  fi
done
```

4. List Buckets and Display Their Creation Dates Only
> aws s3api list-buckets --query "Buckets[].{Name: Name, CreationDate: CreationDate}" --output table

5. List Buckets and Sort Them by Creation Date
> aws s3api list-buckets --query "Buckets | sort_by(@, &CreationDate)[]" --output table

7. List Buckets in a Specific Region
```bash
for bucket in $(aws s3api list-buckets --query "Buckets[].Name" --output text); do
  region=$(aws s3api get-bucket-location --bucket $bucket --query "LocationConstraint" --output text)
  if [ "$region" == "us-east-1" ] || [ "$region" == "None" ]; then
    echo $bucket
  fi
done
```


## Difference between bash and sh.

### Introduction

- **`sh`**: The original Unix shell, known as the Bourne Shell, developed by Stephen Bourne in 1979. It is a simple, POSIX-compliant shell used primarily for basic scripting and command execution.
- **`bash`**: The Bourne Again Shell, developed as part of the GNU Project in 1989. It is an enhanced version of `sh` with many additional features, widely used on Linux systems.

### Key Differences

#### 1. Origin
- **`sh` (Bourne Shell)**: Introduced in Version 7 Unix, 1979.
- **`bash` (Bourne Again Shell)**: Developed as a free replacement for `sh`, released in 1989.

#### 2. Features
- **`sh`**: 
  - Basic scripting capabilities.
  - Lacks advanced features found in modern shells.
  - Suitable for simple scripts and command execution.

- **`bash`**: 
  - Rich feature set, including:
    - Command-line editing.
    - Command history.
    - Arrays.
    - More complex programming constructs.
  - Allows for more sophisticated scripting.

#### 3. Portability
- **`sh`**:
  - Highly portable across Unix-like systems.
  - Ideal for scripts intended to run on various platforms without modification.

- **`bash`**:
  - Widely used, especially on Linux systems.
  - Not as universally available as `sh`, as some Unix-like systems do not include `bash` by default.

#### 4. POSIX Compliance
- **`sh`**:
  - More POSIX-compliant.
  - Adheres strictly to the POSIX standard, ensuring portability and consistency.

- **`bash`**:
  - Mostly backward compatible with `sh`.
  - Can operate in a POSIX-compliant mode when invoked as `sh`, but includes many non-POSIX extensions.

#### 5. Backward Compatibility
- **`sh`** scripts can run in `bash` with minimal or no modification.
- **`bash`** scripts may not be fully compatible with `sh` due to the use of extended features.

### Summary

- **Use `sh`** when you need maximum portability and adherence to the POSIX standard. Ideal for simple, universally compatible scripts.
- **Use `bash`** when you require advanced features, more powerful scripting capabilities, and are working primarily on Linux or GNU systems.

#### Example Usage

#### `sh` Script Example:

```sh
#!/bin/sh
echo "Hello, World!"
```

### Get Caller Identity 

`aws sts get-caller-identity`
`aws sts get-caller-identity --query Arn --output text`
`aws sts get-caller-identity --output json`

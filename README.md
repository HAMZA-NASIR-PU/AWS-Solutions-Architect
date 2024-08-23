# AWS Solutions Architect Learning

=> Video lecture that I am following => https://www.youtube.com/watch?v=c3Cn4xYfxJY&t=41074s

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

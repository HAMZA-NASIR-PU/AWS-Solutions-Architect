# Require necessary libraries
require 'aws-sdk-s3'  # AWS SDK for S3 to interact with S3 services
require 'pry'         # Pry for debugging and exploring code
require 'securerandom' # SecureRandom for generating random UUIDs

# Get the bucket name from environment variables
bucket_name = ENV['BUCKET_NAME']
region = 'ca-central-1'  # Specify the region where the bucket will be created

# Print the bucket name for confirmation
puts bucket_name

# Initialize an S3 client to interact with the S3 service
client = Aws::S3::Client.new

# Create a new bucket with the provided name and region
resp = client.create_bucket({
  bucket: bucket_name,  # Name of the S3 bucket to create
  create_bucket_configuration: {
    location_constraint: region  # Set the region of the bucket
  }
})

# binding.pry

# Generate a random number between 1 and 6 to determine how many files to create
number_of_files = 1 + rand(6)
# Print the number of files to be created
puts "number_of_files: #{number_of_files}"

# Loop to create and upload the files
number_of_files.times.each do |i|
  # Print the current iteration index
  puts "i: #{i}"
  
  # Create a file name based on the current iteration index (e.g., file_0.txt)
  filename = "file_#{i}.txt"
  # Specify the path to save the file temporarily (in the /tmp directory)
  output_path = "/tmp/#{filename}"

  # Open a new file for writing and generate a random UUID to write inside the file
  File.open(output_path, "w") do |f|
    # Write a random UUID to the file
    f.write SecureRandom.uuid
  end

  # Open the newly created file in binary read mode to prepare for uploading to S3
  File.open(output_path, 'rb') do |file|
    # Upload the file to the specified S3 bucket
    client.put_object(
      bucket: bucket_name,  # Name of the S3 bucket
      key: filename,        # The name of the file in the S3 bucket
      body: file            # The file content
    )
  end
end

Spring Boot Project: Top K Most Frequent Words

Description
This Spring Boot project is designed to find the top K most frequent words in a given text file. It exposes a RESTful API endpoint that takes a text file and the value of K as input, processes the file, and returns the K most frequent words along with their frequencies in descending order.

Requirements
Java 11+
Springboot
PostgreSQL
pgAdmin 4
Postman

SetUp and Run

1. Clone the repository:
2. Navigate to the project directory:
3. Build the project using Maven:
4. Edit the following details in application.properties with your DB details

   spring.datasource.url = data source url(jdbc:postgresql://localhost:5432/tech_task)
   spring.datasource.username = your username
   spring.datasource.password = your password

5. Run the Spring Boot application:

6. The application initiates with the execution of a database migration process. A Liquibase query is implemented      to create a 'user' table and insert a sample user. These user details are essential for authentication purposes     within the application.

7. Open the Postman App and create a POST request to access the API endpoint for user authentication.
   Request url  :         http://localhost:8080/api/auth/login
   Method        :       POST
   Request Body  :

		{
    			"email":"admin@mail.com",
    			"password":"admin123"
		}

  Please note that the request body is of JSON format. So you may please change it accordingly.

  Sample Response:

  {
    "accessToken":  "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBuaW50cml2YS5jb20iLCJpYXQiOjE3MDU0MDkxNTksImV4cCI6MTcwNTQwOTQ1OX0.uhXMhZEE_Qc2dnHaZB9WPtsCd1I-z7SzVyGoFDvuelA"
  }

8. Copy the access token generated.

9. Create another Endpoint to find the top K frequent words

   Request url  : http://localhost:8080/api/text/frequent-words-count?count=2 (You may change the value of count as    needed)

   Method: POST

   Select 'Authorization' tab and choose the 'Type' as 'Bearer'. Paste the access token on the specified field.

   Select 'Body' tab and choose 'form-data'. Select Key as 'File' and then type 'file' in the input field as key. Upload a text file in the
   'Value' field.

   Send the request.

   Sample Response:

    {
        "and": 3,
        "i": 3,
        "have": 2,
        "a": 2
    }


    Select a file exceeding 2 KB in size. Upon submitting the request, an exception will occur, and the response      will include an error message with a status code of 400 Bad Request.

    Sample Response:

    {
    "File size exceeds the allowed limit": 5046
    }

    where 5046 denotes the size of the chosen file.


10.  JUnit for FrequentWordService is also implemented.






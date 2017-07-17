# DVISION

Spring Boot REST server implementing a motion detector for network cameras.
Uses an image motion detection algorithm from JavaCV/OpenCV.

### Release notes
* Version 1.0.0 - 2017-07-17
   * First release, based on dvision-server module in https://github.com/dmarell/dvision.

## API usage

Call image-analyze-request along with the image to analyze.
Next, call the same request again with another image. The response contains motion between the two requests and detected
faces.

## Build and run

Build with:
   
``` 
mvn install
``` 

Run integration tests:

``` 
mvn -Pint-test verify
``` 

Run the main method in the Application. It starts an embedded Tomcat on port 8080.

Profile "local" is default:
``` 
java -jar target/dvision-server-1-SNAPSHOT.jar
``` 

Profile "prod":
``` 
java -Dspring.profiles.active=prod -jar target/dvision-server-1-SNAPSHOT.jar
``` 

Configuration for different profiles are in application.yaml.

## Example request/response

HTTP POST http://localhost:8080/image-analyze-request/cam1

Request body is a multipart request with the image file.

Response body:

``` 
{
  "timestamp": 1435161891,
  "imageSize": {
    "width": 1024,
    "height": 384
  },
  "motionAreas": [
    {
      "x": 946,
      "y": 370,
      "width": 15,
      "height": 6,
      "area": 90
    },
    {
      "x": 98,
      "y": 359,
      "width": 3,
      "height": 9,
      "area": 27
    },
    ...
  ]
}
``` 

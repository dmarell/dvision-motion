#imagePath=/Users/daniel/work/dvision/dvision-server/src/test/resources/image2.png
#imagePath=/Users/daniel/Pictures/daniel512.jpeg
imagePath=/Users/daniel/Pictures/460.jpeg
baseUrl=http://35.190.51.14
#baseUrl=localhost:10111
#baseUrl=localhost:8080
curl -F file=@${imagePath} dvisionuser:dvisionpassword@${baseUrl}/image-analyze-request/cam1
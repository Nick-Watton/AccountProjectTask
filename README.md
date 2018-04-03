# AccountProjectTask

This project has only been run in Eclipse, so beware when using other IDEs. 
To run, install Spring Tools 3.9.3 via the Eclipse Marketplace and run the project as 'Spring Boot App'. 
Access the project at http://localhost:8080/

The project *should* run without errors, but I have detailed two errors I have encountered running the project below.


ISSUES

* Sometimes the libraries/dependencies bug out when cloning the repository, and Eclipse does not recognise them. Deleting the contents of C:\Users\UserName\.m2\repository and then running the project as 'Maven install' should fix this issue.
  
* Also upon cloning sometimes some of the src/main/resources/templates .html files can be missing. There should be 8 .html files in total. Deleting the project from your machine and re-cloning usually fixes this issue.

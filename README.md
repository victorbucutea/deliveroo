# Cron Expression Parser



The script was developed in java and maven was used for building and unit testing.
The decision to use these tools was taken given the popularity of the tools in java world. 
only dependency is junit. 

N.b. You will need at least Apache Maven 3.x and JDK 1.8 to build and run the script. 

### Running the script
From the project root, do `mvn clean install` and execute according to specs:
```bash
$ mvn clean install
..
[INFO] BUILD SUCCESS
..
$ java -jar target/deliveroo-1.0-SNAPSHOT.jar "0 0 1,2,3,15 * 1-5 /usr/bin/find"
  minute        0
  hour          0
  day of month  1 2 3 15
  month         1 2 3 4 5 6 7 8 9 10 11 12
  day of week   1 2 3 4 5
  command       /usr/bin/find
```



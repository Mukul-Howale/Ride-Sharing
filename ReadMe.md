# Problem Statement

## Context
 Once upon a time, there was a group of entrepreneurs who were busy building a new ride-sharing service. They were working tirelessly to make sure that their service was the best it could be for their riders (i.e passengers) and drivers. However, they knew that there was one important feature missing, the ability to match riders with drivers within a 5km range (inclusive).
 
 One day, they realized that they needed to implement this feature as soon as possible, and they needed someone to take on the challenge of creating it. Can you help them to complete this?
 
Euclidean Distance Formula
 For ease of calculation, this service assumes a Cartesian coordinate system to represent locations, where the location of drivers and riders are represented as a Point(x, y). The Euclidean distance is utilized to calculate the distance between any two points, and 1 unit is equivalent to 1 km.
 
 The Euclidean distance formula says:
 
d = √[ (x2–x1)2 + (y2–y1)2]
 where,
 
 (x1, y1) are the coordinates of one point. 
 (x2, y2) are the coordinates of the other point. 
 d is the distance between (x1, y1) and (x2, y2).
 
## Goal
 Your task is to build a solution that will help to match riders with drivers based on their location and generate a bill for the ride. 
 
## Assumptions
- It is guaranteed that no two drivers or riders will have the same id. 
- Ride can only be started once the match is completed. 
- Every start ride request will happen after the match request. 
- Every start ride request will have a valid rider id. 
- One rider can make multiple match requests. 
- Bill for the ride will be calculated based on the distance between the rider's location and the destination. 
- Bill can only be generated after the ride is completed. 
- The driver will not be available to accept another rider's request after the ride has started. 
- Time taken for a ride cannot be negative. 
- All floating point numerical values must be rounded to two decimal places.

<pre></pre>
# Sample I/O

## Input Commands & Format
`ADD_DRIVER` <DRIVER_ID> <X_COORDINATE> <Y_COORDINATE>
 
 The ADD_DRIVER command allows a driver to join the service. The command should take in the driver's id and current location (x_coordinate and y_coordinate) as arguments.
 
`ADD_RIDER` <RIDER_ID> <X_COORDINATE> <Y_COORDINATE>
 
 The ADD_RIDER command allows a rider to request a ride. The command should take in the rider's id, current location (x_coordinate and y_coordinate), as arguments.
 
`MATCH` <RIDER_ID>
 
 Matches the rider with the nearest available drivers within 5 kms distance. Print nearest 5 drivers ids in ascending order of their distance from the rider in the following format. In the event of multiple drivers being equidistant, print them in lexicographical order.:
 
 DRIVERS_MATCHED <DRIVER_ID1> <DRIVER_ID2> ... <DRIVER_ID5> 
 If no drivers are available then print ‘NO_DRIVERS_AVAILABLE’
 
`START_RIDE` <RIDE_ID> <N> <RIDER_ID>
 
 Start the ride with the Nth Driver (1 >= N <= 5). If the match has fewer than N number of drivers, driver is not available, or <RIDE_ID> already exists, then print ‘INVALID_RIDE’ otherwise, print ‘RIDE_STARTED <RIDE_ID>’.
 
`STOP_RIDE` <RIDE_ID> <DESTINATION_X_COORDINATE> <DESTINATION_Y_COORDINATE> <TIME_TAKEN_IN_MIN>
 
 If the <RIDE_ID> does not exist, or the ride is already stopped, then print ‘INVALID_RIDE’, otherwise, Print ‘RIDE_STOPPED <RIDE_ID>’
 
`BILL` <RIDE_ID>
 
 Print the total bill of the ride in the format ‘BILL <RIDE_ID> <DRIVER_ID> <AMOUNT>’. To calculate the total bill use the following formula: 
 
- A base fare of ₹50 is charged for every ride. 
- An additional ₹6.5 is charged for every kilometer traveled. 
- An additional ₹2 is charged for every minute spent in the ride. 
- A service tax of 20% is added to the final amount.
 
## Note:
 
- If the ride is not completed then print “RIDE_NOT_COMPLETED” 
- If the <RIDE_ID> does not exist, then print ‘INVALID_RIDE’

## Sample Input/Output 1

### Input
- ADD_DRIVER D1 1 1
- ADD_DRIVER D2 4 5
- ADD_DRIVE RD3 2 2
- ADD_RIDER R1 0 0
- MATCH R1
- START_RIDE RIDE-001 2 R1
- STOP_RIDE RIDE-001 4 5 32
- BILL RIDE-001

### Output
- DRIVERS_MATCHED D1 D3
- RIDE_STARTED RIDE-001
- RIDE_STOPPED RIDE-001
- BILL RIDE-001 D3 186.72

## Sample Input/Output 2

### Input
- ADD_DRIVER D1 0 1
- ADD_DRIVER D2 2 3
- ADD_RIDER R1 3 5
- ADD_DRIVER D3 4 2
- ADD_RIDER R2 1 1
- MATCH R1
- MATCH R2
- START_RIDE RIDE-101 1 R1
- START_RIDE RIDE-102 1 R2
- STOP_RIDE RIDE-101 10 2 48
- STOP_RIDE RIDE-102 7 9 50
- BILL RIDE-101
- BILL RIDE-102

### Output
- DRIVERS_MATCHED D2 D3 D1
- DRIVERS_MATCHED D1 D2 D3
- RIDE_STARTED RIDE-101
- RIDE_STARTED RIDE-102
- RIDE_STOPPED RIDE-101
- RIDE_STOPPED RIDE-102
- BILL RIDE-101 D2 234.64
- BILL RIDE-102 D1 258.00

<pre></pre>
# Pre-requisites
* Java 1.8/1.11/1.15
* Maven

# How to run the code

We have provided scripts to execute the code. 

Use `run.sh` if you are Linux/Unix/macOS Operating systems and `run.bat` if you are on Windows.  Both the files run the commands silently and prints only output from the input file `sample_input/input1.txt`. You are supposed to add the input commands in the file from the appropriate problem statement. 

Internally both the scripts run the following commands 

 * `mvn clean install -DskipTests assembly:single -q` - This will create a jar file `geektrust.jar` in the `target` folder.
 * `java -jar target/geektrust.jar sample_input/input1.txt` - This will execute the jar file passing in the sample input file as the command line argument

 We expect your program to take the location to the text file as parameter. Input needs to be read from a text file, and output should be printed to the console. The text file will contain only commands in the format prescribed by the respective problem.

 Use the pom.xml provided along with this project. Please change the main class entry (`<mainClass>com.example.geektrust.Main</mainClass>`) in the pom.xml if your main class has changed.

 # Running the code for multiple test cases

 Please fill `input1.txt` and `input2.txt` with the input commands and use those files in `run.bat` or `run.sh`. Replace `java -jar target/geektrust.jar sample_input/input1.txt` with `java -jar target/geektrust.jar sample_input/input2.txt` to run the test case from the second file. 

 # How to execute the unit tests

 `mvn clean test` will execute the unit test cases.

# Help

You can refer our help documents [here](https://help.geektrust.com)
You can read build instructions [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java)
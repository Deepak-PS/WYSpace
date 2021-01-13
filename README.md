# WY Space Solution

A java application to find the 30 minute period where the total downlink of all the satellite passes will be at its maximum. It also finds whether the ground station has bandwidth to support it based on the capacity of the ground station.

# Task

WY Space has a fleet of satellites, and a ground station to communicate with them. Each satellite has a downlink rate measured in units per 30 minutes. The ground station has a maximum bandwidth that it can handle at any one time; it can handle multiple satellites in parallel.

A satellite can only downlink data when the ground station can see it, this window is called a pass. When the pass begins, the connection and downlink to the ground station is immediate, and it is assumed that there are no delays. Similarly, the downlink will immediately stop when the pass ends. All passes are a minimum of 30 minutes.

WY Space application would like to take a text based schedule which is available in the project, and uses this program to find the 30 minute period where the total downlink (all satellite passes) will be at its maximum. Furthermore, it also determines if the ground station has the bandwidth to support this. The goal is to test for multiple ground stations, hence the bandwidth of the ground station should be provided as an argument to the program.

The inputs in the file can be interpereted as: 
| Satellite Name | Bandwidth | Start Time | End Time |
| -------------- | :--------:| :---------:| --------:|

# How to run the program

- Import the project on any IDE / use the terminal
- Run the Main.java file by passing the bandwidth of the ground station as an argument.


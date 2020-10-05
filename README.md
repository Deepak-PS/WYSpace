# WY Space Solution

A java application to find the 30 minute period where the total downlink of all the satellite passes will be at its maximum. It also finds whether the ground station has bandwidth to support it based on the capacity of the ground station. 

# How to run the program

- Unzip the archive
- Import the project on any IDE / use the terminal
- Run the Solution.java file by passing the bandwidth of the ground station as an argument.

# Approach

The algorithm which I have written can be explained in the following points: 

- The time values in String format are converted to minutes in integer format. For example, 02:30 can be written as 150. This is the minute equivalent to the time value.

- The duration of the pass is calculated and the counter begins from 0 and extends till the end of the pass. For example, if start = 00:00 and end = 01:30, then the duration is 90 minutes. The counter values range is 0, 30, and 60.

- In order to differentiate whether the pass extends to the next day, the following step is followed:

    - If the start time is greater than the end time (start = 23:30 and end = 00:00), one day will be added to the end time i.e. 1440 minutes. The start time will be greater than the end time only if the end time  of the pass is in the next day.
    
- The start time and the bandwidth are stored in a HashMap, and the bandwidth is updated for every recurring start value.

- The maximum of all the values is the maximum downlink value. The keys which match this maximum value are taken and the 30 minute duration is computed.

- Based on the provided ground station bandwidth, whether it supports the maximum downlink value or not is determined.


# Comments

- 1440 is the total number of minutes in a day.

- In the pass, Nostromo,3,00:00,00:00 - the start time and the end time are the same. Since it is mentioned in the description that each pass is of 30 minutes duration. Hence, the end time is taken as the 00:00 hour of the next day.

# Tracing

For the passes in the provided file, the maximum downlink of all the satellite passes occur between 15:00 and 15:30, and 10:00 and 10:30 periods.

Here, I provide the tracing for 15:00 to 15:30 period from the following passes. <br>
For the pass, RedDwarf,2,15:00,16:30, the downlink rate between 15:00 to 15:30 period is 2. <br>
For the pass, Nostromo,3,00:00,00:00, the downlink rate between 15:00 to 15:30 period is 3. <br>
For the pass, Sulaco,10,15:00,15:30, the downlink rate between 15:00 to 15:30 period is 10. <br>
For the pass, Rocinante,30,12:00,16:30, the downlink rate between 15:00 to 15:30 period is 30. <br>
Hence, the total downlink rate between 15:00 to 15:30 period is 45. 

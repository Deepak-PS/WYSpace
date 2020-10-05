/*
 * ***************************************************************************
 *
 *   Project-Name:    WY Space Solution
 *
 *   Author:          Deepak Pradhan
 *   Date:            26 Sep 2020
 *
 * **************************************************************************
 */
package com.wyspace.solution.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

public class Solution {

	public static void main(String[] args) {

		BufferedReader br;
		String st;
		String[] pass;

		final String FILE_NAME = "2458843pass-schedule.txt";

		int start = 0;
		int end = 0;
		int bandwidth = 0;

		int passCounter = 0;
		int passDuration = 0;
		int groundStationBandwidth = 0;
		int period = 30;

		int maxBandwidth = 0;

		File file = new File(FILE_NAME);
		if (file.exists()) {

			try {
				if (args.length > 0) {
					// receiving maximum bandwidth limit of a ground station from arguments
					final int MAX_BANDWIDTH_LIMIT = toInteger(args[0]);

					HashMap<Integer, Integer> groundStation = new HashMap<Integer, Integer>();

					br = new BufferedReader(new FileReader(file));
					while ((st = br.readLine()) != null) {
						pass = st.split(",");

						start = toMins(pass[2]);
						end = toMins(pass[3]);
						bandwidth = toInteger(pass[1]);
						passCounter = 0;

						// to reinitialise 'end' if it extends to the next day, eg. start = 23:30, end =
						// 00:00
						if (start >= end) {
							end += 1440;
						}

						// get the duration of each pass
						passDuration = getDuration(start, end);

						// pass begins
						while (passCounter < passDuration) {

							// update the ground station bandwidth if the start time of the period
							// already exists
							if (groundStation.get(start) != null) {
								groundStationBandwidth = groundStation.get(start) + bandwidth;
							} else {
								groundStationBandwidth = bandwidth;
							}
							// Create/update the HashMap with start time of the period and bandwidth
							groundStation.put(start, groundStationBandwidth);

							start += period;
							passCounter += period;
						}
						// pass ends
					}

					// obtaining the maximum bandwidth of the ground station from the HashMap
					maxBandwidth = (Collections.max(groundStation.values()));

					for (Map.Entry<Integer, Integer> entry : groundStation.entrySet()) {

						// if an entry of HashMap has maximum bandwidth, then display its bandwidth and
						// period
						if (entry.getValue() == maxBandwidth) {
							System.out.printf(
									"The 30 minute period where the total downlink (all satellite passes) was maximum at %d between %s and %s.\n",
									entry.getValue(), toTime(entry.getKey()), toTime(entry.getKey() + period));

							// if an entry has maximum bandwidth above its provided maximum capacity, then
							// display that the ground station doesn't support it, and vice versa.
							if (entry.getValue() > MAX_BANDWIDTH_LIMIT) {
								System.out.println("The ground station doesn\'t have the bandwidth to support this.\n");
							} else {
								System.out.println("The ground station has the bandwidth to support this.\n");
							}
						}
					}
				}
				else {
					System.out.println("Please provide the bandwidth limit of the ground station as the argument.");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("The file is not present.");
		}
	}

	private static int toInteger(String str) throws NumberFormatException {
		return Integer.parseInt(str);
	}

	private static int toMins(String time) throws NumberFormatException, PatternSyntaxException {
		String[] hourMin = time.split(":");
		int hour = toInteger(hourMin[0]);
		int mins = toInteger(hourMin[1]);
		int hoursInMins = hour * 60;
		return hoursInMins + mins;
	}

	private static int getDuration(int start, int end) {
		return end - start;
	}

	private static String toTime(int num) throws NullPointerException, IllegalArgumentException {
		final DecimalFormat decimalFormat = new DecimalFormat("00");
		int hours = num / 60;
		int minutes = num % 60;
		return decimalFormat.format(hours) + ":" + decimalFormat.format(minutes);
	}
}

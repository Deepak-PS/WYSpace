package com.wyspace.solution.algorithm;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.wyspace.solution.model.Pass;

public class MaxBandwidth {
	private static final int TOTAL_MINUTES = 1440;
	private static final int MINUTES = 60;
	private static final int SECONDS = 60;

	private List<Pass> passes;
	private final int maxBandwidthLimit;
	private final int period;

	HashMap<Integer, Integer> groundStation = new HashMap<Integer, Integer>();

	public MaxBandwidth(List<Pass> passes, String maxBandwidthLimit, int period) {
		this.passes = passes;
		this.maxBandwidthLimit = toInteger(maxBandwidthLimit);
		this.period = period;
	}

	public void showMaxBandwidth() {
		passes.forEach(pass -> {
			int passCounter = 0;
			int passDuration, groundStationBandwidth = 0;
			int period = 30;

			int start = pass.getStart();
			int end = pass.getEnd();
			int bandwidth = pass.getBandwidth();

			// to reinitialise 'end' if it extends to the next day, eg. start = 23:30, end =
			// 00:00
			if (start >= end) {
				end += TOTAL_MINUTES;
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
		});

		print();
	}

	private void print() {
		int maxBandwidth;
		// obtaining the maximum bandwidth of the ground station from the HashMap
		maxBandwidth = (Collections.max(groundStation.values()));

		groundStation.entrySet().stream().filter(entry -> entry.getValue() == maxBandwidth).forEach(entry -> {
			// if an entry of HashMap has maximum bandwidth, then display its bandwidth and
			// period
			System.out.printf(
					"The 30 minute period where the total downlink (all satellite passes) was maximum at %d between %s and %s.\n",
					entry.getValue(), toTime(entry.getKey()), toTime(entry.getKey() + period));

			// if an entry has maximum bandwidth above its provided maximum capacity, then
			// display that the ground station doesn't support it, and vice versa.
			if (entry.getValue() > maxBandwidthLimit) {
				System.out.println("The ground station doesn\'t have the bandwidth to support this.\n");
			} else {
				System.out.println("The ground station has the bandwidth to support this.\n");
			}
		});
	}

	private static int getDuration(int start, int end) {
		return end - start;
	}

	private static String toTime(int num) {
		final String STRINGFORMAT = "00";
		DecimalFormat decimalFormat = new DecimalFormat(STRINGFORMAT);
		int hours = num / MINUTES;
		int minutes = num % SECONDS;
		return decimalFormat.format(hours) + ":" + decimalFormat.format(minutes);
	}

	private static int toInteger(String str) {
		return (str != null && !"".equals(str)) ? Integer.parseInt(str) : 0;
	}

}

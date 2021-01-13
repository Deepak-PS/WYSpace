package com.wyspace.solution;

import java.util.List;

import com.wyspace.solution.algorithm.MaxBandwidth;
import com.wyspace.solution.builder.PassBuilder;
import com.wyspace.solution.model.Pass;

public class Main {

	private static final String FILE_NAME = "2458843pass-schedule.txt";

	public static void main(String[] args) {
		int period = 30;

		if (args.length < 1) {
			System.out.println("Please provide the bandwidth limit of the ground station as the argument.");
			System.exit(1);
		}

		String max_bandwidth_limit = args[0];

		PassBuilder PassBuilder = new PassBuilder(FILE_NAME);
		List<Pass> passes = PassBuilder.buildPassEntity();

		MaxBandwidth maxBandwidth = new MaxBandwidth(passes, max_bandwidth_limit, period);
		maxBandwidth.showMaxBandwidth();
	}

}

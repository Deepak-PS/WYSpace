package com.wyspace.solution.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wyspace.solution.model.Pass;

import java.io.FileReader;

public class PassBuilder {
	private static final int MINUTES = 60;

	private String filename;

	public PassBuilder(String filename) {
		this.filename = filename;
	}

	public List<Pass> buildPassEntity() {
		String line;
		String[] passString;
		Pass pass;
		BufferedReader br;
		List<Pass> passes = new ArrayList<>();

		File file = new File(filename);
		try {
			if (file.exists()) {
				br = new BufferedReader(new FileReader(file));
				while ((line = br.readLine()) != null) {
					passString = line.split(",");
					pass = new Pass();
					pass.setSatelliteName(passString[0]);
					pass.setBandwidth(toInteger(passString[1]));
					pass.setStart(toMins(passString[2]));
					pass.setEnd(toMins(passString[3]));
					passes.add(pass);
				}
			}
		} catch (IOException e) {
			System.out.println("The file is not present or the file format is invalid.");
		}

		return passes;
	}

	private static int toInteger(String str) {
		return (str != null && !"".equals(str)) ? Integer.parseInt(str) : 0;
	}

	private static int toMins(String time) {
		if (time != null && !"".equals(time)) {
			String[] hourMin = time.split(":");
			int hour = toInteger(hourMin[0]);
			int mins = toInteger(hourMin[1]);
			int hoursInMins = hour * MINUTES;

			return hoursInMins + mins;
		}
		return 0;
	}
}

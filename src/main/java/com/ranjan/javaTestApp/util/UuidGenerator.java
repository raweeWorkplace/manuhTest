package com.ranjan.javaTestApp.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public class UuidGenerator {
	public static UuidGenerator uuidGenerator;
	private Random seeder;
	private String midValue;
	private char zero[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static UuidGenerator getInstance() {
		if (uuidGenerator == null) {
			uuidGenerator = new UuidGenerator();
		}
		return uuidGenerator;
	}

	public UuidGenerator() {
		InetAddress inet = null;
		byte bytes[];
		try {
			inet = InetAddress.getLocalHost();
			bytes = inet.getAddress();
		} catch (UnknownHostException e) {
			bytes = "127.0.0.1".getBytes();
		}
		StringBuffer sb = new StringBuffer();
		for (int c = 0; c < 4; c++) {
			int i = bytes[c] & 0xff;
			sb.append(hexFormat(i, 2, false));
		}

		String hexInetAddress = sb.toString();
		String thisHashCode = hexFormat(System.identityHashCode(this), 8, false);
		midValue = hexInetAddress + "-" + thisHashCode;
		seeder = new Random();
	}

	public synchronized String getUuid() {
		long timeNow = System.currentTimeMillis();
		int timeLow = (int) timeNow & -1;
		int node = seeder.nextInt();
		return hexFormat(timeLow, 8, false) + "-" + midValue + "-" + hexFormat(node, 8, false);
	}

	private String hexFormat(int val, int length, boolean flag) {
		StringBuffer sb = new StringBuffer(Integer.toHexString(val));
		if (sb.length() < length) {
			sb.append(zero, 0, length - sb.length());
		}
		if (length > 4 && flag == true) {
			return sb.toString().substring(0, 4);
		} else {
			return sb.toString();
		}

	}

}

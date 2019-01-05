package com.mission.test.baseconversion;

public class BaseConversion {

	public static void main(String[] args) {
		int num = 12345;
		String binary = convertToBinary(num);
		System.out.println("Binary conversion is : " + binary);
		System.out.println("Original number is : " + convertToDecimal(binary));

		String base64 = convertTo64Base(num);
		System.out.println("64 base conversion is : " + base64);
		System.out.println("Original number is : " + convertToDecimalFromBase64(base64));
	}

	private static String convertToBinary(int num) {
		int base = 2;
		StringBuilder b = new StringBuilder();
		while (num != 0) {
			int remainder = num % base;
			num = num / base;
			b.append(remainder);
		}
		return b.reverse().toString();
	}

	private static int convertToDecimal(String binary) {
		if (binary.matches("[0*1*]*")) {
			int num = 0;
			for (int i = 0; i < binary.length(); i++) {
				int digit = Integer.parseInt("" + binary.charAt(i));
				num += digit * (1 << (binary.length() - i - 1));
			}
			return num;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private static String convertTo64Base(int num) {
		int base = 64;
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789./";
		StringBuilder builder = new StringBuilder();
		while (num != 0) {
			int remainder = num % base;
			num = num / base;
			builder.append(str.charAt(remainder));
		}
		return builder.reverse().toString();
	}

	private static int convertToDecimalFromBase64(String base64) {
		int num = 0;
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789./";
		for (int i = 0, pow = base64.length() - 1; i < base64.length(); i++, pow--) {
			int digit = str.indexOf(base64.charAt(i));
			num += digit * Math.pow(64, pow);
		}
		return num;
	}
}

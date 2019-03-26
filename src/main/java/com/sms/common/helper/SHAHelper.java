package com.sms.common.helper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 1		: 356a192b7913b04c54574d18c28d46e6395428ab
// 123 		: 40bd001563085fc35165329ea1ff5c5ecbdbbeef
// sms2018 	: 75c30e97fb9f7af640c5f81e8005be45a2d9ff01
// Nintendo : 0cac146e40c74a6c0b6e5a28eb709d7c9a225bbe

public class SHAHelper {
	private static MessageDigest messageDigest = null;

	public static String generateHashedString(String message)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if (messageDigest == null) {
			messageDigest = MessageDigest.getInstance("SHA-1");
		}

		byte[] sha1hash = new byte[40];
		messageDigest.update(message.getBytes("iso-8859-1"), 0, message.length());
		sha1hash = messageDigest.digest();
		return convertToHexString(sha1hash);
	}

	private static String convertToHexString(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}
}

package com.killmalware.bbsec;

import java.io.UnsupportedEncodingException;
import net.rim.device.api.crypto.SHA1Digest;

public class ShaMethod {
	public static String sha(final String msg)
	
	{
		SHA1Digest sha1Digest = new SHA1Digest();
		String sha = msg;

		byte[] inpData = null;
		try {
			inpData = sha.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		sha1Digest.update(inpData, 0, inpData.length);
		byte[] digest = sha1Digest.getDigest();
		StringBuffer shaRes = new StringBuffer(40); //40 hex char is size of 160-bit SHA-1 result
		for (int a = 0; a < digest.length; a++)
		{
			String tmp = Integer.toHexString(0xff & digest[a]);
			if(tmp.length() == 1) //If hex value is "0X" then tmp is just one digit "X"
			{
				shaRes.append('0');
			}
			shaRes.append(tmp);
		}
		return shaRes.toString();
	}
}
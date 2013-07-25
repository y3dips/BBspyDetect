package com.killmalware.bbsec;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import net.rim.device.api.i18n.SimpleDateFormat;

public class SMSLogResult {
	
	private static String logFileName()
	{
		Date dt = new Date();
		SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yy");
		return "file:///store/home/user/bbsexurity-result-"+sfd.formatLocal(dt.getTime())+".txt";
	}
	
	public static void logSMS(String alert, String num)
	{
		String line = alert+ ", "+num+"\r\n";
		try 
		{
			FileConnection conn = (FileConnection)Connector.open(logFileName(), Connector.READ_WRITE);
			long x = 0;
			if(!conn.exists())
			{
				conn.create();
			} else
			{
				x = conn.fileSize();
			}
			OutputStream ostream = conn.openOutputStream(x);
			ostream.write(line.getBytes());
			ostream.close();
			conn.close();
		} 
		catch (IOException e) 
		{
			Helpers.alertMsg("Error writing to log file");
		}
	
	}
}

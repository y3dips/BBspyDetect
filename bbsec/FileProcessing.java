package com.killmalware.bbsec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.io.LineReader;

public class FileProcessing {
	
	
	private static String logFileName(String filename)
	{
		Date dt = new Date();
		SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yy");
		return "file:///store/home/user/"+filename+sfd.formatLocal(dt.getTime())+".csv";
	}
	
	public static void Process (final String process)
	{
		
	}
	
	public static void Write (String writemsg, String filename)
	{
		try //save to file
			{  
			FileConnection conn = (FileConnection)Connector.open(logFileName(filename),Connector.READ_WRITE);
			long x =0;
        	if (!conn.exists())
        		{                              
        			conn.create();        
        		} 
        	else
        		{
        		OutputStream out = conn.openOutputStream(x);
        		byte[] b = writemsg.getBytes();
				if(conn.canWrite())         
    				{                                             
					out.write(b);
    				}
				else
					{
					System.out.println("Cannot write to this file");
    				out.close();     
					}
        		}
			}
		catch(Exception io)
		{
		 System.out.println("exception" +io);
		}
        		}
	
	public static void Check (String raw, String filename)
	{
		try {
			FileConnection conn = (FileConnection)Connector.open(logFileName(filename),Connector.READ_WRITE); 
            InputStream stream = conn.openInputStream();
            LineReader lineReader = new LineReader(stream);
            String line = new String(lineReader.readLine());
            while(!line.equals("EOF"))
            {
           	//compare
           	 if(line.equals(raw))
            		{
           		 Helpers.alertMsg("SMS sent twice!");
           		 //write to log file
           		 Write(line.toString(), "result");
           		 break;
            		}		
           	 else 
             		{ 
           		 line = new String(lineReader.readLine());
             		}
            }
            stream.close();
            stream = null;
            } 
		catch(IOException e) {}
	}
	
	public void Log (String logmsg)
	{
			
	}
}

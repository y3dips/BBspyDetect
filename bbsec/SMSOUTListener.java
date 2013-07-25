package com.killmalware.bbsec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.wireless.messaging.TextMessage;
import net.rim.blackberry.api.sms.SendListener;
import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.io.LineReader;
import net.rim.device.api.ui.UiApplication;

public class SMSOUTListener implements SendListener
{

	public boolean sendMessage(javax.wireless.messaging.Message message)
    {
		final String num = message.getAddress();
		final String msg = ((TextMessage) message).getPayloadText();
        if (message instanceof TextMessage)
        {
        	UiApplication.getUiApplication().invokeLater( new Runnable()
        	{
            	public void run() 
            	{   
            		//sha the message
            		String raw = (String) ShaMethod.sha(msg);
            		
            		try //save to file
            		{             
            		String data= (raw+"\r\n");
            		
            		//name the file as a date
            		Date dt = new Date();
            		SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yy");
            		String filename = sfd.formatLocal(dt.getTime());
            		
                    FileConnection conn = (FileConnection)Connector.open("file:///store/home/user/bbsexurity-smslog-"+filename+".txt",Connector.READ_WRITE);                          
                    long x =0;
                    	if (!conn.exists())
                    		{                              
                    			conn.create();        
                    		} 
                    	else
                    		{
                    			x = conn.fileSize();
                    			//read file
                    			try {
                 	                 InputStream stream = conn.openInputStream();
	                                 LineReader lineReader = new LineReader(stream);
	                                 String line = new String(lineReader.readLine());
	                                 while(!line.equals("EOF"))
	                                 {
	                                	//compare
	                                	 if(line.equals(raw))
	                                 		{
	                                		 //Helpers.alertMsg("SMS sent twice!");
	                                		 SMSLogResult.logSMS("Double SMS Detected", num);
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
                    		OutputStream out = conn.openOutputStream(x);
                    		byte[] b = data.getBytes();
                       
                        	if(conn.canWrite())         
                        		{                                             
                        		out.write(b);
                        		}
                        	else
                        		System.out.println("Cannot write to this file");
                        		out.close();     
            					}
            			catch(Exception io)
            			{
            				System.out.println("exception" +io);
            			}
            		}
        		}
        	);
        } else
        {  
        	System.out.println("No Idea" );
        }
        return true;
    }
}
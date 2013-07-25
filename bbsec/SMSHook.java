package com.killmalware.bbsec;

import net.rim.blackberry.api.sms.SMS;
	
	public class SMSHook 
	{
		private boolean hooked;
		private SMSOUTListener smsoutListener;
		
		public SMSHook()
		{
			hooked = false;
		}
		
		public void HookSMS()
		{
			smsoutListener = new SMSOUTListener();
			SMS.addSendListener(smsoutListener);
			hooked = true;
		}
		
		public void unHookSMS()
		{
			SMS.removeSendListener(smsoutListener);
			hooked = false;
		}
		
		public boolean isHooked()
		{
			return hooked;
		}
	}
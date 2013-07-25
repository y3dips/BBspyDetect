package com.killmalware.bbsec;

import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class AppScreen extends MainScreen
{
	private SMSHook sh;
	private Bitmap back;

	public AppScreen()
	{
		super();
		back = Bitmap.getBitmapResource("screen2.png");
		VerticalFieldManager base = new VerticalFieldManager(Manager.USE_ALL_HEIGHT|Manager.USE_ALL_WIDTH)
		{
			protected void paint( Graphics g )
            {
                g.drawBitmap( 0, 0, back.getWidth(), back.getHeight(), back, 0, 0 );
                
                super.paint( g );
            }
		
		};
		
		sh = new SMSHook();
		ButtonField hook = new ButtonField("Hook SMSs",ButtonField.CONSUME_CLICK)
		{
			protected boolean keyDown(int keycode, int time)
			{
				if(Keypad.key (keycode) == Keypad.KEY_ENTER)
				{
					if(!sh.isHooked())
					{
						sh.HookSMS();
						Helpers.alertMsg("SMSs Hooked");
					}
					return true;
				}
				
				return super.keyDown(keycode, time);	
			}
			
			protected boolean navigationClick(int status, int time)
			{
				if(!sh.isHooked())
				{
					sh.HookSMS();
					Helpers.alertMsg("SMSs Hooked");
				}
				return true;
			}
		};
		
		
		ButtonField unhook = new ButtonField("Unhook SMSs",ButtonField.CONSUME_CLICK)
		{
			protected boolean keyDown(int keycode, int time)
			{
				if(Keypad.key (keycode) == Keypad.KEY_ENTER)
				{
					if(sh.isHooked())
					{
						sh.unHookSMS();
						Helpers.alertMsg("SMSs Unhooked");
					}
					return true;
				}
				
				return super.keyDown(keycode, time);	
			}
			
			protected boolean navigationClick(int status, int time)
			{
				if(sh.isHooked())
				{
					sh.unHookSMS();
					Helpers.alertMsg("SMSs Unhooked");
				}
				return true;
			}
		};
		
		base.add(hook);
		base.add(unhook);
		add(base);
	}
}

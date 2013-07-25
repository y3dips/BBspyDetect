package com.killmalware.bbsec;

import net.rim.device.api.ui.UiApplication;

public class Main extends UiApplication 
{
	public static void main(String[] args)
	{
		Main app = new Main();
		app.enterEventDispatcher();
	}
	
	public Main()
	{
		pushScreen(new AppScreen());
	}
}

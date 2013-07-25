package com.killmalware.bbsec;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;

public class Helpers 
{
    public static void alertMsg(final String msg)
    {
        System.out.println(msg);
        UiApplication.getUiApplication().invokeLater(new Runnable()
        {
            public void run()
            {
                Dialog.alert(msg);
            }
        }
        );
    }
}
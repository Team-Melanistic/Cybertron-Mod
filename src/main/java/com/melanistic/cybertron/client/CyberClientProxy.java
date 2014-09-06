package com.melanistic.cybertron.client;

import com.melanistic.cybertron.common.CyberCommonProxy;
import com.melanistic.cybertron.lib.CybertronKeyBindings;

public class CyberClientProxy extends CyberCommonProxy {
	public void preInit()
	{
		super.preInit();
	}
	
	public void init()
	{
		super.init();
		CybertronKeyBindings.registerKeys();
	}
}

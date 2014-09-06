package com.melanistic.cybertron.client;

import com.melanistic.cybertron.common.CybertronCommonProxy;
import com.melanistic.cybertron.lib.CybertronKeyBindings;

public class CybertronClientProxy extends CybertronCommonProxy {
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

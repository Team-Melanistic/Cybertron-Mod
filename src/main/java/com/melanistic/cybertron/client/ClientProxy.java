package com.melanistic.cybertron.client;

import com.melanistic.cybertron.common.CommonProxy;
import com.melanistic.cybertron.lib.Keys;
import com.melanistic.cybertron.lib.RenderIds;

public class ClientProxy extends CommonProxy
{
	
	public void preInit()
	{
		super.preInit();
		RenderIds.setupIds();
	}
	
	public void init()
	{
		super.init();
		Keys.registerKeys();
	}

}

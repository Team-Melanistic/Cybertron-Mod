package com.melanistic.cybertron.lib;

import java.util.ArrayList;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

public class CyberKeyBindings {
	
	private static KeyBinding[] keyList = new KeyBinding[5];;
	private static byte number = 0;
	
	public static void registerKeys()
	{
		
	}
	
	static void addKey(String name, int value)
	{
		addKeyBinding(name, value);
		number++;
	}
	
    static void addKeyBinding(String name, int value)
    {
    	keyList[number] = new KeyBinding(name, value, "key.categories.terratempus");
		ClientRegistry.registerKeyBinding(keyList[number]);
    }

    public static KeyBinding[] gatherKeyBindings() 
    {
        return keyList;
    }

}

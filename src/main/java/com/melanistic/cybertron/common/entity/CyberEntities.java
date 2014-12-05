package com.melanistic.cybertron.common.entity;

import net.minecraft.entity.Entity;
import cpw.mods.fml.common.registry.EntityRegistry;

public class CyberEntities
{

	public static int HUMANBORG_ID;
	public static int SKELETRON_ID;
	public static int CARRIER_ID;
	
	private static void initEntity(Class<? extends Entity> entityClass, int id)
	{
		EntityRegistry.registerGlobalEntityID(entityClass, entityClass.getName(), id);
	}

	private static void initEntity(Class<? extends Entity> entityClass, int id, int colorA, int colorB)
	{
		EntityRegistry.registerGlobalEntityID(entityClass, entityClass.getName(), id, colorA, colorB);
	}

	public static void registerEntities()
	{
		HUMANBORG_ID = EntityRegistry.findGlobalUniqueEntityId(); //return the same ID until the ID is used
		initEntity(EntityHumanborg.class, HUMANBORG_ID, 0x00A0A0, 0x698C55);
		SKELETRON_ID = EntityRegistry.findGlobalUniqueEntityId();
		initEntity(EntitySkeletron.class, SKELETRON_ID, 0xacacac, 0x0b0b0b);
		CARRIER_ID = EntityRegistry.findGlobalUniqueEntityId();
		initEntity(EntityCarrier.class, CARRIER_ID, 0x547895, 0xdd4422);
	}

}

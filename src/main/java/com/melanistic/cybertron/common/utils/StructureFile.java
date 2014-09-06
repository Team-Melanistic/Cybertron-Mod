package com.melanistic.cybertron.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class StructureFile
{

	private byte xLength;
	private byte yLength;
	private byte zLength;

	private List<Coord> blockCoords = new ArrayList<Coord>();
	private List<EntityCoord> entityCoords = new ArrayList<EntityCoord>();

	private Block foundationBlock;

	private StructureFile() {}

	public void generateStructure(Random random, int x, int y, int z, World world)
	{
		for(int i = x; i < x + xLength; i++)
		{
			for(int j = y; j < y + yLength; j++)
			{
				for(int k = z; k < z + zLength; k++)
				{
					world.setBlockToAir(i, j, k);
				}
			}
		}
		for(int i = x; i < x + xLength; i++)
		{
			for(int k = z; k < z + zLength; z++)
			{
				int height = world.getHeightValue(i, k);
				if(height < y)
				{
					for(int j = height; j < y; j++)
					{
						world.setBlock(i, j, k, foundationBlock);
					}
				}
			}
		}
		for(int listIterator = 0; listIterator < blockCoords.size(); listIterator++)
		{
			Coord coord = blockCoords.get(listIterator);
			world.setBlock(x + coord.xOffset, y + coord.yOffset, z + coord.zOffset, coord.block, coord.metadata, 2);
		}
	}

	public void generateStructureSmoothly(Random random, int x, int y, int z, World world)
	{

	}

	private void addBlockToStructure(byte offsetX, byte offsetY, byte offsetZ, Block block, byte metadata)
	{
		blockCoords.add(new Coord(offsetX, offsetY, offsetZ, block, metadata));
	}

	private void addPossibleEntitySpawn(byte offsetX, byte offsetY, byte offsetZ, EnumEntity entity)
	{
		entityCoords.add(new EntityCoord(offsetX, offsetY, offsetZ, entity));
	}

	public static StructureFile loadStructure(ResourceLocation location)
	{
		BufferedReader reader;
		StructureFile structure = new StructureFile();
		try 
		{
			reader = new BufferedReader(new FileReader(new File(location.getResourcePath())));
			String line = null;
			Block currentBlock = null;
			String[] coords;
			while((line = reader.readLine()) != null)
			{
				if(line.startsWith("//"))
					continue;
				if(line.startsWith("SIZE"))
				{
					coords = line.split(" ");
					byte xSize = Byte.parseByte(coords[1]);
					byte ySize = Byte.parseByte(coords[2]);
					byte zSize = Byte.parseByte(coords[3]);
					structure.xLength = xSize;
					structure.yLength = ySize;
					structure.zLength = zSize;
					continue;
				}
				if(line.startsWith("FOUNDATION"))
				{
					structure.foundationBlock = Block.getBlockFromName(line.split(" ")[1]);
				}
				if(line.startsWith("BLOCK"))
				{
					currentBlock = Block.getBlockFromName(line.split(" ")[1]);
					continue;
				}
				if(line.startsWith("ENTITY"))
				{
					continue;
				}
				if(currentBlock != null)
				{
					coords = line.split(" ");
					byte x = Byte.parseByte(coords[0]);
					byte y = Byte.parseByte(coords[1]);
					byte z = Byte.parseByte(coords[2]);
					byte metadata = 0;
					if(coords.length == 4)
					{
						metadata = Byte.parseByte(coords[3]);
					}
					structure.addBlockToStructure(x, y, z, currentBlock, metadata);
				}
			}
			reader.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return structure;
	}

	private class Coord
	{
		byte xOffset;
		byte yOffset;
		byte zOffset;
		Block block;
		byte metadata;

		public Coord(byte x, byte y, byte z, Block currentBlock, byte meta)
		{
			xOffset = x;
			yOffset = y;
			zOffset = z;
			block = currentBlock;
			metadata = meta;
		}
	}

	private class EntityCoord
	{
		byte xOffset;
		byte yOffset;
		byte zOffset;
		EnumEntity entity;

		public EntityCoord(byte x, byte y, byte z, EnumEntity e)
		{
			xOffset = x;
			yOffset = y;
			zOffset = z;
			entity = e;
		}
	}

	private enum EnumEntity
	{
		SKELETON;

		public static EnumEntity getEntityByName(String name)
		{
			return null;
		}
	}

}

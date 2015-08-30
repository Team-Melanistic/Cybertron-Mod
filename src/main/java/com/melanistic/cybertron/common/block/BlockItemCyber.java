package com.melanistic.cybertron.common.block;

import net.minecraft.block.material.Material;

/**
 * Class used for item blocks (metadata blocks). All blocks with metadata
 * specific blocks should extend this, cannot be initiated as an object
 * 
 * @author Syllabus
 */
public class BlockItemCyber extends BlockCyber
{

	protected BlockItemCyber(Material material)
	{
		super(material);
	}

}

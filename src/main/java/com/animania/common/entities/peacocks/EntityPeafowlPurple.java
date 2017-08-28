package com.animania.common.entities.peacocks;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeafowlPurple extends EntityPeafowlBase
{

	public EntityPeafowlPurple(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.PURPLE;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peafowl_purple.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peafowl_purple_blink.png");
	}
}
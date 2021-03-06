package com.animania.addons.farm.client.render.goats;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.goats.ModelKidAngora;
import com.animania.addons.farm.common.entity.goats.EntityAnimaniaGoat;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityKidAngora;
import com.animania.client.render.layer.LayerBlinking;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKidAngora<T extends EntityKidAngora> extends RenderLiving<T>
{
	public static final Factory           FACTORY          = new Factory();
	private static final ResourceLocation goatTextures      = new ResourceLocation("animania:textures/entity/goats/kid_angora.png");
	private static final ResourceLocation goatTexturesBlink = new ResourceLocation("animania:textures/entity/goats/angora_blink.png");

	public RenderKidAngora(RenderManager rm) {
		super(rm, new ModelKidAngora(), 0.2F);
        this.addLayer(new LayerBlinking(this, goatTexturesBlink, 0xCAC4B7));
	}

	protected ResourceLocation getGoatTextures(T par1Entity) {
		return RenderKidAngora.goatTextures;
	}

	protected ResourceLocation getGoatTexturesBlink(T par1Entity) {
		return RenderKidAngora.goatTexturesBlink;
	}

	protected void preRenderScale(EntityKidAngora entity, float f) {
		float age = entity.getEntityAge();
		GL11.glScalef(0.30F + (age / entity.getSizeDividend()), 0.30F + (age / entity.getSizeDividend()), 0.30F + (age / entity.getSizeDividend())); 
		GL11.glTranslatef(0f, 0f, -0.5f);
		EntityAnimaniaGoat entityGoat = (EntityAnimaniaGoat) entity;
		if (entityGoat.getSleeping()) {
			this.shadowSize = 0;
			float sleepTimer = entityGoat.getSleepTimer();
			if (sleepTimer > - 0.55F) {
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - .5F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		} else {
			this.shadowSize = 0.2F;
			entityGoat.setSleeping(false);
			entityGoat.setSleepTimer(0F);
		}
	}

		@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return this.getGoatTextures(entity);
	}

	@Override
	protected void preRenderCallback(T entityliving, float f) {
		this.preRenderScale(entityliving, f);
	}

	static class Factory<T extends EntityKidAngora> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderKidAngora(manager);
		}

	}
}

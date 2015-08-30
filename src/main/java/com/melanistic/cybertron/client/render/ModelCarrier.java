package com.melanistic.cybertron.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelCarrier extends ModelBase
{
	//fields
	ModelRenderer body;
    ModelRenderer leg_l_m;
    ModelRenderer leg_l_b;
    ModelRenderer leg_l_f;
    ModelRenderer leg_r_m;
    ModelRenderer leg_r_b;
    ModelRenderer leg_r_f;
    ModelRenderer part_l_m;
    ModelRenderer part_l_b;
    ModelRenderer part_l_f;
    ModelRenderer part_r_m;
    ModelRenderer part_r_b;
    ModelRenderer part_r_f;
  
    public ModelCarrier()
    {
    	textureWidth = 64;
    	textureHeight = 32;
    
      body = new ModelRenderer(this, 0, 14);
      body.addBox(-6F, -2F, -7F, 12, 4, 14);
      body.setRotationPoint(0F, 0F, 0F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      leg_l_m = new ModelRenderer(this, 0, 0);
      leg_l_m.addBox(-2F, -1F, -1F, 8, 2, 2);
      leg_l_m.setRotationPoint(8F, 0F, 0F);
      leg_l_m.setTextureSize(64, 32);
      leg_l_m.mirror = true;
      setRotation(leg_l_m, 0F, 0F, 1.570796F);
      leg_l_b = new ModelRenderer(this, 0, 0);
      leg_l_b.addBox(-2F, -1F, -1F, 8, 2, 2);
      leg_l_b.setRotationPoint(8F, 0F, 5F);
      leg_l_b.setTextureSize(64, 32);
      leg_l_b.mirror = true;
      setRotation(leg_l_b, 0F, 0F, 1.570796F);
      leg_l_f = new ModelRenderer(this, 0, 0);
      leg_l_f.addBox(-2F, -1F, -1F, 8, 2, 2);
      leg_l_f.setRotationPoint(8F, 0F, -5F);
      leg_l_f.setTextureSize(64, 32);
      leg_l_f.mirror = true;
      setRotation(leg_l_f, 0F, 0F, 1.570796F);
      leg_r_m = new ModelRenderer(this, 0, 0);
      leg_r_m.addBox(-2F, -1F, -1F, 8, 2, 2);
      leg_r_m.setRotationPoint(-8F, 0F, 0F);
      leg_r_m.setTextureSize(64, 32);
      leg_r_m.mirror = true;
      setRotation(leg_r_m, 0F, 0F, 1.570796F);
      leg_r_b = new ModelRenderer(this, 0, 0);
      leg_r_b.addBox(-2F, -1F, -1F, 8, 2, 2);
      leg_r_b.setRotationPoint(-8F, 0F, 5F);
      leg_r_b.setTextureSize(64, 32);
      leg_r_b.mirror = true;
      setRotation(leg_r_b, 0F, 0F, 1.570796F);
      leg_r_f = new ModelRenderer(this, 0, 0);
      leg_r_f.addBox(-2F, -1F, -1F, 8, 2, 2);
      leg_r_f.setRotationPoint(-8F, 0F, -5F);
      leg_r_f.setTextureSize(64, 32);
      leg_r_f.mirror = true;
      setRotation(leg_r_f, 0F, 0, 1.570796F);
      part_l_m = new ModelRenderer(this, 0, 0);
      part_l_m.addBox(0F, -1F, -1F, 1, 2, 2);
      part_l_m.setRotationPoint(6F, 0F, 0F);
      part_l_m.setTextureSize(64, 32);
      part_l_m.mirror = true;
      setRotation(part_l_m, 0F, 0F, 0F);
      part_l_b = new ModelRenderer(this, 0, 0);
      part_l_b.addBox(0F, -1F, -1F, 1, 2, 2);
      part_l_b.setRotationPoint(6F, 0F, 5F);
      part_l_b.setTextureSize(64, 32);
      part_l_b.mirror = true;
      setRotation(part_l_b, 0F, 0F, 0F);
      part_l_f = new ModelRenderer(this, 0, 0);
      part_l_f.addBox(0F, -1F, -1F, 1, 2, 2);
      part_l_f.setRotationPoint(6F, 0F, -5F);
      part_l_f.setTextureSize(64, 32);
      part_l_f.mirror = true;
      setRotation(part_l_f, 0F, 0F, 0F);
      part_r_m = new ModelRenderer(this, 0, 0);
      part_r_m.addBox(-1F, -1F, -1F, 1, 2, 2);
      part_r_m.setRotationPoint(-6F, 0F, 0F);
      part_r_m.setTextureSize(64, 32);
      part_r_m.mirror = true;
      setRotation(part_r_m, 0F, 0F, 0F);
      part_r_b = new ModelRenderer(this, 0, 0);
      part_r_b.addBox(-1F, -1F, -1F, 1, 2, 2);
      part_r_b.setRotationPoint(-6F, 0F, 5F);
      part_r_b.setTextureSize(64, 32);
      part_r_b.mirror = true;
      setRotation(part_r_b, 0F, 0F, 0F);
      part_r_f = new ModelRenderer(this, 0, 0);
      part_r_f.addBox(-1F, -1F, -1F, 1, 2, 2);
      part_r_f.setRotationPoint(-6F, 0F, -5F);
      part_r_f.setTextureSize(64, 32);
      part_r_f.mirror = true;
      setRotation(part_r_f, 0F, 0F, 0F);
  }
  
    public void render(float f5)
    {
    	body.render(f5);
    	leg_l_m.render(f5);
    	leg_l_b.render(f5);
    	leg_l_f.render(f5);
    	leg_r_m.render(f5);
    	leg_r_b.render(f5);
    	leg_r_f.render(f5);
    	part_l_m.render(f5);
    	part_l_b.render(f5);
    	part_l_f.render(f5);
    	part_r_m.render(f5);
    	part_r_b.render(f5);
    	part_r_f.render(f5);
    }

    @Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
    	render(p_78088_7_);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
    	model.rotateAngleX = x;
    	model.rotateAngleY = y;
    	model.rotateAngleZ = z;
    }

    public void setSwingProgress(float swingProgress)
    {
    	double rot = Math.sin(2D * Math.PI * swingProgress);
	  
    	leg_l_b.rotateAngleX = leg_l_f.rotateAngleX = leg_r_m.rotateAngleX = (float) rot;
    	leg_r_b.rotateAngleX = leg_r_f.rotateAngleX = leg_l_m.rotateAngleX = (float) rot;
    }

    @Override
    public void setLivingAnimations(EntityLivingBase e, float f1, float f2, float f3) 
    {
    	setSwingProgress(e.getSwingProgress(f3));
    }
}

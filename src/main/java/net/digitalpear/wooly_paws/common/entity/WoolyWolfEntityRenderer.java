package net.digitalpear.wooly_paws.common.entity;


import net.digitalpear.wooly_paws.WoolyPaws;
import net.digitalpear.wooly_paws.common.entity.features.*;
import net.digitalpear.wooly_paws.common.entity.models.WoolyWolfEntityModel;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class WoolyWolfEntityRenderer extends AgeableMobEntityRenderer<WoolyWolfEntity, WoolyWolfEntityRenderState, WoolyWolfEntityModel> {
    public static final Identifier TEXTURE = WoolyPaws.id("textures/entity/wolf/wooly_wolf.png");

    public WoolyWolfEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new WoolyWolfEntityModel(context.getPart(EntityModelLayers.WOLF)), new WoolyWolfEntityModel(context.getPart(EntityModelLayers.WOLF_BABY)), 0.5F);
        this.addFeature(new WoolyWolfWoolFeature(this, context.getEntityModels()));
        this.addFeature(new WoolyWolfEntityShearedLayerFeature(this, context.getEntityModels()));
        this.addFeature(new WoolyWolfUndercoatFeature(this, context.getEntityModels()));
        this.addFeature(new WoolyWolfCollarFeature(this));
        this.addFeature(new WoolyWolfArmorFeature(this, context.getEntityModels(), context.getEquipmentRenderer()));
        this.addFeature(new WoolyWolfWoolCollarFeature(this,context.getEntityModels()));
    }

    @Override
    public Identifier getTexture(WoolyWolfEntityRenderState state) {
        return state.texture;
    }

    @Override
    public WoolyWolfEntityRenderState createRenderState() {
        return new WoolyWolfEntityRenderState();
    }

    @Override
    public void updateRenderState(WoolyWolfEntity wolfEntity, WoolyWolfEntityRenderState wolfEntityRenderState, float f) {
        super.updateRenderState(wolfEntity, wolfEntityRenderState, f);
        wolfEntityRenderState.angerTime = wolfEntity.hasAngerTime();
        wolfEntityRenderState.inSittingPose = wolfEntity.isInSittingPose();
        wolfEntityRenderState.tailAngle = wolfEntity.getTailAngle();
        wolfEntityRenderState.begAnimationProgress = wolfEntity.getBegAnimationProgress(f);
        wolfEntityRenderState.shakeProgress = wolfEntity.getShakeProgress(f);
        wolfEntityRenderState.texture = wolfEntity.getTextureId();
        wolfEntityRenderState.furWetBrightnessMultiplier = wolfEntity.getFurWetBrightnessMultiplier(f);
        wolfEntityRenderState.collarColor = wolfEntity.isTamed() ? wolfEntity.getCollarColor() : null;
        wolfEntityRenderState.bodyArmor = wolfEntity.getBodyArmor().copy();
        wolfEntityRenderState.color = wolfEntity.getColor();
        wolfEntityRenderState.id = wolfEntity.getId();
        wolfEntityRenderState.sheared = wolfEntity.isSheared();
    }
}

package net.digitalpear.wooly_paws.common.entity.features;

import net.digitalpear.wooly_paws.WoolyPaws;
import net.digitalpear.wooly_paws.common.entity.WoolyWolfEntityRenderState;
import net.digitalpear.wooly_paws.common.entity.models.WoolyWolfEntityModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class WoolyWolfEntityShearedLayerFeature extends FeatureRenderer<WoolyWolfEntityRenderState, WoolyWolfEntityModel> {

    private static final Identifier SKIN = WoolyPaws.id("textures/entity/wolf/wolf_shave.png");
    private final WolfEntityModel model;
    private final WolfEntityModel babyModel;

    public WoolyWolfEntityShearedLayerFeature(FeatureRendererContext<WoolyWolfEntityRenderState, WoolyWolfEntityModel> context, LoadedEntityModels loader) {
        super(context);
        this.model = new WolfEntityModel(loader.getModelPart(EntityModelLayers.WOLF));
        this.babyModel = new WolfEntityModel(loader.getModelPart(EntityModelLayers.WOLF_BABY));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, WoolyWolfEntityRenderState state, float limbAngle, float limbDistance) {
        if (!state.sheared){
            return;
        }
        WolfEntityModel entityModel = state.baby ? this.babyModel : this.model;
        render(entityModel, SKIN, matrices, vertexConsumers, light, state, state.getRgbColor());
    }

}
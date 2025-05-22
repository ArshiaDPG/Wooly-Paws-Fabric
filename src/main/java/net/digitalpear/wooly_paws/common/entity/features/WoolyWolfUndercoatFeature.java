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
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class WoolyWolfUndercoatFeature extends FeatureRenderer<WoolyWolfEntityRenderState, WoolyWolfEntityModel> {
    private static final Identifier SKIN = WoolyPaws.id("textures/entity/wolf/wolf_undercoat.png");
    private final WolfEntityModel model;
    private final WolfEntityModel babyModel;

    public WoolyWolfUndercoatFeature(FeatureRendererContext<WoolyWolfEntityRenderState, WoolyWolfEntityModel> context, LoadedEntityModels loader) {
        super(context);
        this.model = new WolfEntityModel(loader.getModelPart(EntityModelLayers.WOLF));
        this.babyModel = new WolfEntityModel(loader.getModelPart(EntityModelLayers.WOLF_BABY));
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, WoolyWolfEntityRenderState woolyWolfEntityRenderState, float f, float g) {
        if (!woolyWolfEntityRenderState.invisible && (woolyWolfEntityRenderState.isJeb() || woolyWolfEntityRenderState.color != DyeColor.WHITE)) {
            WolfEntityModel entityModel = woolyWolfEntityRenderState.baby ? this.babyModel : this.model;
            render(entityModel, SKIN, matrixStack, vertexConsumerProvider, i, woolyWolfEntityRenderState, woolyWolfEntityRenderState.getRgbColor());
        }
    }
}
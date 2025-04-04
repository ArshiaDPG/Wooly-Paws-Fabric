package net.digitalpear.wooly_paws.common.entity.features;

import net.digitalpear.wooly_paws.WoolyPaws;
import net.digitalpear.wooly_paws.client.WoolyPawsClient;
import net.digitalpear.wooly_paws.common.entity.WoolyWolfEntityRenderState;
import net.digitalpear.wooly_paws.common.entity.models.WoolyWolfEntityModel;
import net.digitalpear.wooly_paws.common.entity.models.WoolyWolfWoolEntityModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class WoolyWolfWoolCollarFeature extends FeatureRenderer<WoolyWolfEntityRenderState, WoolyWolfEntityModel> {
    private static final Identifier SKIN = WoolyPaws.id("textures/entity/wolf/wooly_wolf_collar.png");
    private final WoolyWolfWoolEntityModel model;
    private final WoolyWolfWoolEntityModel babyModel;
    public WoolyWolfWoolCollarFeature(FeatureRendererContext<WoolyWolfEntityRenderState, WoolyWolfEntityModel> context, LoadedEntityModels loader) {
        super(context);
        this.model = new WoolyWolfWoolEntityModel(loader.getModelPart(WoolyPawsClient.WOOLY_WOLF_WOOL));
        this.babyModel = new WoolyWolfWoolEntityModel(loader.getModelPart(WoolyPawsClient.WOOLY_WOLF_BABY_WOOL));
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, WoolyWolfEntityRenderState wolfEntityRenderState, float limbAngle, float limbDistance) {
        DyeColor dyeColor = wolfEntityRenderState.collarColor;

        if (dyeColor != null && !wolfEntityRenderState.invisible && !wolfEntityRenderState.sheared) {
            WoolyWolfWoolEntityModel entityModel = wolfEntityRenderState.baby ? this.babyModel : this.model;
            int color = dyeColor.getEntityColor();
            render(entityModel, SKIN, matrixStack, vertexConsumerProvider, light, wolfEntityRenderState, color);
        }
    }
}

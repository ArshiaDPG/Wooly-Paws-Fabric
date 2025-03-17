package net.digitalpear.wooly_paws.common.entity.features;

import net.digitalpear.wooly_paws.common.entity.WoolyWolfEntityRenderState;
import net.digitalpear.wooly_paws.common.entity.models.WoolyWolfEntityModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class WoolyWolfCollarFeature extends FeatureRenderer<WoolyWolfEntityRenderState, WoolyWolfEntityModel> {
    private static final Identifier SKIN = Identifier.ofVanilla("textures/entity/wolf/wolf_collar.png");

    public WoolyWolfCollarFeature(FeatureRendererContext<WoolyWolfEntityRenderState, WoolyWolfEntityModel> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, WoolyWolfEntityRenderState wolfEntityRenderState, float f, float g) {
        if (!wolfEntityRenderState.sheared){
            return;
        }
        DyeColor dyeColor = wolfEntityRenderState.collarColor;
        if (dyeColor != null && !wolfEntityRenderState.invisible) {
            int j = dyeColor.getEntityColor();
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(SKIN));
            this.getContextModel().render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, j);
        }
    }
}
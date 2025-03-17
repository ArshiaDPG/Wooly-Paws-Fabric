package net.digitalpear.wooly_paws.common.entity.features;



import net.digitalpear.wooly_paws.client.WoolyPawsClient;
import net.digitalpear.wooly_paws.common.entity.WoolyWolfEntityRenderState;
import net.digitalpear.wooly_paws.common.entity.WoolyWolfEntityRenderer;
import net.digitalpear.wooly_paws.common.entity.models.WoolyWolfEntityModel;
import net.digitalpear.wooly_paws.common.entity.models.WoolyWolfWoolEntityModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WoolyWolfWoolFeature extends FeatureRenderer<WoolyWolfEntityRenderState, WoolyWolfEntityModel> {
    private final WoolyWolfWoolEntityModel woolModel;
    private final WoolyWolfWoolEntityModel babyWoolModel;
    private static final Identifier TEXTURE = WoolyWolfEntityRenderer.TEXTURE;
    public WoolyWolfWoolFeature(FeatureRendererContext<WoolyWolfEntityRenderState, WoolyWolfEntityModel> context, LoadedEntityModels loader) {
        super(context);
        this.woolModel = new WoolyWolfWoolEntityModel(loader.getModelPart(WoolyPawsClient.WOOLY_WOLF_WOOL));
        this.babyWoolModel = new WoolyWolfWoolEntityModel(loader.getModelPart(WoolyPawsClient.WOOLY_WOLF_BABY_WOOL));
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, WoolyWolfEntityRenderState sheepEntityRenderState, float f, float g) {
        if (!sheepEntityRenderState.sheared) {
            EntityModel<WoolyWolfEntityRenderState> entityModel = sheepEntityRenderState.baby ? this.babyWoolModel : this.woolModel;
            if (sheepEntityRenderState.invisible) {
                if (sheepEntityRenderState.hasOutline) {
                    entityModel.setAngles(sheepEntityRenderState);
                    VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getOutline(TEXTURE));
                    entityModel.render(matrixStack, vertexConsumer, i, LivingEntityRenderer.getOverlay(sheepEntityRenderState, 0.0F), -16777216);
                }

            } else {
                render(entityModel, TEXTURE, matrixStack, vertexConsumerProvider, i, sheepEntityRenderState, sheepEntityRenderState.getRgbColor());
            }
        }
    }
}

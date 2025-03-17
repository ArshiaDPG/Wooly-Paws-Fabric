package net.digitalpear.wooly_paws.client;

import net.digitalpear.wooly_paws.WoolyPaws;
import net.digitalpear.wooly_paws.common.entity.WoolyWolfEntityRenderer;
import net.digitalpear.wooly_paws.common.entity.models.WoolyWolfEntityModel;
import net.digitalpear.wooly_paws.common.entity.models.WoolyWolfWoolEntityModel;
import net.digitalpear.wooly_paws.init.WPEntityType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class WoolyPawsClient implements ClientModInitializer {
    public static final EntityModelLayer WOOLY_WOLF_WOOL = new EntityModelLayer(WoolyPaws.id("wooly_wolf"), "wool");
    public static final EntityModelLayer WOOLY_WOLF_BABY_WOOL = new EntityModelLayer(WoolyPaws.id("wooly_wolf_baby"), "wool");
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(WOOLY_WOLF_WOOL, WoolyWolfWoolEntityModel::getWoolModelData);
        EntityModelLayerRegistry.registerModelLayer(WOOLY_WOLF_BABY_WOOL, () -> WoolyWolfWoolEntityModel.getWoolModelData().transform(WoolyWolfEntityModel.BABY_TRANSFORMER));

        EntityRendererRegistry.register(WPEntityType.WOOLY_WOLF, WoolyWolfEntityRenderer::new);
    }
}

package net.digitalpear.wooly_paws.common.entity.features;


import net.digitalpear.wooly_paws.common.entity.WoolyWolfEntityRenderState;
import net.digitalpear.wooly_paws.common.entity.models.WoolyWolfEntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.passive.Cracks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Map;

public class WoolyWolfArmorFeature extends FeatureRenderer<WoolyWolfEntityRenderState, WoolyWolfEntityModel> {
    private final WoolyWolfEntityModel model;
    private final WoolyWolfEntityModel babyModel;
    private final EquipmentRenderer equipmentRenderer;
    private static final Map<Cracks.CrackLevel, Identifier> CRACK_TEXTURES;

    public WoolyWolfArmorFeature(FeatureRendererContext<WoolyWolfEntityRenderState, WoolyWolfEntityModel> context, LoadedEntityModels loader, EquipmentRenderer equipmentRenderer) {
        super(context);
        this.model = new WoolyWolfEntityModel(loader.getModelPart(EntityModelLayers.WOLF_ARMOR));
        this.babyModel = new WoolyWolfEntityModel(loader.getModelPart(EntityModelLayers.WOLF_BABY_ARMOR));
        this.equipmentRenderer = equipmentRenderer;
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, WoolyWolfEntityRenderState wolfEntityRenderState, float f, float g) {
        ItemStack itemStack = wolfEntityRenderState.bodyArmor;
        EquippableComponent equippableComponent = itemStack.get(DataComponentTypes.EQUIPPABLE);
        if (equippableComponent != null && !equippableComponent.assetId().isEmpty()) {
            WoolyWolfEntityModel wolfEntityModel = wolfEntityRenderState.baby ? this.babyModel : this.model;
            wolfEntityModel.setAngles(wolfEntityRenderState);
            this.equipmentRenderer.render(EquipmentModel.LayerType.WOLF_BODY, equippableComponent.assetId().get(), wolfEntityModel, itemStack, matrixStack, vertexConsumerProvider, i);
            this.renderCracks(matrixStack, vertexConsumerProvider, i, itemStack, wolfEntityModel);
        }
    }

    private void renderCracks(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ItemStack stack, Model model) {
        Cracks.CrackLevel crackLevel = Cracks.WOLF_ARMOR.getCrackLevel(stack);
        if (crackLevel != Cracks.CrackLevel.NONE) {
            Identifier identifier = CRACK_TEXTURES.get(crackLevel);
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.createArmorTranslucent(identifier));
            model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        }
    }

    static {
        CRACK_TEXTURES = Map.of(Cracks.CrackLevel.LOW, Identifier.ofVanilla("textures/entity/wolf/wolf_armor_crackiness_low.png"), Cracks.CrackLevel.MEDIUM, Identifier.ofVanilla("textures/entity/wolf/wolf_armor_crackiness_medium.png"), Cracks.CrackLevel.HIGH, Identifier.ofVanilla("textures/entity/wolf/wolf_armor_crackiness_high.png"));
    }
}

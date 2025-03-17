package net.digitalpear.wooly_paws.common.entity.models;


import net.digitalpear.wooly_paws.common.entity.WoolyWolfEntityRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BabyModelTransformer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelTransformer;
import net.minecraft.util.math.MathHelper;

import java.util.Set;

public class WoolyWolfWoolEntityModel extends EntityModel<WoolyWolfEntityRenderState> {
    public static final ModelTransformer BABY_TRANSFORMER = new BabyModelTransformer(Set.of("head"));
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    public WoolyWolfWoolEntityModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head_wool");
        this.body = root.getChild("body_wool");
        this.rightHindLeg = root.getChild("leg0_wool");
        this.leftHindLeg = root.getChild("leg1_wool");
        this.rightFrontLeg = root.getChild("leg2_wool");
        this.leftFrontLeg = root.getChild("leg3_wool");
    }
    public static TexturedModelData getWoolModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData head = modelPartData.addChild("head_wool", ModelPartBuilder.create().uv(0, 30).cuboid(-3F, -4.0F, -1.0F, 8F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(-1.0F, 13.5F, -7.0F));

        ModelPartData body = modelPartData.addChild("body_wool", ModelPartBuilder.create().uv(26, 25).cuboid(-6.0F, -7.25F, -4.0F, 10.0F, 15.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(1, 14.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData leg0 = modelPartData.addChild("leg0_wool", ModelPartBuilder.create().uv(0, 44).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(-1.5F, 16.0F, 7.0F));

        ModelPartData leg1 = modelPartData.addChild("leg1_wool", ModelPartBuilder.create().uv(0, 44).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(1.5F, 16.0F, 7.0F));

        ModelPartData leg2 = modelPartData.addChild("leg2_wool", ModelPartBuilder.create().uv(0, 44).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(-1.5F, 16.0F, -4.0F));

        ModelPartData leg3 = modelPartData.addChild("leg3_wool", ModelPartBuilder.create().uv(0, 44).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(1.5F, 16.0F, -4.0F));


        return TexturedModelData.of(modelData, 64, 64);
    }



    @Override
    public void setAngles(WoolyWolfEntityRenderState livingEntityRenderState) {
        super.setAngles(livingEntityRenderState);
        this.head.pitch = livingEntityRenderState.pitch * 0.017453292F;
        this.head.yaw = livingEntityRenderState.relativeHeadYaw * 0.017453292F;
        float f = livingEntityRenderState.limbSwingAnimationProgress;
        float g = livingEntityRenderState.limbSwingAmplitude;
        this.rightHindLeg.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.leftHindLeg.pitch = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
        this.rightFrontLeg.pitch = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
        this.leftFrontLeg.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.head.roll = livingEntityRenderState.begAnimationProgress + livingEntityRenderState.getRoll(0.0F);
        this.body.roll = livingEntityRenderState.getRoll(-0.16F);
        this.head.pitch = livingEntityRenderState.pitch * 0.017453292F;
        this.head.yaw = livingEntityRenderState.relativeHeadYaw * 0.017453292F;

        if (livingEntityRenderState.inSittingPose) {
            float h = livingEntityRenderState.ageScale;
            ModelPart var10000  = this.body;
            var10000.originY += 4.0F * h;
            var10000.originZ -= 2.0F * h;
            this.body.pitch = 0.7853982F;
            var10000 = this.rightHindLeg;
            var10000.originZ += 6.7F * h;
            var10000.originZ -= 5.0F * h;
            this.rightHindLeg.pitch = 4.712389F;
            var10000 = this.leftHindLeg;
            var10000.originY += 6.7F * h;
            var10000.originZ -= 5.0F * h;
            this.leftHindLeg.pitch = 4.712389F;
            this.rightFrontLeg.pitch = 5.811947F;
            var10000 = this.rightFrontLeg;
            var10000.originX += 0.01F * h;
            var10000.originY += h;
            this.leftFrontLeg.pitch = 5.811947F;
            var10000 = this.leftFrontLeg;
            var10000.originX -= 0.01F * h;
            var10000.originY += h;
        } else {
            this.rightHindLeg.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g;
            this.leftHindLeg.pitch = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
            this.rightFrontLeg.pitch = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
            this.leftFrontLeg.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        }
    }
}

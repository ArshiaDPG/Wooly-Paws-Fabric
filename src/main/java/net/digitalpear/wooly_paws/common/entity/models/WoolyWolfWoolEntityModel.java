package net.digitalpear.wooly_paws.common.entity.models;


import net.digitalpear.wooly_paws.common.entity.WoolyWolfEntityRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class WoolyWolfWoolEntityModel extends EntityModel<WoolyWolfEntityRenderState> {
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
        float limbSwingAnimationProgress = livingEntityRenderState.limbSwingAnimationProgress;
        float limbSwingAmplitude = livingEntityRenderState.limbSwingAmplitude;
        this.rightHindLeg.pitch = MathHelper.cos(limbSwingAnimationProgress * 0.6662F) * 1.4F * limbSwingAmplitude;
        this.leftHindLeg.pitch = MathHelper.cos(limbSwingAnimationProgress * 0.6662F + ((float) Math.PI)) * 1.4F * limbSwingAmplitude;
        this.rightFrontLeg.pitch = MathHelper.cos(limbSwingAnimationProgress * 0.6662F + ((float) Math.PI)) * 1.4F * limbSwingAmplitude;
        this.leftFrontLeg.pitch = MathHelper.cos(limbSwingAnimationProgress * 0.6662F) * 1.4F * limbSwingAmplitude;
        this.head.roll = livingEntityRenderState.begAnimationProgress + livingEntityRenderState.getRoll(0.0F);
        this.body.roll = livingEntityRenderState.getRoll(-0.16F);
        this.head.pitch = livingEntityRenderState.pitch * 0.017453292F;
        this.head.yaw = livingEntityRenderState.relativeHeadYaw * 0.017453292F;


        if (livingEntityRenderState.inSittingPose) {
            float ageScale = livingEntityRenderState.ageScale;

            ModelPart currentPart  = this.body;
            currentPart.originY += 4.0F * ageScale;
            currentPart.originZ -= 2.0F * ageScale;
            this.body.pitch = 0.7853982F;

            currentPart = this.rightHindLeg;
            currentPart.originY += 6.7F * ageScale;
            currentPart.originZ -= 5.0F * ageScale;
            this.rightHindLeg.pitch = 4.712389F;

            currentPart = this.leftHindLeg;
            currentPart.originY += 6.7F * ageScale;
            currentPart.originZ -= 5.0F * ageScale;
            this.leftHindLeg.pitch = 4.712389F;

            currentPart = this.rightFrontLeg;
            currentPart.originX += 0.01F * ageScale;
            currentPart.originY += ageScale;
            this.rightFrontLeg.pitch = 5.811947F;

            currentPart = this.leftFrontLeg;
            currentPart.originX -= 0.01F * ageScale;
            currentPart.originY += ageScale;
            this.leftFrontLeg.pitch = 5.811947F;
        } else {
            this.rightHindLeg.pitch = MathHelper.cos(limbSwingAnimationProgress * 0.6662F) * 1.4F * limbSwingAmplitude;
            this.leftHindLeg.pitch = MathHelper.cos(limbSwingAnimationProgress * 0.6662F + ((float) Math.PI)) * 1.4F * limbSwingAmplitude;
            this.rightFrontLeg.pitch = MathHelper.cos(limbSwingAnimationProgress * 0.6662F + ((float) Math.PI)) * 1.4F * limbSwingAmplitude;
            this.leftFrontLeg.pitch = MathHelper.cos(limbSwingAnimationProgress * 0.6662F) * 1.4F * limbSwingAmplitude;
        }
    }
}

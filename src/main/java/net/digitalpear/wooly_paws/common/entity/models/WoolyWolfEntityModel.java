package net.digitalpear.wooly_paws.common.entity.models;

import net.digitalpear.wooly_paws.common.entity.WoolyWolfEntityRenderState;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BabyModelTransformer;
import net.minecraft.client.render.entity.model.ModelTransformer;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.client.render.entity.state.WolfEntityRenderState;

import java.util.Set;

@SuppressWarnings("unused")
public class WoolyWolfEntityModel extends WolfEntityModel {
    public static final ModelTransformer BABY_TRANSFORMER = new BabyModelTransformer(Set.of("head", "head_wool"));


    public WoolyWolfEntityModel(ModelPart modelPart) {
        super(modelPart);
    }

    @Override
    public void setAngles(WolfEntityRenderState wolfEntityRenderState) {
        super.setAngles(wolfEntityRenderState);
        if (wolfEntityRenderState instanceof WoolyWolfEntityRenderState woolyWolfEntityRenderState){
            ModelPart neck = this.root.getChild("upper_body");
            if (!((WoolyWolfEntityRenderState) wolfEntityRenderState).sheared){
                neck.xScale = 0;
                neck.yScale = 0;
                neck.zScale = 0;
            }
            else{
                neck.xScale = 1;
                neck.yScale = 1;
                neck.zScale = 1;
            }
        }
    }
}

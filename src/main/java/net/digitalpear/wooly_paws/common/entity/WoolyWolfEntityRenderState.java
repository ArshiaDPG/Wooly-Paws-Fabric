package net.digitalpear.wooly_paws.common.entity;

import net.minecraft.client.render.entity.state.WolfEntityRenderState;
import net.minecraft.client.util.ColorLerper;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

public class WoolyWolfEntityRenderState extends WolfEntityRenderState {
    public boolean sheared;
    public DyeColor color;
    public int id;

    public int getRgbColor() {
        return this.isJeb() ? ColorLerper.lerpColor(ColorLerper.Type.SHEEP, this.age) : ColorLerper.Type.SHEEP.getArgb(this.color);
    }
    public WoolyWolfEntityRenderState(){
        this.color = DyeColor.WHITE;
    }

    public boolean isJeb() {
        return this.customName != null && "jeb_".equals(this.customName.getString());
    }
}

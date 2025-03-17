package net.digitalpear.wooly_paws.common.entity;

import net.minecraft.client.render.entity.state.WolfEntityRenderState;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

public class WoolyWolfEntityRenderState extends WolfEntityRenderState {
    public boolean sheared;
    public DyeColor color;
    public int id;

    public int getRgbColor() {
        if (this.isJeb()) {
            int j = MathHelper.floor(this.age);
            int k = j / 25 + this.id;
            int l = DyeColor.values().length;
            int m = k % l;
            int n = (k + 1) % l;
            float f = ((float)(j % 25) + MathHelper.fractionalPart(this.age)) / 25.0F;
            int o = SheepEntity.getRgbColor(DyeColor.byIndex(m));
            int p = SheepEntity.getRgbColor(DyeColor.byIndex(n));
            return ColorHelper.lerp(f, o, p);
        } else {
            return SheepEntity.getRgbColor(this.color);
        }
    }
    public WoolyWolfEntityRenderState(){
        this.color = DyeColor.WHITE;
    }

    public boolean isJeb() {
        return this.customName != null && "jeb_".equals(this.customName.getString());
    }
}

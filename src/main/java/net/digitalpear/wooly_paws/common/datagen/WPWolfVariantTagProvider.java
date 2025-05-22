package net.digitalpear.wooly_paws.common.datagen;

import net.digitalpear.wooly_paws.init.WPTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class WPWolfVariantTagProvider extends FabricTagProvider<WolfVariant> {
    public WPWolfVariantTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.WOLF_VARIANT, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getTagBuilder(WPTags.WolfVariants.WOOLY_WOLF_VARIANT_BLACKLIST);
    }
}

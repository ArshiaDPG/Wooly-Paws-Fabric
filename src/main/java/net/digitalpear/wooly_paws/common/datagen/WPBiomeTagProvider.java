package net.digitalpear.wooly_paws.common.datagen;

import net.digitalpear.wooly_paws.init.WPTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.concurrent.CompletableFuture;

public class WPBiomeTagProvider extends FabricTagProvider<Biome> {
    public WPBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(WPTags.Biomes.SPAWNS_WOOLY_WOLF)
                .add(BiomeKeys.FOREST)
                .add(BiomeKeys.BIRCH_FOREST)
                .add(BiomeKeys.TAIGA)
                .add(BiomeKeys.SAVANNA);
    }
}

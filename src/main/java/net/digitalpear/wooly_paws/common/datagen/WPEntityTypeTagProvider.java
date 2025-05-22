package net.digitalpear.wooly_paws.common.datagen;

import net.digitalpear.wooly_paws.init.WPTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class WPEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public WPEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(WPTags.EntityTypes.WOOLY_WOLF_CAN_STEAL_WOOL).add(EntityType.SHEEP);
    }
}

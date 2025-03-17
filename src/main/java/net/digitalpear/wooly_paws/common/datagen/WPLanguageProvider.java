package net.digitalpear.wooly_paws.common.datagen;

import net.digitalpear.wooly_paws.init.WPEntityType;
import net.digitalpear.wooly_paws.init.WPItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class WPLanguageProvider extends FabricLanguageProvider {
    public WPLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(WPEntityType.WOOLY_WOLF, "Wooly Wolf");
        translationBuilder.add(WPItems.WOOLY_WOLF_SPAWN_EGG, "Wooly Wolf Spawn Egg");
    }
}

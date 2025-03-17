package net.digitalpear.wooly_paws;

import net.digitalpear.wooly_paws.common.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class WoolyPawsDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(WPEntityLootTableProvider::new);
        pack.addProvider(WPShearingLootTableProvider::new);

        pack.addProvider(WPLanguageProvider::new);
        pack.addProvider(WPModelProvider::new);
        pack.addProvider(WPBiomeTagProvider::new);
    }
}

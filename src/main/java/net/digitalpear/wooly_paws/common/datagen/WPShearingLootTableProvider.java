package net.digitalpear.wooly_paws.common.datagen;

import net.digitalpear.wooly_paws.init.WPLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.data.loottable.EntityLootTableGenerator;
import net.minecraft.data.loottable.LootTableData;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.context.ContextType;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class WPShearingLootTableProvider extends SimpleFabricLootTableProvider {
    public WPShearingLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.SHEARING);
    }


    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
        lootTableBiConsumer.accept(WPLootTables.WOOLY_WOLF_SHEARING, LootTable.builder().pool(WPEntityLootTableProvider.createForWoolyWolves(WPLootTables.WOLF_SHEARING_FROM_DYE_COLOR)));
        LootTableData.WOOL_FROM_DYE_COLOR.forEach((color, wool) -> {
            lootTableBiConsumer.accept(WPLootTables.WOLF_SHEARING_FROM_DYE_COLOR.get(color), LootTable.builder().pool(LootPool.builder().rolls(UniformLootNumberProvider.create(1.0F, 3.0F)).with(ItemEntry.builder(wool))));
        });
    }

}

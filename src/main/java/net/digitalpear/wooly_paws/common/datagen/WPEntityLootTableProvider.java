package net.digitalpear.wooly_paws.common.datagen;

import net.digitalpear.wooly_paws.common.data.WoolyWolfPredicate;
import net.digitalpear.wooly_paws.init.WPEntityType;
import net.digitalpear.wooly_paws.init.WPLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.data.loottable.EntityLootTableGenerator;
import net.minecraft.data.loottable.LootTableData;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.predicate.component.ComponentMapPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.SheepPredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.DyeColor;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class WPEntityLootTableProvider extends SimpleFabricLootTableProvider {
    public WPEntityLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.ENTITY);
    }


    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
        lootTableBiConsumer.accept(WPEntityType.WOOLY_WOLF.getLootTableKey().get(),
                LootTable.builder()
                        .pool(LootPool.builder().with(LootTableEntry.builder(EntityType.WOLF.getLootTableKey().get())).build())
                        .pool(createForWoolyWolves(WPLootTables.WOLF_DROPS_FROM_DYE_COLOR))
        );
        LootTableData.WOOL_FROM_DYE_COLOR.forEach((color, wool) -> {
            lootTableBiConsumer.accept(WPLootTables.WOLF_DROPS_FROM_DYE_COLOR.get(color), LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(wool))));
        });
    }
    public static LootPool.Builder createForWoolyWolves(Map<DyeColor, RegistryKey<LootTable>> colorLootTables) {
        AlternativeEntry.Builder builder = AlternativeEntry.builder();

        Map.Entry entry;
        for(Iterator<Map.Entry<DyeColor, RegistryKey<LootTable>>> var2 = colorLootTables.entrySet().iterator(); var2.hasNext(); builder = builder.alternatively(LootTableEntry.builder((RegistryKey)entry.getValue()).conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().components(net.minecraft.predicate.component.ComponentsPredicate.Builder.create().exact(ComponentMapPredicate.of(DataComponentTypes.SHEEP_COLOR, (DyeColor)entry.getKey())).build()).typeSpecific(WoolyWolfPredicate.unsheared()))))) {
            entry = var2.next();
        }

        return LootPool.builder().with(builder);
    }
}

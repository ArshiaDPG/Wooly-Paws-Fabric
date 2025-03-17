package net.digitalpear.wooly_paws.init;

import net.digitalpear.wooly_paws.WoolyPaws;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

public class WPItems {
    private static RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, WoolyPaws.id(id));
    }
    private static Item createItem(String id, Function<Item.Settings, Item> factory, Item.Settings settings){
        return Items.register(keyOf(id), factory, settings);
    }

    public static final Item WOOLY_WOLF_SPAWN_EGG = createItem("wooly_wolf_spawn_egg", settings -> new SpawnEggItem(WPEntityType.WOOLY_WOLF, settings), new Item.Settings());

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.addAfter(Items.WOLF_SPAWN_EGG, WOOLY_WOLF_SPAWN_EGG);
        });

    }
}

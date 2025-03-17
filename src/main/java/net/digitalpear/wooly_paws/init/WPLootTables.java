package net.digitalpear.wooly_paws.init;

import net.digitalpear.wooly_paws.WoolyPaws;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Util;

import java.util.Map;

public class WPLootTables {

    public static final RegistryKey<LootTable> WOOLY_WOLF_SHEARING = register("shearing/wooly_wolf");
    public static final Map<DyeColor, RegistryKey<LootTable>> WOLF_SHEARING_FROM_DYE_COLOR = registerAllDyeColors("shearing/wooly_wolf");
    public static final Map<DyeColor, RegistryKey<LootTable>> WOLF_DROPS_FROM_DYE_COLOR = registerAllDyeColors("entities/wooly_wolf");

    private static Map<DyeColor, RegistryKey<LootTable>> registerAllDyeColors(String prefix) {
        return Util.mapEnum(DyeColor.class, (color) -> register(prefix + "/" + color.getId()));
    }

    private static RegistryKey<LootTable> register(String name){
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, WoolyPaws.id(name));
    }
}

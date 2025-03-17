package net.digitalpear.wooly_paws.init;

import net.digitalpear.wooly_paws.WoolyPaws;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class WPTags {

    private static <T> TagKey<T> of(String id, RegistryKey<? extends Registry<T>> registryRef){
        return TagKey.of(registryRef, WoolyPaws.id(id));
    }

    public static class Biomes{
        private static TagKey<Biome> of(String id){
            return WPTags.of(id, RegistryKeys.BIOME);
        }

        public static TagKey<Biome> SPAWNS_WOOLY_WOLF = of("spawns_wooly_wolf");
    }
}

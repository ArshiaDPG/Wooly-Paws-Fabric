package net.digitalpear.wooly_paws.init;

import net.digitalpear.wooly_paws.WoolyPaws;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WolfVariant;
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

        public static final TagKey<Biome> SPAWNS_WOOLY_WOLF = of("spawns_wooly_wolf");
    }
    public static class WolfVariants {
        private static TagKey<WolfVariant> of(String id){
            return WPTags.of(id, RegistryKeys.WOLF_VARIANT);
        }

        public static final TagKey<WolfVariant> WOOLY_WOLF_VARIANT_BLACKLIST = of("wooly_wolf_variant_blacklist");
    }

    public static class EntityTypes{
        private static TagKey<EntityType<?>> of(String id){
            return WPTags.of(id, RegistryKeys.ENTITY_TYPE);
        }
        public static final TagKey<EntityType<?>> WOOLY_WOLF_CAN_STEAL_WOOL = of("wooly_wolf_can_steal_wool");

    }
}

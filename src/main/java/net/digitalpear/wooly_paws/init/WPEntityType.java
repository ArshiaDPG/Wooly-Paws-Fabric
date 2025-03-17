package net.digitalpear.wooly_paws.init;

import net.digitalpear.wooly_paws.WoolyPaws;
import net.digitalpear.wooly_paws.common.entity.WoolyWolfEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.Vec3d;

public class WPEntityType {

    public static final EntityType<WoolyWolfEntity> WOOLY_WOLF = register("wooly_wolf", EntityType.Builder.create(WoolyWolfEntity::new, SpawnGroup.CREATURE).dimensions(0.6F, 0.85F).eyeHeight(0.68F).passengerAttachments(new Vec3d(0.0, 0.81875, -0.0625)).maxTrackingRange(10));

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> type) {
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, WoolyPaws.id(name));
        return Registry.register(Registries.ENTITY_TYPE, key, type.build(key));
    }
    public static void init() {
        FabricDefaultAttributeRegistry.register(WOOLY_WOLF, WoolyWolfEntity.createWolfAttributes());
        BiomeModifications.addSpawn(BiomeSelectors.tag(WPTags.Biomes.SPAWNS_WOOLY_WOLF), SpawnGroup.CREATURE, WOOLY_WOLF, 2, 1, 4);
    }
}

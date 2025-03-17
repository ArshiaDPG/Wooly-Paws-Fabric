package net.digitalpear.wooly_paws.init;

import com.mojang.serialization.MapCodec;
import net.digitalpear.wooly_paws.WoolyPaws;
import net.digitalpear.wooly_paws.common.data.WoolyWolfPredicate;
import net.minecraft.predicate.entity.EntitySubPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class WPEntitySubPredicateTypes {
    public static final MapCodec<WoolyWolfPredicate> WOOLY_WOLF = register("wooly_wolf", WoolyWolfPredicate.CODEC);

    private static <T extends EntitySubPredicate> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(Registries.ENTITY_SUB_PREDICATE_TYPE, WoolyPaws.id(id), codec);
    }

    public static void init(){}
}

package net.digitalpear.wooly_paws.common.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.digitalpear.wooly_paws.common.entity.WoolyWolfEntity;
import net.digitalpear.wooly_paws.init.WPEntitySubPredicateTypes;
import net.minecraft.entity.Entity;
import net.minecraft.predicate.entity.EntitySubPredicate;
import net.minecraft.predicate.entity.EntitySubPredicateTypes;
import net.minecraft.predicate.entity.SheepPredicate;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record WoolyWolfPredicate(Optional<Boolean> sheared) implements EntitySubPredicate {
    public static final MapCodec<WoolyWolfPredicate> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Codec.BOOL.optionalFieldOf("sheared").forGetter(WoolyWolfPredicate::sheared)).apply(instance, WoolyWolfPredicate::new));

    public WoolyWolfPredicate(Optional<Boolean> sheared) {
        this.sheared = sheared;
    }

    public MapCodec<WoolyWolfPredicate> getCodec() {
        return WPEntitySubPredicateTypes.WOOLY_WOLF;
    }

    public boolean test(Entity entity, ServerWorld world, @Nullable Vec3d pos) {
        if (entity instanceof WoolyWolfEntity woolyWolfEntity) {
            return !this.sheared.isPresent() || woolyWolfEntity.isSheared() == this.sheared.get();
        } else {
            return false;
        }
    }

    public static WoolyWolfPredicate unsheared() {
        return new WoolyWolfPredicate(Optional.of(false));
    }

    public Optional<Boolean> sheared() {
        return this.sheared;
    }
}

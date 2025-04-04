package net.digitalpear.wooly_paws.init;

import net.digitalpear.wooly_paws.WoolyPaws;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.dynamic.Codecs;

import java.util.function.UnaryOperator;

public class WPDataComponentTypes {
    public static final ComponentType<Integer> DIGESTION = register("digestion", (builder) ->
            builder.codec(Codecs.POSITIVE_INT).packetCodec(PacketCodecs.VAR_INT));
    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, WoolyPaws.id(id), (builderOperator.apply(ComponentType.builder())).build());
    }

    public static void init() {

    }
}

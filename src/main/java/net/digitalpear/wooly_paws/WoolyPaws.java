package net.digitalpear.wooly_paws;

import net.digitalpear.wooly_paws.init.WPDataComponentTypes;
import net.digitalpear.wooly_paws.init.WPEntitySubPredicateTypes;
import net.digitalpear.wooly_paws.init.WPEntityType;
import net.digitalpear.wooly_paws.init.WPItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WoolyPaws implements ModInitializer {

    public static final String MOD_ID = "wooly_paws";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id(String name){
        return Identifier.of(MOD_ID, name);
    }

    @Override
    public void onInitialize() {
        WPEntityType.init();
        WPItems.init();
        WPDataComponentTypes.init();
        WPEntitySubPredicateTypes.init();
    }
}

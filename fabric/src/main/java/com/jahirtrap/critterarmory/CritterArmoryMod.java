package com.jahirtrap.critterarmory;

import com.jahirtrap.configlib.TXFConfig;
import com.jahirtrap.critterarmory.init.ModConfig;
import com.jahirtrap.critterarmory.init.ModContent;
import net.fabricmc.api.ModInitializer;

public class CritterArmoryMod implements ModInitializer {

    public static final String MODID = "critterarmory";

    @Override
    public void onInitialize() {
        TXFConfig.init(MODID, ModConfig.class);
        ModContent.init();
    }
}

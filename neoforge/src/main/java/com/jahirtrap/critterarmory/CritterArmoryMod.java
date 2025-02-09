package com.jahirtrap.critterarmory;

import com.jahirtrap.configlib.TXFConfig;
import com.jahirtrap.critterarmory.init.ModConfig;
import com.jahirtrap.critterarmory.init.ModContent;
import com.jahirtrap.critterarmory.init.ModTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(CritterArmoryMod.MODID)
public class CritterArmoryMod {

    public static final String MODID = "critterarmory";

    public CritterArmoryMod(IEventBus bus) {
        TXFConfig.init(MODID, ModConfig.class);
        ModContent.init(bus);
        ModTab.init(bus);
    }
}

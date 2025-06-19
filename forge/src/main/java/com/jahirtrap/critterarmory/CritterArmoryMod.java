package com.jahirtrap.critterarmory;

import com.jahirtrap.configlib.TXFConfig;
import com.jahirtrap.critterarmory.init.ModConfig;
import com.jahirtrap.critterarmory.init.ModContent;
import com.jahirtrap.critterarmory.init.ModTab;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CritterArmoryMod.MODID)
public class CritterArmoryMod {

    public static final String MODID = "critterarmory";

    public CritterArmoryMod(FMLJavaModLoadingContext context) {
        BusGroup bus = context.getModBusGroup();

        TXFConfig.init(MODID, ModConfig.class);
        ModContent.init(bus);
        ModTab.init(bus);
    }
}

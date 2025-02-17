package com.jahirtrap.critterarmory.init;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public class ModTab {
    public static final CreativeModeTab TAB_CRITTER_ARMORY = FabricItemGroupBuilder.build(
            new ResourceLocation(MODID, "tab_critterarmory"), () -> new ItemStack(ModContent.NETHERITE_MOB_ARMORS.get(4)));
}

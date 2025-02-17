package com.jahirtrap.critterarmory.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTab {
    public static final CreativeModeTab TAB_CRITTER_ARMORY = new CreativeModeTab("critterarmory.tab_critterarmory") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModContent.NETHERITE_MOB_ARMORS.get(4).get());
        }
    };
}

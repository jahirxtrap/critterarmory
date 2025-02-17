package com.jahirtrap.critterarmory.item;

import net.minecraft.world.item.Item;

import static com.jahirtrap.critterarmory.init.ModTab.TAB_CRITTER_ARMORY;

public class BaseItem extends Item {
    public BaseItem(Properties properties) {
        super(properties.tab(TAB_CRITTER_ARMORY));
    }
}

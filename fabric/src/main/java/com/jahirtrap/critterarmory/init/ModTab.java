package com.jahirtrap.critterarmory.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public class ModTab {
    public static void init() {
        CreativeModeTab TAB_CRITTER_ARMORY = FabricItemGroup.builder(new ResourceLocation(MODID, "tab_critterarmory"))
                .icon(() -> new ItemStack(ModContent.NETHERITE_MOB_ARMORS.get(4)))
                .displayItems((features, event) -> {
                    for (Item item : ModContent.ITEMS)
                        event.accept(item);
                })
                .title(Component.translatable("itemGroup.critterarmory.tab_critterarmory"))
                .build();
    }
}

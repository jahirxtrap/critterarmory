package com.jahirtrap.critterarmory.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public class ModTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final Supplier<CreativeModeTab> TAB_CRITTER_ARMORY = TABS.register("tab_critterarmory", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModContent.NETHERITE_MOB_ARMORS.get(4).get()))
            .displayItems((features, event) -> {
                for (DeferredHolder<Item, ? extends Item> item : ModContent.ITEMS.getEntries())
                    event.accept(item.get());
            })
            .title(Component.translatable("itemGroup.critterarmory.tab_critterarmory"))
            .build());

    public static void init(IEventBus bus) {
        TABS.register(bus);
    }
}

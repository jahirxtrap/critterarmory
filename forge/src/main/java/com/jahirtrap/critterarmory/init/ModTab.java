package com.jahirtrap.critterarmory.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public class ModTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final Supplier<CreativeModeTab> TAB_CRITTER_ARMORY = TABS.register("tab_critterarmory", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModContent.NETHERITE_MOB_ARMORS.get(3).get()))
            .displayItems((features, event) -> {
                for (RegistryObject<Item> item : ModContent.ITEMS.getEntries())
                    event.accept(item.get());
            })
            .title(Component.translatable("itemGroup.critterarmory.tab_critterarmory"))
            .build());

    public static void init(IEventBus bus) {
        TABS.register(bus);
    }
}

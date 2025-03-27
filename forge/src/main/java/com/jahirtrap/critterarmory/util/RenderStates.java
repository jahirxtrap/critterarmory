package com.jahirtrap.critterarmory.util;

import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderStates {
    public static class Cat extends CatRenderState {
        public ItemStack bodyArmorItem;

        public Cat() {
            this.bodyArmorItem = ItemStack.EMPTY;
        }
    }

    public static class Chicken extends ChickenRenderState {
        public ItemStack bodyArmorItem;

        public Chicken() {
            this.bodyArmorItem = ItemStack.EMPTY;
        }
    }

    public static class Cow extends CowRenderState {
        public ItemStack bodyArmorItem;

        public Cow() {
            this.bodyArmorItem = ItemStack.EMPTY;
        }
    }

    public static class MushroomCow extends MushroomCowRenderState {
        public ItemStack bodyArmorItem;

        public MushroomCow() {
            this.bodyArmorItem = ItemStack.EMPTY;
        }
    }

    public static class Pig extends PigRenderState {
        public ItemStack bodyArmorItem;

        public Pig() {
            this.bodyArmorItem = ItemStack.EMPTY;
        }
    }

    public static class Sheep extends SheepRenderState {
        public ItemStack bodyArmorItem;

        public Sheep() {
            this.bodyArmorItem = ItemStack.EMPTY;
        }
    }
}

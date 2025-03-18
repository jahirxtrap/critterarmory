package com.jahirtrap.critterarmory.item;

import com.jahirtrap.critterarmory.util.AnimalMaterial;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BaseAnimalArmorItem {
    public static class Vanilla extends HorseArmorItem {
        private final AnimalMaterial material;
        private final ResourceLocation texture;

        public Vanilla(AnimalMaterial material, Properties properties) {
            super(material.getDefense(), material.getName(), properties.stacksTo(1));
            this.material = material;
            this.texture = material.getLocation().withPath(path -> "textures/entity/equipment/horse_body/" + path + ".png");
        }

        public AnimalMaterial getMaterial() {
            return this.material;
        }

        @Override
        public ResourceLocation getTexture() {
            return this.texture;
        }
    }

    public static class Modded extends Item {
        private final AnimalMaterial material;
        private final BodyType bodyType;

        public Modded(AnimalMaterial material, BodyType type, Properties properties) {
            super(properties.stacksTo(1));
            this.material = material;
            this.bodyType = type;
        }

        public AnimalMaterial getMaterial() {
            return this.material;
        }

        public HolderSet<EntityType<?>> getAllowedEntities() {
            return this.bodyType.allowedEntities;
        }

        @Override
        public boolean isEnchantable(ItemStack stack) {
            return false;
        }
    }

    public enum BodyType {
        CANINE(EntityType.WOLF),
        CAT(EntityType.CAT),
        CHICKEN(EntityType.CHICKEN),
        COW(EntityType.COW, EntityType.MOOSHROOM),
        PIG(EntityType.PIG),
        SHEEP(EntityType.SHEEP);

        public final HolderSet<EntityType<?>> allowedEntities;

        BodyType(final EntityType<?>... entityTypes) {
            this.allowedEntities = HolderSet.direct(EntityType::builtInRegistryHolder, entityTypes);
        }
    }
}

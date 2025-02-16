package com.jahirtrap.critterarmory.util;

import com.jahirtrap.critterarmory.init.ModConfig;
import com.jahirtrap.critterarmory.init.ModTags;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class CommonUtils {
    private static final Map<TagKey<Item>, String> entityArmorMap = new HashMap<>();

    static {
        addArmorType(ModTags.Items.CHICKEN_ARMOR, "chicken_body");
        addArmorType(ModTags.Items.COW_ARMOR, "cow_body");
        addArmorType(ModTags.Items.PIG_ARMOR, "pig_body");
        addArmorType(ModTags.Items.SHEEP_ARMOR, "sheep_body");
    }

    public static void addArmorType(TagKey<Item> key, String value) {
        entityArmorMap.put(key, value);
    }

    public static String getArmorType(ItemStack stack) {
        return entityArmorMap.entrySet().stream().filter(entry -> stack.is(entry.getKey())).map(Map.Entry::getValue).findFirst().orElse("");
    }

    public static void renderArmor(ResourceLocation location, Model model, ItemStack stack, PoseStack poseStack, MultiBufferSource bufferSource, int i) {
        if (!ModConfig.renderArmors || (!ModConfig.renderChickenArmors && stack.is(ModTags.Items.CHICKEN_ARMOR)) || (!ModConfig.renderCowArmors && stack.is(ModTags.Items.COW_ARMOR)) || (!ModConfig.renderPigArmors && stack.is(ModTags.Items.PIG_ARMOR)) || (!ModConfig.renderSheepArmors && stack.is(ModTags.Items.SHEEP_ARMOR)))
            return;
        String type = getArmorType(stack);
        if (!type.isBlank()) {
            VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(location.withPath(path -> "textures/entity/equipment/" + type + "/" + path + ".png")));
            model.renderToBuffer(poseStack, consumer, i, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        }
    }

    public static boolean canWearArmor(LivingEntity entity) {
        return (entity instanceof Chicken || entity instanceof Cow || entity instanceof Pig || entity instanceof Sheep);
    }
}

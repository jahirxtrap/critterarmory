package com.jahirtrap.critterarmory.util;

import com.jahirtrap.critterarmory.init.ModConfig;
import com.jahirtrap.critterarmory.init.ModContent;
import com.jahirtrap.critterarmory.init.ModTags;
import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {
    private static final Map<TagKey<Item>, String> entityArmorMap = new HashMap<>();

    static {
        addArmorType(ModTags.Items.CAT_ARMOR, "cat_body");
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

    public static void renderArmor(ResourceKey<EquipmentAsset> resourceKey, Model model, ItemStack stack, PoseStack poseStack, MultiBufferSource bufferSource, int i) {
        if (!ModConfig.renderArmors || (!ModConfig.renderCatArmors && stack.is(ModTags.Items.CAT_ARMOR)) || (!ModConfig.renderChickenArmors && stack.is(ModTags.Items.CHICKEN_ARMOR)) || (!ModConfig.renderCowArmors && stack.is(ModTags.Items.COW_ARMOR)) || (!ModConfig.renderPigArmors && stack.is(ModTags.Items.PIG_ARMOR)) || (!ModConfig.renderSheepArmors && stack.is(ModTags.Items.SHEEP_ARMOR)))
            return;
        String type = getArmorType(stack);
        if (!type.isBlank()) {
            VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(resourceKey.location().withPath(path -> "textures/entity/equipment/" + type + "/" + path + ".png")));
            model.renderToBuffer(poseStack, consumer, i, OverlayTexture.NO_OVERLAY);
        }
    }

    public static boolean feedEntity(Player player, InteractionHand hand, Animal entity) {
        if (!entity.level().isClientSide()) {
            var stack = player.getItemInHand(hand);
            if (stack.is(ModContent.BALANCED_FEED.get()) && entity.getHealth() < entity.getMaxHealth()) {
                entity.usePlayerItem(player, hand, stack);
                entity.heal(ModConfig.healAmount);
                return true;
            } else if (stack.is(ModContent.VITALITY_FEED.get())) {
                int healthLimit = ModConfig.healthIncreaseLimit;
                if (entity.getMaxHealth() < healthLimit || entity.getHealth() < entity.getMaxHealth()) {
                    entity.usePlayerItem(player, hand, stack);
                    if (entity.getMaxHealth() < healthLimit) {
                        entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(Math.min(entity.getMaxHealth() + ModConfig.healthIncreaseAmount, healthLimit));
                        entity.playSound(SoundEvents.PLAYER_LEVELUP);
                    }
                    entity.setHealth(entity.getMaxHealth());
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean canWearArmor(LivingEntity entity) {
        return Arrays.stream(BaseAnimalArmorItem.BodyType.values()).flatMap(bodyType -> bodyType.allowedEntities.stream()).anyMatch(holder -> holder.value() == entity.getType());
    }

    public static boolean canFeed(LivingEntity entity) {
        return canWearArmor(entity);
    }
}

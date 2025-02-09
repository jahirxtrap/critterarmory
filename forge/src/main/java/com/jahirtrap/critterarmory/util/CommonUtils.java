package com.jahirtrap.critterarmory.util;

import com.jahirtrap.critterarmory.init.ModConfig;
import com.jahirtrap.critterarmory.init.ModContent;
import com.jahirtrap.critterarmory.init.ModTags;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraftforge.common.ToolActions;

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

    public static void renderArmor(ResourceKey<EquipmentAsset> resourceKey, Model model, ItemStack stack, PoseStack poseStack, MultiBufferSource bufferSource, int i) {
        if (!ModConfig.renderArmors || (!ModConfig.renderChickenArmors && stack.is(ModTags.Items.CHICKEN_ARMOR)) || (!ModConfig.renderCowArmors && stack.is(ModTags.Items.COW_ARMOR)) || (!ModConfig.renderPigArmors && stack.is(ModTags.Items.PIG_ARMOR)) || (!ModConfig.renderSheepArmors && stack.is(ModTags.Items.SHEEP_ARMOR)))
            return;
        String type = getArmorType(stack);
        if (!type.isBlank()) {
            VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(resourceKey.location().withPath("textures/entity/equipment/" + type + "/" + resourceKey.location().getPath() + ".png")));
            model.renderToBuffer(poseStack, consumer, i, OverlayTexture.NO_OVERLAY);
        }
    }

    public static boolean extendedMobInteract(Animal entity, Player player, InteractionHand hand) {
        if (canWearArmor(entity)) {
            ItemStack stack = player.getItemInHand(hand);
            int healthLimit = ModConfig.healthIncreaseLimit;
            if (stack.is(ModContent.FEED.get()) && entity.getHealth() < entity.getMaxHealth()) {
                entity.usePlayerItem(player, hand, stack);
                entity.heal(ModConfig.healAmount);
                return true;
            } else if (stack.is(ModContent.PREMIUM_FEED.get()) && (entity.getMaxHealth() < healthLimit || entity.getHealth() < entity.getMaxHealth())) {
                entity.usePlayerItem(player, hand, stack);
                if (entity.getMaxHealth() < healthLimit) {
                    entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(Math.min(entity.getMaxHealth() + ModConfig.healthIncreaseAmount, healthLimit));
                    entity.playSound(SoundEvents.PLAYER_LEVELUP);
                }
                entity.setHealth(entity.getMaxHealth());
                return true;
            } else {
                if (entity.isEquippableInSlot(stack, EquipmentSlot.BODY) && !entity.isWearingBodyArmor() && !entity.isBaby()) {
                    entity.setBodyArmorItem(stack.copyWithCount(1));
                    stack.consume(1, player);
                    return true;
                } else if ((stack.getItem() instanceof ShearsItem || stack.canPerformAction(ToolActions.SHEARS_HARVEST)) && entity.isWearingBodyArmor() && !(EnchantmentHelper.has(entity.getBodyArmorItem(), EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE) && !player.isCreative())) {
                    stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                    entity.playSound(SoundEvents.ARMOR_UNEQUIP_WOLF);
                    ItemStack armor = entity.getBodyArmorItem();
                    entity.setBodyArmorItem(ItemStack.EMPTY);
                    if (entity.level() instanceof ServerLevel level) entity.spawnAtLocation(level, armor);
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean canWearArmor(LivingEntity entity) {
        return (entity instanceof Chicken || entity instanceof Cow || entity instanceof Pig || entity instanceof Sheep);
    }
}

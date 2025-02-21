package com.jahirtrap.critterarmory.init;

import com.jahirtrap.configlib.TXFConfig;

public class ModConfig extends TXFConfig {
    @Entry(name = "Heal Amount", min = 0, max = Integer.MAX_VALUE, itemDisplay = "critterarmory:balanced_feed")
    public static int healAmount = 6;
    @Entry(name = "Health Increase Limit", min = 0, max = Integer.MAX_VALUE, itemDisplay = "critterarmory:vitality_feed")
    public static int healthIncreaseLimit = 30;
    @Entry(name = "Health Increase Amount", min = 0, max = Integer.MAX_VALUE, itemDisplay = "critterarmory:vitality_feed")
    public static int healthIncreaseAmount = 6;
    @Comment(centered = true)
    public static Comment armors;
    @Entry(name = "Render Armors", itemDisplay = "critterarmory:netherite_pig_armor")
    public static boolean renderArmors = true;
    @Entry(name = "Render Horse Armors", itemDisplay = "critterarmory:netherite_horse_armor")
    public static boolean renderHorseArmors = true;
    @Entry(name = "Render Wolf Armors", itemDisplay = "critterarmory:netherite_wolf_armor")
    public static boolean renderWolfArmors = true;
    @Entry(name = "Render Chicken Armors", itemDisplay = "critterarmory:netherite_chicken_armor")
    public static boolean renderChickenArmors = true;
    @Entry(name = "Render Cow Armors", itemDisplay = "critterarmory:netherite_cow_armor")
    public static boolean renderCowArmors = true;
    @Entry(name = "Render Pig Armors", itemDisplay = "critterarmory:netherite_pig_armor")
    public static boolean renderPigArmors = true;
    //@Entry(name = "Render Sheep Armors", itemDisplay = "critterarmory:netherite_sheep_armor")
    public static boolean renderSheepArmors = false;
}

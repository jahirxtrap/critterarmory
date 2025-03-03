package com.jahirtrap.critterarmory.init;

import com.jahirtrap.configlib.TXFConfig;

public class ModConfig extends TXFConfig {
    public static final String GENERAL = "general", RENDERING = "rendering";

    @Entry(category = GENERAL, name = "Heal Amount", min = 0, max = Integer.MAX_VALUE, itemDisplay = "critterarmory:balanced_feed")
    public static int healAmount = 6;
    @Entry(category = GENERAL, name = "Health Increase Limit", min = 0, max = Integer.MAX_VALUE, itemDisplay = "critterarmory:vitality_feed")
    public static int healthIncreaseLimit = 30;
    @Entry(category = GENERAL, name = "Health Increase Amount", min = 0, max = Integer.MAX_VALUE, itemDisplay = "critterarmory:vitality_feed")
    public static int healthIncreaseAmount = 6;

    @Entry(category = RENDERING, name = "Render Armors", itemDisplay = "critterarmory:netherite_pig_armor")
    public static boolean renderArmors = true;
    @Entry(category = RENDERING, name = "Render Horse Armors", itemDisplay = "critterarmory:netherite_horse_armor")
    public static boolean renderHorseArmors = true;
    @Entry(category = RENDERING, name = "Render Wolf Armors", itemDisplay = "critterarmory:netherite_wolf_armor")
    public static boolean renderWolfArmors = true;
    @Entry(category = RENDERING, name = "Render Chicken Armors", itemDisplay = "critterarmory:netherite_chicken_armor")
    public static boolean renderChickenArmors = true;
    @Entry(category = RENDERING, name = "Render Cow Armors", itemDisplay = "critterarmory:netherite_cow_armor")
    public static boolean renderCowArmors = true;
    @Entry(category = RENDERING, name = "Render Pig Armors", itemDisplay = "critterarmory:netherite_pig_armor")
    public static boolean renderPigArmors = true;
    //@Entry(category = RENDERING, name = "Render Sheep Armors", itemDisplay = "critterarmory:netherite_sheep_armor")
    public static boolean renderSheepArmors = false;
    @Entry(category = RENDERING, name = "Modern Wolf Collar", itemDisplay = "minecraft:bone")
    public static boolean modernWolfCollar = true;
}

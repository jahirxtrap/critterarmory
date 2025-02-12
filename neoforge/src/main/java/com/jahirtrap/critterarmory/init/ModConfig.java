package com.jahirtrap.critterarmory.init;

import com.jahirtrap.configlib.TXFConfig;

public class ModConfig extends TXFConfig {
    public static final String GENERAL = "general", ARMORS = "armors";

    @Entry(category = GENERAL, name = "Heal Amount", min = 0, max = Integer.MAX_VALUE, itemDisplay = "critterarmory:balanced_feed")
    public static int healAmount = 6;
    @Entry(category = GENERAL, name = "Health Increase Limit", min = 0, max = Integer.MAX_VALUE, itemDisplay = "critterarmory:vitality_feed")
    public static int healthIncreaseLimit = 30;
    @Entry(category = GENERAL, name = "Health Increase Amount", min = 0, max = Integer.MAX_VALUE, itemDisplay = "critterarmory:vitality_feed")
    public static int healthIncreaseAmount = 6;

    @Entry(category = ARMORS, name = "Render Armors", itemDisplay = "critterarmory:netherite_pig_armor")
    public static boolean renderArmors = true;
    @Entry(category = ARMORS, name = "Render Horse Armors", itemDisplay = "critterarmory:netherite_horse_armor")
    public static boolean renderHorseArmors = true;
    //@Entry(category = ARMORS, name = "Render Wolf Armors", itemDisplay = "critterarmory:netherite_wolf_armor")
    public static boolean renderWolfArmors = false;
    //@Entry(category = ARMORS, name = "Render Chicken Armors", itemDisplay = "critterarmory:netherite_chicken_armor")
    public static boolean renderChickenArmors = false;
    @Entry(category = ARMORS, name = "Render Cow Armors", itemDisplay = "critterarmory:netherite_cow_armor")
    public static boolean renderCowArmors = true;
    @Entry(category = ARMORS, name = "Render Pig Armors", itemDisplay = "critterarmory:netherite_pig_armor")
    public static boolean renderPigArmors = true;
    //@Entry(category = ARMORS, name = "Render Sheep Armors", itemDisplay = "critterarmory:netherite_sheep_armor")
    public static boolean renderSheepArmors = false;
}

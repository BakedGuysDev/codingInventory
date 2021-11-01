package com.egirlsnation.codingInventory.utils;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public final class ItemUtil {
	
	private static List<Material> listOfFuels;
    private static List<Material> furnaceBurnables;
    private static List<Material> blastFurnaceBurnables;
    private static List<Material> smokerBurnables;

    public static void init() {
        loadDefaultList();
    }
    
    private static boolean isAxe(Material mat) {
        boolean isAxe = false;

        switch(mat) {
            case WOODEN_AXE:
            case STONE_AXE:
            case IRON_AXE:
            case GOLDEN_AXE:
            case DIAMOND_AXE:
            case NETHERITE_AXE:
                isAxe = true;
                break;
            default:
                break;
        }
        return isAxe;
    }

    private static boolean isHoe(Material mat) {
        boolean isHoe = false;

        switch(mat) {
            case WOODEN_HOE:
            case STONE_HOE:
            case IRON_HOE:
            case GOLDEN_HOE:
            case DIAMOND_HOE:
            case NETHERITE_HOE:
                isHoe = true;
                break;
            default:
                break;
        }
        return isHoe;
    }

    private static boolean isPickaxe(Material mat) {
        boolean isPickaxe = false;

        switch(mat) {
            case WOODEN_PICKAXE:
            case STONE_PICKAXE:
            case IRON_PICKAXE:
            case GOLDEN_PICKAXE:
            case DIAMOND_PICKAXE:
            case NETHERITE_PICKAXE:
                isPickaxe = true;
                break;
            default:
                break;
        }
        return isPickaxe;
    }

    private static boolean isShovel(Material mat) {
        boolean isShovel = false;

        switch(mat) {
            case WOODEN_SHOVEL:
            case STONE_SHOVEL:
            case IRON_SHOVEL:
            case GOLDEN_SHOVEL:
            case DIAMOND_SHOVEL:
            case NETHERITE_SHOVEL:
                isShovel = true;
                break;
            default:
                break;
        }
        return isShovel;
    }

    private static boolean isSword(Material mat) {
        boolean isSword = false;

        switch(mat) {
            case WOODEN_SWORD:
            case STONE_SWORD:
            case IRON_SWORD:
            case GOLDEN_SWORD:
            case DIAMOND_SWORD:
            case NETHERITE_SWORD:
                isSword = true;
                break;
            default:
                break;
        }
        return isSword;
    }

    public static boolean isBoots(Material mat) {
        boolean isBoots = false;

        switch(mat) {
            case CHAINMAIL_BOOTS:
            case LEATHER_BOOTS:
            case IRON_BOOTS:
            case GOLDEN_BOOTS:
            case DIAMOND_BOOTS:
            case NETHERITE_BOOTS:
                isBoots = true;
                break;
            default:
                break;
        }
        return isBoots;
    }

    public static boolean isChestplate(Material mat) {
        boolean isChestplate = false;

        switch(mat) {
            case CHAINMAIL_CHESTPLATE:
            case LEATHER_CHESTPLATE:
            case IRON_CHESTPLATE:
            case GOLDEN_CHESTPLATE:
            case DIAMOND_CHESTPLATE:
            case NETHERITE_CHESTPLATE:
            case ELYTRA:
                isChestplate = true;
                break;
            default:
                break;
        }
        return isChestplate;
    }

    public static boolean isChestplateEnchantable(Material mat) {
        boolean isChestplate = false;

        switch(mat) {
            case CHAINMAIL_CHESTPLATE:
            case LEATHER_CHESTPLATE:
            case IRON_CHESTPLATE:
            case GOLDEN_CHESTPLATE:
            case DIAMOND_CHESTPLATE:
            case NETHERITE_CHESTPLATE:
                isChestplate = true;
                break;
            default:
                break;
        }
        return isChestplate;
    }

    public static boolean isHelmet(Material mat) {
        boolean isHelmet = false;

        switch(mat) {
            case CHAINMAIL_HELMET:
            case LEATHER_HELMET:
            case IRON_HELMET:
            case GOLDEN_HELMET:
            case DIAMOND_HELMET:
            case NETHERITE_HELMET:
            case TURTLE_HELMET:
                isHelmet = true;
                break;
            default: break;
        }
        return isHelmet;
    }

    public static boolean isLeggings(Material mat) {
        boolean isLeggings = false;

        switch(mat) {
            case CHAINMAIL_LEGGINGS:
            case LEATHER_LEGGINGS:
            case IRON_LEGGINGS:
            case GOLDEN_LEGGINGS:
            case DIAMOND_LEGGINGS:
            case NETHERITE_LEGGINGS:
                isLeggings = true;
                break;
            default:
                break;
        }
        return isLeggings;
    }

    public static boolean isOffhand(Material mat) {
        return mat == Material.SHIELD;
    }

    public static boolean isTool(Material mat) {
        boolean isTool = false;

        if (isAxe(mat) || isHoe(mat) || isPickaxe(mat) || isShovel(mat)) {
            isTool = true;
        }

        return isTool;
    }

    public static boolean isWeapon(Material mat) {
        boolean isWeapon = false;

        if (isSword(mat) || mat == Material.BOW || mat == Material.CROSSBOW || mat == Material.TRIDENT) {
            isWeapon = true;
        }

        return isWeapon;
    }

    public static boolean isArmor(Material mat) {
        boolean isArmor = false;

        if (isBoots(mat) || isChestplate(mat) || isHelmet(mat) || isLeggings(mat) || isOffhand(mat)) {
            isArmor = true;
        }

        return isArmor;
    }

    public static boolean isArmorEnchantable(Material mat) {
        boolean isArmor = false;

        if (isBoots(mat) || isChestplateEnchantable(mat) || isHelmet(mat) || isLeggings(mat)) {
            isArmor = true;
        }

        return isArmor;
    }

    public static boolean isRepairable(Material mat) {
        boolean repairable = false;

        if (isTool(mat) || isWeapon(mat) || isArmor(mat)) {
            repairable = true;
        } else {
            switch(mat) {
                case FISHING_ROD:
                case FLINT_AND_STEEL:
                case SHEARS:
                case CARROT_ON_A_STICK:
                    repairable = true;
                    break;
                default:
                    break;
            }
        }

        return repairable;
    }

    public static boolean isEnchantable(Material mat) {
        boolean enchantable = false;
        if (isWeapon(mat) || isArmorEnchantable(mat) || isPickaxe(mat) || isShovel(mat) || isAxe(mat)) {
            enchantable = true;
        } else {
            switch(mat) {
                case BOOK:
                case FISHING_ROD:
                case SHEARS:
                case CARROT_ON_A_STICK:
                    enchantable = true;
                    break;
                default:
                    break;
            }
        }

        return enchantable;
    }


    public static boolean isBrewingIngredient(Material mat) {
        boolean brewingIngredient = false;

        switch(mat) {
            case BLAZE_POWDER:
            case FERMENTED_SPIDER_EYE:
            case GHAST_TEAR:
            case GLISTERING_MELON_SLICE:
            case GLOWSTONE_DUST:
            case GOLDEN_CARROT:
            case GUNPOWDER:
            case MAGMA_CREAM:
            case NETHER_WART:
            case PHANTOM_MEMBRANE:
            case PUFFERFISH:
            case RABBIT_FOOT:
            case REDSTONE:
            case SPIDER_EYE:
            case SUGAR:
            case TURTLE_HELMET:
                brewingIngredient = true;
                break;
            default:
                break;
        }

        return brewingIngredient;
    }

    public static boolean isBeaconFuel(Material mat) {
        boolean beaconFuel = false;

        switch(mat) {
            case DIAMOND:
            case EMERALD:
            case IRON_INGOT:
            case GOLD_INGOT:
                beaconFuel = true;
                break;
            default:
                break;
        }
        return beaconFuel;
    }

    public static boolean isShulkerBox(Material mat) {
        boolean isShulkerBox = false;

        switch(mat) {
            case SHULKER_BOX:
            case BLACK_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
            case BROWN_SHULKER_BOX:
            case CYAN_SHULKER_BOX:
            case GRAY_SHULKER_BOX:
            case GREEN_SHULKER_BOX:
            case LIGHT_BLUE_SHULKER_BOX:
            case LIGHT_GRAY_SHULKER_BOX:
            case LIME_SHULKER_BOX:
            case MAGENTA_SHULKER_BOX:
            case ORANGE_SHULKER_BOX:
            case PINK_SHULKER_BOX:
            case PURPLE_SHULKER_BOX:
            case RED_SHULKER_BOX:
            case WHITE_SHULKER_BOX:
            case YELLOW_SHULKER_BOX:
                isShulkerBox = true;
                break;
            default:
                break;
        }

        return isShulkerBox;
    }

    public static boolean isSameItem(ItemStack one, ItemStack two) {
        return isSameItem(one, two, false);
    }

    public static boolean isSameItem(ItemStack one, ItemStack two, boolean negativeDurAllowed) {
        boolean same = false;

        if (one != null && two != null) {
            boolean sameType = one.getType() == two.getType();
            @SuppressWarnings("deprecation")
			boolean sameDur = one.getDurability() == two.getDurability();
            @SuppressWarnings("deprecation")
			boolean negativeDur = (one.getDurability() == Short.MAX_VALUE) || (two.getDurability() == Short.MAX_VALUE);

            boolean sameEnchant = false;
            boolean noEnchant = one.getEnchantments().isEmpty() && two.getEnchantments().isEmpty();
            if (!noEnchant) {
                sameEnchant = one.getEnchantments().equals(two.getEnchantments());
            }

            boolean sameMeta = false;
            boolean noMeta = one.getItemMeta() == null && two.getItemMeta() == null;

            if (!noMeta) {
                // Handles an empty slot being compared
                if (one.getItemMeta() == null || two.getItemMeta() == null) {
                    sameMeta = false;
                } else {
                    sameMeta = one.getItemMeta().equals(two.getItemMeta());
                }
            }

            if (sameType && (sameDur || (negativeDurAllowed && negativeDur)) && (sameEnchant || noEnchant) && (sameMeta || noMeta)) {
                same = true;
            }
        }
        return same;
    }
    
    // Fuel Utils
    private static void loadDefaultList() {
        listOfFuels = new ArrayList<>();

        listOfFuels.add(Material.ACACIA_BOAT);
        listOfFuels.add(Material.ACACIA_BUTTON);
        listOfFuels.add(Material.ACACIA_DOOR);
        listOfFuels.add(Material.ACACIA_FENCE);
        listOfFuels.add(Material.ACACIA_FENCE_GATE);
        listOfFuels.add(Material.ACACIA_LOG);
        listOfFuels.add(Material.ACACIA_PLANKS);
        listOfFuels.add(Material.ACACIA_PRESSURE_PLATE);
        listOfFuels.add(Material.ACACIA_SAPLING);
        listOfFuels.add(Material.ACACIA_SIGN);
        listOfFuels.add(Material.ACACIA_SLAB);
        listOfFuels.add(Material.ACACIA_STAIRS);
        listOfFuels.add(Material.ACACIA_TRAPDOOR);
        listOfFuels.add(Material.ACACIA_WOOD);

        listOfFuels.add(Material.BARREL);
        listOfFuels.add(Material.BAMBOO);

        listOfFuels.add(Material.BIRCH_BOAT);
        listOfFuels.add(Material.BIRCH_BUTTON);
        listOfFuels.add(Material.BIRCH_DOOR);
        listOfFuels.add(Material.BIRCH_FENCE);
        listOfFuels.add(Material.BIRCH_FENCE_GATE);
        listOfFuels.add(Material.BIRCH_LOG);
        listOfFuels.add(Material.BIRCH_PLANKS);
        listOfFuels.add(Material.BIRCH_PRESSURE_PLATE);
        listOfFuels.add(Material.BIRCH_SAPLING);
        listOfFuels.add(Material.BIRCH_SIGN);
        listOfFuels.add(Material.BIRCH_SLAB);
        listOfFuels.add(Material.BIRCH_STAIRS);
        listOfFuels.add(Material.BIRCH_TRAPDOOR);
        listOfFuels.add(Material.BIRCH_WOOD);

        listOfFuels.add(Material.BLACK_BANNER);
        listOfFuels.add(Material.BLACK_CARPET);
        listOfFuels.add(Material.BLACK_WOOL);
        listOfFuels.add(Material.BLAZE_ROD);
        listOfFuels.add(Material.BLUE_BANNER);
        listOfFuels.add(Material.BLUE_CARPET);
        listOfFuels.add(Material.BLUE_WOOL);
        listOfFuels.add(Material.BROWN_BANNER);
        listOfFuels.add(Material.BROWN_CARPET);
        listOfFuels.add(Material.BROWN_WOOL);
        listOfFuels.add(Material.BOW);
        listOfFuels.add(Material.BOWL);
        listOfFuels.add(Material.BOOKSHELF);
        listOfFuels.add(Material.BROWN_MUSHROOM_BLOCK);
        listOfFuels.add(Material.CARTOGRAPHY_TABLE);
        listOfFuels.add(Material.CHARCOAL);
        listOfFuels.add(Material.CHEST);
        listOfFuels.add(Material.COAL);
        listOfFuels.add(Material.COAL_BLOCK);
        listOfFuels.add(Material.COMPOSTER);
        listOfFuels.add(Material.CRAFTING_TABLE);
        listOfFuels.add(Material.CYAN_BANNER);
        listOfFuels.add(Material.CYAN_CARPET);
        listOfFuels.add(Material.CYAN_WOOL);

        listOfFuels.add(Material.DARK_OAK_BOAT);
        listOfFuels.add(Material.DARK_OAK_BUTTON);
        listOfFuels.add(Material.DARK_OAK_DOOR);
        listOfFuels.add(Material.DARK_OAK_FENCE);
        listOfFuels.add(Material.DARK_OAK_FENCE_GATE);
        listOfFuels.add(Material.DARK_OAK_LOG);
        listOfFuels.add(Material.DARK_OAK_PLANKS);
        listOfFuels.add(Material.DARK_OAK_PRESSURE_PLATE);
        listOfFuels.add(Material.DARK_OAK_SAPLING);
        listOfFuels.add(Material.DARK_OAK_SIGN);
        listOfFuels.add(Material.DARK_OAK_SLAB);
        listOfFuels.add(Material.DARK_OAK_STAIRS);
        listOfFuels.add(Material.DARK_OAK_TRAPDOOR);
        listOfFuels.add(Material.DARK_OAK_WOOD);

        listOfFuels.add(Material.DAYLIGHT_DETECTOR);
        listOfFuels.add(Material.DEAD_BUSH);
        listOfFuels.add(Material.DRIED_KELP_BLOCK);
        listOfFuels.add(Material.FISHING_ROD);
        listOfFuels.add(Material.FLETCHING_TABLE);
        listOfFuels.add(Material.GRAY_BANNER);
        listOfFuels.add(Material.GRAY_CARPET);
        listOfFuels.add(Material.GRAY_WOOL);
        listOfFuels.add(Material.GREEN_BANNER);
        listOfFuels.add(Material.GREEN_CARPET);
        listOfFuels.add(Material.GREEN_WOOL);
        listOfFuels.add(Material.JUKEBOX);

        listOfFuels.add(Material.JUNGLE_BOAT);
        listOfFuels.add(Material.JUNGLE_BUTTON);
        listOfFuels.add(Material.JUNGLE_DOOR);
        listOfFuels.add(Material.JUNGLE_FENCE);
        listOfFuels.add(Material.JUNGLE_FENCE_GATE);
        listOfFuels.add(Material.JUNGLE_LOG);
        listOfFuels.add(Material.JUNGLE_PLANKS);
        listOfFuels.add(Material.JUNGLE_PRESSURE_PLATE);
        listOfFuels.add(Material.JUNGLE_SAPLING);
        listOfFuels.add(Material.JUNGLE_SIGN);
        listOfFuels.add(Material.JUNGLE_SLAB);
        listOfFuels.add(Material.JUNGLE_STAIRS);
        listOfFuels.add(Material.JUNGLE_TRAPDOOR);
        listOfFuels.add(Material.JUNGLE_WOOD);

        listOfFuels.add(Material.LADDER);
        listOfFuels.add(Material.LAVA_BUCKET);
        listOfFuels.add(Material.LIGHT_BLUE_BANNER);
        listOfFuels.add(Material.LECTERN);
        listOfFuels.add(Material.LIGHT_BLUE_CARPET);
        listOfFuels.add(Material.LIGHT_BLUE_WOOL);
        listOfFuels.add(Material.LIGHT_GRAY_BANNER);
        listOfFuels.add(Material.LIGHT_GRAY_CARPET);
        listOfFuels.add(Material.LIGHT_GRAY_WOOL);
        listOfFuels.add(Material.LIME_BANNER);
        listOfFuels.add(Material.LIME_CARPET);
        listOfFuels.add(Material.LIME_WOOL);
        listOfFuels.add(Material.LOOM);
        listOfFuels.add(Material.MAGENTA_BANNER);
        listOfFuels.add(Material.MAGENTA_CARPET);
        listOfFuels.add(Material.MAGENTA_WOOL);
        listOfFuels.add(Material.NOTE_BLOCK);

        listOfFuels.add(Material.OAK_BOAT);
        listOfFuels.add(Material.OAK_BUTTON);
        listOfFuels.add(Material.OAK_DOOR);
        listOfFuels.add(Material.OAK_FENCE);
        listOfFuels.add(Material.OAK_FENCE_GATE);
        listOfFuels.add(Material.OAK_LOG);
        listOfFuels.add(Material.OAK_PLANKS);
        listOfFuels.add(Material.OAK_PRESSURE_PLATE);
        listOfFuels.add(Material.OAK_SAPLING);
        listOfFuels.add(Material.OAK_SIGN);
        listOfFuels.add(Material.OAK_SLAB);
        listOfFuels.add(Material.OAK_STAIRS);
        listOfFuels.add(Material.OAK_TRAPDOOR);
        listOfFuels.add(Material.OAK_WOOD);

        listOfFuels.add(Material.ORANGE_BANNER);
        listOfFuels.add(Material.ORANGE_CARPET);
        listOfFuels.add(Material.ORANGE_WOOL);
        listOfFuels.add(Material.PINK_BANNER);
        listOfFuels.add(Material.PINK_CARPET);
        listOfFuels.add(Material.PINK_WOOL);
        listOfFuels.add(Material.PURPLE_BANNER);
        listOfFuels.add(Material.PURPLE_CARPET);
        listOfFuels.add(Material.PURPLE_WOOL);
        listOfFuels.add(Material.RED_BANNER);
        listOfFuels.add(Material.RED_CARPET);
        listOfFuels.add(Material.RED_MUSHROOM_BLOCK);
        listOfFuels.add(Material.RED_WOOL);
        listOfFuels.add(Material.SCAFFOLDING);
        listOfFuels.add(Material.SMITHING_TABLE);

        listOfFuels.add(Material.SPRUCE_BOAT);
        listOfFuels.add(Material.SPRUCE_BUTTON);
        listOfFuels.add(Material.SPRUCE_DOOR);
        listOfFuels.add(Material.SPRUCE_FENCE);
        listOfFuels.add(Material.SPRUCE_FENCE_GATE);
        listOfFuels.add(Material.SPRUCE_LOG);
        listOfFuels.add(Material.SPRUCE_PLANKS);
        listOfFuels.add(Material.SPRUCE_PRESSURE_PLATE);
        listOfFuels.add(Material.SPRUCE_SAPLING);
        listOfFuels.add(Material.SPRUCE_SIGN);
        listOfFuels.add(Material.SPRUCE_SLAB);
        listOfFuels.add(Material.SPRUCE_STAIRS);
        listOfFuels.add(Material.SPRUCE_TRAPDOOR);

        listOfFuels.add(Material.STICK);
        listOfFuels.add(Material.STRIPPED_ACACIA_LOG);
        listOfFuels.add(Material.STRIPPED_ACACIA_WOOD);
        listOfFuels.add(Material.STRIPPED_BIRCH_LOG);
        listOfFuels.add(Material.STRIPPED_BIRCH_WOOD);
        listOfFuels.add(Material.STRIPPED_DARK_OAK_LOG);
        listOfFuels.add(Material.STRIPPED_DARK_OAK_WOOD);
        listOfFuels.add(Material.STRIPPED_JUNGLE_LOG);
        listOfFuels.add(Material.STRIPPED_JUNGLE_WOOD);
        listOfFuels.add(Material.STRIPPED_OAK_LOG);
        listOfFuels.add(Material.STRIPPED_OAK_WOOD);
        listOfFuels.add(Material.STRIPPED_SPRUCE_LOG);
        listOfFuels.add(Material.STRIPPED_SPRUCE_WOOD);
        listOfFuels.add(Material.TRAPPED_CHEST);
        listOfFuels.add(Material.WHITE_BANNER);
        listOfFuels.add(Material.WHITE_CARPET);
        listOfFuels.add(Material.WHITE_WOOL);
        listOfFuels.add(Material.WOODEN_AXE);
        listOfFuels.add(Material.WOODEN_HOE);
        listOfFuels.add(Material.WOODEN_PICKAXE);
        listOfFuels.add(Material.WOODEN_SHOVEL);
        listOfFuels.add(Material.WOODEN_SWORD);
        listOfFuels.add(Material.YELLOW_BANNER);
        listOfFuels.add(Material.YELLOW_CARPET);
        listOfFuels.add(Material.YELLOW_WOOL);
    }

    public static boolean isFuel(Material mat) {
        return listOfFuels.contains(mat);
    }
    
    public static boolean isFurnaceBurnable(Material mat) {
        return furnaceBurnables.contains(mat);
    }

    public static boolean isBlastFurnaceBurnable(Material mat) {
        return blastFurnaceBurnables.contains(mat);
    }

    public static boolean isSmokerBurnable(Material mat) {
        return smokerBurnables.contains(mat);
    }
}

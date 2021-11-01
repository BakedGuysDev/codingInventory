package com.egirlsnation.codingInventory.listeners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.*;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import com.egirlsnation.codingInventory.CodingInventory;
import com.egirlsnation.codingInventory.Config;
import com.egirlsnation.codingInventory.utils.InventoryUtil;
import com.egirlsnation.codingInventory.utils.ItemUtil;

public class PlayerListener implements Listener {

	private CodingInventory plugin;

	public PlayerListener(CodingInventory ci) {
		plugin = ci;
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void fillBucket(PlayerBucketFillEvent event) {
		Player player = event.getPlayer();
		ItemStack holding = player.getInventory().getItemInMainHand();

		int amount = holding.getAmount();

		if (amount > 1) {
			ItemStack toAdd = event.getItemStack();
			int maxItems = Config.getItemMax(toAdd.getType());

			// Let Vanilla handle filling buckets for default value
			if (maxItems != Config.ITEM_DEFAULT) {
				int slot = player.getInventory().getHeldItemSlot();

				ItemStack clone = holding.clone();
				clone.setAmount(amount - 1);

				InventoryUtil.replaceItem(player.getInventory(), slot, clone);
				InventoryUtil.addItemsToPlayer(player, toAdd, "");

				event.setCancelled(true);

				Block clickedBlock = event.getBlockClicked();

				Material bucketType = toAdd.getType();
				if (bucketType == Material.WATER_BUCKET) {
					BlockData data = clickedBlock.getBlockData();
					if (data instanceof Waterlogged) {
						Waterlogged waterloggedData = (Waterlogged) data;
						waterloggedData.setWaterlogged(false);
						clickedBlock.setBlockData(waterloggedData);
					} else {
						clickedBlock.setType(Material.AIR);
					}
				} else {
					clickedBlock.setType(Material.AIR);
				}

				InventoryUtil.updateInventory(player);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void emptyBucket(PlayerBucketEmptyEvent event) {
		Player player = event.getPlayer();
		ItemStack holding = player.getInventory().getItemInMainHand();

		int amount = holding.getAmount();

		if (amount > 1) {
			ItemStack clone = holding.clone();
			clone.setAmount(amount - 1);

			int slot = player.getInventory().getHeldItemSlot();

			InventoryUtil.replaceItem(player.getInventory(), slot, clone);
			InventoryUtil.addItemsToPlayer(player, event.getItemStack(), "");
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void consumeItem(PlayerItemConsumeEvent event) {
		ItemStack consumedItem = event.getItem();
		int amt = consumedItem.getAmount();

		if (amt > 1) {
			Player player = event.getPlayer();
			Material type = consumedItem.getType();

			if (type == Material.MILK_BUCKET) {
				InventoryUtil.addItemsToPlayer(player, new ItemStack(Material.BUCKET), "");
			} else if (type == Material.MUSHROOM_STEW || type == Material.RABBIT_STEW || type == Material.BEETROOT_SOUP
					|| type == Material.SUSPICIOUS_STEW) {
				int heldSlot = player.getInventory().getHeldItemSlot();

				InventoryUtil.replaceItem(player.getInventory(), heldSlot, new ItemStack(type, amt - 1));
				InventoryUtil.addItemsToPlayer(player, new ItemStack(Material.BOWL), "");
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void playerClick(PlayerInteractEvent event) {
		Action action = event.getAction();

		// Right click air is cancelled for some reason, even when it succeeds
		if (action != Action.RIGHT_CLICK_AIR
				&& (event.useInteractedBlock() == Result.DENY || event.useItemInHand() == Result.DENY)) {
			return;
		}

		ItemStack holding = event.getItem();
		Player player = event.getPlayer();

		if (holding != null) {
			if ((action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR)
					&& holding.getType() == Material.GLASS_BOTTLE) {
				Block targetBlock = player.getTargetBlockExact(5, FluidCollisionMode.SOURCE_ONLY);

				if (targetBlock != null && targetBlock.getType() == Material.WATER) {
					ItemStack toAdd = new ItemStack(Material.POTION);
					PotionMeta meta = (PotionMeta) toAdd.getItemMeta();
					if (meta != null) {
						meta.setBasePotionData(new PotionData(PotionType.WATER));
						toAdd.setItemMeta(meta);
					}

					int maxItems = Config.getItemMax(toAdd.getType());

					// Let Vanilla handle filling bottles for default value
					if (maxItems != Config.ITEM_DEFAULT) {
						int amount = holding.getAmount();
						int slot = player.getInventory().getHeldItemSlot();

						ItemStack clone = holding.clone();
						clone.setAmount(amount - 1);

						InventoryUtil.replaceItem(player.getInventory(), slot, clone);
						InventoryUtil.addItemsToPlayer(player, toAdd, "");

						event.setCancelled(true);

						InventoryUtil.updateInventory(player);
					}
				}
			} else if (action == Action.RIGHT_CLICK_BLOCK && holding.getType() == Material.FLINT_AND_STEEL) {
				Block clickedBlock = event.getClickedBlock();
				if (clickedBlock != null) {
					Material placedType = clickedBlock.getRelative(event.getBlockFace()).getType();

					switch (placedType) {
					case WATER:
					case LAVA:
					case FIRE:
						event.setUseItemInHand(Result.DENY);
						event.setUseInteractedBlock(Result.DENY);
						break;
					default:
						break;
					}

					InventoryUtil.updateInventory(player);
				}
			}

			InventoryUtil.splitStack(player, true);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void playerIgniteBlock(BlockIgniteEvent event) {
		if (event.getCause() == IgniteCause.FLINT_AND_STEEL) {
			Player player = event.getPlayer();
			// Only deal with players.
			if (player != null) {
				ItemStack holding = player.getInventory().getItemInMainHand();

				// Since repeatedly using flint and steel causes durability loss, reset
				// durability on a new hit.
				ItemStack newStack = holding.clone();
				newStack.setDurability((short) 0);
				int maxItems = Config.getItemMax(newStack.getType());

				// Don't touch default items.
				if (maxItems == Config.ITEM_DEFAULT) {
					return;
				}
				// Handle unlimited flint and steel
				if (maxItems == Config.ITEM_INFINITE) {
					player.getInventory().setItemInMainHand(newStack);
					InventoryUtil.updateInventory(player);
				} else {
					InventoryUtil.splitStack(player, false);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void playerShearEntity(PlayerShearEntityEvent event) {
		Player player = event.getPlayer();
		ItemStack holding = player.getInventory().getItemInMainHand();

		ItemStack clone = holding.clone();
		int maxItems = Config.getItemMax(clone.getType());
		// Don't touch default items.
		if (maxItems == Config.ITEM_DEFAULT) {
			return;
		}

		// Handle unlimited shears
		if (maxItems == Config.ITEM_INFINITE) {
			player.getInventory().setItemInMainHand(clone);
		} else {
			InventoryUtil.splitStack(player, false);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void inventoryClick(InventoryClickEvent event) {

		// Get inventory information
		ItemStack cursor = event.getCursor();
		ItemStack clicked = event.getCurrentItem();
		SlotType slotType = event.getSlotType();
		InventoryView view = event.getView();
		// Sometimes shit happens so we quit when its time
		if (cursor == null || clicked == null || slotType == null || view == null) {
			if (Config.isDebugging()) plugin.log.info("Player clicked outside inventory.");
			return;
		}
		// Continue getting juicy info
		Inventory top = view.getTopInventory();
		InventoryType topType = top.getType();
		String topName = event.getView().getTitle();
		InventoryAction action = event.getAction();
		Player player = (Player) event.getWhoClicked();
		Material cursorType = cursor.getType();
		int cursorAmount = cursor.getAmount();
		Material clickedType = clicked.getType();
		int clickedAmount = clicked.getAmount();
		boolean cursorEmpty = cursorType == Material.AIR;
		boolean slotEmpty = clickedType == Material.AIR;
		boolean isHorseInventory = topName.equalsIgnoreCase("Horse") || topName.equalsIgnoreCase("Donkey")
				|| topName.equalsIgnoreCase("Mule") || topName.equalsIgnoreCase("Undead horse")
				|| topName.equalsIgnoreCase("Skeleton horse");
		boolean isPickingVanilla = (cursorEmpty
				&& (clickedAmount <= clickedType.getMaxStackSize() || clickedAmount > Config.getItemMax(clickedType))
				&& clickedType != Material.AIR);
		boolean isPlacingVanilla = false;
		if (!cursorEmpty) {
			if (cursorAmount <= cursorType.getMaxStackSize() || clickedAmount > Config.getItemMax(cursorType)) {
				isPlacingVanilla = true;
			}
		}

		// Ignore the inventories we don't want to manage:
		// Im only dealing with overstacked items cuz fuck the rest i cba rn
		if (isPickingVanilla && !isPlacingVanilla) {
			// check the amount being clicked
			if (Config.isDebugging()) plugin.log.info("Picking stack can be handled by vanilla.");
			return;
		} else if (!isPickingVanilla && isPlacingVanilla) {
			// check the amount being placed
			if (Config.isDebugging()) plugin.log.info("Placing stack can be handled by vanilla.");
			return;
		} else if (isPickingVanilla && isPlacingVanilla) {
			// Check the amounts being merged/swapped
			if (Config.isDebugging()) plugin.log.info("swapping or merging stacks can be handled by vanilla.");
			return;
		}

		// Ignore horse inventory
		if (event.getRawSlot() < 2 && topType == InventoryType.CHEST && isHorseInventory) {
			return;
		}

		// Ignore drop events
		if (action == InventoryAction.DROP_ALL_SLOT || action == InventoryAction.DROP_ALL_CURSOR
				|| action == InventoryAction.DROP_ONE_SLOT || action == InventoryAction.DROP_ONE_CURSOR) {
			return;
		}

		// Ignore shift click event
		if (event.isShiftClick()) {
			if (Config.isDebugging()) plugin.log.info("shift click illegal stacks can be handled by vanilla.");
			return;
		}

		// prevent clicks outside the inventory area or within result slots
		if (slotType != SlotType.RESULT) {
			// Fix single click for stacked items
			if (event.isLeftClick()) {
				if (Config.isDebugging()) plugin.log.info("LeftClick event caught.");
				// Pick up a stack with an empty hand
				if (cursorEmpty && !slotEmpty) {
					if (Config.isDebugging()) plugin.log.info("picking up illegal stack");
					event.setCursor(clicked.clone());
					event.setCurrentItem(null);
					event.setResult(Result.DENY);
				} else if (!slotEmpty && !cursorEmpty) {
					if (Config.isDebugging()) plugin.log.info("switching two items.");
					boolean sameType = clickedType.equals(cursorType);
					if (sameType) {
						if (ItemUtil.isSameItem(cursor, clicked)) {
							// Swap two unstackable items
							event.setCurrentItem(cursor.clone());
							event.setCursor(clicked.clone());
							event.setResult(Result.DENY);
							// These inventories need a 2 tick update for RecipeManager
							if (topType == InventoryType.CRAFTING || topType == InventoryType.WORKBENCH)
								InventoryUtil.updateInventoryLater(player, 2);
						} else {
							event.setCancelled(true);
						}
					}
				} else if (!cursorEmpty && slotEmpty) {
					// Disable placing shulkers inside other shulkers
					if (top.getType() == InventoryType.SHULKER_BOX && cursorType == Material.SHULKER_BOX) {
						if (Config.isDebugging()) plugin.log.info("Placing shulkers inside shulkers isn't allowed");
						return;
					}
					if (Config.isDebugging()) plugin.log.info("placing illegal stack");
					event.setCurrentItem(cursor.clone());
					event.setCursor(null);
					event.setResult(Result.DENY);

					// These inventories need a 2 tick update for RecipeManager
					if (topType == InventoryType.CRAFTING || topType == InventoryType.WORKBENCH) {
						InventoryUtil.updateInventoryLater(player, 2);
					} else {
						InventoryUtil.updateInventory(player);
					}
				}
			} else if (event.isRightClick()) {
				if (Config.isDebugging()) plugin.log.info("RightClick event caught.");
				if (!slotEmpty && !cursorEmpty) {
					if (Config.isDebugging()) plugin.log.info("swapping two overstacked items.");
					// Swap two unstackable items
					if (ItemUtil.isSameItem(cursor, clicked)) {
						// Swap two unstackable items
						event.setCurrentItem(cursor.clone());
						event.setCursor(clicked.clone());
						event.setResult(Result.DENY);
						// These inventories need a 2 tick update for RecipeManager
						if (topType == InventoryType.CRAFTING || topType == InventoryType.WORKBENCH)
							InventoryUtil.updateInventoryLater(player, 2);
					} else {
						event.setCancelled(true);
					}
				} else if (!slotEmpty && cursorEmpty) {
					if (Config.isDebugging()) plugin.log.info("picking up half of overstacked item");
					// Pickup half a stack
					int maxPickup = (int) Math.round((clickedAmount + 0.5) / 2);
					ItemStack clone = clicked.clone();
					ItemStack clone2 = clicked.clone();
					clone.setAmount(maxPickup);
					event.setCursor(clone);
					clone2.setAmount(clickedAmount - maxPickup);
					event.setCurrentItem(clone2);
					event.setResult(Result.DENY);
				} else if (slotEmpty && !cursorEmpty) {
					if (Config.isDebugging()) plugin.log.info("Placing half a stack");
					// Placing half a stack
					int maxPlace = (int) Math.round((cursorAmount + 0.5) / 2);
					ItemStack clone = cursor.clone();
					ItemStack clone2 = cursor.clone();
					clone.setAmount(maxPlace);
					event.setCursor(clone);
					clone2.setAmount(cursorAmount - maxPlace);
					event.setCurrentItem(clone2);
					event.setResult(Result.DENY);
				}
			}
		}
	}
}

package com.egirlsnation.codingInventory;

import java.util.logging.Logger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.egirlsnation.codingInventory.listeners.PlayerListener;
import com.egirlsnation.codingInventory.utils.InventoryUtil;
import com.egirlsnation.codingInventory.utils.ItemUtil;

public class CodingInventory extends JavaPlugin {
	public Logger log;
	public boolean supportsInventoryStackSize = true;

	public void onEnable() {
		
		log = this.getLogger();

		// Initialize classes
		Config.init(this);
		InventoryUtil.init(this);
		ItemUtil.init();

		// Register the plugin listener
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(this), this);
		
		log.info("Max stack size to handle by the plugin is set to: " + String.valueOf(Config.getMax()));
	
	}

	public void onDisable() {
		getLogger().info("codingInventory plugin has been disabled.");
	}

}

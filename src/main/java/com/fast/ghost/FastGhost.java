package com.fast.ghost;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class FastGhost extends JavaPlugin {

    private static FastGhost instance;
    private GhostHitManager ghostHitManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        this.ghostHitManager = new GhostHitManager();

        getServer().getPluginManager().registerEvents(new GhostHitListener(this, ghostHitManager), this);
        
        if (getCommand("fastghost") != null) {
            getCommand("fastghost").setExecutor(new FastGhostCommand(ghostHitManager));
        }

        String startupMsg = getConfig().getString("settings.console-startup-message", "&aFastGhost enabled successfully!");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', startupMsg));
    }

    @Override
    public void onDisable() {
        if (ghostHitManager != null) {
            ghostHitManager.clearAllData();
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "FastGhost plugin has been safely disabled.");
    }

    public static FastGhost getInstance() {
        return instance;
    }

    public GhostHitManager getGhostHitManager() {
        return ghostHitManager;
    }
}

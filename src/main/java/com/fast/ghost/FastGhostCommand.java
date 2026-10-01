package com.fast.ghost;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FastGhostCommand implements CommandExecutor {

    private final GhostHitManager ghostHitManager;

    public FastGhostCommand(GhostHitManager ghostHitManager) {
        this.ghostHitManager = ghostHitManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be executed by players!");
            return true;
        }

        int hits = ghostHitManager.getPlayerGhostHits(player);
        String messageTemplate = FastGhost.getInstance().getConfig().getString(
                "settings.stats-message", 
                "&aProcessed Ghost Hits: &e%hits%"
        );

        String formattedMessage = messageTemplate.replace("%hits%", String.valueOf(hits));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', formattedMessage));
        return true;
    }
}

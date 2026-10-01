package com.fast.ghost;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class GhostHitListener implements Listener {

    private final FastGhost plugin;
    private final GhostHitManager ghostHitManager;

    public GhostHitListener(FastGhost plugin, GhostHitManager ghostHitManager) {
        this.plugin = plugin;
        this.ghostHitManager = ghostHitManager;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onPlayerDamageEntity(EntityDamageByEntityEvent event) {
        if (!plugin.getConfig().getBoolean("settings.enabled", true)) {
            return;
        }

        if (event.getDamager() instanceof Player attacker && event.getEntity() instanceof Player target) {
            if (event.isCancelled()) {
                event.setCancelled(false);
                ghostHitManager.processAndForceHit(attacker, target, event.getDamage());
            }
        }
    }
}

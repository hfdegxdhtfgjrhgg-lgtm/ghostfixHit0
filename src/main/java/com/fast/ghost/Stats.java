package com.fast.ghost;

import org.bukkit.entity.Player;

public class Stats {

    private final GhostHitManager ghostHitManager;

    public Stats(GhostHitManager ghostHitManager) {
        this.ghostHitManager = ghostHitManager;
    }

    public int fetchPlayerGhostHits(Player player) {
        if (player == null) {
            return 0;
        }
        return ghostHitManager.getPlayerGhostHits(player);
    }
}

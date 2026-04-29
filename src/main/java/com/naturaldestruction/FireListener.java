package com.naturaldestruction;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockSpreadEvent;

public class FireListener implements Listener {

    /**
     * Cancels fire spreading to adjacent blocks.
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockSpread(BlockSpreadEvent event) {
        if (event.getNewState().getType() == org.bukkit.Material.FIRE) {
            event.setCancelled(true);
        }
    }

    /**
     * Cancels blocks being burned/destroyed by fire.
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockBurn(BlockBurnEvent event) {
        event.setCancelled(true);
    }

    /**
     * Cancels fire being ignited by fire spread (but allows flint & steel, lighters etc.).
     * Only blocks SPREAD and LAVA ignition — not player-ignited fire.
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockIgnite(BlockIgniteEvent event) {
        BlockIgniteEvent.IgniteCause cause = event.getCause();
        if (cause == BlockIgniteEvent.IgniteCause.SPREAD
                || cause == BlockIgniteEvent.IgniteCause.LAVA) {
            event.setCancelled(true);
        }
    }
}

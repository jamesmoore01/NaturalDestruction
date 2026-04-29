package com.naturaldestruction;

import org.bukkit.plugin.java.JavaPlugin;

public class NaturalDestruction extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ExplosionListener(), this);
        getLogger().info("NaturalDestruction enabled! TNT will only break whitelisted natural blocks.");
    }

    @Override
    public void onDisable() {
        getLogger().info("NaturalDestruction disabled.");
    }
}

package me.yeroc.PlasmaHub.listeners;

import me.yeroc.PlasmaHub.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Created by Corey on 28/11/2018.
 */
public class WorldListener implements Listener {
    public WorldListener(Main main) {
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        World w = Bukkit.getServer().getWorld("world");
        if (w.hasStorm()) {
            w.setWeatherDuration(0);
        } else {
            e.setCancelled(true);
        }
    }

    // TIME CHANGE IS IN MAIN onEnable();

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if (!(e.getEntityType().equals(EntityType.PLAYER))) {
            e.setCancelled(true);
        }
    }
}

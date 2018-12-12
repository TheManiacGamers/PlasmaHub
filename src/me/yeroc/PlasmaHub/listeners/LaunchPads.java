package me.yeroc.PlasmaHub.listeners;

import me.yeroc.PlasmaHub.Main;
import me.yeroc.PlasmaHub.managers.PermissionsManager;
import me.yeroc.PlasmaHub.managers.Strings;
import me.yeroc.PlasmaHub.utils.API;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Corey on 28/11/2018.
 */
public class LaunchPads implements Listener {
    public LaunchPads(Main main) {
    }

    private API api = API.getInstance();
    private PermissionsManager perms = PermissionsManager.getInstance();
    private Strings strings = Strings.getInstance();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission(perms.launchpads_use)) {
            if (e.getPlayer().getLocation().getBlock().getRelative(0, 0, 0).getType().equals(Material.STONE_PRESSURE_PLATE)) {
                if (e.getPlayer().getLocation().getBlock().getRelative(0, -1, 0).getType().equals(Material.GOLD_BLOCK)) {
                    if (p.getItemInHand().getType().equals(Material.DIAMOND_SWORD)) {
                        return;
                    }
                    if (!(p.getGameMode().equals(GameMode.CREATIVE))) {
                        p.setVelocity(p.getLocation().getDirection().multiply(2.5).setY(1.0));
                    }
                }
            }
        }
    }
}

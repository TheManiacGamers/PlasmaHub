package me.yeroc.PlasmaHub.utils.rewards;

import me.yeroc.PlasmaHub.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * Created by Corey on 1/12/2018.
 */
public class GemsManager implements Listener {

    private Main plugin;
    private static GemsManager instance = new GemsManager();

    public GemsManager(Main plugin) {
        this.plugin = plugin;
    }

    private GemsManager() {
    }

    public static GemsManager getInstance() {
        return instance;
    }


    public static int getGems(Player p) {
        return Main.pfm_totalGems.get(p.getUniqueId());
    }

    public static void addGems(Player p, int i) {
        int oldGems = Main.pfm_totalGems.get(p.getUniqueId());
        int newGems = oldGems + i;
        Main.pfm_totalGems.put(p.getUniqueId(), newGems);
    }

    public static void takeGems(Player p, int i) {
        int oldGems = Main.pfm_totalGems.get(p.getUniqueId());
        int newGems = oldGems - i;
        Main.pfm_totalGems.put(p.getUniqueId(), newGems);
    }

}

package me.yeroc.PlasmaHub.utils.rewards;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.PlasmaHub.Main;
import me.yeroc.PlasmaHub.managers.Configs;
import me.yeroc.PlasmaHub.managers.PermissionsManager;
import me.yeroc.PlasmaHub.managers.PlayerFileManager;
import me.yeroc.PlasmaHub.managers.Strings;
import me.yeroc.PlasmaHub.serverselector.ServerSelector;
import me.yeroc.PlasmaHub.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Random;

/**
 * Created by Corey on 1/12/2018.
 */
public class RewardsManager {
    private Main plugin;

    private static RewardsManager instance = new RewardsManager();

    public RewardsManager(Main plugin) {
        this.plugin = plugin;
    }

    private RewardsManager() {
    }

    public static RewardsManager getInstance() {
        return instance;
    }

    public void getReward(Player p, String s) {

    }

    private API api = API.getInstance();
    private PermissionsManager perms = PermissionsManager.getInstance();
    private Strings strings = Strings.getInstance();
    private Configs configs = Configs.getInstance();
    private ServerSelector serverSelector = ServerSelector.getInstance();
    private RewardsManager rewards = RewardsManager.getInstance();
    private GemsManager gems = GemsManager.getInstance();
    private PlayerFileManager pfm = PlayerFileManager.getInstance();

    public static Integer getRandomGems(Player p, int maxRandomAmount) {
        Random rand = new Random();
        int i = rand.nextInt(maxRandomAmount);
        if (i == 0) {
            i = 3;
        }
        if (Main.pfm_totalGems.get(p.getUniqueId()) == null) {
            Main.pfm_totalGems.put(p.getUniqueId(), 10);
        }
        int oldAmount = Main.pfm_totalGems.get(p.getUniqueId());
        int newAmount = i + oldAmount;
        Main.pfm_totalGems.put(p.getUniqueId(), newAmount);
        return i;
    }

    public static void addGems(Player p, int i) {
        if (Main.pfm_totalGems.get(p.getUniqueId()) == null) {
            Main.pfm_totalGems.put(p.getUniqueId(), 10);
        }
        int oldAmount = Main.pfm_totalGems.get(p.getUniqueId());
        int newAmount = i + oldAmount;
        Main.pfm_totalGems.put(p.getUniqueId(), newAmount);
    }
}

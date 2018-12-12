package me.yeroc.PlasmaHub.utils.rewards;

import me.yeroc.PlasmaHub.Main;
import me.yeroc.PlasmaHub.managers.Configs;
import me.yeroc.PlasmaHub.managers.PermissionsManager;
import me.yeroc.PlasmaHub.managers.PlayerFileManager;
import me.yeroc.PlasmaHub.managers.Strings;
import me.yeroc.PlasmaHub.serverselector.ServerSelector;
import me.yeroc.PlasmaHub.utils.API;
import org.bukkit.entity.Player;

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
    private GemsManager gems = GemsManager.getInstance();    private PlayerFileManager pfm = PlayerFileManager.getInstance();


}

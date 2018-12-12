package me.yeroc.PlasmaHub.serverselector;

import me.yeroc.PlasmaHub.Main;
import me.yeroc.PlasmaHub.managers.PermissionsManager;
import me.yeroc.PlasmaHub.managers.Strings;
import me.yeroc.PlasmaHub.utils.API;
import org.bukkit.event.Listener;

/**
 * Created by Corey on 28/11/2018.
 */
public class ServerSelector implements Listener {
    private Main plugin;

    private static ServerSelector instance = new ServerSelector();

    public ServerSelector(Main plugin) {
        this.plugin = plugin;
    }

    private ServerSelector() {
    }

    public static ServerSelector getInstance() {
        return instance;
    }

    private API api = API.getInstance();
    private PermissionsManager perms = PermissionsManager.getInstance();
    private Strings strings = Strings.getInstance();





}

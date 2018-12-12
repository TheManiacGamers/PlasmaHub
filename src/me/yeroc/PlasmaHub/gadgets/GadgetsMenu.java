package me.yeroc.PlasmaHub.gadgets;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import me.yeroc.PlasmaHub.Main;
import me.yeroc.PlasmaHub.managers.Configs;
import me.yeroc.PlasmaHub.managers.PermissionsManager;
import me.yeroc.PlasmaHub.managers.PlayerFileManager;
import me.yeroc.PlasmaHub.managers.Strings;
import me.yeroc.PlasmaHub.serverselector.ServerSelector;
import me.yeroc.PlasmaHub.utils.API;
import me.yeroc.PlasmaHub.utils.rewards.GemsManager;
import me.yeroc.PlasmaHub.utils.rewards.RewardsManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * Created by Corey on 1/12/2018.
 */
public class GadgetsMenu implements Listener {

    private Main plugin;

    private static GadgetsMenu instance = new GadgetsMenu();

    public GadgetsMenu(Main plugin) {
        this.plugin = plugin;
    }

    private GadgetsMenu() {
    }

    public static GadgetsMenu getInstance() {
        return instance;
    }

    private API api = API.getInstance();
    private PermissionsManager perms = PermissionsManager.getInstance();
    private Strings strings = Strings.getInstance();
    private Configs configs = Configs.getInstance();
    private ServerSelector serverSelector = ServerSelector.getInstance();
    private RewardsManager rewards = RewardsManager.getInstance();
    private GemsManager gems = GemsManager.getInstance();
    private PlayerFileManager pfm = PlayerFileManager.getInstance();



    @Command(aliases = "gadgets", desc = "Opens a menu where you buy gadgets!")
    public void onGadgetsBuy(CommandContext args, CommandSender sender) {
        if (api.isPlayer(sender)) {
            Player p = (Player) sender;
            if (p.hasPermission(perms.plasma_gadgets_buy)) {
                p.sendMessage(strings.getMessage("currentlyDisabled"));
//                if (!p.hasPermission("gadgetsmenu.hats.hamburger")) {
//                    add(p, "hats", "hamburger");
//                }
//                if (!p.hasPermission("gadgetsmenu.hats.chocolatedonut")) {
//                    add(p, "hats", "chocolatedonut");
//                }
//                if (!p.hasPermission("gadgetsmenu.hats.sandwich")) {
//                    add(p, "hats", "sandwich");
//                }
//                if (!p.hasPermission("gadgetsmenu.hats.blackchocolatebar")) {
//                    add(p, "hats", "blackchocolatebar");
//                }
//                if (!p.hasPermission("gadgetsmenu.particles.portal")) {
//                    add(p, "particles", "portal");
//                }
            }
        } else {
            sender.sendMessage(strings.getMessage("mustBePlayer"));
        }
    }

}

package me.yeroc.PlasmaHub;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Corey on 1/12/2018.
 */
public class StatsCommands {
    private static StatsCommands instance = new StatsCommands();
    private Main plugin;

    public StatsCommands(Main plugin) {
        this.plugin = plugin;
    }

    private StatsCommands() {
    }

    public static StatsCommands getInstance() {
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

    public void getTime(Player p, long seconds) {
        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
        p.sendMessage(strings.getMessage("stats_timeOnline") + "D:" + day + ", H:" + hours + ", M:" + minute + ", S:" + second);
    }

    @Command(aliases = "stats", desc = "List your statistics.")
    public void onStats(CommandContext args, CommandSender sender) {
        if (api.isPlayer(sender)) {
            Player p = (Player) sender;
            if (p.hasPermission(perms.plasma_command_stats)) {
                if (args.argsLength() == 0) {
                    int sec = Main.pfm_timeOnline.get(p.getUniqueId());
                    Date d = new Date(86400 / sec);
                    SimpleDateFormat df = new SimpleDateFormat("dd:HH:mm:ss");
                    String time = df.format(d);
                    p.sendMessage(strings.getMessage("stats_1") + p.getName() + strings.getMessage("stats_2"));
                    p.sendMessage(strings.getMessage("stats_kills") + Main.pfm_kills.get(p.getUniqueId()));
                    p.sendMessage(strings.getMessage("stats_deaths") + Main.pfm_deaths.get(p.getUniqueId()));
                    p.sendMessage(strings.getMessage("stats_joins") + Main.pfm_joins.get(p.getUniqueId()));
                    p.sendMessage(strings.getMessage("stats_gems") + Main.pfm_totalGems.get(p.getUniqueId()));
                    p.sendMessage(strings.getMessage("stats_pvplevel") + Main.pfm_pvpLevel.get(p.getUniqueId()));
                    p.sendMessage(strings.getMessage("stats_pvpexp") + Main.pfm_pvpExp.get(p.getUniqueId()));
                    getTime(p, Main.pfm_timeOnline.get(p.getUniqueId()));
                    p.sendMessage(strings.getMessage("stats_1") + p.getUniqueId() + strings.getMessage("stats_2"));
                } else {
                    p.sendMessage(strings.getMessage("incorrectArguments"));
                }
//                if (args.argsLength() == 1) {
//                    if (args.getString(0).equalsIgnoreCase("Kills") || (args.getString(0).equalsIgnoreCase("Kill"))) {
//                        p.sendMessage(strings.getMessage("stats_1 + p.getName() + strings.getMessage("stats_2);
//                        p.sendMessage(strings.getMessage("stats_kills + Main.pfm_kills.get(p.getUniqueId()).toString());
//                        p.sendMessage(strings.getMessage("stats_1 + p.getName() + strings.getMessage("stats_2);
//                        return;
//                    }
//                    if (args.getString(0).equalsIgnoreCase("Deaths") || (args.getString(0).equalsIgnoreCase("Death"))) {
//                        p.sendMessage(strings.getMessage("stats_1 + p.getName() + strings.getMessage("stats_2);
//                        p.sendMessage(strings.getMessage("stats_deaths + Main.pfm_deaths.get(p.getUniqueId()).toString());
//                        p.sendMessage(strings.getMessage("stats_1 + p.getUniqueId().toString() + strings.getMessage("stats_2);
//                        return;
//                    }
//                    if (args.getString(0).equalsIgnoreCase("Joins") || (args.getString(0).equalsIgnoreCase("Join"))) {
//                        p.sendMessage(strings.getMessage("stats_1 + p.getName() + strings.getMessage("stats_2);
//                        p.sendMessage(strings.getMessage("stats_joins + Main.pfm_joins.get(p.getUniqueId()).toString());
//                        p.sendMessage(strings.getMessage("stats_1 + p.getUniqueId().toString() + strings.getMessage("stats_2);
//                        return;
//                    }
//                    if (args.getString(0).equalsIgnoreCase("Gems") || (args.getString(0).equalsIgnoreCase("Gem"))) {
//                        p.sendMessage(strings.getMessage("stats_1 + p.getName() + strings.getMessage("stats_2);
//                        p.sendMessage(strings.getMessage("stats_gems + Main.pfm_kills.get(p.getUniqueId()));
//                        p.sendMessage(strings.getMessage("stats_1 + p.getUniqueId().toString() + strings.getMessage("stats_2);
//                    } else {
//                        p.sendMessage(strings.getMessage("stats_args);
//                    }
//                } else {
//                    p.sendMessage(strings.getMessage("incorrectArguments);
//                }
            } else {
                sender.sendMessage(strings.getMessage("noPermission"));
            }
        } else {
            sender.sendMessage(strings.getMessage("mustBePlayer"));
        }
    }
}

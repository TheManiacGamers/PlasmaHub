package me.yeroc.PlasmaHub;

import com.sk89q.minecraft.util.commands.ChatColor;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandNumberFormatException;
import me.yeroc.PlasmaHub.managers.Configs;
import me.yeroc.PlasmaHub.managers.PermissionsManager;
import me.yeroc.PlasmaHub.managers.PlayerFileManager;
import me.yeroc.PlasmaHub.managers.Strings;
import me.yeroc.PlasmaHub.serverselector.ServerSelector;
import me.yeroc.PlasmaHub.utils.API;
import me.yeroc.PlasmaHub.utils.rewards.GemsManager;
import me.yeroc.PlasmaHub.utils.rewards.RewardsManager;
import org.bukkit.Bukkit;
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
                    p.sendMessage(strings.getMessage("stats_pvpLevel") + Main.pfm_pvpLevel.get(p.getUniqueId()));
                    p.sendMessage(strings.getMessage("stats_killstreak") + Main.pfm_killstreak.get(p.getUniqueId()));
                    p.sendMessage(strings.getMessage("stats_longestKillstreak") + Main.pfm_longestKillstreak.get(p.getUniqueId()));
                    p.sendMessage(strings.getMessage("stats_pvpExp") + Main.pfm_pvpExp.get(p.getUniqueId()));
                    getTime(p, Main.pfm_timeOnline.get(p.getUniqueId()));
                    p.sendMessage(strings.getMessage("stats_1") + p.getUniqueId() + strings.getMessage("stats_2"));
                    return;
                }
                if (args.argsLength() == 1) {
                    if (p.hasPermission(perms.plasma_command_stats_others)) {
                        Player t = Bukkit.getPlayer(args.getString(0));
                        if (t != null) {
                            if (t.isOnline()) {
                                p.sendMessage(strings.getMessage("stats_1") + t.getName() + strings.getMessage("stats_2"));
                                p.sendMessage(strings.getMessage("stats_kills") + Main.pfm_kills.get(t.getUniqueId()));
                                p.sendMessage(strings.getMessage("stats_deaths") + Main.pfm_deaths.get(t.getUniqueId()));
                                p.sendMessage(strings.getMessage("stats_joins") + Main.pfm_joins.get(t.getUniqueId()));
                                p.sendMessage(strings.getMessage("stats_gems") + Main.pfm_totalGems.get(t.getUniqueId()));
                                p.sendMessage(strings.getMessage("stats_pvpLevel") + Main.pfm_pvpLevel.get(t.getUniqueId()));
                                p.sendMessage(strings.getMessage("stats_killstreak") + Main.pfm_killstreak.get(t.getUniqueId()));
                                p.sendMessage(strings.getMessage("stats_longestKillstreak") + Main.pfm_longestKillstreak.get(t.getUniqueId()));
                                p.sendMessage(strings.getMessage("stats_pvpExp") + Main.pfm_pvpExp.get(t.getUniqueId()));
                                getTime(p, Main.pfm_timeOnline.get(t.getUniqueId()));
                                p.sendMessage(strings.getMessage("stats_1") + t.getUniqueId() + strings.getMessage("stats_2"));
                            } else {
                                sender.sendMessage(ChatColor.RED + "That player is not online!");
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "That player is not online!");
                        }
                    } else {
                        p.sendMessage(strings.getMessage("noPermission"));
                    }
                    return;
                } //stats set kills 2
                if (args.argsLength() == 2) {
                    p.sendMessage(strings.getMessage("incorrectArguments"));
                    return;
                }
                if (args.argsLength() == 3) {
                    if (args.getString(0).equalsIgnoreCase("set")) {
                        if (args.getString(1).equalsIgnoreCase("kills")) {
                            try {
                                Main.pfm_kills.put(p.getUniqueId(), args.getInteger(2));
                                p.sendMessage(strings.getMessage("stats") + ChatColor.GREEN + " You set your kills to: " + ChatColor.RED + args.getInteger(2));
                            } catch (CommandNumberFormatException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                        if (args.getString(1).equalsIgnoreCase("deaths")) {
                            try {
                                Main.pfm_deaths.put(p.getUniqueId(), args.getInteger(2));
                                p.sendMessage(strings.getMessage("stats") + ChatColor.GREEN + " You set your deaths to: " + ChatColor.RED + args.getInteger(2));
                            } catch (CommandNumberFormatException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                        if (args.getString(1).equalsIgnoreCase("joins")) {
                            try {
                                Main.pfm_joins.put(p.getUniqueId(), args.getInteger(2));
                                p.sendMessage(strings.getMessage("stats") + ChatColor.GREEN + " You set your joins to: " + ChatColor.RED + args.getInteger(2));
                            } catch (CommandNumberFormatException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                        if (args.getString(1).equalsIgnoreCase("gems")) {
                            try {
                                Main.pfm_totalGems.put(p.getUniqueId(), args.getInteger(2));
                                p.sendMessage(strings.getMessage("stats") + ChatColor.GREEN + " You set your gems to: " + ChatColor.RED + args.getInteger(2));
                            } catch (CommandNumberFormatException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                        if (args.getString(1).equalsIgnoreCase("pvplevel")) {
                            try {
                                Main.pfm_pvpLevel.put(p.getUniqueId(), args.getInteger(2));
                                p.sendMessage(strings.getMessage("stats") + ChatColor.GREEN + " You set your pvp level to: " + ChatColor.RED + args.getInteger(2));
                            } catch (CommandNumberFormatException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                        if (args.getString(1).equalsIgnoreCase("longestkillstreak")) {
                            try {
                                Main.pfm_longestKillstreak.put(p.getUniqueId(), args.getInteger(2));
                                p.sendMessage(strings.getMessage("stats") + ChatColor.GREEN + " You set your longest killstreak to: " + ChatColor.RED + args.getInteger(2));
                            } catch (CommandNumberFormatException e) {
                                e.printStackTrace();
                            }

                        }
                        if (args.getString(1).equalsIgnoreCase("timeonline") || (args.getString(1).equalsIgnoreCase("time"))) {
                            try {
                                Main.pfm_timeOnline.put(p.getUniqueId(), args.getInteger(2));
                                p.sendMessage(strings.getMessage("stats") + ChatColor.GREEN + " You set your time online to: " + ChatColor.RED + args.getInteger(2) + ChatColor.GREEN + " seconds");
                            } catch (CommandNumberFormatException e) {
                                e.printStackTrace();
                            }
                        } else {
                            p.sendMessage(strings.getMessage("incorrectArguments"));
                            p.sendMessage(strings.getMessage("prefix") + ChatColor.RED + " Possible arguments: kills, deaths, joins, gems, pvplevel, longestkillstreak, time");
                        }
                    } else {
                        p.sendMessage(strings.getMessage("incorrectArguments"));
                    }
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

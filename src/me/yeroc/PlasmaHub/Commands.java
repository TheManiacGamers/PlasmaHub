package me.yeroc.PlasmaHub;

import com.sk89q.minecraft.util.commands.ChatColor;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandNumberFormatException;
import me.yeroc.PlasmaHub.managers.Configs;
import me.yeroc.PlasmaHub.managers.PermissionsManager;
import me.yeroc.PlasmaHub.managers.PlayerFileManager;
import me.yeroc.PlasmaHub.managers.Strings;
import me.yeroc.PlasmaHub.utils.API;
import me.yeroc.PlasmaHub.utils.TitleAPI.TitleAPI;
import me.yeroc.PlasmaHub.utils.rewards.GemsManager;
import me.yeroc.PlasmaHub.utils.rewards.RewardsManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Commands {

    private static Commands instance = new Commands();
    private Main plugin;

    public Commands(Main plugin) {
        this.plugin = plugin;
    }

    private Commands() {
    }

    public static Commands getInstance() {
        return instance;
    }

    private API api = API.getInstance();
    private PermissionsManager perms = PermissionsManager.getInstance();
    private Strings strings = Strings.getInstance();
    private Configs configs = Configs.getInstance();
    private PlayerListener playerListener = PlayerListener.getInstance();
    private RewardsManager rewards = RewardsManager.getInstance();
    private GemsManager gems = GemsManager.getInstance();
    private PlayerFileManager pfm = PlayerFileManager.getInstance();
    private AutoBroadcast ab = AutoBroadcast.getInstance();

    private String mitchell = ("eee3a024-f05f-45f1-a741-b2938fff4f44");
    private String corey = ("3d87ff2a-90e9-4e00-acac-1338331b595d");

    private void sendHelp(Player p, int i) {
        if (i == 1) {
            p.sendMessage(strings.getMessage("helpHeader"));
            p.sendMessage(strings.getMessage("help1"));
            p.sendMessage(strings.getMessage("help2"));
            p.sendMessage(strings.getMessage("help3"));
            p.sendMessage(strings.getMessage("helpFooter1"));
        } else if (i == 2) {
            p.sendMessage(strings.getMessage("helpHeader"));
            p.sendMessage(strings.getMessage("helpEmpty"));
            p.sendMessage(strings.getMessage("helpFooter2"));
        } else {
            Bukkit.broadcastMessage(strings.getMessage("error") + "Commands");
        }

    }


    @Command(aliases = "gmc", desc = "Change to creative mode.")
    public void onGMC(CommandContext args, CommandSender sender) {
        if (api.isPlayer(sender)) {
            Player p = (Player) sender;
            if (api.hasPermSender(p, perms.plasma_gamemode_creative)) {
                if (p.getGameMode().equals(GameMode.CREATIVE)) {
                    p.sendMessage(strings.getMessage("alreadyThatGamemode"));
                } else {
                    p.sendMessage(strings.getMessage("changedGamemode") + "Creative.");
                    p.getInventory().clear();
                    p.setGameMode(GameMode.CREATIVE);
                    if (Main.canDoubleJump.get(p.getUniqueId()) == null) {
                        Main.canDoubleJump.put(p.getUniqueId(), "yes");
                    }
                    if (Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                        Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                        Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
                        p.sendMessage(strings.getMessage("parkour_stop"));
                        Main.canDoubleJump.put(p.getUniqueId(), "yes");
                    }
                    if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                        Main.maze_isInMaze.put(p.getUniqueId(), "no");
                        p.sendMessage(strings.getMessage("maze_stop"));
                        Main.canDoubleJump.put(p.getUniqueId(), "yes");
                    }
                    if (Main.canDoubleJump.get(p.getUniqueId()) == null) {
                        Main.canDoubleJump.put(p.getUniqueId(), "yes");
                    }
                }
            } else {
                sender.sendMessage(strings.getMessage("noPermission"));
            }
        } else {
            sender.sendMessage(strings.getMessage("mustBePlayer"));
        }
    }

    @Command(aliases = "gma", desc = "Change to adventure mode.")
    public void onGMA(CommandContext args, CommandSender sender) {
        if (api.isPlayer(sender)) {
            Player p = (Player) sender;
            if (api.hasPermSender(p, perms.plasma_gamemode_advemture)) {
                if (p.getGameMode().equals(GameMode.ADVENTURE)) {
                    p.sendMessage(strings.getMessage("alreadyThatGamemode"));
                } else {
                    p.sendMessage(strings.getMessage("changedGamemode") + "Adventure.");
                    api.resetInventory(p);
                    p.setGameMode(GameMode.ADVENTURE);
                }
            } else {
                sender.sendMessage(strings.getMessage("noPermission"));
            }
        } else {
            sender.sendMessage(strings.getMessage("mustBePlayer"));
        }
    }

    @Command(aliases = "gmsp", desc = "Change to spectator mode.")
    public void onGMSP(CommandContext args, CommandSender sender) {
        if (api.isPlayer(sender)) {
            Player p = (Player) sender;
            if (api.hasPermSender(p, perms.plasma_gamemode_spectator)) {
                if (p.getGameMode().equals(GameMode.SPECTATOR)) {
                    p.sendMessage(strings.getMessage("alreadyThatGamemode"));
                } else {
                    p.sendMessage(strings.getMessage("changedGamemode") + "Spectator.");
                    p.setGameMode(GameMode.SPECTATOR);
                }
            } else {
                sender.sendMessage(strings.getMessage("noPermission"));
            }
        } else {
            sender.sendMessage(strings.getMessage("mustBePlayer"));
        }
    }


    @Command(aliases = "gms", desc = "Change to survival mode.")
    public void onGMS(CommandContext args, CommandSender sender) {
        if (api.isPlayer(sender)) {
            Player p = (Player) sender;
            if (api.hasPermSender(p, perms.plasma_gamemode_survival)) {
                if (p.getGameMode().equals(GameMode.SURVIVAL)) {
                    p.sendMessage(strings.getMessage("alreadyThatGamemode"));
                } else {
                    p.sendMessage(strings.getMessage("changedGamemode") + "Survival.");
                    p.setGameMode(GameMode.SURVIVAL);
                    api.resetInventory(p);
                    Main.canDoubleJump.put(p.getUniqueId(), "yes");
                    if (Main.canDoubleJump.get(p.getUniqueId()) == null) {
                        Main.canDoubleJump.put(p.getUniqueId(), "yes");
                    }
                    if (Main.maze_isInMaze.get(p.getUniqueId()) == null) {
                        Main.maze_isInMaze.put(p.getUniqueId(), "no");
                    }
                    if (Main.parkour_playerCheckpoints.get(p.getUniqueId()) == null) {
                        Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                        Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
                    }
                    if (Main.parkour_isInParkour.get(p.getUniqueId()) == null) {
                        Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                        Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
                    }
                    if (Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                        Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                        Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
                    }
                    if (Main.maze_isInMaze.get(p.getUniqueId()) == null) {
                        Main.maze_isInMaze.put(p.getUniqueId(), "no");
                    }
                    if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                        Main.maze_isInMaze.put(p.getUniqueId(), "no");
                    }
                }
            } else {
                sender.sendMessage(strings.getMessage("noPermission"));
            }
        } else {
            sender.sendMessage(strings.getMessage("mustBePlayer"));
        }
    }

    private int reloadTimeLeft = 5;
    public boolean stillAllowed = false;
    public boolean reloadStarted = false;

    @Command(aliases = "rr", desc = "Reload with a warning")
    public void onRR(CommandContext args, final CommandSender sender) {
        if (sender.getName().equalsIgnoreCase("TheManiacGamers") || (sender.getName().equalsIgnoreCase("Facket") || (sender.getName().equalsIgnoreCase("Rookie1200")))) {
            int importantPlayerAmount = 0;
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getName().equalsIgnoreCase("TheManiacGamers")) {
                    importantPlayerAmount++;
                }
                if (p.getName().equalsIgnoreCase("Rookie1200")) {
                    importantPlayerAmount++;
                }
                if (p.getName().equalsIgnoreCase("Facket")) {
                    importantPlayerAmount++;
                }
            }
            if (importantPlayerAmount == 0 || (importantPlayerAmount == 1)) {
                sender.sendMessage(strings.getMessage("reloading"));
                Bukkit.reload();
                return;
            }
            if (Main.reloadConfirmed.get(Bukkit.getServer()).equalsIgnoreCase("empty")) {
                Main.reloadConfirmed.put(Bukkit.getServer(), "requested");
            }
            Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("PlasmaHub"), new BukkitRunnable() {
                @Override
                public void run() {
                    if (Main.reloadConfirmed.get(Bukkit.getServer()).equalsIgnoreCase("confirmed")) {
                        if (reloadTimeLeft <= 5 && reloadTimeLeft >= 0) {
                            Bukkit.broadcastMessage(strings.getMessage("reloadIn") + reloadTimeLeft + "seconds");
                            reloadTimeLeft--;
                            reloadStarted = true;
                            return;
                        }
                        doReload();
//                        } else {
//                            reloadTimeLeft = 5;
//                            for (Player p : Bukkit.getOnlinePlayers()) {
//                                if (sender.getName().equalsIgnoreCase("TheManiacGamers") || (sender.getName().equalsIgnoreCase("Facket") || (sender.getName().equalsIgnoreCase("Rookie1200")))) {
//                                    p.sendMessage(strings.getMessage("reloadCancelled);
//                                }
//                            }
//                            Main.reloadConfirmed.put(Bukkit.getServer(), "empty");
//                            stillAllowed = true;
//                            cancel();
//                        }
                    } else {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (sender.getName().equalsIgnoreCase("TheManiacGamers") || (sender.getName().equalsIgnoreCase("Facket") || (sender.getName().equalsIgnoreCase("Rookie1200")))) {
                                p.sendMessage(strings.getMessage("reloadAwaitingConfirmation"));
                            }
                        }
                    }
                }
            }, 0L, 20L);
        } else {
            sender.sendMessage(strings.getMessage("noPermission"));
        }

    }

    private void doReload() {
        Bukkit.broadcastMessage(strings.getMessage("reloading"));
        Bukkit.getServer().reload();
        reloadTimeLeft = 5;
    }


    @Command(aliases = "plasma", desc = "Base command for the PlasmaHub plugin")
    public void onPlasma(CommandContext args, CommandSender sender) {
        if (!Main.pluginEnabled) {
            sender.sendMessage(strings.getMessage("pluginDisabled"));
            return;
        }
        if (api.hasPermSender(sender, perms.plasma_command)) {
            if (args.argsLength() == 0) {
                if (api.isPlayer(sender)) {
                    Player p = (Player) sender;
                    sendHelp(p, 1);
                } else {
                    sender.sendMessage(strings.getMessage("mustBePlayer"));
                }
                return;
            }
            if (args.argsLength() == 1) {
                if (args.getString(0).equalsIgnoreCase("help")) {
                    if (api.isPlayer(sender)) {
                        Player p = (Player) sender;
                        sendHelp(p, 1);
                    } else {
                        sender.sendMessage(strings.getMessage("mustBePlayer"));
                    }
                    return;
                }
                if (args.getString(0).equalsIgnoreCase("reload")) {
                    if (sender.hasPermission(perms.plasma_config_reload)) {
                        sender.sendMessage(strings.getMessage("reloadingConfig"));
                        configs.reloadConfig();
                        configs.reloadMessages();
                        if (Main.messages.size() != 0) {
                            Main.messages.clear();
                        }
                        if (Main.autoBroadcastMessages.size() != 0) {
                            Main.autoBroadcastMessages.clear();
                        }
                        if (Main.autoBroadcastTime != null) {
                            Main.autoBroadcastTime = configs.getConfig().getInt("AutoBroadcast.Interval");
                        }
                        strings.loadMessages();
                        ab.loadMessages();
                        sender.sendMessage(strings.getMessage("reloadedConfig"));
                        sender.sendMessage(strings.getMessage("prefix") + strings.getMessage("messagesLoaded") + Main.messages.size() + ".");
                        sender.sendMessage(strings.getMessage("prefix") + strings.getMessage("autoBroadcastMessagesLoaded") + Main.autoBroadcastMessages.size() + ".");
                        sender.sendMessage(strings.getMessage("prefix") + strings.getMessage("autoBroadcastTimeSet") + Main.autoBroadcastTime + " minutes.");
                    } else {
                        sender.sendMessage(strings.getMessage("noPermission"));
                    }
                } else {
                    sender.sendMessage(strings.getMessage("incorrectArguments") + " /plasma [help]");
                }
            } else {
                sender.sendMessage(strings.getMessage("incorrectArguments") + " /plasma [help]");
            }
        } else {
            sender.sendMessage(strings.getMessage("noPermission"));
        }
    }

    public void setSpawn(Player p) {
        Location pLoc = p.getLocation();
        configs.getConfig().set("Spawn.X", pLoc.getX());
        configs.getConfig().set("Spawn.Y", pLoc.getY());
        configs.getConfig().set("Spawn.Z", pLoc.getZ());
        configs.getConfig().set("Spawn.Yaw", pLoc.getYaw());
        configs.getConfig().set("Spawn.Pitch", pLoc.getPitch());
        configs.getConfig().set("Spawn.World", "world");
        configs.saveConfig();
        Main.spawn = pLoc;
        p.sendMessage(strings.getMessage("spawnSet"));
    }

    @Command(aliases = "servers", desc = "Opens the Server Selector menu")
    public void onServers(CommandContext args, CommandSender sender) {
        if (api.isPlayer(sender)) {
            Player p = (Player) sender;
            if (api.hasPermSender(p, perms.plasma_serverselector_use)) {
                playerListener.openSelector(p);
            }
        } else {
            sender.sendMessage(strings.getMessage("mustBePlayer"));
        }
    }

    @Command(aliases = "spawn", desc = "Spawn command")
    public void onSpawn(CommandContext args, CommandSender sender) {
        if (api.isPlayer(sender)) {
            if (api.hasPermSender(sender, perms.plasma_command_spawn)) {
                Player p = (Player) sender;
                if (Main.spawn != null) {
                    if (Main.spawn != new Location(Bukkit.getWorld("world"), 0, 0, 0, 0, 0)) {
                        p.teleport(Main.spawn);
                    } else {
                        if (Bukkit.getOnlinePlayers().size() != 0) {
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                if (api.hasPermSender(pl, perms.plasma_command_setspawn)) {
                                    p.sendMessage(strings.getMessage("spawnNotSet"));
                                    p.sendMessage(strings.getMessage("spawnNotSetAllowed"));
                                }
                            }
                        }
                        Main.log("Spawn has not been set yet.");
                    }
                } else {
                    if (Bukkit.getOnlinePlayers().size() != 0) {
                        for (Player pl : Bukkit.getOnlinePlayers()) {
                            if (api.hasPermSender(pl, perms.plasma_command_setspawn)) {
                                p.sendMessage(strings.getMessage("spawnNotSet"));
                                p.sendMessage(strings.getMessage("spawnNotSetAllowed"));
                            }
                        }
                    }
                    Main.log("Spawn has not been set yet.");
                }
            }
        } else {
            sender.sendMessage(strings.getMessage("mustBePlayer"));
        }
    }

    @Command(aliases = "setspawn", desc = "Setspawn command")
    public void onSetSpawn(CommandContext args, CommandSender sender) {
        if (api.isPlayer(sender)) {
            if (api.hasPermSender(sender, perms.plasma_command_setspawn)) {
                Player p = (Player) sender;
                setSpawn(p);
            }
        } else {
            sender.sendMessage(strings.getMessage("mustBePlayer"));
        }
    }

    @Command(aliases = "console", desc = "Send a command as console. This is only for the Owners.")
    public void onConsoleCommand(CommandContext args, CommandSender sender) {
        if (api.isPlayer(sender)) {
            Player p = (Player) sender;
            if (args.argsLength() == 0) {
                p.sendMessage(strings.getMessage("incorrectArguments"));
                return;
            }
            if (p.getUniqueId().equals(UUID.fromString(mitchell)) || (p.getUniqueId().equals(UUID.fromString(corey)))) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), args.getRemainingString(0));
            } else {
                p.sendMessage(strings.getMessage("noPermission"));
            }
        } else {
            sender.sendMessage(strings.getMessage("mustBePlayer"));
        }
    }

    @Command(aliases = "maze", desc = "Maze base command")
    public void onMaze(CommandContext args, CommandSender sender) {
        if (api.isPlayer(sender)) {
            Player p = (Player) sender;
            if (args.argsLength() == 0) {
                p.sendMessage(strings.getMessage("incorrectArguments"));
                return;
            }
            if (args.argsLength() == 1) {
                if (args.getString(0).equalsIgnoreCase("quit")) {
                    if (p.hasPermission(perms.plasma_maze_use)) {
                        if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                            Main.maze_isInMaze.put(p.getUniqueId(), "no");
                            p.teleport(Main.spawn);
                            p.sendMessage(strings.getMessage("maze_stop"));
                        }
                    } else {
                        sender.sendMessage(strings.getMessage("noPermission"));
                        return;
                    }
                    return;
                }
                if (args.getString(0).equalsIgnoreCase("load")) {
                    if (p.hasPermission(perms.plasma_maze_load)) {
                        if (p.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(Main.maze_start)) {
                            if (Bukkit.getOnlinePlayers().size() != 0) {
                                int anyoneInMaze = 0;
                                for (Player online : Bukkit.getOnlinePlayers()) {
                                    if (Main.maze_isInMaze.get(online.getUniqueId()) == null) {
                                        Main.maze_isInMaze.put(online.getUniqueId(), "no");
                                    }
                                    if (Main.maze_isInMaze.get(online.getUniqueId()).equalsIgnoreCase("yes")) {
                                        anyoneInMaze++;
                                    }
                                }
                                if (anyoneInMaze >= 2) {
                                    p.sendMessage(strings.getMessage("maze_peopleInGame"));
                                } else {
                                    if (Main.maze_loaded == null) {
                                        Main.maze_loaded = 1;
                                    }
                                    Main.maze_loaded++;
                                    if (Main.maze_loaded > Main.mazes) {
                                        Main.maze_loaded = 1;
                                        p.sendMessage(strings.getMessage("maze_loaded") + "Maze 1");
                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_1");
                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
                                        p.sendMessage(strings.getMessage("maze_loaded_done"));
                                        return;
                                    }
                                    int newMazeLoaded = Main.maze_loaded;
                                    if (Main.maze_loaded <= Main.mazes) {
                                        Main.maze_loaded = newMazeLoaded;
                                        p.sendMessage(strings.getMessage("maze_loaded") + "Maze " + Main.maze_loaded);
                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_" + Main.maze_loaded);
                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
                                        p.sendMessage(strings.getMessage("maze_loaded_done"));
                                    }
                                    return;


//                                    if (Main.loadedMaze == null) {
//                                        Main.loadedMaze = "Maze_1";
//                                    }
//                                    if (Main.loadedMaze.equals("Maze_1")) {
//                                        Main.loadedMaze = "Maze_2";
//                                        p.sendMessage(strings.getMessage("maze_loaded") + "Maze 2");
//                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_2");
//                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
//                                        p.sendMessage(strings.getMessage("maze_loaded_done"));
//                                        return;
//                                    }
//                                    if (Main.loadedMaze.equals("Maze_2")) {
//                                        Main.loadedMaze = "Maze_3";
//                                        p.sendMessage(strings.getMessage("maze_loaded") + "Maze 3");
//                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_3");
//                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
//                                        p.sendMessage(strings.getMessage("maze_loaded_done"));
//                                        return;
//                                    }
//                                    if (Main.loadedMaze.equals("Maze_3")) {
//                                        Main.loadedMaze = "Maze_4";
//                                        p.sendMessage(strings.getMessage("maze_loaded") + "Maze 4");
//                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_4");
//                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
//                                        p.sendMessage(strings.getMessage("maze_loaded_done"));
//                                        return;
//                                    }
//                                    if (Main.loadedMaze.equals("Maze_4")) {
//                                        Main.loadedMaze = "Maze_5";
//                                        p.sendMessage(strings.getMessage("maze_loaded") + "Maze 5");
//                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_5");
//                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
//                                        p.sendMessage(strings.getMessage("maze_loaded_done"));
//                                        return;
//                                    }
//                                    if (Main.loadedMaze.equals("Maze_5")) {
//                                        Main.loadedMaze = "Maze_6";
//                                        p.sendMessage(strings.getMessage("maze_loaded") + "Maze 6");
//                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_6");
//                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
//                                        p.sendMessage(strings.getMessage("maze_loaded_done"));
//                                        return;
//                                    }
//                                    if (Main.loadedMaze.equals("Maze_6")) {
//                                        Main.loadedMaze = "Maze_1";
//                                        p.sendMessage(strings.getMessage("maze_loaded") + "Maze 1");
//                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_1");
//                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
//                                        p.sendMessage(strings.getMessage("maze_loaded_done"));
//                                    } else {
//                                        Main.loadedMaze = "Maze_1";
//                                        p.sendMessage(strings.getMessage("maze_loaded") + "Maze 1");
//                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_1");
//                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
//                                        p.sendMessage(strings.getMessage("maze_loaded_done"));
//                                    }
                                }
                                return;
                            }
                            return;
                        } else {
                            p.sendMessage(strings.getMessage("maze_wrongLocation"));
                        }
                        return;
                    } else {
                        p.sendMessage(strings.getMessage("noPermission"));
                    }
                    return;
                }
                if (args.getString(0).equalsIgnoreCase("add")) {
                    if (p.hasPermission(perms.plasma_maze_add)) {
                        int mazeAmount = Main.mazes;
                        int newMazeAmount = mazeAmount + 1;
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /copy");
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic save maze_" + newMazeAmount);
                        Main.mazes = newMazeAmount;
                        p.sendMessage(strings.getMessage("newMazeAmount") + newMazeAmount);
                        return;
                    } else {
                        p.sendMessage(strings.getMessage("noPermission"));
                        return;
                    }
                }
                if (args.getString(0).equalsIgnoreCase("default")) {
                    if (p.hasPermission(perms.plasma_maze_load)) {
                        if (p.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(Main.maze_start)) {
                            Main.maze_loaded = 1;
                            p.sendMessage(strings.getMessage("maze_loaded") + "Maze " + 1);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_1");
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
                            p.sendMessage(strings.getMessage("maze_loaded_done"));
                        } else {
                            sender.sendMessage(strings.getMessage("maze_wrongLocation"));
                            return;
                        }
                    } else {
                        sender.sendMessage(strings.getMessage("noPermission"));
                        return;
                    }
                    return;
                } else {
                    p.sendMessage(strings.getMessage("incorrectArguments"));
                }
                return;
            }
            if (args.argsLength() == 2) {
                if (args.getString(0).equalsIgnoreCase("set")) {
                    if (p.hasPermission(perms.plasma_maze_load)) {
                        if (p.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(Main.maze_start)) {
                            try {
                                if (args.getInteger(1) <= Main.mazes) {
                                    int newMazeAmount = args.getInteger(1);
                                    p.sendMessage(strings.getMessage("maze_loaded_set") + "Maze " + newMazeAmount);
                                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_" + newMazeAmount);
                                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
                                    Main.maze_loaded = newMazeAmount;
                                    p.sendMessage(strings.getMessage("maze_loaded_done"));
                                } else {
                                    p.sendMessage(strings.getMessage("numberTooHigh"));
                                    p.sendMessage(strings.getMessage("mazeAmount") + Main.mazes + " mazes");
                                }
                            } catch (CommandNumberFormatException e) {
                                p.sendMessage(strings.getMessage("mustBeNumber"));
                            }
                        } else {
                            sender.sendMessage(strings.getMessage("maze_wrongLocation"));
                        }
                    } else {
                        sender.sendMessage(strings.getMessage("noPermission"));
                    }
                } else {
                    sender.sendMessage(strings.getMessage("incorrectArguments"));
                }
            } else {
                sender.sendMessage(strings.getMessage("incorrectArguments"));
            }
        } else

        {
            sender.sendMessage(strings.getMessage("mustBePlayer"));
        }

    }

    @Command(aliases = "parkour", desc = "Parkour base command")
    public void onParkour(CommandContext args, CommandSender sender) {
        if (api.isPlayer(sender)) {
            Player p = (Player) sender;
            if (p.hasPermission(perms.plasma_parkour_use)) {
                if (args.argsLength() == 0) {
                    if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("one")) {
                        p.teleport(Main.parkour_start);
                        Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                        TitleAPI.sendActionBar(p, strings.getMessage("parkour_teleport_start"));
                        return;
                    }
                    if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("two")) {
                        p.teleport(Main.parkour_chp1);
                        TitleAPI.sendActionBar(p, strings.getMessage("parkour_teleport") + "1");
                        Main.parkour_isInParkour.put(p.getUniqueId(), "yes");
                        return;
                    }
                    if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("three")) {
                        p.teleport(Main.parkour_chp2);
                        TitleAPI.sendActionBar(p, strings.getMessage("parkour_teleport") + "2");
                        Main.parkour_isInParkour.put(p.getUniqueId(), "yes");
                        return;
                    }
                    if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("four")) {
                        p.teleport(Main.parkour_chp3);
                        TitleAPI.sendActionBar(p, strings.getMessage("parkour_teleport") + "3");
                        Main.parkour_isInParkour.put(p.getUniqueId(), "yes");
                        return;
                    }
                    if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("five")) {
                        p.teleport(Main.parkour_chp4);
                        Main.parkour_isInParkour.put(p.getUniqueId(), "yes");
                        TitleAPI.sendActionBar(p, strings.getMessage("parkour_teleport") + "4");
                    } else {
                        p.sendMessage(strings.getMessage("parkour_notStarted"));
                    }

                } else if (args.argsLength() == 1) {
                    if (args.getString(0).equalsIgnoreCase("quit")) {
                        if (Main.parkour_isInParkour.get(p.getUniqueId()) == null) {
                            Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
                            Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                            p.sendMessage(strings.getMessage("parkour_notStarted"));
                        } else if (Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                            Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
                            Main.canDoubleJump.put(p.getUniqueId(), "yes");
                            Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                            p.getInventory().setItem(7, new ItemStack(Material.AIR, 1));
                            p.teleport(Main.spawn);
                            p.sendMessage(strings.getMessage("parkour_stop"));
                        }
                    } else {
                        p.sendMessage(strings.getMessage("parkour_notStarted"));
                    }
                } else {
                    p.sendMessage(strings.getMessage("incorrectArguments"));
                }
            } else {
                p.sendMessage(strings.getMessage("noPermission"));
            }
        } else {
            sender.sendMessage(strings.getMessage("mustBePlayer"));
        }
    }

    @Command(aliases = "resetallinventory", desc = "Reset all inventories for players in survival mode")
    public void onResetAllInventory(CommandContext args, CommandSender sender) {
        if (sender.hasPermission(perms.plasma_command_resetAllInventory)) {
            if (Bukkit.getOnlinePlayers().size() != 0) {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    if (online.getGameMode().equals(GameMode.CREATIVE)) {
                        return;
                    }
                    api.resetInventory(online);
                    online.sendMessage(strings.getMessage("inventoryResetManual"));
                }
                sender.sendMessage(strings.getMessage("resetAllInventory"));
            } else {
                sender.sendMessage(strings.getMessage("error"));
            }
        } else {
            sender.sendMessage(strings.getMessage("noPermission"));
        }
    }

}
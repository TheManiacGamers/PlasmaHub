package me.yeroc.PlasmaHub.managers;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.PlasmaHub.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Corey on 28/11/2018.
 */
public class Strings {

    private static Strings instance = new Strings();
    private Main plugin;

    private Strings() {

    }

    public static Strings getInstance() {
        return instance;
    }

    private String prefix = ("§0[§b§lPlasma§0]§r");
    private String server = ("§0[§a§lServer§0]§r");
    private String mustBePlayer = ("{PREFIX} §cYou must be a player to use this command.");
    private String noPermission = ("{PREFIX} §cYou do not have permission to use this.");
    private String incorrectArguments = ("{PREFIX} §cYou have entered incorrect arguments: §l");
    private String error = ("{SERVER} §4There has been an error. Please contact administration. Class: ");
    private String spawnNotSet = ("{SERVER} §4Spawn has not been set. Please contact administration.");
    private String spawnNotSetAllowed = ("{SERVER} §4You have permission to use /setspawn.");
    private String spawnSet = ("{SERVER} §aSpawn has been set to your location.");
    private String inventoryResetReload = ("{SERVER} §cDue to a server reload, your inventory has been reset");
    private String alreadyThatGamemode = ("{PREFIX} §cYou are already in that gamemode.");
    private String changedGamemode = ("{PREFIX} §aYou changed your GameMode to: ");
    private String currentlyDisabled = ("{PREFIX} §cThis is currently disabled by the owner.");
    private String join = ("§0[§a+§0]§7");
    private String quit = ("§0[§c-§0]§7");
    private String reloadIn = ("{SERVER} §cThe server will be reloaded in: ");
    private String reloading = ("{SERVER} §cReloading the server now.");
    private String reloadCancelled = ("{SERVER} §cThe server reload has been cancelled.");
    private String reloadAwaitingConfirmation = ("{SERVER} §cServer reload awaiting approval. Please say yes.");
    private String serverReloaded = ("{SERVER} §aThe server has been reloaded.");
    private String pfLoaded = ("The PlayerFile has been loaded for: ");
    private String sendToServer = ("§aSending you to: ");
    private String inventoryResetManual = ("{SERVER} §cYour inventory has been manually reset.");
    private String resetAllInventory = ("{SERVER} §cYou reset players in Survival modes inventory.");
    private String noPermissionServer = ("{SERVER} §cYou do not have permission to enter that server.");
    private String cantUseHere = ("{SERVER} §cYou can not use that here.");

    // HELP PAGE(S):
    private String helpHeader = ("§6-=[§aPlasmaNetwork§6]=- ");
    private String helpFooter1 = ("§6-=[§aPage : 1§6]=- ");
    private String helpFooter2 = ("§6-=[§aPage : 2§6]=- ");
    private String helpEmpty = ("§aThis page is empty.");
    private String help1 = ("§6  /plasma [help] §a- shows help page by default.");
    private String help2 = ("§6  /disco  §a- start a Disco.");
    private String help3 = ("§6  /dj  §a- toggle Double Jump.");

    // MINIGAMES
    private String kotl = ("§0[§b§lKOTL§0]§a");
    private String kotl_enter = ("§4Entering §cKing of the Ladder region!");
    private String kotl_leave = ("§aLeaving §cKing of the Ladder region!");
    private String kotl_newLeader = ("§cis the new King of the Ladder!");
    private String kotl_youAreNewLeader = ("§aYou are the new King of the Ladder");
    private String kotl_currentLeader = ("§cis the current King of the Ladder");
    private String kotl_leaderLeft = ("§cThe King of the Ladder has left the game");
    private String kotl_noLongerLeader = ("§cYou are no longer the King of the Ladder");
    private String kotl_died = ("§cThe King of the Ladder died!");

    private String pvp = ("§0[§b§lPVP§0]§a");
    private String pvp_enabled = ("§cPvP Enabled");
    private String pvp_disabled = ("§aPvP Disabled");
    private String pvp_cantHit = ("§c You do not have PvP enabled!");
    private String snowball_onFloor = ("{PREFIX} §cYou must be on the floor to throw that.");

    private String parkour = ("§0[§b§lParkour§0]§a");
    private String parkour_start = ("{PARKOUR} §aYou have started parkour. Good luck! To quit, do /parkour quit");
    private String parkour_stop = ("{PARKOUR} §cYou have quit parkour. Better luck next time.");
    private String parkour_finish = ("{PARKOUR} §aCongratulations, you have finished parkour!");
    private String parkour_notStarted = ("{PARKOUR} §cYou have not started parkour yet.");
    private String parkour_chp = ("§a You have §creached §acheckpoint: §6");
    private String parkour_error = ("{PARKOUR} §cThere has been an error. Perhaps you did not go through all the checkpoints?");
    private String parkour_teleport_start = ("§cYou were teleported to the start.");
    private String parkour_teleport = ("§aYou were §cteleported §ato checkpoint: §6");
    private String parkour_left_reload = ("{PARKOUR} §cDue to a server reload, you have quit parkour.");
    private String parkour_noPermission = ("{PARKOUR} §cYou do not have permission to start parkour.");
    private String parkour_alreadyCompleted = ("{PARKOUR} §cYou have already completed the parkour today!");
    private String parkour_useParkourToReturn = ("{PARKOUR} §aYou can use §6/parkour §ato teleport to your last checkpoint");
    private String parkour_isInParkour = ("§aYou are in the Parkour!");

    private String maze = ("§0[§b§lMaze§0]§a");
    private String maze_start = ("{MAZE} §aYou have started the maze. Good luck! To quit, do /maze quit");
    private String maze_stop = ("{MAZE} §cYou have left the maze. Better luck next time.");
    private String maze_left_reload = ("{MAZE} §cDue to a server reload, you have quit maze.");
    private String maze_finish = ("{MAZE} §aCongratulations, you have completed the maze!");
    private String maze_notStarted = ("{MAZE} §cThere has been an error. Perhaps you did not start the maze?");
    private String maze_loaded = ("{MAZE} §aYou have loaded a new maze: ");
    private String maze_loaded_done = ("{MAZE} §aThe maze has been loaded successfully");
    private String maze_nameTaken = ("{MAZE} §cThat maze name is already taken. Try a different one.");
    private String maze_givenSelector = ("{MAZE} §cYou have been given the Maze creator selection tool.");
    private String maze_selectArea = ("{MAZE} §cYour selection area consists of the default area. Please reselect your area for: ");
    private String maze_location1set = ("{MAZE} §aMaze location 1 has been set at: ");
    private String maze_location2set = ("{MAZE} §aMaze location 2 has been set at: ");
    private String maze_mustSelectBlock = ("{MAZE} §cYou must click a block.");
    private String maze_thereWasErrorFixed = ("{MAZE} §cThere was an error, I tried fixing it. Try resetting your area again.");
    private String maze_created = ("{MAZE} §aYou have created a new maze. This can be found at: /root/Hub/plugins/PlasmaHub/mazes.yml with the name of: ");
    private String maze_wrongLocation = ("{MAZE} §cYou are standing in the wrong location to be using this command. Please go to the start of the maze and try again.");
    private String maze_noPermission = ("{MAZE} §cYou do not have permission to start the maze.");
    private String maze_default = ("Loaded maze has been set to the default: LoadedMaze: Maze_1");
    private String maze_onlySurvival = ("{MAZE} §cYou can only play this game in Survival mode!");
    private String maze_alreadyCompleted = ("{MAZE} §cYou have already completed the maze! Try again later.");
    private String maze_peopleInGame = ("{MAZE} §cYou can not use this command whilst people are in the maze.");
    private String maze_youAreInMaze = ("§aYou are in the Maze!");

    private String minigames = ("§0[§b§lMinigames§0]§a");
    private String minigame_bypass = ("{MINIGAMES} §cYou have bypassed the cooldown period for this game.");
    private String minigame_onceADay = ("{MINIGAMES} §cMinigames can only be completed once each day GMT-5 time (Toronto, OH). Current Time: ");


    private String stats = ("§0[§6§lStats§0]§a");
    private String stats_kills = ("§aKills: §c");
    private String stats_deaths = ("§aDeaths: §c");
    private String stats_joins = ("§aJoins: §c");
    private String stats_gems = ("§aGems: §c");
    private String stats_timeOnline = ("§aTime Online: §c");
    private String stats_1 = ("§6 -=[§a");
    private String stats_2 = ("§6]=-§r");

    private String pvp_killedBy = ("§c killed you!");
    private String pvp_youKilled = ("§c You killed ");

    private String chair = ("§0[§b§lChair§0]§a");
    private String chair_sitting = ("{CHAIR} §aYou are now sitting!");
    private String chair_standing = ("{CHAIR} §cYou are no longer sitting!");

    private String pfm_addMoneyDisabled = ("Add player money is disabled ");
    private String pfm_addStrengthDisabled = ("Add player strength is disabled ");
    private String pfm_pvpLevelUp = ("{PVP} LEVEL UP!!");
    private String pfm_pvpNewLevel = ("{PVP} You are now level: ");
    private String pfm_pvp1o4left = ("{PVP} §aYou are One Quarter (1/4th) of the way to level 100!");
    private String pfm_pvp2o4left = ("{PVP} §aYou are Half Way way to level 100!");
    private String pfm_pvp3o4left = ("{PVP} §aYou are Three Quarter (3/4th) of to level 100!");
    private String pfm_pvpFinalLevel = ("{PVP} §aYou have reached the final level! Congratulations!");
    private String pfm_broadcastNewLevel = ("§a has reached level 100!");

    private String ownerPrefix = ("§6[§4Owner§6]§c");
    private String sparkPrefix = ("§a[§bSpark§a]§7");

    private String reloadingConfig = ("{PREFIX} §aThe configuration files are reloading.");
    private String reloadedConfig = ("{PREFIX} §aThe configuration files have been reloaded.");
    private String errorGettingMessageFix = ("Could not find a message. Attempting to fix: ");
    private String errorLoadingMessageFix = ("Could not load a message. Attempting to fix: ");
    private String loadedMessage = ("Message loaded: ");
    private String errorGettingMessage = ("§4There was an error getting the message. Please contact an Owner.");

    private String messagesLoaded = ("§aTotal general messages loaded: ");
    private String autoBroadcastIntervalDefault = ("AutoBroadcast Interval Time was set to the default time: ");
    private String errorGettingAutoBroadcastMessage = ("§4There was an error getting an AutoBroadcast message. Please contact an Owner.");
    private String autoBroadcastPrefix = ("§0[§9§lPlasma§0] §r");
    private String autoBroadcastTimeSet = ("§aThe Auto Broadcast time was set to: ");
    private String autoBroadcastMessagesLoaded = ("§aTotal AutoBroadcast messages loaded: ");
    private String autoBroadcastMessages = ("§aAutoBroadcast message loaded: ");
    private String newMazeAmount = ("{MAZE}§aYou added another maze, meaning there are now: ");
    private String mustBeNumber = ("{PREFIX}§cYou must enter a number, not a letter.");
    private String numberTooHigh = ("{PREFIX}§cThe number you entered is too high. Try again.");
    private String mazeAmount = ("{MAZE}§cThere is a total of: ");
    private String maze_loaded_set = ("{MAZE}§aYou have set a new loaded maze: ");
    private String stats_pvpLevel = ("§aPVP Level: §c");
    private String stats_expLevel = ("§aExp Level: §c");
    private String pluginDisabled = ("{SERVER}§cThe plugin is currently disabled.");
    private String clearingEntities = ("{SERVER}§aClearing entities in the world: ");
    private String barToggled = ("{PREFIX} §bYou have toggled your bar: ");
    private String PlasmaNetwork = ("§7-=[ §2§lPlasmaNetwork §7]=-");
    private String stats_killstreak = ("§aKillstreak: ");
    private String stats_longestKillstreak = ("§aLongest Killstreak: ");
    private String maze_bugged = ("{MAZE} §aThis maze is §criddled§a with bugs. You may see §csomething§a, you may see §cnothing§a, you may even see things with a §cbit of nausea§a. Good luck.");
    private String maze_loaded_bugged = ("{MAZE} §aThe maze you just loaded is §criddled§a with bugs.");
    private String maze_cannotBeZero = ("{MAZE} §cYou cannot use the number 0 here. 1 and above.");
    private String maze_mazeAmount = ("{MAZE} §aThere are a total of: ");
    private String rewards = ("§0[§b§lRewards§0]§a ");
    private String dailyRewardsClaimed = ("{REWARDS} §aYou claimed your reward for the: §c{DATE}");
    private String dailyRewardUnclaimed = ("{REWARDS} §aYou can type /dr to claim your daily reward!");
    private String dailyRewardsAlreadyClaimed = ("{REWARDS} §cYou can only claim this once a day. Come back later. Current time: ");
    private String welcome = ("§aWelcome to §2§lPlasmaNetwork!§a");
    private String welcomeBack = ("§aWelcome back to §2§lPlasmaNetwork!§a");
    private String welcomeNew_1 = ("§2§lPlasmaNetwork§a welcomes ");
    private String welcomeNew_2 = ("§a to the server!");
    private String rewards_gemsAdded = ("{REWARDS} §aYou earned more gems! Added to your account: §c");
    private String pingPrefix = ("§0[§9Ping§0] §r");

    // prefix: text
    public String getMessage(String message) {
        for (int i = 0; i < Main.messages.size(); ) {
            for (String thePrefix : Main.messages.keySet()) {
                if (thePrefix.equalsIgnoreCase(message)) {
                    String s = Main.messages.get(message);
                    s = ChatColor.translateAlternateColorCodes('§', s);
                    if (s.contains("{PREFIX}")) {
                        if (Main.messages.get("prefix") == null) {
                            Main.messages.put("prefix", prefix);
                        }
                        s = s.replace("{PREFIX}", Main.messages.get("prefix"));
                    }
                    if (s.contains("{SERVER}")) {
                        if (Main.messages.get("server") == null) {
                            Main.messages.put("server", server);
                        }
                        s = s.replace("{SERVER}", Main.messages.get("server"));
                    }
                    if (s.contains("{PVP}")) {
                        if (Main.messages.get("pvp") == null) {
                            Main.messages.put("pvp", pvp);
                        }
                        s = s.replace("{PVP}", Main.messages.get("pvp"));
                    }
                    if (s.contains("{KOTL}")) {
                        if (Main.messages.get("kotl") == null) {
                            Main.messages.put("kotl", kotl);
                        }
                        s = s.replace("{KOTL}", Main.messages.get("kotl"));
                    }
                    if (s.contains("{PARKOUR}")) {
                        if (Main.messages.get("parkour") == null) {
                            Main.messages.put("parkour", parkour);
                        }
                        s = s.replace("{PARKOUR}", Main.messages.get("parkour"));
                    }
                    if (s.contains("{MAZE}")) {
                        if (Main.messages.get("maze") == null) {
                            Main.messages.put("maze", maze);
                        }
                        s = s.replace("{MAZE}", Main.messages.get("maze"));
                    }
                    if (s.contains("{MINIGAMES}")) {
                        if (Main.messages.get("minigames") == null) {
                            Main.messages.put("minigames", minigames);
                        }
                        s = s.replace("{MINIGAMES}", Main.messages.get("minigames"));
                    }
                    if (s.contains("{REWARDS}")) {
                        if (Main.messages.get("rewards") == null) {
                            Main.messages.put("rewards", rewards);
                        }
                        s = s.replace("{REWARDS}", Main.messages.get("rewards"));
                    }
                    if (s.contains("{DATE}")) {
                        String thedate = new SimpleDateFormat("dd-MM").format(new Date());
                        s = s.replace("{DATE}", thedate);
                    }
                    return s;
                } else {
                    if (Main.messages.get(message) == null) {
                        if (Main.messagesConfig.getConfig().getString("Messages." + message) == null) {
                            Main.messagesConfig.getConfig().set("Messages." + message, message);
                            Main.messagesConfig.saveConfig();
                            Main.log("There was an error getting the message: " + message + ". Please check.");
                            Bukkit.broadcastMessage(ChatColor.RED + "There was an error getting the message: " + message + ". Please contact an owner.");
//                            if (Main.messagesConfig.getConfig().getString("Messages." + message) == null) {
//                                if (setDefault(message)) {
//                                    Main.messagesConfig.saveConfig();
//                                    Bukkit.broadcastMessage(ChatColor.RED + "Please reload config. /plasma reload, " + message + " has to be fixed.");
//                                } else {
//                                    return errorGettingMessage;
//                                }
//                            } else {
//                                if (setDefault(message)) {
//                                    Main.messagesConfig.saveConfig();
//                                    Bukkit.broadcastMessage(ChatColor.RED + "Please reload config. /plasma reload, " + message + " has to be fixed."); // make it so it only sends to people with permission
//                                } else {
//                                    Main.messagesConfig.saveConfig();
//                                    Main.log(ChatColor.RED + "There was an error getting the message: " + message + ". This will need to be fixed.");
//                                    Bukkit.broadcastMessage(ChatColor.RED + "There was an error getting the message: " + message + ". Please contact an owner.");
//                                    message = "Fix: " + message;
//                                    return message;
//                                }
                        }
                    }
                }
            }
            i++;
        }
        return errorGettingMessage;
    }

    private PermissionsManager perms = PermissionsManager.getInstance();

    public void loadMessages() {
        Main.log("Loading messages");
        Main.log("Messages to load: " + Main.messagesConfig.getConfig().getConfigurationSection("Messages").getKeys(true).size());
        for (int i = 0; i < Main.messagesConfig.getConfig().getConfigurationSection("Messages").getKeys(false).size(); ) {
            for (final String s : Main.messagesConfig.getConfig().getConfigurationSection("Messages").getKeys(false)) {
                String sX = Main.messagesConfig.getConfig().getString("Messages." + s);
                if (sX.contains("\'")) {
                    if (Bukkit.getOnlinePlayers().size() != 0) {
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission(perms.plasma_command_reload)) {
                                p.sendMessage(ChatColor.RED + "Can not load: " + s + " because there is an illegal character in the messages.yml file.");
                            }
                        }
                        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new BukkitRunnable() {
                            @Override
                            public void run() {
                                Main.log("Can not load: " + s + " because there is an illegal character in the messages.yml file.");
                            }
                        }, 20L);
                    }
                    continue;
                }
                Main.messages.put(s, Main.messagesConfig.getConfig().getString("Messages." + s));
                Main.log("Loaded: " + s + ": " + Main.messagesConfig.getConfig().getString("Messages." + s));
            }
            if (Main.messages.size() < Main.messagesConfig.getConfig().getConfigurationSection("Messages").getKeys(true).size()) {
                i++;
            } else {
                Main.log("Completed loading.");
                Main.log("Loaded: " + Main.messages.size() + " messages.");
                return;
            }
        }

    }

    public void checkDefaults() {
        if (Main.messagesConfig.getConfig().get("Messages") == null) {
            Main.messagesConfig.getConfig().set("Messages.prefix", prefix);
            Main.messagesConfig.getConfig().set("Messages.server", server);
            Main.messagesConfig.getConfig().set("Messages.mustBePlayer", mustBePlayer);
            Main.messagesConfig.getConfig().set("Messages.noPermission", noPermission);
            Main.messagesConfig.getConfig().set("Messages.incorrectArguments", incorrectArguments);
            Main.messagesConfig.getConfig().set("Messages.error", error);
            Main.messagesConfig.getConfig().set("Messages.spawnNotSet", spawnNotSet);
            Main.messagesConfig.getConfig().set("Messages.spawnNotSetAllowed", spawnNotSetAllowed);
            Main.messagesConfig.getConfig().set("Messages.spawnSet", spawnSet);
            Main.messagesConfig.getConfig().set("Messages.inventoryResetReload", inventoryResetReload);
            Main.messagesConfig.getConfig().set("Messages.alreadyThatGamemode", alreadyThatGamemode);
            Main.messagesConfig.getConfig().set("Messages.changedGamemode", changedGamemode);
            Main.messagesConfig.getConfig().set("Messages.currentlyDisabled", currentlyDisabled);
            Main.messagesConfig.getConfig().set("Messages.join", join);
            Main.messagesConfig.getConfig().set("Messages.quit", quit);
            Main.messagesConfig.getConfig().set("Messages.reloadIn", reloadIn);
            Main.messagesConfig.getConfig().set("Messages.reloadCancelled", reloadCancelled);
            Main.messagesConfig.getConfig().set("Messages.reloadAwaitingConfirmation", reloadAwaitingConfirmation);
            Main.messagesConfig.getConfig().set("Messages.serverReloaded", serverReloaded);
            Main.messagesConfig.getConfig().set("Messages.pfLoaded", pfLoaded);
            Main.messagesConfig.getConfig().set("Messages.sendToServer", sendToServer);
            Main.messagesConfig.getConfig().set("Messages.inventoryResetManual", inventoryResetManual);
            Main.messagesConfig.getConfig().set("Messages.resetAllInventory", resetAllInventory);
            Main.messagesConfig.getConfig().set("Messages.noPermissionServer", noPermissionServer);
            Main.messagesConfig.getConfig().set("Messages.cantUseHere", cantUseHere);
            Main.messagesConfig.getConfig().set("Messages.helpHeader", helpHeader);
            Main.messagesConfig.getConfig().set("Messages.helpFooter1", helpFooter1);
            Main.messagesConfig.getConfig().set("Messages.helpFooter2", helpFooter2);
            Main.messagesConfig.getConfig().set("Messages.helpEmpty", helpEmpty);
            Main.messagesConfig.getConfig().set("Messages.help1", help1);
            Main.messagesConfig.getConfig().set("Messages.help2", help2);
            Main.messagesConfig.getConfig().set("Messages.help3", help3);
            Main.messagesConfig.getConfig().set("Messages.kotl", kotl);
            Main.messagesConfig.getConfig().set("Messages.kotl_enter", kotl_enter);
            Main.messagesConfig.getConfig().set("Messages.kotl_leave", kotl_leave);
            Main.messagesConfig.getConfig().set("Messages.kotl_newLeader", kotl_newLeader);
            Main.messagesConfig.getConfig().set("Messages.kotl_youAreNewLeader", kotl_youAreNewLeader);
            Main.messagesConfig.getConfig().set("Messages.kotl_currentLeader", kotl_currentLeader);
            Main.messagesConfig.getConfig().set("Messages.kotl_leaderLeft", kotl_leaderLeft);
            Main.messagesConfig.getConfig().set("Messages.kotl_noLongerLeader", kotl_noLongerLeader);
            Main.messagesConfig.getConfig().set("Messages.kotl_died", kotl_died);
            Main.messagesConfig.getConfig().set("Messages.pvp", pvp);
            Main.messagesConfig.getConfig().set("Messages.pvp_enabled", pvp_enabled);
            Main.messagesConfig.getConfig().set("Messages.pvp_disabled", pvp_disabled);
            Main.messagesConfig.getConfig().set("Messages.pvp_cantHit", pvp_cantHit);
            Main.messagesConfig.getConfig().set("Messages.pvp_killedBy", pvp_killedBy);
            Main.messagesConfig.getConfig().set("Messages.pvp_youKilled", pvp_youKilled);
            Main.messagesConfig.getConfig().set("Messages.snowball_onFloor", snowball_onFloor);
            Main.messagesConfig.getConfig().set("Messages.parkour", parkour);
            Main.messagesConfig.getConfig().set("Messages.parkour_start", parkour_start);
            Main.messagesConfig.getConfig().set("Messages.parkour_stop", parkour_stop);
            Main.messagesConfig.getConfig().set("Messages.parkour_finish", parkour_finish);
            Main.messagesConfig.getConfig().set("Messages.parkour_notStarted", parkour_notStarted);
            Main.messagesConfig.getConfig().set("Messages.parkour_chp", parkour_chp);
            Main.messagesConfig.getConfig().set("Messages.parkour_error", parkour_error);
            Main.messagesConfig.getConfig().set("Messages.parkour_teleport_start", parkour_teleport_start);
            Main.messagesConfig.getConfig().set("Messages.parkour_teleport", parkour_teleport);
            Main.messagesConfig.getConfig().set("Messages.parkour_left_reload", parkour_left_reload);
            Main.messagesConfig.getConfig().set("Messages.parkour_noPermission", parkour_noPermission);
            Main.messagesConfig.getConfig().set("Messages.parkour_alreadyCompleted", parkour_alreadyCompleted);
            Main.messagesConfig.getConfig().set("Messages.parkour_useParkourToReturn", parkour_useParkourToReturn);
            Main.messagesConfig.getConfig().set("Messages.parkour_isInParkour", parkour_isInParkour);
            Main.messagesConfig.getConfig().set("Messages.maze", maze);
            Main.messagesConfig.getConfig().set("Messages.maze_start", maze_start);
            Main.messagesConfig.getConfig().set("Messages.maze_stop", maze_stop);
            Main.messagesConfig.getConfig().set("Messages.maze_left_reload", maze_left_reload);
            Main.messagesConfig.getConfig().set("Messages.maze_finish", maze_finish);
            Main.messagesConfig.getConfig().set("Messages.maze_notStarted", maze_notStarted);
            Main.messagesConfig.getConfig().set("Messages.maze_loaded", maze_loaded);
            Main.messagesConfig.getConfig().set("Messages.maze_loaded_done", maze_loaded_done);
            Main.messagesConfig.getConfig().set("Messages.maze_nameTaken", maze_nameTaken);
            Main.messagesConfig.getConfig().set("Messages.maze_givenSelector", maze_givenSelector);
            Main.messagesConfig.getConfig().set("Messages.maze_selectArea", maze_selectArea);
            Main.messagesConfig.getConfig().set("Messages.maze_location1set", maze_location1set);
            Main.messagesConfig.getConfig().set("Messages.maze_location2set", maze_location2set);
            Main.messagesConfig.getConfig().set("Messages.maze_mustSelectBlock", maze_mustSelectBlock);
            Main.messagesConfig.getConfig().set("Messages.maze_thereWasErrorFixed", maze_thereWasErrorFixed);
            Main.messagesConfig.getConfig().set("Messages.maze_created", maze_created);
            Main.messagesConfig.getConfig().set("Messages.maze_wrongLocation", maze_wrongLocation);
            Main.messagesConfig.getConfig().set("Messages.maze_noPermission", maze_noPermission);
            Main.messagesConfig.getConfig().set("Messages.maze_default", maze_default);
            Main.messagesConfig.getConfig().set("Messages.maze_onlySurvival", maze_onlySurvival);
            Main.messagesConfig.getConfig().set("Messages.maze_alreadyCompleted", maze_alreadyCompleted);
            Main.messagesConfig.getConfig().set("Messages.maze_peopleInGame", maze_peopleInGame);
            Main.messagesConfig.getConfig().set("Messages.maze_youAreInMaze", maze_youAreInMaze);
            Main.messagesConfig.getConfig().set("Messages.minigames", minigames);
            Main.messagesConfig.getConfig().set("Messages.minigame_bypass", minigame_bypass);
            Main.messagesConfig.getConfig().set("Messages.minigame_onceADay", minigame_onceADay);
            Main.messagesConfig.getConfig().set("Messages.stats", stats);
            Main.messagesConfig.getConfig().set("Messages.stats_kills", stats_kills);
            Main.messagesConfig.getConfig().set("Messages.stats_deaths", stats_deaths);
            Main.messagesConfig.getConfig().set("Messages.stats_joins", stats_joins);
            Main.messagesConfig.getConfig().set("Messages.stats_gems", stats_gems);
            Main.messagesConfig.getConfig().set("Messages.stats_timeOnline", stats_timeOnline);
            Main.messagesConfig.getConfig().set("Messages.stats_1", stats_1);
            Main.messagesConfig.getConfig().set("Messages.stats_2", stats_2);
            Main.messagesConfig.getConfig().set("Messages.chair", chair);
            Main.messagesConfig.getConfig().set("Messages.chair_sitting", chair_sitting);
            Main.messagesConfig.getConfig().set("Messages.chair_standing", chair_standing);
            Main.messagesConfig.getConfig().set("Messages.pfm_addMoneyDisabled", pfm_addMoneyDisabled);
            Main.messagesConfig.getConfig().set("Messages.pfm_addStrengthDisabled", pfm_addStrengthDisabled);
            Main.messagesConfig.getConfig().set("Messages.pfm_pvpLevelUp", pfm_pvpLevelUp);
            Main.messagesConfig.getConfig().set("Messages.pfm_pvpNewLevel", pfm_pvpNewLevel);
            Main.messagesConfig.getConfig().set("Messages.pfm_pvp1o4left", pfm_pvp1o4left);
            Main.messagesConfig.getConfig().set("pfm_pvp2o4left", pfm_pvp2o4left);
            Main.messagesConfig.getConfig().set("Messages.pfm_pvp3o4left", pfm_pvp3o4left);
            Main.messagesConfig.getConfig().set("Messages.pfm_pvpFinalLevel", pfm_pvpFinalLevel);
            Main.messagesConfig.getConfig().set("Messages.pfm_broadcastNewLevel", pfm_broadcastNewLevel);
            Main.messagesConfig.getConfig().set("Messages.ownerPrefix", ownerPrefix);
            Main.messagesConfig.getConfig().set("Messages.sparkPrefix", sparkPrefix);
            Main.messagesConfig.getConfig().set("Messages.reloadingConfig", reloadingConfig);
            Main.messagesConfig.getConfig().set("Messages.reloadedConfig", reloadedConfig);
            Main.messagesConfig.getConfig().set("Messages.errorGettingMessageFix", errorGettingMessageFix);
            Main.messagesConfig.getConfig().set("Messages.errorLoadingMessageFix", errorLoadingMessageFix);
            Main.messagesConfig.getConfig().set("Messages.loadedMessage", loadedMessage);
            Main.messagesConfig.getConfig().set("Messages.errorGettingMessage", errorGettingMessage);
            Main.messagesConfig.getConfig().set("Messages.messagesLoaded", messagesLoaded);
            Main.messagesConfig.getConfig().set("Messages.autoBroadcastIntervalDefault", autoBroadcastIntervalDefault);
            Main.messagesConfig.getConfig().set("Messages.errorGettingAutoBroadcastMessage", errorGettingAutoBroadcastMessage);
            Main.messagesConfig.getConfig().set("Messages.autoBroadcastPrefix", autoBroadcastPrefix);
            Main.messagesConfig.getConfig().set("Messages.autoBroadcastTimeSet", autoBroadcastTimeSet);
            Main.messagesConfig.getConfig().set("Messages.autoBroadcastMessagesLoaded", autoBroadcastMessagesLoaded);
            Main.messagesConfig.getConfig().set("Messages.autoBroadcastMessages", autoBroadcastMessages);
            Main.messagesConfig.getConfig().set("Messages.newMazeAmount", newMazeAmount);
            Main.messagesConfig.getConfig().set("Messages.mustBeNumber", mustBeNumber);
            Main.messagesConfig.getConfig().set("Messages.numberTooHigh", numberTooHigh);
            Main.messagesConfig.getConfig().set("Messages.mazeAmount", mazeAmount);
            Main.messagesConfig.getConfig().set("Messages.maze_loaded_set", maze_loaded_set);
            Main.messagesConfig.getConfig().set("Messages.pvpLevel", stats_pvpLevel);
            Main.messagesConfig.getConfig().set("Messages.expLevel", stats_expLevel);
            Main.messagesConfig.getConfig().set("Messages.pluginDisabled", pluginDisabled);
            Main.messagesConfig.getConfig().set("Messages.clearingEntities", clearingEntities);
            Main.messagesConfig.getConfig().set("Messages.barToggled", barToggled);
            Main.messagesConfig.getConfig().set("Messages.PlasmaNetwork", PlasmaNetwork);
            Main.messagesConfig.getConfig().set("Messages.stats_killstreak", stats_killstreak);
            Main.messagesConfig.getConfig().set("Messages.stats_longestKillstreak", stats_longestKillstreak);
            Main.messagesConfig.getConfig().set("Messages.maze_bugged", maze_bugged);
            Main.messagesConfig.getConfig().set("Messages.maze_loaded_bugged", maze_loaded_bugged);
            Main.messagesConfig.getConfig().set("Messages.maze_cannotBeZero", maze_cannotBeZero);
            Main.messagesConfig.getConfig().set("Messages.maze_mazeAmount", maze_mazeAmount);
            Main.messagesConfig.getConfig().set("Messages.rewards", rewards);
            Main.messagesConfig.getConfig().set("Messages.dailyRewardsClaimed", dailyRewardsClaimed);
            Main.messagesConfig.getConfig().set("Messages.dailyRewardUnclaimed", dailyRewardUnclaimed);
            Main.messagesConfig.getConfig().set("Messages.dailyRewardsAlreadyClaimed", dailyRewardsAlreadyClaimed);
            Main.messagesConfig.getConfig().set("Messages.welcome", welcome);
            Main.messagesConfig.getConfig().set("Messages.welcomeBack", welcomeBack);
            Main.messagesConfig.getConfig().set("Messages.welcomeNew_1", welcomeNew_1);
            Main.messagesConfig.getConfig().set("Messages.welcomeNew_2", welcomeNew_2);
            Main.messagesConfig.getConfig().set("Messages.rewards_gemsAdded", rewards_gemsAdded);
            Main.messagesConfig.getConfig().set("Messages.pingPrefix", pingPrefix);
            Main.messagesConfig.saveConfig();
        }
        for (String s : Main.messagesConfig.getConfig().getStringList("Messages")) {
            if (Main.messagesConfig.getConfig().get("Messages." + s) == null) {
                if (s.equalsIgnoreCase("prefix")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, prefix);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, prefix);
                }
                if (s.equalsIgnoreCase("server")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, server);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, server);

                }
                if (s.equalsIgnoreCase("mustBePlayer")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, mustBePlayer);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, mustBePlayer);

                }
                if (s.equalsIgnoreCase("noPermission")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, noPermission);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, noPermission);

                }
                if (s.equalsIgnoreCase("incorrectArguments")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, incorrectArguments);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, incorrectArguments);

                }
                if (s.equalsIgnoreCase("error")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, error);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, error);

                }
                if (s.equalsIgnoreCase("spawnNotSet")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, spawnNotSet);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, spawnNotSet);

                }
                if (s.equalsIgnoreCase("spawnNotSetAllowed")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, spawnNotSetAllowed);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, spawnNotSetAllowed);

                }
                if (s.equalsIgnoreCase("spawnSet")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, spawnSet);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, spawnSet);

                }
                if (s.equalsIgnoreCase("inventoryResetReload")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, inventoryResetReload);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, inventoryResetReload);

                }
                if (s.equalsIgnoreCase("alreadyThatGamemode")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, alreadyThatGamemode);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, alreadyThatGamemode);

                }
                if (s.equalsIgnoreCase("changedGamemode")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, changedGamemode);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, changedGamemode);

                }
                if (s.equalsIgnoreCase("currentlyDisabled")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, currentlyDisabled);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, currentlyDisabled);

                }
                if (s.equalsIgnoreCase("join")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, join);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, join);

                }
                if (s.equalsIgnoreCase("quit")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, quit);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, quit);

                }
                if (s.equalsIgnoreCase("reloadIn")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, reloadIn);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, reloadIn);

                }
                if (s.equalsIgnoreCase("reloadCancelled")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, reloadCancelled);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, reloadCancelled);

                }
                if (s.equalsIgnoreCase("reloadAwaitingConfirmation")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, reloadAwaitingConfirmation);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, reloadAwaitingConfirmation);

                }
                if (s.equalsIgnoreCase("serverReloaded")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, serverReloaded);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, serverReloaded);

                }
                if (s.equalsIgnoreCase("pfLoaded")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pfLoaded);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pfLoaded);

                }
                if (s.equalsIgnoreCase("sendToServer")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, sendToServer);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, sendToServer);

                }
                if (s.equalsIgnoreCase("inventoryResetManual")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, inventoryResetManual);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, inventoryResetManual);

                }
                if (s.equalsIgnoreCase("resetAllInventory")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, resetAllInventory);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, resetAllInventory);

                }
                if (s.equalsIgnoreCase("noPermissionServer")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, noPermissionServer);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, noPermissionServer);

                }
                if (s.equalsIgnoreCase("cantUseHere")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, cantUseHere);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, cantUseHere);

                }
                if (s.equalsIgnoreCase("helpHeader")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, helpHeader);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, helpHeader);

                }
                if (s.equalsIgnoreCase("helpFooter1")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, helpFooter1);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, helpFooter1);

                }
                if (s.equalsIgnoreCase("helpFooter2")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, helpFooter2);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, helpFooter2);

                }
                if (s.equalsIgnoreCase("helpEmpty")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, helpEmpty);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, helpEmpty);

                }
                if (s.equalsIgnoreCase("help1")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, help1);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, help1);

                }
                if (s.equalsIgnoreCase("help2")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, help2);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, help2);

                }
                if (s.equalsIgnoreCase("help3")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, help3);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, help3);

                }
                if (s.equalsIgnoreCase("kotl")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, kotl);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, kotl);

                }
                if (s.equalsIgnoreCase("kotl_enter")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, kotl_enter);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, kotl_enter);

                }
                if (s.equalsIgnoreCase("kotl_leave")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, kotl_leave);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, kotl_leave);

                }
                if (s.equalsIgnoreCase("kotl_newLeader")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, kotl_newLeader);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, kotl_newLeader);

                }
                if (s.equalsIgnoreCase("kotl_youAreNewLeader")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, kotl_youAreNewLeader);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, kotl_youAreNewLeader);

                }
                if (s.equalsIgnoreCase("kotl_currentLeader")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, kotl_currentLeader);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, kotl_currentLeader);

                }
                if (s.equalsIgnoreCase("kotl_leaderLeft")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, kotl_leaderLeft);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, kotl_leaderLeft);

                }
                if (s.equalsIgnoreCase("kotl_noLongerLeader")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, kotl_noLongerLeader);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, kotl_noLongerLeader);

                }
                if (s.equalsIgnoreCase("kotl_died")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, kotl_died);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, kotl_died);

                }
                if (s.equalsIgnoreCase("pvp")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pvp);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pvp);

                }
                if (s.equalsIgnoreCase("pvp_enabled")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pvp_enabled);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pvp_enabled);

                }
                if (s.equalsIgnoreCase("pvp_disabled")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pvp_disabled);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pvp_disabled);

                }
                if (s.equalsIgnoreCase("pvp_cantHit")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pvp_cantHit);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pvp_cantHit);

                }
                if (s.equalsIgnoreCase("pvp_killedBy")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pvp_killedBy);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pvp_killedBy);

                }
                if (s.equalsIgnoreCase("pvp_youKilled")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pvp_youKilled);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pvp_youKilled);

                }
                if (s.equalsIgnoreCase("snowball_onFloor")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, snowball_onFloor);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, snowball_onFloor);

                }
                if (s.equalsIgnoreCase("parkour")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, parkour);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, parkour);

                }
                if (s.equalsIgnoreCase("parkour_start")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, parkour_start);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, parkour_start);

                }
                if (s.equalsIgnoreCase("parkour_stop")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, parkour_stop);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, parkour_stop);

                }
                if (s.equalsIgnoreCase("parkour_finish")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, parkour_finish);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, parkour_finish);

                }
                if (s.equalsIgnoreCase("parkour_notStarted")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, parkour_notStarted);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, parkour_notStarted);

                }
                if (s.equalsIgnoreCase("parkour_chp")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, parkour_chp);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, parkour_chp);

                }
                if (s.equalsIgnoreCase("parkour_error")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, parkour_error);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, parkour_error);

                }
                if (s.equalsIgnoreCase("parkour_teleport_start")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, parkour_teleport_start);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, parkour_teleport_start);

                }
                if (s.equalsIgnoreCase("parkour_teleport")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, parkour_teleport);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, parkour_teleport);

                }
                if (s.equalsIgnoreCase("parkour_left_reload")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, parkour_left_reload);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, parkour_left_reload);

                }
                if (s.equalsIgnoreCase("parkour_noPermission")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, parkour_noPermission);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, parkour_noPermission);

                }
                if (s.equalsIgnoreCase("parkour_alreadyCompleted")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, parkour_alreadyCompleted);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, parkour_alreadyCompleted);

                }
                if (s.equalsIgnoreCase("parkour_useParkourToReturn")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, parkour_useParkourToReturn);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, parkour_useParkourToReturn);

                }
                if (s.equalsIgnoreCase("parkour_isInParkour")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, parkour_isInParkour);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, parkour_isInParkour);

                }
                if (s.equalsIgnoreCase("maze")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze);

                }
                if (s.equalsIgnoreCase("maze_start")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_start);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_start);

                }
                if (s.equalsIgnoreCase("maze_stop")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_stop);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_stop);

                }
                if (s.equalsIgnoreCase("maze_left_reload")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_left_reload);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_left_reload);

                }
                if (s.equalsIgnoreCase("maze_finish")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_finish);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_finish);

                }
                if (s.equalsIgnoreCase("maze_notStarted")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_notStarted);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_notStarted);

                }
                if (s.equalsIgnoreCase("maze_loaded")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_loaded);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_loaded);

                }
                if (s.equalsIgnoreCase("maze_loaded_done")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_loaded_done);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_loaded_done);

                }
                if (s.equalsIgnoreCase("maze_nameTaken")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_nameTaken);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_nameTaken);

                }
                if (s.equalsIgnoreCase("maze_givenSelector")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_givenSelector);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_givenSelector);

                }
                if (s.equalsIgnoreCase("maze_selectArea")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_selectArea);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_selectArea);

                }
                if (s.equalsIgnoreCase("maze_location1set")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_location1set);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_location1set);

                }
                if (s.equalsIgnoreCase("maze_location2set")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_location2set);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_location2set);

                }
                if (s.equalsIgnoreCase("maze_mustSelectBlock")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_mustSelectBlock);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_mustSelectBlock);

                }
                if (s.equalsIgnoreCase("maze_thereWasError")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_thereWasErrorFixed);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_thereWasErrorFixed);

                }
                if (s.equalsIgnoreCase("maze_created")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_created);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_created);

                }
                if (s.equalsIgnoreCase("maze_wrongLocation")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_wrongLocation);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_wrongLocation);

                }
                if (s.equalsIgnoreCase("maze_noPermission")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_noPermission);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_noPermission);

                }
                if (s.equalsIgnoreCase("maze_default")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_default);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_default);

                }
                if (s.equalsIgnoreCase("maze_onlySurvival")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_onlySurvival);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_onlySurvival);

                }
                if (s.equalsIgnoreCase("maze_alreadyCompleted")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_alreadyCompleted);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_alreadyCompleted);

                }
                if (s.equalsIgnoreCase("maze_peopleInGame")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_peopleInGame);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_peopleInGame);

                }
                if (s.equalsIgnoreCase("maze_youAreInMaze")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_youAreInMaze);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_youAreInMaze);

                }
                if (s.equalsIgnoreCase("minigames")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, minigames);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, minigames);

                }
                if (s.equalsIgnoreCase("minigames_bypass")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, minigame_bypass);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, minigame_bypass);

                }
                if (s.equalsIgnoreCase("minigame_onceADay")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, minigame_onceADay);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, minigame_onceADay);

                }
                if (s.equalsIgnoreCase("stats")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, stats);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, stats);

                }
                if (s.equalsIgnoreCase("stats_kills")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, stats_kills);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, stats_kills);

                }
                if (s.equalsIgnoreCase("stats_deaths")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, stats_deaths);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, stats_deaths);

                }
                if (s.equalsIgnoreCase("stats_joins")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, stats_joins);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, stats_joins);

                }
                if (s.equalsIgnoreCase("stats_gems")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, stats_gems);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, stats_gems);

                }
                if (s.equalsIgnoreCase("stats_timeOnline")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, stats_timeOnline);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, stats_timeOnline);

                }
                if (s.equalsIgnoreCase("stats_1")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, stats_1);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, stats_1);

                }
                if (s.equalsIgnoreCase("stats_2")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, stats_2);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, stats_2);

                }
                if (s.equalsIgnoreCase("chair")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, chair);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, chair);

                }
                if (s.equalsIgnoreCase("chair_sitting")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, chair_sitting);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, chair_sitting);

                }
                if (s.equalsIgnoreCase("chair_standing")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, chair_standing);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, chair_standing);

                }
                if (s.equalsIgnoreCase("pfm_addMoneyDisabled")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pfm_addMoneyDisabled);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pfm_addMoneyDisabled);

                }
                if (s.equalsIgnoreCase("pfm_addStrengthDisabled")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pfm_addStrengthDisabled);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pfm_addStrengthDisabled);

                }
                if (s.equalsIgnoreCase("pfm_pvpLevelUp")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pfm_pvpLevelUp);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pfm_pvpLevelUp);

                }
                if (s.equalsIgnoreCase("pfm_pvpNewLevel")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pfm_pvpNewLevel);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pfm_pvpNewLevel);

                }
                if (s.equalsIgnoreCase("pfm_pvp1o4left")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pfm_pvp1o4left);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pfm_pvp1o4left);

                }
                if (s.equalsIgnoreCase("pfm_pvp2o4left")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pfm_pvp2o4left);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pfm_pvp2o4left);

                }
                if (s.equalsIgnoreCase("pfm_pvp3o4left")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pfm_pvp3o4left);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pfm_pvp3o4left);

                }
                if (s.equalsIgnoreCase("pfm_pvpFinalLevel")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pfm_pvpFinalLevel);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pfm_pvpFinalLevel);

                }
                if (s.equalsIgnoreCase("pfm_broadcastNewLevel")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pfm_broadcastNewLevel);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pfm_broadcastNewLevel);

                }
                if (s.equalsIgnoreCase("ownerPrefix")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, ownerPrefix);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, ownerPrefix);

                }
                if (s.equalsIgnoreCase("sparkPrefix")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, sparkPrefix);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, sparkPrefix);

                }
                if (s.equalsIgnoreCase("reloadingConfig")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, reloadingConfig);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, reloadingConfig);

                }
                if (s.equalsIgnoreCase("reloadedConfig")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, reloadedConfig);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, reloadedConfig);

                }
                if (s.equalsIgnoreCase("errorGettingMessageFix")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, errorGettingMessageFix);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, errorGettingMessageFix);

                }
                if (s.equalsIgnoreCase("errorLoadingMessageFix")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, errorLoadingMessageFix);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, errorLoadingMessageFix);

                }
                if (s.equalsIgnoreCase("loadedMessage")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, loadedMessage);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, loadedMessage);

                }
                if (s.equalsIgnoreCase("errorGettingMessage")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, errorGettingMessage);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, errorGettingMessage);

                }
                if (s.equalsIgnoreCase("messagesLoaded")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, messagesLoaded);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, messagesLoaded);
                }
                if (s.equalsIgnoreCase("autoBroadcastIntervalDefault")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, autoBroadcastIntervalDefault);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, autoBroadcastIntervalDefault);

                }
                if (s.equalsIgnoreCase("errorGettingAutoBroadcastMessage")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, errorGettingAutoBroadcastMessage);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, errorGettingAutoBroadcastMessage);

                }
                if (s.equalsIgnoreCase("autoBroadcastPrefix")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, autoBroadcastPrefix);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, autoBroadcastPrefix);

                }
                if (s.equalsIgnoreCase("autoBroadcastTimeSet")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, autoBroadcastTimeSet);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, autoBroadcastTimeSet);

                }
                if (s.equalsIgnoreCase("autoBroadcastMessagesLoaded")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, autoBroadcastMessagesLoaded);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, autoBroadcastMessagesLoaded);

                }
                if (s.equalsIgnoreCase("autoBroadcastMessages")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, autoBroadcastMessages);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, autoBroadcastMessages);

                }
                if (s.equalsIgnoreCase("newMazeAmount")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, newMazeAmount);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, newMazeAmount);

                }
                if (s.equalsIgnoreCase("mustBeNumber")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, mustBeNumber);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, mustBeNumber);

                }
                if (s.equalsIgnoreCase("numberTooHigh")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, numberTooHigh);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, numberTooHigh);
                }
                if (s.equalsIgnoreCase("mazeAmount")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, mazeAmount);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, mazeAmount);
                }
                if (s.equalsIgnoreCase("maze_loaded_set")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_loaded_set);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_loaded_set);
                }
                if (s.equalsIgnoreCase("stats_pvpLevel")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, stats_pvpLevel);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, stats_pvpLevel);
                }
                if (s.equalsIgnoreCase("stats_expLevel")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, stats_expLevel);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, stats_expLevel);
                }
                if (s.equalsIgnoreCase("pluginDisabled")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pluginDisabled);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pluginDisabled);
                }
                if (s.equalsIgnoreCase("clearingEntities")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, clearingEntities);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, clearingEntities);
                }
                if (s.equalsIgnoreCase("reloading")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, reloading);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, reloading);
                }
                if (s.equalsIgnoreCase("barToggled")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, barToggled);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, barToggled);
                }
                if (s.equalsIgnoreCase("PlasmaNetwork")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, PlasmaNetwork);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, PlasmaNetwork);
                }
                if (s.equalsIgnoreCase("stats_killstreak")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, stats_killstreak);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, stats_killstreak);
                }
                if (s.equalsIgnoreCase("stats_longestKillstreak")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, stats_longestKillstreak);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, stats_longestKillstreak);
                }
                if (s.equalsIgnoreCase("maze_bugged")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_bugged);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_bugged);
                }
                if (s.equalsIgnoreCase("maze_loaded_bugged")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_loaded_bugged);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_loaded_bugged);
                }
                if (s.equalsIgnoreCase("maze_cannotBeZero")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_cannotBeZero);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_cannotBeZero);
                }
                if (s.equalsIgnoreCase("maze_mazeAmount")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, maze_mazeAmount);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, maze_mazeAmount);
                }
                if (s.equalsIgnoreCase("rewards")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, rewards);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, rewards);
                }
                if (s.equalsIgnoreCase("dailyRewardsClaimed")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, dailyRewardsClaimed);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, dailyRewardsClaimed);
                }
                if (s.equalsIgnoreCase("dailyRewardUnclaimed")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, dailyRewardUnclaimed);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, dailyRewardUnclaimed);
                }
                if (s.equalsIgnoreCase("dailyRewardsAlreadyClaimed")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, dailyRewardsAlreadyClaimed);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, dailyRewardsAlreadyClaimed);
                }
                if (s.equalsIgnoreCase("welcome")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, welcome);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, welcome);
                }
                if (s.equalsIgnoreCase("welcomeBack")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, welcomeBack);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, welcomeBack);
                }
                if (s.equalsIgnoreCase("welcomeNew_1")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, welcomeNew_1);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, welcomeNew_1);
                }
                if (s.equalsIgnoreCase("welcomeNew_2")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, welcomeNew_2);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, welcomeNew_2);
                }
                if (s.equalsIgnoreCase("rewards_gemsAdded")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, rewards_gemsAdded);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, rewards_gemsAdded);
                }
                if (s.equalsIgnoreCase("pingPrefix")) {
                    Main.messagesConfig.getConfig().set("Messages." + s, pingPrefix);
                    Main.log("Set default for: " + s + ".");
                    Main.messages.put(s, pingPrefix);
                }
            }
        }
        Main.messagesConfig.saveConfig();
        loadMessages();
    }

//    public void loadMessages() {
//        Main.log("Loading messages from messages.yml");
//        if (Main.messagesConfig.getConfig().get("Messages.prefix") == null) {
//            Main.messagesConfig.getConfig().set("Messages.prefix", prefix);
//            Main.log(loadedMessage + "prefix");
//        }
//        if (Main.messagesConfig.getConfig().get("Messages.server") == null) {
//            Main.messagesConfig.getConfig().set("Messages.server", server);
//            Main.log(loadedMessage + "server");
//        }
//        if (Main.messagesConfig.getConfig().get("Messages.reloadingConfig") == null) {
//            Main.messagesConfig.getConfig().set("Messages.reloadingConfig", reloadingConfig);
//            Main.log(loadedMessage + "reloadingConfig");
//        }
//        if (Main.messagesConfig.getConfig().get("Messages.reloadedConfig") == null) {
//            Main.messagesConfig.getConfig().set("Messages.reloadedConfig", reloadedConfig);
//            Main.log(loadedMessage + "reloadedConfig");
//        }
//        if (Main.messagesConfig.getConfig().get("Messages.ownerPrefix") == null) {
//            Main.messagesConfig.getConfig().set("Messages.ownerPrefix", ownerPrefix);
//            Main.log(loadedMessage + "ownerPrefix");
//        }
//        if (Main.messagesConfig.getConfig().get("Messages.sparkPrefix") == null) {
//            Main.messagesConfig.getConfig().set("Messages.sparkPrefix", sparkPrefix);
//            Main.log(loadedMessage + "sparkPrefix");
//        }
//        Main.messagesConfig.saveConfig();
//        Main.messages.put("prefix", Main.messagesConfig.getConfig().getString("Messages.prefix"));
//        Main.messages.put("server", Main.messagesConfig.getConfig().getString("Messages.server"));
//        Main.messages.put("reloadingConfig", Main.messagesConfig.getConfig().getString("Messages.reloadingConfig"));
//        Main.messages.put("reloadedConfig", Main.messagesConfig.getConfig().getString("Messages.reloadedConfig"));
//        Main.messages.put("ownerPrefix", Main.messagesConfig.getConfig().getString("Messages.ownerPrefix"));
//        Main.messages.put("sparkPrefix", Main.messagesConfig.getConfig().getString("Messages.sparkPrefix"));
//    }
//
//    public String getMessage(String message) {
//        String s = null;
//        if (Main.messages.size() == 0) {
//            loadMessages();
//        }
//        if (message.equalsIgnoreCase("prefix")) {
//            if (Main.messages.get("prefix") == null) {
//                Main.messages.put("prefix", prefix);
//                Main.log(errorGettingMessageFix + message);
//            }
//            s = Main.messages.get("prefix");
//        }
//        if (message.equalsIgnoreCase("server")) {
//            if (Main.messages.get("server") == null) {
//                Main.messages.put("server", server);
//                Main.log(errorGettingMessageFix + message);
//            }
//            s = Main.messages.get("server");
//        }
//        if (message.equalsIgnoreCase("reloadingConfig")) {
//            if (Main.messages.get("reloadingConfig") == null) {
//                Main.messages.put("reloadingConfig", reloadingConfig);
//                Main.log(errorGettingMessageFix + message);
//            }
//            s = Main.messages.get("reloadingConfig");
//        }
//        if (message.equalsIgnoreCase("reloadedConfig")) {
//            if (Main.messages.get("reloadedConfig") == null) {
//                Main.messages.put("reloadedConfig", reloadedConfig);
//                Main.log(errorGettingMessageFix + message);
//            }
//            s = Main.messages.get("reloadedConfig");
//        }
//        if (message.equalsIgnoreCase("ownerPrefix")) {
//            if (Main.messages.get("ownerPrefix") == null) {
//                Main.messages.put("ownerPrefix", ownerPrefix);
//                Main.log(errorGettingMessageFix + message);
//            }
//            s = Main.messages.get("ownerPrefix");
//        }
//        if (message.equalsIgnoreCase("sparkPrefix")) {
//            if (Main.messages.get("sparkPrefix") == null) {
//                Main.messages.put("sparkPrefix", sparkPrefix);
//                Main.log(errorGettingMessageFix + message);
//            }
//            s = Main.messages.get("sparkPrefix");
//        }
//        if (message.equalsIgnoreCase("sparkPrefix")) {
//            if (Main.messages.get("sparkPrefix") == null) {
//                Main.messages.put("sparkPrefix", sparkPrefix);
//                Main.log(errorGettingMessageFix + message);
//            }
//            s = Main.messages.get("sparkPrefix");
//        }
//        if (s == null) {
//            return errorGettingMessage;
//        }
//        s = ChatColor.translateAlternateColorCodes('§', s);
//        if (s.contains("{PREFIX}")) {
//            s = s.replace("{PREFIX}", Main.messages.get("prefix"));
//        }
//        if (s.contains("{SERVER}")) {
//            s = s.replace("{SERVER}", Main.messages.get("server"));
//        }
//        if (s.contains("{KOTL}")) {
//            s = s.replace("{KOTL}", Main.messages.get("kotl"));
//        }
//        if (s.contains("{PARKOUR}")) {
//            s = s.replace("{PARKOUR}", Main.messages.get("parkour"));
//        }
//        if (s.contains("{MAZE}")) {
//            s = s.replace("{MAZE}", Main.messages.get("maze"));
//        }
//        return s;
//    }


}

package me.yeroc.PlasmaHub;

import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.ChatColor;
import com.sk89q.minecraft.util.commands.*;
import me.yeroc.PlasmaHub.listeners.LaunchPads;
import me.yeroc.PlasmaHub.listeners.WorldListener;
import me.yeroc.PlasmaHub.managers.Configs;
import me.yeroc.PlasmaHub.managers.PermissionsManager;
import me.yeroc.PlasmaHub.managers.PlayerFileManager;
import me.yeroc.PlasmaHub.managers.Strings;
import me.yeroc.PlasmaHub.serverselector.ServerSelector;
import me.yeroc.PlasmaHub.utils.API;
import me.yeroc.PlasmaHub.utils.rewards.GemsManager;
import me.yeroc.PlasmaHub.utils.rewards.RewardsManager;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Main extends JavaPlugin implements Listener {
    public static Main instance;
    public static Main plugin;
    protected ArrayList<Class> cmdClasses;
    public static Economy econ = null;
    private CommandsManager<CommandSender> commands;
    public static Chat chat = null;

    public static Main getInstance() {
        return instance;
    }

    public static void log(String message) {
        System.out.println("[PlasmaHub] " + message);
    }

    private static World w = Bukkit.getServer().getWorld("world");

    public static HashMap<Server, String> reloadConfirmed = new HashMap<>();
    public static HashMap<UUID, String> canDoubleJump = new HashMap<>();

    // PARKOUR
    public static HashMap<UUID, String> parkour_isInParkour = new HashMap<>();
    public static HashMap<UUID, String> parkour_playerCheckpoints = new HashMap<>();

    // KOTL
    public static ArrayList<UUID> kotl_playersInRegion = new ArrayList<>();
    public static HashMap<UUID, Integer> kotl_secondsAsKotl = new HashMap<>();
    public static HashMap<UUID, String> kotl_playerInRegion = new HashMap<>();
    public static HashMap<UUID, String> kotl_toldInRegion = new HashMap<>();
    public static String kotl_player = "blank";
    public static Location kotl_box1 = new Location(Bukkit.getServer().getWorld("world"), 927, 44, 613); //927 44 613
    public static Location kotl_box2 = new Location(Bukkit.getServer().getWorld("world"), 911, 1, 597); // 911 1 597
    public static Location kotl_ladder = new Location(Bukkit.getServer().getWorld("world"), 919, 15, 605); // 919 15 605

    // MAZE
    public static HashMap<UUID, String> maze_isInMaze = new HashMap<>();
    public static Location maze_start = new Location(w, 877, 3, 529); // 860 2 512
    public static Location maze_1_4_finish = new Location(w, 860, 2, 512); // 860 512
    public static Location maze_5_finish = new Location(w, 846, 2, 526); // 846 526
    public static Location maze_6_finish = new Location(w, 847, 2, 499); // 847 499
    public static Location mazebox1 = new Location(w, 876, 10, 528);
    public static Location mazebox2 = new Location(w, 844, 1, 495);
    public static HashMap<Server, ArrayList<Integer>> maze_effectedMazes = new HashMap<>();
    public static HashMap<UUID, Integer> maze_playerEffectSeconds = new HashMap<>();
    public static Integer maze_loaded = 0;
    public static Integer mazes = 0;
    public static Location slimeBlock = new Location(w, 919, 2, 533);
//    public static String loadedMaze = "Maze_1";

    // PARKOUR //(919, 2, 533
    public static Location parkour_startBlock = new Location(w, 950, 4, 602);
    public static Location parkour_chp1block = new Location(w, 945, 14, 613);
    public static Location parkour_chp2block = new Location(w, 954, 27, 584);
    public static Location parkour_chp3block = new Location(w, 948, 32, 552); // 948 33 552 -100 0
    public static Location parkour_chp4block = new Location(w, 943, 35, 536);
    public static Location parkour_finishBlock = new Location(w, 919, 41, 528);

    public static Location parkour_start = new Location(w, 950.5, 5, 602.5, -80, 0);
    public static Location parkour_chp1 = new Location(w, 945.5, 15, 613.5, -180, 0); //945 15 613 0 0
    public static Location parkour_chp2 = new Location(w, 954.5, 28, 584.5, -180, 0);
    public static Location parkour_chp3 = new Location(w, 948.5, 33, 552.5, 75, 0); // 948 33 552 -100 0
    public static Location parkour_chp4 = new Location(w, 943.5, 36, 536.5, 90, 0);
    public static Location parkour_finish = new Location(w, 919, 42, 528);

    // PLAYER FILES
    public static HashMap<UUID, String> pfm_name = new HashMap<>();
    public static HashMap<UUID, String> pfm_uuid = new HashMap<>(); /// deaths joins gems
    public static HashMap<UUID, Integer> pfm_kills = new HashMap<>();
    public static HashMap<UUID, Integer> pfm_deaths = new HashMap<>();
    public static HashMap<UUID, Integer> pfm_joins = new HashMap<>();
    public static HashMap<UUID, Integer> pfm_totalGems = new HashMap<>();
    public static HashMap<UUID, String> pfm_completedParkour = new HashMap<>();
    public static HashMap<UUID, String> pfm_completedMaze = new HashMap<>();
    public static HashMap<UUID, Integer> pfm_timeOnline = new HashMap<>();
    public static HashMap<UUID, Integer> pfm_pvpLevel = new HashMap<>();
    public static HashMap<UUID, Integer> pfm_pvpExp = new HashMap<>();
    public static HashMap<UUID, Integer> pfm_killstreak = new HashMap<>();
    public static HashMap<UUID, Integer> pfm_longestKillstreak = new HashMap<>();
    public static HashMap<UUID, Integer> pfm_deathstreak = new HashMap<>();

    //    public static HashMap<UUID, String> pfm_dailyReward = new HashMap<>();
    //    public static HashMap<UUID, String> pfm_dailyRewardDates = new HashMap<>();

    // GENERAL
    public static Location spawn = new Location(Bukkit.getWorld("world"), 0, 0, 0, 0, 0);
    public static HashMap<UUID, String> isSitting = new HashMap<>();
    public static HashMap<UUID, Entity> arrow = new HashMap<>();
    public static Integer autoBroadcastTime = 0;
    public static List<String> autoBroadcastMessages = new ArrayList<>();
    public static HashMap<UUID, String> scoreboardEnabled = new HashMap<>();
    public static HashMap<UUID, String> barEnabled = new HashMap<>();
    public static HashMap<UUID, Integer> barProgress = new HashMap<>();

    public static HashMap<String, String> messages = new HashMap<>();

    public static HashMap<UUID, String> dailyRewards = new HashMap<>();
    public static HashMap<UUID, Boolean> firstJoin = new HashMap<>();

    // GET INSTANCES
    private API api = API.getInstance();
    private PermissionsManager perms = PermissionsManager.getInstance();
    private Strings strings = Strings.getInstance();
    private Configs configs = Configs.getInstance();
    private RewardsManager rewards = RewardsManager.getInstance();
    private GemsManager gems = GemsManager.getInstance();
    private PlayerFileManager pfm = PlayerFileManager.getInstance();
    private AutoBroadcast ab = AutoBroadcast.getInstance();
    public static boolean pluginEnabled = false;
    public static boolean effectedMazesLoaded = false;
    private int maze_chance = 0;

    public void onEnable() {
        plugin = this;
        pluginEnabled = false;
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new me.yeroc.PlasmaHub.PlayerListener(this), this);
        pm.registerEvents(new WorldListener(this), this);
        pm.registerEvents(new LaunchPads(this), this);
        pm.registerEvents(new ServerSelector(this), this);
        registerCommandClass(Commands.class);
        registerCommandClass(StatsCommands.class);
//        pm.registerEvents(new PlayerListener(this), this);
//        pm.registerEvents(new DoubleJump(this), this);
//        pm.registerEvents(new PvP(this), this);
//        pm.registerEvents(new KOTL(this), this);
//        pm.registerEvents(new Parkour(this), this);
//        pm.registerEvents(new Maze(this), this);
//        pm.registerEvents(new OldMazeSwapper(this), this);
//        registerCommandClass(OldMazeSwapper.class);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new BukkitRunnable() {
            @Override
            public void run() {
                if (!pluginEnabled) {
                    Bukkit.broadcastMessage(ChatColor.RED + "PLUGIN MIGHT NOT HAVE STARTED PROPERLY. CONTACT AN OWNER.");
                } else {
                    Main.log("Plugin was loaded, according to the delayed check.");
                }
                if (effectedMazesLoaded) {
                    Main.log("This reload contains some buggy mazes.");
                }
            }
        }, 100L);
        registerCommands();
        setupEconomy();
        configs.setup(this);
        strings.checkDefaults();
        loadSpawn();
        registerTasks();
        if (configs.getConfig().get("Maze.Loaded") == null) {
            configs.getConfig().set("Maze.Loaded", 1);
            configs.saveConfig();
            maze_loaded = 1;
            log(strings.getMessage("maze_default"));
        }
        if (configs.getConfig().get("Maze.Amount") == null || (configs.getConfig().getInt("Maze.Amount") == 0)) {
            configs.getConfig().set("Maze.Amount", 7);
            configs.saveConfig();
        }
        mazes = configs.getConfig().getInt("Maze.Amount");
        if (configs.getConfig().get("AutoBroadcast.Interval") == null) {
            configs.getConfig().set("AutoBroadcast.Interval", 5);
            configs.saveConfig();
            autoBroadcastTime = 5;
            log(strings.getMessage("autoBroadcastIntervalDefault"));
        }
        if (configs.getConfig().get("AutoBroadcast.Messages") == null) {
            ab.setDefaults();
            configs.saveConfig();
        }
        if (configs.getConfig().get("Maze.BuggedChance") == null) {
            configs.getConfig().set("Maze.BuggedChance", 50);
        }
        maze_chance = configs.getConfig().getInt("Maze.BuggedChance");
        Main.log(strings.getMessage("autoBroadcastTimeSet") + autoBroadcastTime);
        ab.loadMessages();
        maze_loaded = configs.getConfig().getInt("Maze.Loaded");
        reloadConfirmed.put(Bukkit.getServer(), "empty");
        setupChat();
        Random random = new Random();
        int randomChance = 100;
        final int RANDOM_NUMBER = random.nextInt(randomChance);
        if (RANDOM_NUMBER <= maze_chance) {
            log("A few buggy mazes are being loaded ;)");
            int amount = mazes / 2;
            if (amount >= 2) {
                for (int newi = 1; newi < amount; ) {
                    int i = random.nextInt(mazes);
                    if (i == 0 || i == 10) {
                        continue;
                    }
                    if (maze_effectedMazes.get(Bukkit.getServer()) == null) {
                        ArrayList<Integer> l = new ArrayList<>();
                        l.add(i);
                        maze_effectedMazes.put(Bukkit.getServer(), l);
                        log("A buggy maze: maze #" + i);
                        newi++;
                        continue;
                    }
                    if (!(maze_effectedMazes.get(Bukkit.getServer()).contains(i))) {
                        maze_effectedMazes.get(Bukkit.getServer()).add(i);
                        log("A buggy maze: maze #" + i);
                        newi++;
                    }
                }
            }
        }
        if (Bukkit.getOnlinePlayers().size() != 0) {
            for (final Player p : Bukkit.getOnlinePlayers()) {
                Main.canDoubleJump.put(p.getUniqueId(), "yes");
                Main.maze_isInMaze.put(p.getUniqueId(), "no");
                Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
                pfm.load(p);
                if (Main.maze_effectedMazes.get(Bukkit.getServer()) != null) {
                    p.sendMessage(strings.getMessage("maze") + ChatColor.RED + " Uh oh, there could be a chance your maze is bugged!");
                }
                if (p.getGameMode().equals(GameMode.SURVIVAL)) {
                    p.getInventory().clear();
                    api.resetInventory(p);
                    p.setFlying(false);
                    p.setAllowFlight(true);
                    p.sendMessage(strings.getMessage("inventoryResetReload"));

                    Bukkit.getScheduler().scheduleSyncDelayedTask(this, new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (!pluginEnabled) {
                                p.sendMessage(ChatColor.RED + "DUE TO THIS, YOUR PLAYER FILE MAY HAVE CORRUPTED.");
                            }
                        }
                    }, 120L);
                }
                api.applyAttackSpeed(p);
                p.setPlayerListHeader("                           ");
                p.setPlayerListFooter(strings.getMessage("PlasmaNetwork"));
            }
            Bukkit.broadcastMessage(strings.getMessage("serverReloaded"));
        }
        pluginEnabled = true;
        if (Main.maze_effectedMazes.get(Bukkit.getServer()) != null) {
            effectedMazesLoaded = true;
        }
        log("Enabled successfully!");
    }

    public void onDisable() {
        if (Bukkit.getOnlinePlayers().size() != 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                    parkour_isInParkour.put(p.getUniqueId(), "no");
                    parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
                    p.teleport(Main.spawn);
                    p.sendMessage(strings.getMessage("parkour_left_reload"));
                }
                if (maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                    maze_isInMaze.put(p.getUniqueId(), "no");
                    p.sendMessage(strings.getMessage("maze_left_reload"));
                    p.teleport(Main.spawn);
                }
                if (p.getActivePotionEffects().size() != 0) {
                    for (PotionEffect effect : p.getActivePotionEffects()) {
                        if (p.getActivePotionEffects().size() == 1) {
                            if (effect.getType().equals(PotionEffectType.GLOWING)) {
                                break;
                            }
                        }
                        if (!effect.getType().equals(PotionEffectType.GLOWING)) {
                            p.removePotionEffect(effect.getType());
                        }
                    }
                }
                if (Main.pluginEnabled) {
                    pfm.save(p);
                } else {
                    Main.log("Did not save " + p.getName() + "'s player file because the plugin did not load properly.");
                }
            }
        }
        configs.getConfig().set("Maze.Loaded", maze_loaded);
        configs.getConfig().set("Maze.Amount", mazes);
        configs.saveConfig();
    }


    public HashMap<Server, String> getReloadConfirmed() {
        return reloadConfirmed;
    }

    public static void setReloadConfirmed(String s) {
        reloadConfirmed.clear();
        reloadConfirmed.put(Bukkit.getServer(), s);
    }

    private void registerTasks() {
        if (configs.getConfig().get("AutoBroadcast.Interval") == null) {
            configs.getConfig().set("AutoBroadcast.Interval", 5);
            configs.saveConfig();
            autoBroadcastTime = 5;
            log(strings.getMessage("autoBroadcastIntervalDefault"));
        }
        autoBroadcastTime = configs.getConfig().getInt("AutoBroadcast.Interval");
        log(strings.getMessage("autoBroadcastTimeSet") + autoBroadcastTime);
        new me.yeroc.PlasmaHub.PlayerListener(this).runTaskTimer(this, 0L, 20L);
        new me.yeroc.PlasmaHub.managers.PlayerFileManager(this).runTaskTimer(this, 0L, 20L);
        new me.yeroc.PlasmaHub.AutoBroadcast().runTaskTimer(this, 0L, autoBroadcastTime * 20 * 60);
//        new me.yeroc.PlasmaHub.managers.Scoreboard().runTaskTimer(this, 0L, 20L);
        new me.yeroc.PlasmaHub.utils.API().runTaskTimer(this, 0L, 65L);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (World w : Bukkit.getServer().getWorlds()) {
                    w.setTime(2000L);
                }
            }
        }, 0L, 0L);
        log("Tasks started.");
    }

    public void loadSpawn() {
        if (configs.getConfig().get("Spawn") != null) {
            double x = configs.getConfig().getDouble("Spawn.X");
            double y = configs.getConfig().getDouble("Spawn.Y");
            double z = configs.getConfig().getDouble("Spawn.Z");
            double yaw = configs.getConfig().getDouble("Spawn.Yaw");
            double pitch = configs.getConfig().getDouble("Spawn.Pitch");
            spawn = new Location(Bukkit.getWorld("world"), x, y, z, (float) yaw, (float) pitch);
            Main.log("Spawn has been loaded.");
        } else {
            if (Bukkit.getOnlinePlayers().size() != 0) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission(perms.plasma_command_setspawn)) {
                        p.sendMessage(strings.getMessage("spawnNotSet"));
                        p.sendMessage(strings.getMessage("spawnNotSetAllowed"));
                    }
                }
            }
            Main.log("Spawn has not been set yet.");
        }

    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        return chat != null;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            commands.execute(cmd.getName(), args, sender, sender);
        } catch (CommandPermissionsException e) {

            sender.sendMessage(ChatColor.RED + "You don't have permission.");
        } catch (MissingNestedCommandException e) {
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (CommandUsageException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (WrappedCommandException e) {
            if (e.getCause() instanceof NumberFormatException) {

                sender.sendMessage(ChatColor.RED + "You need to enter a number!");
            } else {
                sender.sendMessage(ChatColor.RED + "Error occurred, contact developer. [TheManiacGamers]");
                sender.sendMessage(ChatColor.RED + "Message: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (CommandException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }

        return true;
    }

    protected void registerCommandClass(Class cmdClass) {
        if (cmdClasses == null)
            cmdClasses = new ArrayList<Class>();

        cmdClasses.add(cmdClass);
    }

    protected void registerCommands() {
        if (cmdClasses == null || cmdClasses.size() < 1) {

            log("Could not register commands! Perhaps you registered no classes?");
            return;
        }

        // Register the commands that we want to use
        commands = new CommandsManager<CommandSender>() {
            @Override
            public boolean hasPermission(CommandSender player, String perm) {
                return getInstance().hasPerm(player, perm);
            }


        };
        commands.setInjector(new SimpleInjector(this));
        final CommandsManagerRegistration cmdRegister = new CommandsManagerRegistration(this, commands);

        for (Class cmdClass : cmdClasses)
            cmdRegister.register(cmdClass);
    }

    public boolean hasPerm(CommandSender sender, String perm) {
        return sender instanceof ConsoleCommandSender || sender.hasPermission(perm);
    }
}

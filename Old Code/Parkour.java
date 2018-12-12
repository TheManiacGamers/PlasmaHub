//package me.yeroc.PlasmaHub.minigames.parkour;
//
//import com.sk89q.minecraft.util.commands.Command;
//import com.sk89q.minecraft.util.commands.CommandContext;
//import me.yeroc.PlasmaHub.Main;
//import me.yeroc.PlasmaHub.listeners.DoubleJump;
//import me.yeroc.PlasmaHub.managers.Configs;
//import me.yeroc.PlasmaHub.managers.PermissionsManager;
//import me.yeroc.PlasmaHub.managers.PlayerFileManager;
//import me.yeroc.PlasmaHub.managers.Strings;
//import me.yeroc.PlasmaHub.minigames.maze.Maze;
//import me.yeroc.PlasmaHub.minigames.pvp.PvP;
//import me.yeroc.PlasmaHub.serverselector.ServerSelector;
//import me.yeroc.PlasmaHub.utils.API;
//import me.yeroc.PlasmaHub.utils.TitleAPI.TitleAPI;
//import me.yeroc.PlasmaHub.utils.rewards.GemsManager;
//import me.yeroc.PlasmaHub.utils.rewards.RewardsManager;
//import org.bukkit.Bukkit;
//import org.bukkit.Location;
//import org.bukkit.Material;
//import org.bukkit.World;
//import org.bukkit.block.Block;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerInteractEvent;
//import org.bukkit.event.player.PlayerJoinEvent;
//import org.bukkit.event.player.PlayerQuitEvent;
//
///**
// * Created by Corey on 28/11/2018.
// */
//public class Parkour implements Listener {
//    private Main plugin;
//
//    private static Parkour instance = new Parkour();
//
//    public Parkour(Main plugin) {
//        this.plugin = plugin;
//    }
//
//    private Parkour() {
//    }
//
//    public static Parkour getInstance() {
//        return instance;
//    }
//
//    private API api = API.getInstance();
//    private PermissionsManager perms = PermissionsManager.getInstance();
//    private Strings strings = Strings.getInstance();
//    private Configs configs = Configs.getInstance();
//    private ServerSelector serverSelector = ServerSelector.getInstance();
//    private PvP pvp = PvP.getInstance();
//    private Maze maze = Maze.getInstance();
//    private Parkour parkour = Parkour.getInstance();
//    private RewardsManager rewards = RewardsManager.getInstance();
//    private GemsManager gems = GemsManager.getInstance();
//    private PlayerFileManager pfm = PlayerFileManager.getInstance();
//    private DoubleJump dj = DoubleJump.getInstance();
//    private World w = Bukkit.getServer().getWorld("world");
//
//    private Location startBlock = new Location(w, 950, 4, 602);
//    private Location chp1block = new Location(w, 945, 14, 613);
//    private Location chp2block = new Location(w, 954, 27, 584);
//    private Location chp3block = new Location(w, 948, 32, 552); // 948 33 552 -100 0
//    private Location chp4block = new Location(w, 943, 35, 536);
//    private Location finishBlock = new Location(w, 919, 41, 528);
//
//
//    private Location start = new Location(w, 950, 5, 602);
//    private Location chp1 = new Location(w, 945, 15, 613, -180, 0); //945 15 613 0 0
//    private Location chp2 = new Location(w, 954, 28, 584, -180, 0);
//    private Location chp3 = new Location(w, 948, 33, 552, 75, 0); // 948 33 552 -100 0
//    private Location chp4 = new Location(w, 943, 36, 536, 90, 0);
//    private Location finish = new Location(w, 919, 42, 528);
//
////    public HashMap<UUID, String> Main.parkour_playerCheckpoints = new HashMap<>();
//
////    @EventHandler
////    public void onPlayerJoin(PlayerJoinEvent e) {
////        Main.parkour_playerCheckpoints.put(e.getPlayer().getUniqueId(), "zero");
////        Main.parkour_isInParkour.put(e.getPlayer().getUniqueId(), "no");
////        Main.canDoubleJump.put(e.getPlayer().getUniqueId(), "yes");
////    }
////
////    @EventHandler
////    public void onQuit(PlayerQuitEvent e) {
////        Player p = e.getPlayer();
////        p.sendMessage(strings.parkour_stop);
////        if (Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
////            Main.parkour_playerCheckpoints.put(e.getPlayer().getUniqueId(), "zero");
////            Main.parkour_isInParkour.put(e.getPlayer().getUniqueId(), "no");
////        }
////        if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
////            Main.maze_isInMaze.put(p.getUniqueId(), "no");
////        }
////    }
//
////    @EventHandler
////    public void onPlayerInteract(PlayerInteractEvent e) {
////        Player p = e.getPlayer();
////        Block b = e.getClickedBlock(); // 012345 = stopped, start, chp1, chp2, chp3, chp4
////        if (Main.parkour_playerCheckpoints.get(p.getUniqueId()) == null) {
////            Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
////        }
////        if (Main.parkour_isInParkour.get(p.getUniqueId()) == null) {
////            Main.parkour_isInParkour.put(p.getUniqueId(), "no");
////        }
////        if (b == null || (b.getType().equals(Material.AIR))) {
////            return;
////        }
////        if (b.getType().equals(Material.LIGHT_WEIGHTED_PRESSURE_PLATE)) {
////            if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
////                p.sendMessage(strings.maze_stop);
////                Main.maze_isInMaze.put(p.getUniqueId(), "no");
////                Main.canDoubleJump.put(p.getUniqueId(), "yes");
////            }
////            if (b.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(startBlock)) {
////                if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("zero")) {
////                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "one");
////                    Main.canDoubleJump.put(p.getUniqueId(), "no");
////                    Main.parkour_isInParkour.put(p.getUniqueId(), "yes");
////                    p.sendMessage(strings.parkour_start);
////                }
////                return;
////            }
////            if (b.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(chp1block)) {
////                if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("one")) {
////                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "two");
////                    TitleAPI.sendActionBar(p, strings.parkour_chp + "1");
////                    Main.parkour_isInParkour.put(p.getUniqueId(), "yes");
////                }
////                return;
////            }
////            if (b.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(chp2block)) {
////                if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("two")) {
////                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "three");
////                    TitleAPI.sendActionBar(p, strings.parkour_chp + "2");
////                    Main.parkour_isInParkour.put(p.getUniqueId(), "yes");
////                }
////                return;
////            }
////            if (b.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(chp3block)) {
////                if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("three")) {
////                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "four");
////                    TitleAPI.sendActionBar(p, strings.parkour_chp + "3");
////                    Main.parkour_isInParkour.put(p.getUniqueId(), "yes");
////                }
////                return;
////            }
////            if (b.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(chp4block)) {
////                if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("four")) {
////                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "five");
////                    TitleAPI.sendActionBar(p, strings.parkour_chp + "4");
////                    Main.parkour_isInParkour.put(p.getUniqueId(), "yes");
////                }
////                return;
////            }
////            if (b.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(finishBlock)) {
////                if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("five")) {
////                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
////                    p.sendMessage(strings.parkour_finish);
////                    p.teleport(Main.spawn);
////                    if (Main.canDoubleJump.get(p.getUniqueId()) == null) {
////                        Main.canDoubleJump.put(p.getUniqueId(), "yes");
////                    }
////                    Main.canDoubleJump.put(p.getUniqueId(), "yes");
////                    Main.parkour_isInParkour.put(p.getUniqueId(), "no");
////                } else {
////                    p.sendMessage(strings.parkour_error);
////                }
////            }
////        }
////
////    }
//
//
//}

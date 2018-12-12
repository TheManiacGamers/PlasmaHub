//package me.yeroc.PlasmaHub.minigames.maze;
//
//import com.sk89q.minecraft.util.commands.Command;
//import com.sk89q.minecraft.util.commands.CommandContext;
//import me.yeroc.PlasmaHub.Main;
//import me.yeroc.PlasmaHub.managers.Configs;
//import me.yeroc.PlasmaHub.managers.PermissionsManager;
//import me.yeroc.PlasmaHub.managers.PlayerFileManager;
//import me.yeroc.PlasmaHub.managers.Strings;
//import me.yeroc.PlasmaHub.minigames.parkour.Parkour;
//import me.yeroc.PlasmaHub.minigames.pvp.PvP;
//import me.yeroc.PlasmaHub.serverselector.ServerSelector;
//import me.yeroc.PlasmaHub.utils.API;
//import me.yeroc.PlasmaHub.utils.rewards.GemsManager;
//import me.yeroc.PlasmaHub.utils.rewards.RewardsManager;
//import org.bukkit.Bukkit;
//import org.bukkit.World;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//import org.bukkit.event.Listener;
//
///**
// * Created by Corey on 28/11/2018.
// */
//public class Maze implements Listener {
//    private Main plugin;
//
//    private static Maze instance = new Maze();
//
//    public Maze(Main plugin) {
//        this.plugin = plugin;
//    }
//
//    private Maze() {
//    }
//
//    public static Maze getInstance() {
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
//
//    private World w = Bukkit.getServer().getWorld("world");
//
////    @Command(aliases = "mazeload", desc = "Load a new maze")
////    public void onMazeLoad(CommandContext args, CommandSender sender) {
////        if (api.isPlayer(sender)) {
////            Player p = (Player) sender;
////            if (p.hasPermission(perms.plasma_maze_load)) {
////                if (Main.loadedMaze == null) {
////                    Main.loadedMaze = "Maze_1";
////                }
////                if (Main.loadedMaze.equals("Maze_1")) {
////                    Main.loadedMaze = "Maze_2";
////                    p.sendMessage(strings.maze_loaded + "Maze 2");
////                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_2");
////                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
////                    p.sendMessage(strings.maze_loaded_done);
////                    return;
////                }
////                if (Main.loadedMaze.equals("Maze_2")) {
////                    Main.loadedMaze = "Maze_3";
////                    p.sendMessage(strings.maze_loaded + "Maze 3");
////                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_3");
////                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
////                    p.sendMessage(strings.maze_loaded_done);
////                    return;
////                }
////                if (Main.loadedMaze.equals("Maze_3")) {
////                    Main.loadedMaze = "Maze_4";
////                    p.sendMessage(strings.maze_loaded + "Maze 4");
////                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_4");
////                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
////                    p.sendMessage(strings.maze_loaded_done);
////                    return;
////                }
////                if (Main.loadedMaze.equals("Maze_4")) {
////                    Main.loadedMaze = "Maze_1";
////                    p.sendMessage(strings.maze_loaded + "Maze 1");
////                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_1");
////                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
////                    p.sendMessage(strings.maze_loaded_done);
////                } else {
////                    Main.loadedMaze = "Maze_1";
////                    p.sendMessage(strings.maze_loaded + "Maze 1");
////                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " schematic load maze_1");
////                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo " + p.getName() + " /paste");
////                    p.sendMessage(strings.maze_loaded_done);
////                }
////            } else {
////                p.sendMessage(strings.noPermission);
////            }
////        } else {
////            sender.sendMessage(strings.mustBePlayer);
////        }
////    }
//
////    @EventHandler
////    public void onPlayerInteract(PlayerInteractEvent e) {
////        Player p = e.getPlayer();
////        Block b = e.getClickedBlock(); // 012345 = stopped, start, chp1, chp2, chp3, chp4
////        if (Main.maze_isInMaze.get(p.getUniqueId()) == null) {
////            Main.maze_isInMaze.put(p.getUniqueId(), "no");
////        }
////        if (b == null || (b.getType().equals(Material.AIR))) {
////            return;
////        }
////        if (b.getType().equals(Material.HEAVY_WEIGHTED_PRESSURE_PLATE)) {
////            if (Main.parkour_isInParkour.get(p.getUniqueId()) == null) {
////                Main.parkour_isInParkour.put(p.getUniqueId(), "no");
////                Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
////            }
////            if (Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
////                Main.parkour_isInParkour.put(p.getUniqueId(), "no");
////                Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
////                p.sendMessage(strings.parkour_stop);
////                Main.canDoubleJump.put(p.getUniqueId(), "yes");
////            }
////            if (b.getLocation().equals(start)) {
////                if (Main.maze_isInMaze.get(p.getUniqueId()) == null) {
////                    Main.maze_isInMaze.put(p.getUniqueId(), "yes");
////                    Main.canDoubleJump.put(p.getUniqueId(), "yes");
////                    p.sendMessage(strings.maze_start);
////                }
////                if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("no")) {
////                    Main.maze_isInMaze.put(p.getUniqueId(), "yes");
////                    Main.canDoubleJump.put(p.getUniqueId(), "yes");
////                    p.sendMessage(strings.maze_start);
////                }
////            }
//////            if (b.getLocation().equals(finish)) {
//////                p.teleport(Main.spawn);
//////                Main.maze_isInMaze.put(p.getUniqueId(), "no");
//////                p.sendMessage(strings.maze_1_finish);
//////            }
////        } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
////            if (e.getClickedBlock().getType().equals(Material.GOLD_BLOCK)) {
////                if (e.getClickedBlock().getLocation().equals(finish)) {
////                    if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
////                        p.teleport(Main.spawn);
////                        Main.maze_isInMaze.put(p.getUniqueId(), "no");
////                        p.sendMessage(strings.maze_1_finish);
////                    } else {
////                        p.sendMessage(strings.maze_notStarted);
////                        p.teleport(Main.spawn);
////                    }
////                }
////            }
////        }
////    }
//}
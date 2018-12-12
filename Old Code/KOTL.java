//package me.yeroc.PlasmaHub.minigames.kotl;
//
//import com.sk89q.minecraft.util.commands.ChatColor;
//import me.yeroc.PlasmaHub.Main;
//import me.yeroc.PlasmaHub.listeners.DoubleJump;
//import me.yeroc.PlasmaHub.managers.Configs;
//import me.yeroc.PlasmaHub.managers.PermissionsManager;
//import me.yeroc.PlasmaHub.managers.PlayerFileManager;
//import me.yeroc.PlasmaHub.managers.Strings;
//import me.yeroc.PlasmaHub.minigames.maze.Maze;
//import me.yeroc.PlasmaHub.minigames.parkour.Parkour;
//import me.yeroc.PlasmaHub.minigames.pvp.PvP;
//import me.yeroc.PlasmaHub.serverselector.ServerSelector;
//import me.yeroc.PlasmaHub.utils.API;
//import me.yeroc.PlasmaHub.utils.TitleAPI.TitleAPI;
//import me.yeroc.PlasmaHub.utils.rewards.GemsManager;
//import me.yeroc.PlasmaHub.utils.rewards.RewardsManager;
//import org.bukkit.Bukkit;
//import org.bukkit.Location;
//import org.bukkit.block.Block;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerJoinEvent;
//import org.bukkit.event.player.PlayerMoveEvent;
//import org.bukkit.event.player.PlayerQuitEvent;
//import org.bukkit.scheduler.BukkitRunnable;
//
//import java.util.ArrayList;
//import java.util.UUID;
//
///**
// * Created by Corey on 28/11/2018.
// */
//public class KOTL extends BukkitRunnable implements Listener {
//    private Main plugin;
//
//    private static KOTL instance = new KOTL();
//
//    public KOTL(Main plugin) {
//        this.plugin = plugin;
//    }
//
//    private KOTL() {
//    }
//
//    public static KOTL getInstance() {
//        return instance;
//    }
//
////    public void run() {
////        if (Bukkit.getOnlinePlayers().size() != 0) {
////            for (Player p : Bukkit.getOnlinePlayers()) {
////                if (locationIsInCuboid(p) || (parkour.Main.parkour_playerCheckpoints.get(p.getUniqueId()) == 1 || (parkour.Main.parkour_playerCheckpoints.get(p.getUniqueId()) == 2 || (parkour.Main.parkour_playerCheckpoints.get(p.getUniqueId()) == 3 || (parkour.Main.parkour_playerCheckpoints.get(p.getUniqueId()) == 4 || (parkour.Main.parkour_playerCheckpoints.get(p.getUniqueId()) == 5)))))) {
////                    Main.canDoubleJump.put(p, "no");
////                } else {
////                    Main.canDoubleJump.put(p, "yes");
////                }
////            }
////        }
////    }
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
//
//
////    public void startKOTL() {
////        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("PlasmaHub"), new BukkitRunnable() {
////            @Override
////            public void run() {
////                for (Player players : playersInRegion) {
////                    checkKOTL(players);
////                }
////            }
////        }, 0L, 20L);
////    }
//
////    public void checkKOTL(final Player p) {
////        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("PlasmaHub"), new BukkitRunnable() {
////            @Override
////            public void run() {
////                Location playerLocation = p.getLocation();
////                playerLocation.setY(playerLocation.getY() - 1);
////                Block b = playerLocation.getBlock();
////                Location pLoc = b.getLocation();
////                playersInRegion.clear();
////                for (Player players : Bukkit.getOnlinePlayers()) {
////                    if (locationIsInCuboid(players)) {
////                        playersInRegion.add(players);
////                        dj.setDisabled(p);
////                    }
////                }
////                if (!(told.contains(p.getUniqueId())) && (playersInRegion.contains(p))) {
////                    p.sendMessage(strings.kotl_enter);
////
////                    told.add(p.getUniqueId());
////                }
////                if (told.contains(p.getUniqueId()) && (!(playersInRegion.contains(p)))) {
////                    p.sendMessage(strings.kotl_leave);
////                    dj.setEnabled(p);
////                    told.remove(p.getUniqueId());
////                }
////                if (kotl.equalsIgnoreCase(p.getName())) {
////                    return;
////                }
////                if (kotl != null && (!(kotl.equalsIgnoreCase(p.getName())))) {
////                    if (pLoc.equals(ladder)) {
////                        for (Player kotlPlayers : playersInRegion) {
////                            kotlPlayers.sendMessage(strings.kotl + ChatColor.GOLD + "" + ChatColor.BOLD + p.getName() + strings.kotl_newLeader);
////                        }
////                        p.sendMessage(strings.kotl_youAreNewLeader);
////                        kotl = p.getName();
////                    }
////                }
////            }
////        }, 0L, 5L);
////    }
//
//    public void addSeconds(Player p) {
//
//    }
//
//    @EventHandler
//    public void onPlayerJoin(PlayerJoinEvent e) {
//        Player p = e.getPlayer();
//        if (kotl != null && (!(kotl.equalsIgnoreCase("blank")))) {
//            TitleAPI.sendActionBar(p, ChatColor.GOLD + "" + ChatColor.BOLD + strings.kotl_currentLeader);
//        }
//
//    }
//
//    @EventHandler
//    public void onPlayerLeave(PlayerQuitEvent e) {
//        Player p = e.getPlayer();
//        if (kotl.equalsIgnoreCase(p.getName())) {
//            kotl = "blank";
//            for (Player players : playersInRegion) {
//                TitleAPI.sendActionBar(players, strings.kotl_leaderLeft);
//            }
//        }
//        told.remove(p.getUniqueId());
//        playersInRegion.remove(p);
//    }
//
////    private void addSeconds(Player p) {
////        if (kotl != null && (!(kotl.equalsIgnoreCase("blank")))) {
////            if (kotl.equalsIgnoreCase(p.getName())) {
////                if (Main.kotl_secondsAsKotl.get(p.getUniqueId()) == null) {
////                    Main.kotl_secondsAsKotl.put(p.getUniqueId(), 1);
////                } else {
////                    Main.kotl_secondsAsKotl.put(p.getUniqueId(), Main.kotl_secondsAsKotl.get(p.getUniqueId()) + 1);
////                }
////            }
////        }
////    }
//
//    public void run() {
//        if (Bukkit.getOnlinePlayers().size() != 0) {
//            for (Player p : Bukkit.getOnlinePlayers()) {
//                addSeconds(p);
//            }
//        }
//    }
//
////    public boolean isPlayerInRegion(Player p) {
////        return playersInRegion.contains(p);
////    }
//
////    @EventHandler
////    public void onPlayerMove(PlayerMoveEvent e) {
////        Player p = e.getPlayer();
////        Location playerLocation = p.getLocation();
////        playerLocation.setY(playerLocation.getY() - 1);
////        Block b = playerLocation.getBlock();
////        Location pLoc = b.getLocation();
////        playersInRegion.clear();
////        for (Player players : Bukkit.getOnlinePlayers()) {
////            if (locationIsInCuboid(players)) {
////                playersInRegion.add(players);
////                if (Main.canDoubleJump.get(p.getUniqueId()) == null) {
////                    Main.canDoubleJump.put(p.getUniqueId(), "no");
////                } else {
////                    Main.canDoubleJump.put(p.getUniqueId(), "no");
////                }
////                if (Main.parkour_playerCheckpoints.get(p.getUniqueId()) == null) {
////                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
////                }
////                if (!Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("zero")) {
////                    p.sendMessage(strings.parkour_stop);
////                    Main.parkour_isInParkour.put(p.getUniqueId(), "no");
////                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
////                }
////                if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
////                    p.sendMessage(strings.maze_stop);
////                    Main.maze_isInMaze.put(p.getUniqueId(), "no");
////                }
////            }
////        }
////        if (!(told.contains(p.getUniqueId())) && (playersInRegion.contains(p))) {
////            p.sendMessage(strings.kotl_enter);
////            told.add(p.getUniqueId());
////        }
////        if (told.contains(p.getUniqueId()) && (!(playersInRegion.contains(p)))) {
////            p.sendMessage(strings.kotl_leave);
////            told.remove(p.getUniqueId());
////            Main.canDoubleJump.put(p.getUniqueId(), "yes");
////        }
////        if (kotl.equalsIgnoreCase(p.getName()) && (!(kotl.equalsIgnoreCase(null)) && (!(kotl.equalsIgnoreCase("blank"))))) {
////            return;
////        }
////        if (pLoc.equals(ladder)) {
////            for (Player kotlPlayers : playersInRegion) {
////                TitleAPI.sendActionBar(kotlPlayers, ChatColor.RED + "" + ChatColor.BOLD + p.getName() + strings.kotl_newLeader);
////                if (kotl.equalsIgnoreCase(kotlPlayers.getName())) {
////                    TitleAPI.sendActionBar(kotlPlayers, strings.kotl_noLongerLeader);
////                    Main.kotl_secondsAsKotl.remove(p.getUniqueId());
////                }
////            }
////            kotl = p.getName();
////        }
////
////    }
////
////    //Location box1 = new Location(Bukkit.getServer().getWorld("world"), 927, 44, 613); //927 44 613 - box 2
////    //Location box2 = new Location(Bukkit.getServer().getWorld("world"), 911, 1, 597); // 911 1 597 - box 1
////    //Location box1 = new Location(Bukkit.getServer().getWorld("world"), 131, 4, -237);
////    //Location box2 = new Location(Bukkit.getServer().getWorld("world"), 95, 21, -201);
////
////
//}

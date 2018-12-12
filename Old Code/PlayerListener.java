//package me.yeroc.PlasmaHub.listeners;
//
//import me.yeroc.PlasmaHub.Main;
//import me.yeroc.PlasmaHub.managers.Configs;
//import me.yeroc.PlasmaHub.managers.PermissionsManager;
//import me.yeroc.PlasmaHub.managers.PlayerFileManager;
//import me.yeroc.PlasmaHub.managers.Strings;
//import me.yeroc.PlasmaHub.minigames.maze.Maze;
//import me.yeroc.PlasmaHub.minigames.parkour.Parkour;
//import me.yeroc.PlasmaHub.minigames.pvp.PvP;
//import me.yeroc.PlasmaHub.serverselector.ServerSelector;
//import me.yeroc.PlasmaHub.utils.API;
//import me.yeroc.PlasmaHub.utils.rewards.GemsManager;
//import me.yeroc.PlasmaHub.utils.rewards.RewardsManager;
//import org.bukkit.Bukkit;
//import org.bukkit.GameMode;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.block.BlockBreakEvent;
//import org.bukkit.event.entity.EntityDamageEvent;
//import org.bukkit.event.entity.FoodLevelChangeEvent;
//import org.bukkit.event.player.*;
//
//public class PlayerListener implements Listener {
//    public PlayerListener(Main main) {
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
//    @EventHandler
//    public void onLoseHunger(FoodLevelChangeEvent e) {
//        e.setCancelled(true);
//    }
//
//    @EventHandler
//    public void onDropItem(PlayerDropItemEvent e) {
//        if (!(e.getPlayer().getGameMode().equals(GameMode.CREATIVE))) {
//            e.setCancelled(true);
//        }
//    }
//
//    @EventHandler
//    public void onPlayerBreakBlock(BlockBreakEvent e) {
//        if (!(e.getPlayer().getGameMode().equals(GameMode.CREATIVE))) {
//            e.setCancelled(true);
//        }
//    }
//
//    @EventHandler
//    public void onEntityDamage(EntityDamageEvent e) {
//
//    }
//
////    @EventHandler
////    public void onDeath(PlayerFight e) {
////        e.setDeathMessage(null);
////        Player p = e.getEntity();
////        Player k = e.
////    }
//
//    @EventHandler
//    public void onAchievement(PlayerAchievementAwardedEvent e) {
//        e.setCancelled(true);
//    }
//
//    @EventHandler
//    public void onMove(PlayerMoveEvent e) {
//        if (e.getPlayer().getLocation().getY() <= -10) {
//            e.getPlayer().teleport(Main.spawn);
//        }
//    }
////        Player p = e.getPlayer();
//////        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sudo TheManiacGamers c:Yes I can, bitch.");
////        Location spawnDiamondBlock = new Location(Bukkit.getWorld("world"), 919, 5, 571);
////        if (e.getPlayer().getLocation().getBlock().getRelative(0, -1, 0).getType().equals(Material.DIAMOND_BLOCK)) {
////            if (e.getPlayer().getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(spawnDiamondBlock)) {
////                if (Main.canDoubleJump.get(p.getUniqueId()) == null) {
////                    Main.canDoubleJump.put(p.getUniqueId(), "no");
////                } else {
////                    Main.canDoubleJump.put(p.getUniqueId(), "yes");
////                }
////                if (Main.parkour_playerCheckpoints.get(p.getUniqueId()) == null) {
////                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
////                }
////                if (!Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("zero")) {
////                    p.sendMessage(strings.parkour_stop);
////                    Main.parkour_isInParkour.put(p.getUniqueId(), "no");
////                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
////                }
////                if (Main.maze_isInMaze.get(p.getUniqueId()) == null) {
////                    Main.maze_isInMaze.put(p.getUniqueId(), "no");
////                }
////                if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
////                    p.sendMessage(strings.maze_stop);
////                    Main.maze_isInMaze.put(p.getUniqueId(), "no");
////                }
////            }
////        }
////    }
//
//    @EventHandler
//    public void onJoin(PlayerJoinEvent e) {
//        Player p = e.getPlayer();
//        p.teleport(Main.spawn);
//        p.setGameMode(GameMode.SURVIVAL);
//        api.resetInventory(p);
//        if (p.hasPermission(perms.plasma_join_notify)) {
//            Bukkit.broadcastMessage(strings.join + p.getName());
//        }
//        e.setJoinMessage(null);
//        pfm.load(p);
//        p.getInventory().setHeldItemSlot(0);
//    }
//
//    @EventHandler
//    public void onQuit(PlayerQuitEvent e) {
//        Player p = e.getPlayer();
//        p.getInventory().clear();
//        if (p.hasPermission(perms.plasma_quit_notify)) {
//            Bukkit.broadcastMessage(strings.quit + p.getName());
//        }
//        e.setQuitMessage(null);
//        pfm.save(p);
//    }
//
//
//
//}

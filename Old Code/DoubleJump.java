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
//import org.bukkit.GameMode;
//import org.bukkit.Material;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerJoinEvent;
//import org.bukkit.event.player.PlayerMoveEvent;
//import org.bukkit.event.player.PlayerToggleFlightEvent;
//
///**
// * Created by Corey on 28/11/2018.
// */
//public class DoubleJump implements Listener {
//    private Main plugin;
//
//    private static DoubleJump instance = new DoubleJump();
//
//    public DoubleJump(Main plugin) {
//        this.plugin = plugin;
//    }
//
//    private DoubleJump() {
//    }
//
//    public static DoubleJump getInstance() {
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
//    @EventHandler
//    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
//        Player p = event.getPlayer();
//        if (p.getGameMode() == GameMode.SURVIVAL) {
//            if (Main.canDoubleJump.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
//                if (p.hasPermission(perms.plasma_doublejump_use)) {
//                    event.setCancelled(true);
//                    p.setAllowFlight(false);
//                    p.setFlying(false);
//                    p.setVelocity(p.getLocation().getDirection().multiply(2.5).setY(1.0));
//                }
//            }
//        }
//    }
//
//    @EventHandler
//    public void onPlayerMove(PlayerMoveEvent e) {
//        Player p = e.getPlayer();
//        if ((p.getGameMode() == GameMode.SURVIVAL) && (p.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) && (!p.isFlying())) {
//            if (Main.canDoubleJump.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
//                p.setAllowFlight(true);
//            } else {
//                p.setAllowFlight(false);
//            }
//        }
//    }
//
//    @EventHandler
//    public void onPlayerJoin(PlayerJoinEvent e) {
//        Player p = e.getPlayer();
//        p.setAllowFlight(true);
//        p.setFlying(false);
//        Main.canDoubleJump.put(p.getUniqueId(), "yes");
//    }
//}

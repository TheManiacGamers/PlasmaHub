package me.yeroc.PlasmaHub;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.PlasmaHub.managers.Configs;
import me.yeroc.PlasmaHub.managers.PermissionsManager;
import me.yeroc.PlasmaHub.managers.PlayerFileManager;
import me.yeroc.PlasmaHub.managers.Strings;
import me.yeroc.PlasmaHub.utils.API;
import me.yeroc.PlasmaHub.utils.TitleAPI.TitleAPI;
import me.yeroc.PlasmaHub.utils.rewards.GemsManager;
import me.yeroc.PlasmaHub.utils.rewards.RewardsManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Corey on 4/12/2018.
 */
public class PlayerListener extends BukkitRunnable implements Listener {

    private Main plugin;

    private static PlayerListener instance = new PlayerListener();

    public PlayerListener(Main plugin) {
        this.plugin = plugin;
    }

    private PlayerListener() {
    }

    public static PlayerListener getInstance() {
        return instance;
    }

    private API api = API.getInstance();
    private PermissionsManager perms = PermissionsManager.getInstance();
    private Strings strings = Strings.getInstance();
    private Configs configs = Configs.getInstance();
    private RewardsManager rewards = RewardsManager.getInstance();
    private GemsManager gems = GemsManager.getInstance();
    private PlayerFileManager pfm = PlayerFileManager.getInstance();

    private World w = Bukkit.getServer().getWorld("world");
    public String mitchell = ("eee3a024-f05f-45f1-a741-b2938fff4f44");
    public String corey = ("3d87ff2a-90e9-4e00-acac-1338331b595d");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.teleport(Main.spawn);
        p.setGameMode(GameMode.SURVIVAL);
        if (p.getPlayer().getUniqueId().equals(UUID.fromString(mitchell)) || (p.getPlayer().getUniqueId().equals(UUID.fromString(corey)))) {
            p.setDisplayName(ChatColor.GRAY + "[" + ChatColor.GREEN + pfm.getLevel(p) + ChatColor.GRAY + "]" + strings.getMessage("ownerPrefix") + p.getName() + ChatColor.RESET); // ChatColor.GOLD + "[" + ChatColor.DARK_RED + "Owner" + ChatColor.GOLD + "]" + ChatColor.RED
        } else {
            p.setDisplayName(ChatColor.GRAY + "[" + ChatColor.GREEN + pfm.getLevel(p) + ChatColor.GRAY + "]" + strings.getMessage("sparkPrefix") + p.getName() + ChatColor.RESET); // ChatColor.AQUA + "[" + ChatColor.BLUE + "Spark" + ChatColor.AQUA + "]" + ChatColor.GRAY
        }
        api.resetInventory(p);
        if (p.hasPermission(perms.plasma_join_notify)) {
            Bukkit.broadcastMessage(strings.getMessage("join") + p.getName());
        }
        e.setJoinMessage(null);
        pfm.load(p);
        pfm.addJoins(p, 1);
        Main.log(strings.getMessage("pfLoaded") + p.getName());
        p.getInventory().setHeldItemSlot(0);
        p.setAllowFlight(true);
        p.setFlying(false);
        //SETTING HASHMAPS
        api.resetPlayerHashMap(p);
        // KOTL
        if (!(Main.kotl_player.equalsIgnoreCase("blank"))) {
            TitleAPI.sendActionBar(p, Main.kotl_player + strings.getMessage("kotl_currentLeader"));
        }
        Main.canDoubleJump.put(p.getUniqueId(), "yes");
        Main.maze_isInMaze.put(p.getUniqueId(), "no");
        Main.parkour_isInParkour.put(p.getUniqueId(), "no");
        Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
        p.sendMessage(strings.getMessage("server") + ChatColor.RED + " The PlasmaHub plugin's messaging system underwent an overhaul. If you see any message errors, please contact an Owner.");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        p.getInventory().clear();
        if (p.hasPermission(perms.plasma_quit_notify)) {
            Bukkit.broadcastMessage(strings.getMessage("quit") + p.getName());
        }
        e.setQuitMessage(null);
        api.resetPlayerHashMap(p);
        pfm.save(p);
        if (Main.kotl_player.equalsIgnoreCase(p.getName())) {
            if (Bukkit.getOnlinePlayers().size() != 0) {
                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    TitleAPI.sendActionBar(onlinePlayers, strings.getMessage("kotl_leaderLeft"));

                }
            }
            Main.kotl_player = "blank";
        }
        Main.canDoubleJump.put(p.getUniqueId(), "yes");
        Main.maze_isInMaze.put(p.getUniqueId(), "no");
        Main.parkour_isInParkour.put(p.getUniqueId(), "no");
        Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
        if (Main.kotl_playersInRegion.contains(p.getUniqueId())) {
            Main.kotl_playersInRegion.remove(p.getUniqueId());
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        api.resetInventory(p);
        api.resetPlayerHashMap(p);
        e.setRespawnLocation(Main.spawn);
//        p.teleport(Main.spawn);
    }

    private void giveParkourCheckpointItem(Player p) {
        p.getInventory().setItem(7, getItemStack(p, "parkourCheckpoint"));
        p.updateInventory();
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.getLocation().getY() <= -10) {
            p.teleport(Main.spawn);
        }
        if (Main.kotl_toldInRegion.get(p.getUniqueId()) == null) {
            Main.kotl_toldInRegion.put(p.getUniqueId(), "no");
        }
        // DOUBLE JUMP
        if ((p.getGameMode() == GameMode.SURVIVAL) && (p.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) && (!p.isFlying())) {
            if (Main.canDoubleJump.get(p.getUniqueId()) == null) {
                Main.canDoubleJump.put(p.getUniqueId(), "yes");
            }
            if (Main.canDoubleJump.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                if (p.hasPermission(perms.plasma_doublejump_use)) {
                    p.setAllowFlight(true);
                }
            } else {
                p.setAllowFlight(false);
            }
        }
        // KOTL CHECKING IF PLAYER IS IN LOCATION
        if (locationIsInCuboid(p, Main.kotl_box1, Main.kotl_box2)) {
            if (p.hasPermission(perms.plasma_kotl_use)) {
                if (p.getGameMode().equals(GameMode.CREATIVE)) {
                    return;
                }
                p.setAllowFlight(false);
                p.setFlying(false);
                if (p.hasPermission(perms.plasma_doublejump_bypass)) {
                    p.setAllowFlight(true);
                }
                // REMOVING PLAYER FROM ALL OTHER GAMES
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
                if (Main.canDoubleJump.get(p.getUniqueId()) == null || (Main.canDoubleJump.get(p.getUniqueId()).equalsIgnoreCase("yes"))) {
                    if (!p.hasPermission(perms.plasma_doublejump_bypass)) {
                        Main.canDoubleJump.put(p.getUniqueId(), "no");
                    }
                }
                // CONTINUE
                if (Main.kotl_toldInRegion.get(p.getUniqueId()).equalsIgnoreCase("no")) {
                    TitleAPI.sendActionBar(p, strings.getMessage("kotl_enter"));
                    Main.kotl_toldInRegion.put(p.getUniqueId(), "yes");
                    Main.kotl_playerInRegion.put(p.getUniqueId(), "yes");
                    if (!p.hasPermission(perms.plasma_doublejump_bypass)) {
                        Main.canDoubleJump.put(p.getUniqueId(), "no");
                    }
                    Main.kotl_playersInRegion.add(p.getUniqueId());
                }

                if (p.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(Main.kotl_ladder)) {
                    if (!(Main.kotl_player.equalsIgnoreCase(p.getName()))) {
                        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                            if (locationIsInCuboid(onlinePlayers, Main.kotl_box1, Main.kotl_box2)) {
                                if (p.getName().equalsIgnoreCase(Main.kotl_player)) {
                                    TitleAPI.sendActionBar(onlinePlayers, strings.getMessage("kotl_noLongerLeader"));
                                } else if (!(p.getName().equals(onlinePlayers.getName()) || (!(onlinePlayers.getName().equalsIgnoreCase(Main.kotl_player))))) {
                                    TitleAPI.sendActionBar(onlinePlayers, p.getName() + strings.getMessage("kotl_newLeader"));
                                } else {
                                    TitleAPI.sendActionBar(onlinePlayers, p.getName() + strings.getMessage("kotl_newLeader"));
                                }
                            }
                        }
                        Main.kotl_player = p.getName();
                        TitleAPI.sendActionBar(p, strings.getMessage("kotl_youAreNewLeader"));
                    }
                }
            }
        }
        if (!locationIsInCuboid(p, Main.kotl_box1, Main.kotl_box2) && (Main.kotl_toldInRegion.get(p.getUniqueId()).equalsIgnoreCase("yes"))) {
            TitleAPI.sendActionBar(p, strings.getMessage("kotl_leave"));
            Main.kotl_playersInRegion.remove(p.getUniqueId());
            api.resetPlayerHashMap(p);
        }
        if (p.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(Main.maze_start)) {
            // REMOVING PLAYER FROM PARKOUR
            if (p.hasPermission(perms.plasma_maze_use)) {
                if (Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                    Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
                    p.sendMessage(strings.getMessage("parkour_stop"));
                    Main.canDoubleJump.put(p.getUniqueId(), "yes");
                }
                if (Main.maze_isInMaze.get(p.getUniqueId()) == null) {
                    Main.maze_isInMaze.put(p.getUniqueId(), "yes");
                    Main.canDoubleJump.put(p.getUniqueId(), "yes");
                    p.sendMessage(strings.getMessage("maze_start"));
                }
                if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("no")) {
                    Main.maze_isInMaze.put(p.getUniqueId(), "yes");
                    Main.canDoubleJump.put(p.getUniqueId(), "yes");
                    p.sendMessage(strings.getMessage("maze_start"));


                }
            } else {
                if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("no")) {
                    p.sendMessage(strings.getMessage("maze_noPermission"));
                    Main.maze_isInMaze.put(p.getUniqueId(), "denied");
                }
            }
            return;
        }
        if (p.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(Main.parkour_startBlock)) {
            if (p.hasPermission(perms.plasma_parkour_use)) {
                if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("zero")) {
                    if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                        Main.maze_isInMaze.put(p.getUniqueId(), "no");
                        p.sendMessage(strings.getMessage("maze_stop"));
                    }
                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "one");
                    giveParkourCheckpointItem(p);
                    Main.canDoubleJump.put(p.getUniqueId(), "no");
                    Main.parkour_isInParkour.put(p.getUniqueId(), "yes");
                    p.sendMessage(strings.getMessage("parkour_start"));
                }
            } else {
                if (Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("no")) {
                    p.sendMessage(strings.getMessage("parkour_noPermission"));
                    Main.parkour_isInParkour.put(p.getUniqueId(), "denied");
                }
            }
            return;
        }
        if (p.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(Main.parkour_chp1block)) {
            if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("one")) {
                Main.parkour_playerCheckpoints.put(p.getUniqueId(), "two");
                TitleAPI.sendActionBar(p, strings.getMessage("parkour_chp") + "1");
                Main.parkour_isInParkour.put(p.getUniqueId(), "yes");
            }
            return;
        }
        if (p.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(Main.parkour_chp2block)) {
            if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("two")) {
                Main.parkour_playerCheckpoints.put(p.getUniqueId(), "three");
                TitleAPI.sendActionBar(p, strings.getMessage("parkour_chp") + "2");
                Main.parkour_isInParkour.put(p.getUniqueId(), "yes");
            }
            return;
        }
        if (p.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(Main.parkour_chp3block)) {
            if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("three")) {
                Main.parkour_playerCheckpoints.put(p.getUniqueId(), "four");
                TitleAPI.sendActionBar(p, strings.getMessage("parkour_chp") + "3");
                Main.parkour_isInParkour.put(p.getUniqueId(), "yes");
            }
            return;
        }
        if (p.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(Main.parkour_chp4block)) {
            if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("four")) {
                Main.parkour_playerCheckpoints.put(p.getUniqueId(), "five");
                TitleAPI.sendActionBar(p, strings.getMessage("parkour_chp") + "4");
                Main.parkour_isInParkour.put(p.getUniqueId(), "yes");
            }
            return;
        }
        if (p.getLocation().getBlock().getRelative(0, -1, 0).getLocation().equals(Main.parkour_finishBlock)) {
            if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("five")) {
                Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
                String date = format.format(now);
                if (Main.pfm_completedParkour.get(p.getUniqueId()) == null) {
                    Main.pfm_completedParkour.put(p.getUniqueId(), "no");
                }
                if (Main.pfm_completedParkour.get(p.getUniqueId()).equalsIgnoreCase("no")) {
                    p.teleport(Main.spawn);
                    p.getInventory().setItem(2, new ItemStack(Material.AIR, 1));
                    Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                    Main.pfm_completedParkour.put(p.getUniqueId(), date);
                    Main.canDoubleJump.put(p.getUniqueId(), "yes");
                    p.sendMessage(strings.getMessage("parkour_finish"));
                    return;
                }
                if (Main.pfm_completedParkour.get(p.getUniqueId()).equals(date)) {
                    if (!p.hasPermission(perms.plasma_minigame_bypass)) {
                        p.getInventory().setItem(7, new ItemStack(Material.AIR, 1));
                        p.sendMessage(strings.getMessage("parkour_alreadyCompleted"));
                        p.sendMessage(strings.getMessage("minigame_onceADay"));
                        Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                        Main.canDoubleJump.put(p.getUniqueId(), "yes");
                        p.teleport(Main.spawn);
                        return;
                    }
                    p.teleport(Main.spawn);
                    p.getInventory().setItem(7, new ItemStack(Material.AIR, 1));
                    Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                    Main.pfm_completedParkour.put(p.getUniqueId(), date);
                    p.sendMessage(strings.getMessage("minigame_bypass"));
                    p.sendMessage(strings.getMessage("parkour_finish"));
                    p.teleport(Main.spawn);
                    if (Main.canDoubleJump.get(p.getUniqueId()) == null) {
                        Main.canDoubleJump.put(p.getUniqueId(), "yes");
                    }
                    Main.canDoubleJump.put(p.getUniqueId(), "yes");
                    Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                } else {
                    p.teleport(Main.spawn);
                    p.getInventory().setItem(7, new ItemStack(Material.AIR, 1));
                    p.sendMessage(strings.getMessage("parkour_error"));
                }
            } else {
                p.teleport(Main.spawn);
                p.getInventory().setItem(7, new ItemStack(Material.AIR, 1));
                p.sendMessage(strings.getMessage("parkour_error"));
            }
        }

    }


//    @EventHandler
//    public void onPlayerDeath(PlayerDeathEvent e) {
//        Player p = e.getEntity();
//        if (Main.kotl_player.equals(p.getName())) {
//            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
//                TitleAPI.sendActionBar(onlinePlayers, strings.getMessage("kotl_died);
//                Main.kotl_player = "blank";
//                Main.kotl_secondsAsKotl.remove(p.getUniqueId());
//                Main.kotl_playersInRegion.remove(p.getUniqueId());
//            }
//        }
//        if (!Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
//
//        }
//        e.setDeathMessage(null);
//    }


    public void run() {
        if (Bukkit.getServer().getOnlinePlayers().size() != 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
//                    TitleAPI.sendTitle(p, 0, 25, 0, strings.getMessage("parkour_isInParkour);

                    return;
                }
                if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
//                    TitleAPI.sendTitle(p, 0, 25, 0, strings.getMessage("maze_youAreInMaze);
                    return;
                }
                if (Main.kotl_player.equalsIgnoreCase(null) || (Main.kotl_player.equalsIgnoreCase("blank"))) {
                    return;
                }
                if (Main.kotl_player.equalsIgnoreCase(p.getName())) {
                    if (Bukkit.getOnlinePlayers().size() >= 2 && (Main.kotl_playersInRegion.size() >= 2) && (Main.kotl_playerInRegion.get(p.getUniqueId()).equalsIgnoreCase("yes"))) {
                        if (Main.kotl_secondsAsKotl.get(p.getUniqueId()) == null) {
                            Main.kotl_secondsAsKotl.put(p.getUniqueId(), 1);
                            return;
                        }
                        int kotlTime = (Main.kotl_secondsAsKotl.get(p.getUniqueId()));
                        int kotlTimeNew = kotlTime + 1;
                        Main.kotl_secondsAsKotl.put(p.getUniqueId(), kotlTimeNew);
                    }
                } else {
                    Main.kotl_secondsAsKotl.remove(p.getUniqueId());
                }
            }
        }
    }

//    public void kotl_checkOnLadder(Player p) {
//
//    }

    private Date now = new Date();
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat systemFormat = new SimpleDateFormat("dd-MM hh:mm:ss");

    private void removeEntity(Entity e) {
        if (e instanceof Arrow) {
            e.remove();
        }
    }

    @EventHandler
    public void onPlayerShift(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        if (Main.isSitting.get(p.getUniqueId()) == null) {
            Main.isSitting.put(p.getUniqueId(), "no");
        }
        if (Main.arrow.get(p.getUniqueId()) == null) {
            return;
        }
        if (Main.isSitting.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
            Main.arrow.get(p.getUniqueId()).getVehicle().leaveVehicle();
            removeEntity(Main.arrow.get(p.getUniqueId()));
        }
    }

    // maze = heavy weighted pressure plate
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block b = e.getClickedBlock(); // 012345 = stopped, maze_start, chp1, chp2, chp3, chp4
        if (Main.maze_isInMaze.get(p.getUniqueId()) == null)

        {
            Main.maze_isInMaze.put(p.getUniqueId(), "no");
        }
        if (Main.parkour_isInParkour.get(p.getUniqueId()) == null)

        {
            Main.parkour_isInParkour.put(p.getUniqueId(), "no");
        }
        if (e.getAction().

                equals(Action.RIGHT_CLICK_BLOCK))

        {
            if (p.getItemInHand().getType().equals(Material.NETHER_STAR)) {
                if (!(p.hasPermission(perms.plasma_gadgetsmenu_use))) {
                    if (p.getOpenInventory() != null) {
                        p.closeInventory();
                    }
                    p.sendMessage(strings.getMessage("noPermission"));
                    return;
                }
            }
            if (p.getItemInHand().getType().equals(Material.MUSIC_DISC_CAT)) {
                if (!(p.hasPermission(perms.plasma_music_use))) {
                    if (p.getOpenInventory() != null) {
                        p.closeInventory();
                    }
                    p.sendMessage(strings.getMessage("noPermission"));
                    return;
                }
            }
            // HUB GADGETS EYE OF ENDER RANDOM TELEPORTER
            if (p.getItemInHand().getType().equals(Material.ENDER_EYE)) {
                if (Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                    Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
                    p.sendMessage(strings.getMessage("parkour_stop"));
                }
                if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                    Main.maze_isInMaze.put(p.getUniqueId(), "no");
                    p.sendMessage(strings.getMessage("parkour_stop"));
                }
                Main.canDoubleJump.put(p.getUniqueId(), "yes");
                return;
            }
            // HUB GADGETS STICK TELEPORTED
            if (p.getItemInHand().getType().equals(Material.STICK)) {
                if (Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                    Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
                    p.sendMessage(strings.getMessage("parkour_stop"));
                }
                if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                    Main.maze_isInMaze.put(p.getUniqueId(), "no");
                    p.sendMessage(strings.getMessage("parkour_stop"));
                }
                Main.canDoubleJump.put(p.getUniqueId(), "yes");
                return;
            }
            if (p.getItemInHand().getType().equals(Material.MUSIC_DISC_CAT)) {
                Bukkit.dispatchCommand(p, "music");
                return;
            }
            if (e.getClickedBlock().getType().equals(Material.QUARTZ_STAIRS)) {
                Location l = e.getClickedBlock().getLocation();
                World w = p.getWorld();
                Main.arrow.put(p.getUniqueId(), w.spawn(l.add(0.5D, -0.25D, 0.4D), Arrow.class));
                Main.arrow.get(p.getUniqueId()).setSilent(true);
                Main.arrow.get(p.getUniqueId()).setPassenger(p);
                return;
            }
            if (e.getClickedBlock().getType().equals(Material.GOLD_BLOCK)) {
                if (p.hasPermission(perms.plasma_maze_use)) {
                    if (locationIsInCuboid(p, Main.mazebox1, Main.mazebox2)) {// if (e.getClickedBlock().getLocation().equals(Main.maze_1_finish)) {
                        if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                            String date = format.format(now);
                            String systemDate = systemFormat.format(now);
                            if (Main.pfm_completedMaze.get(p.getUniqueId()) == null) {
                                Main.pfm_completedMaze.put(p.getUniqueId(), "no");
                            }
                            if (Main.pfm_completedMaze.get(p.getUniqueId()).equalsIgnoreCase("no")) {
                                p.teleport(Main.spawn);
                                Main.maze_isInMaze.put(p.getUniqueId(), "no");
                                Main.pfm_completedMaze.put(p.getUniqueId(), date);
                                p.sendMessage(strings.getMessage("maze_finish"));
                                return;
                            }
                            if (Main.pfm_completedMaze.get(p.getUniqueId()).equals(date)) {
                                if (!p.hasPermission(perms.plasma_minigame_bypass)) {
                                    p.sendMessage(strings.getMessage("maze_alreadyCompleted"));
                                    p.sendMessage(strings.getMessage("minigame_onceADay") + systemDate);
                                    Main.maze_isInMaze.put(p.getUniqueId(), "no");
                                    p.teleport(Main.spawn);
                                    return;
                                }
                            }
                            Main.maze_isInMaze.put(p.getUniqueId(), "no");
                            Main.pfm_completedMaze.put(p.getUniqueId(), date);
                            p.sendMessage(strings.getMessage("maze_finish"));
                            p.teleport(Main.spawn);
                        } else {
                            p.sendMessage(strings.getMessage("maze_notStarted"));
                            p.teleport(Main.spawn);
                        }
                        return;
                    }
                } else {
                    p.sendMessage(strings.getMessage("maze_noPermission"));
                    p.teleport(Main.spawn);
                    Main.maze_isInMaze.put(p.getUniqueId(), "denied");
                }

            }
            if (p.getItemInHand().getType().equals(Material.COMPASS)) {
                if (p.hasPermission(perms.plasma_serverselector_use)) {
                    openSelector(p);
                } else {
                    p.sendMessage(strings.getMessage("noPermission"));
                }
                return;
            }
            if (p.getItemInHand().getType().equals(Material.ENDER_PEARL)) {
                if (p.hasPermission(perms.plasma_parkour_use)) {
                    if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("zero")) {
                        p.sendMessage(strings.getMessage("parkour_error"));
                        p.getInventory().setItem(7, new ItemStack(Material.AIR, 1));
                        return;
                    }
                    if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("one")) {
                        p.teleport(Main.parkour_startBlock.add(0, 1, 0));
                        return;
                    }
                    if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("two")) {
                        p.teleport(Main.parkour_chp1block.add(0, 1, 0));
                        return;
                    }
                    if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("three")) {
                        p.teleport(Main.parkour_chp2block.add(0, 1, 0));
                        return;
                    }
                    if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("four")) {
                        p.teleport(Main.parkour_chp3block.add(0, 1, 0));
                        return;
                    }
                    if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("five")) {
                        p.teleport(Main.parkour_chp4block.add(0, 1, 0));
                        return;
                    }
                }
            }
            return;
        }
        if (e.getAction().

                equals(Action.RIGHT_CLICK_AIR))

        {
            if (p.getItemInHand().getType().equals(Material.NETHER_STAR)) {
                if (!(p.hasPermission(perms.plasma_gadgetsmenu_use))) {
                    if (p.getOpenInventory() != null) {
                        p.closeInventory();
                    }
                    p.sendMessage(strings.getMessage("noPermission"));
                    return;
                }
            }
            if (p.getItemInHand().getType().equals(Material.MUSIC_DISC_CAT)) {
                if (!(p.hasPermission(perms.plasma_music_use))) {
                    if (p.getOpenInventory() != null) {
                        p.closeInventory();
                    }
                    p.sendMessage(strings.getMessage("noPermission"));
                    return;
                }
            }
            // HUB GADGETS RANDOM TELEPORTER
            if (p.getItemInHand().getType().equals(Material.STICK)) {
                if (Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                    Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
                    p.sendMessage(strings.getMessage("parkour_stop"));
                }
                if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                    Main.maze_isInMaze.put(p.getUniqueId(), "no");
                    p.sendMessage(strings.getMessage("parkour_stop"));
                }
                Main.canDoubleJump.put(p.getUniqueId(), "yes");
                return;
            }
            // HUB GADGETS EYE OF ENDER TELEPORTED
            if (p.getItemInHand().getType().equals(Material.ENDER_EYE)) {
                if (Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                    Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
                    p.getInventory().setItem(7, new ItemStack(Material.AIR, 1));
                    p.sendMessage(strings.getMessage("parkour_stop"));
                }
                if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                    Main.maze_isInMaze.put(p.getUniqueId(), "no");
                    p.sendMessage(strings.getMessage("maze_stop"));
                }
                Main.canDoubleJump.put(p.getUniqueId(), "yes");
                return;
            }
            if (p.getItemInHand().getType().equals(Material.MUSIC_DISC_CAT)) {
                Bukkit.dispatchCommand(p, "music");
                return;
            }
            if (p.getItemInHand().getType().equals(Material.COMPASS)) {
                if (p.hasPermission(perms.plasma_serverselector_use)) {
                    openSelector(p);
                } else {
                    p.sendMessage(strings.getMessage("noPermission"));
                }
            }
            if (p.getItemInHand().getType().equals(Material.ENDER_PEARL)) {
                if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("zero")) {
                    p.getInventory().setItem(7, new ItemStack(Material.AIR, 1));
                    p.sendMessage(strings.getMessage("parkour_error"));
                    return;
                }
                if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("one")) {
                    p.teleport(Main.parkour_start);
                    return;
                }
                if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("two")) {
                    p.teleport(Main.parkour_chp1);
                    return;
                }
                if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("three")) {
                    p.teleport(Main.parkour_chp2);
                    return;
                }
                if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("four")) {
                    p.teleport(Main.parkour_chp3);
                    return;
                }
                if (Main.parkour_playerCheckpoints.get(p.getUniqueId()).equalsIgnoreCase("five")) {
                    p.teleport(Main.parkour_chp4);
                    return;
                }
            }
            return;
        }
        if (e.getAction().

                equals(Action.LEFT_CLICK_BLOCK))

        {
            if (p.getItemInHand().getType().equals(Material.COMPASS)) {
                if (p.hasPermission(perms.plasma_serverselector_use)) {
                    openSelector(p);
                } else {
                    p.sendMessage(strings.getMessage("noPermission"));
                }
            }
        }
        if (e.getAction().

                equals(Action.LEFT_CLICK_AIR))

        {
            if (p.getItemInHand().getType().equals(Material.COMPASS)) {
                if (p.hasPermission(perms.plasma_serverselector_use)) {
                    openSelector(p);
                } else {
                    p.sendMessage(strings.getMessage("noPermission"));
                }
            }
        }

    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onLoseHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        if (!(e.getPlayer().getGameMode().equals(GameMode.CREATIVE))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e) {
        if (!(e.getPlayer().getGameMode().equals(GameMode.CREATIVE))) {
            e.setCancelled(true);
        }
    }


    // DOUBLE JUMP
    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player p = event.getPlayer();
        if (p.getGameMode() == GameMode.SURVIVAL) {
            if (Main.canDoubleJump.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                if (p.hasPermission(perms.plasma_doublejump_use)) {
                    if (Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                        p.setFlying(false);
                        p.setAllowFlight(false);
                        return;
                    }
                    if (Main.canDoubleJump.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                        event.setCancelled(true);
                        p.setFlying(false);
                        p.setAllowFlight(false);
                        p.setVelocity(p.getLocation().getDirection().multiply(2.5).setY(1.0));
                    } else {
                        p.setFlying(false);
                        p.setAllowFlight(false);
                    }
                } else {
                    p.setFlying(false);
                    p.setAllowFlight(false);
                }
            }
        }
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
//        String mitchell = ("eee3a024-f05f-45f1-a741-b2938fff4f44");
//        String corey = ("3d87ff2a-90e9-4e00-acac-1338331b595d");
//        if (p.getUniqueId().equals(UUID.fromString(corey)) || p.getUniqueId().equals(UUID.fromString(mitchell))) {
        Player p = e.getPlayer();
        if (p.getPlayer().getUniqueId().equals(UUID.fromString(mitchell)) || (p.getPlayer().getUniqueId().equals(UUID.fromString(corey)))) {
            p.setDisplayName(ChatColor.GRAY + "[" + ChatColor.GREEN + pfm.getLevel(p) + ChatColor.GRAY + "]" + strings.getMessage("ownerPrefix") + p.getName() + ChatColor.RESET); // ChatColor.GOLD + "[" + ChatColor.DARK_RED + "Owner" + ChatColor.GOLD + "]" + ChatColor.RED
        } else {
            p.setDisplayName(ChatColor.GRAY + "[" + ChatColor.GREEN + pfm.getLevel(p) + ChatColor.GRAY + "]" + strings.getMessage("sparkPrefix") + p.getName() + ChatColor.RESET); // ChatColor.AQUA + "[" + ChatColor.BLUE + "Spark" + ChatColor.AQUA + "]" + ChatColor.GRAY
        }
        if (p.getPlayer().getUniqueId().equals(UUID.fromString(mitchell)) || (p.getPlayer().getUniqueId().equals(UUID.fromString(corey)))) {
            if (e.getMessage().equalsIgnoreCase("yes")) {
                if (Main.reloadConfirmed.get(Bukkit.getServer()).equalsIgnoreCase("requested")) {
                    Main.reloadConfirmed.put(Bukkit.getServer(), "confirmed");
                    Commands.getInstance().stillAllowed = true;
                }
            }
            if (e.getMessage().equalsIgnoreCase("no")) {
                if (Main.reloadConfirmed.get(Bukkit.getServer()).equalsIgnoreCase("requested")) {
                    if (Commands.getInstance().reloadStarted) {
                        Commands.getInstance().stillAllowed = false;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onAttacked(EntityDamageByEntityEvent e) {
        if (e.getEntityType().equals(EntityType.PLAYER)) {
            if (e.getDamager().getType().equals(EntityType.PLAYER)) {
                final Player p = (Player) e.getEntity();
                Player d = (Player) e.getDamager();
                if (p.getHealth() - e.getDamage() < 0) {
                    TitleAPI.sendActionBar(p, strings.getMessage("pvp_killedBy") + d.getName());
                    TitleAPI.sendActionBar(d, strings.getMessage("pvp_youKilled") + p.getName());
                    pfm.addKills(d, 1);
                    pfm.addDeaths(p, 1);
                    checkLevelRankup(p);
                    p.setFireTicks(0);
                    p.setFoodLevel(20);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("PlasmaHub"), new BukkitRunnable() {
                        @Override
                        public void run() {
                            p.teleport(Main.spawn);
                            p.getInventory().setHeldItemSlot(7);
                            p.getInventory().setArmorContents(null);
                            p.setHealth(p.getMaxHealth());
                        }
                    }, 10L);
                    return;
                }
                if (!(Main.kotl_playersInRegion.contains(p.getUniqueId()))) {
                    if (!(Main.kotl_playersInRegion.contains(d.getUniqueId()))) {
                        if (Main.kotl_player.equalsIgnoreCase(p.getName())) {
                            if (!d.getItemInHand().getType().equals(Material.DIAMOND_SWORD)) {
                                e.setCancelled(true);
                                return;
                            }
                        }
                        if (p.getItemInHand().getType().equals(Material.DIAMOND_SWORD)) {
                            if (d.getItemInHand().getType().equals(Material.DIAMOND_SWORD)) {
                                return;
                            }
                            TitleAPI.sendActionBar(p, strings.getMessage("pvp_disabled"));
                            e.setCancelled(true);
                            return;
                        }
                        e.setCancelled(true);
                        return;
                    }
                    // player in kotl region
                    return;
                }
                // player in kotl region
                return;
            }
            e.setCancelled(true);
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.setDeathMessage(null);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (p.getOpenInventory().getTopInventory().equals(serverSelector)) {
            if (e.getClick().equals(ClickType.RIGHT) || (e.getClick().equals(ClickType.LEFT))) {
                if (e.getCurrentItem().equals(getItemStack(p, "Creative"))) {
                    e.setCancelled(true);
                    p.closeInventory();
                    if (!p.hasPermission(perms.plasma_server_creative)) {
                        p.sendMessage(strings.getMessage("noPermissionServer"));
                        return;
                    }
                    sendToServer(p, "Creative");
                    p.sendMessage(strings.getMessage("sendToServer") + "Creative");
                } else if (e.getCurrentItem().equals(getItemStack(p, "Survival"))) {
                    e.setCancelled(true);
                    p.closeInventory();
                    if (!p.hasPermission(perms.plasma_server_survival)) {
                        p.sendMessage(strings.getMessage("noPermissionServer"));
                        return;
                    }
                    sendToServer(p, "Survival");
                    p.sendMessage(strings.getMessage("sendToServer") + "Survival");
                } else if (e.getCurrentItem().equals(getItemStack(p, "Skyblock"))) {
                    e.setCancelled(true);
                    p.closeInventory();
                    if (!p.hasPermission(perms.plasma_server_skyblock)) {
                        p.sendMessage(strings.getMessage("noPermissionServer"));
                        return;
                    }
                    sendToServer(p, "Skyblock");
                    p.sendMessage(strings.getMessage("sendToServer") + "Skyblock");
                }
                e.setCancelled(true);
            }
        }
        if (p.getOpenInventory().getTopInventory().getType().equals(InventoryType.PLAYER)) {
            if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                e.setCancelled(true);
            }
            if (p.getOpenInventory().getBottomInventory().getType().equals(InventoryType.PLAYER)) {
                if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    //PVP
    @EventHandler
    public void onPlayerChangeSlot(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        if (e.getNewSlot() == 8 && (p.getGameMode().equals(GameMode.SURVIVAL))) {
            if (p.hasPermission(perms.plasma_pvp_use)) {
                ItemStack Helmet = new ItemStack(Material.IRON_HELMET, 1);
                ItemMeta HelmetMeta = Helmet.getItemMeta();
                HelmetMeta.setDisplayName(ChatColor.GREEN + "PVP Hat");
                HelmetMeta.setUnbreakable(true);
                Helmet.setItemMeta(HelmetMeta);
                ItemStack Chestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
                ItemMeta ChestplateMeta = Chestplate.getItemMeta();
                ChestplateMeta.setDisplayName(ChatColor.GREEN + "PVP Shirt");
                ChestplateMeta.setUnbreakable(true);
                Chestplate.setItemMeta(ChestplateMeta);
                ItemStack Leggings = new ItemStack(Material.IRON_LEGGINGS, 1);
                ItemMeta LeggingsMeta = Leggings.getItemMeta();
                LeggingsMeta.setDisplayName(ChatColor.GREEN + "PVP Shorts (long)");
                LeggingsMeta.setUnbreakable(true);
                Leggings.setItemMeta(LeggingsMeta);
                ItemStack Boots = new ItemStack(Material.IRON_BOOTS, 1);
                ItemMeta BootsMeta = Boots.getItemMeta();
                BootsMeta.setDisplayName(ChatColor.GREEN + "PVP Shoes");
                BootsMeta.setUnbreakable(true);
                Boots.setItemMeta(BootsMeta);
                ItemStack Sword = new ItemStack(Material.DIAMOND_SWORD, 1);
                ItemMeta SwordMeta = Sword.getItemMeta();
                SwordMeta.setDisplayName(ChatColor.GREEN + "Enable PvP");
                SwordMeta.setUnbreakable(true);
                Sword.setItemMeta(SwordMeta);
                p.getInventory().setHelmet(Helmet);
                p.getInventory().setChestplate(Chestplate);
                p.getInventory().setLeggings(Leggings);
                p.getInventory().setBoots(Boots);
                TitleAPI.sendActionBar(p, strings.getMessage("pvp_enabled"));
                p.updateInventory();
                Main.canDoubleJump.put(p.getUniqueId(), "no");

            }
        } else {
            if (e.getPreviousSlot() == 8 && (p.getGameMode().equals(GameMode.SURVIVAL))) {
                ItemStack air = new ItemStack(Material.AIR, 1);
                p.getInventory().setHelmet(air);
                p.getInventory().setChestplate(air);
                p.getInventory().setLeggings(air);
                p.getInventory().setBoots(air);
                TitleAPI.sendActionBar(p, strings.getMessage("pvp_disabled"));
                p.updateInventory();
                Main.canDoubleJump.put(p.getUniqueId(), "yes");
            }
        }
    }


    @EventHandler
    public void onProjectileThrow(ProjectileLaunchEvent e) {
        if (e.getEntity().getType().equals(EntityType.ENDER_PEARL)) {
            e.setCancelled(true);
        }
        Projectile proj = e.getEntity();
        if (proj instanceof Snowball) {
            final Snowball ball = (Snowball) proj;
            ProjectileSource source = ball.getShooter();
            if (source instanceof Player) {
                final Player p = (Player) source;
                if (locationIsInCuboid(p, Main.kotl_box1, Main.kotl_box2)) {
                    p.sendMessage(strings.getMessage("cantUseHere"));
                    e.setCancelled(true);
                    return;
                }
                if (!p.hasPermission(perms.plasma_snowball_use)) {
                    p.sendMessage(strings.getMessage("noPermission"));
                    e.setCancelled(true);
                    return;
                }
                if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.AIR)) {
                    if (!p.getUniqueId().equals(UUID.fromString(mitchell)) || (!p.getUniqueId().equals(UUID.fromString(corey)))) {
                        p.sendMessage(strings.getMessage("snowball_onFloor"));
                        e.setCancelled(true);
                        return;
                    }
                }
                if (Main.parkour_isInParkour.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                    Main.parkour_isInParkour.put(p.getUniqueId(), "no");
                    Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
                    p.sendMessage(strings.getMessage("parkour_stop"));
                    Main.canDoubleJump.put(p.getUniqueId(), "yes");
                    p.getInventory().setItem(7, new ItemStack(Material.AIR, 1));
                }
                if (Main.maze_isInMaze.get(p.getUniqueId()).equalsIgnoreCase("yes")) {
                    Main.maze_isInMaze.put(p.getUniqueId(), "no");
                    p.sendMessage(strings.getMessage("maze_stop"));
                    Main.canDoubleJump.put(p.getUniqueId(), "yes");
                }
                ball.setPassenger(p);
                final int particle = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("PlasmaHub"), new Runnable() {
                    public void run() {
                        if (p.isInsideVehicle()) {
                            if (p.getVehicle().getType().equals(EntityType.SNOWBALL)) {
                                randomParticle(p);
                            }
                        }
                    }
                }, 0, 5);
                if (!p.isInsideVehicle()) {
                    Bukkit.getScheduler().cancelTask(particle);
                }

            }
        }
    }

    private Random rand = new Random();

    private void spawnParticles(Player p, String s) {
        if (s.equalsIgnoreCase("flame")) {
            p.spawnParticle(Particle.FLAME, p.getLocation(), 4);
        }
        if (s.equalsIgnoreCase("fireworksSpark")) {
            p.spawnParticle(Particle.FIREWORKS_SPARK, p.getLocation(), 4);
        }
        if (s.equalsIgnoreCase("cloud")) {
            p.spawnParticle(Particle.CLOUD, p.getLocation(), 4);
        }
        if (s.equalsIgnoreCase("smoke")) {
            p.spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 4);
        }
        if (s.equalsIgnoreCase("lava")) {
            p.spawnParticle(Particle.LAVA, p.getLocation(), 4);
        } else {
            p.spawnParticle(Particle.FLAME, p.getLocation(), 4);
        }
    }

    public void randomParticle(Player p) {
        int i = rand.nextInt(5) + 1;
        if (i == 1) {
            spawnParticles(p, "flame");
        }
        if (i == 2) {
            spawnParticles(p, "fireworksSpark");
        }
        if (i == 3) {
            spawnParticles(p, "cloud");
        }
        if (i == 4) {
            spawnParticles(p, "smoke");
        }
        if (i == 5) {
            spawnParticles(p, "lava");
        } else {
            spawnParticles(p, "flame");
        }
    }

    public void addSnowball(Player p) {
        ItemStack Snowball = new ItemStack(Material.SNOWBALL, 1);
        ItemMeta SnowballMeta = Snowball.getItemMeta();
        SnowballMeta.setDisplayName(com.sk89q.minecraft.util.commands.ChatColor.AQUA + "Flying Snowball!");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "Throw me and travel along the snowball!");
        if (!p.hasPermission(perms.plasma_snowball_use)) {
            lore.add(ChatColor.GREEN + " ");
            lore.add(ChatColor.AQUA + "Permission: " + ChatColor.RED + "NO");
            lore.add(ChatColor.GREEN + " ");
        } else {
            lore.add(ChatColor.GREEN + " ");
            lore.add(ChatColor.AQUA + "Permission: " + ChatColor.GREEN + "YES");
            lore.add(ChatColor.GREEN + " ");
        }
        SnowballMeta.setLore(lore);
        Snowball.setItemMeta(SnowballMeta);
        p.getInventory().setItem(6, Snowball);
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        Projectile proj = e.getEntity();
        if (proj instanceof Snowball) {
            Snowball ball = (Snowball) proj;
            ProjectileSource source = ball.getShooter();
            if (source instanceof Player) {
                Player p = (Player) source;
                addSnowball(p);
                p.teleport(p.getLocation().add(0, 1, 0));
                ball.remove();

            }
        }
    }

    private static Inventory serverSelector = Bukkit.createInventory(null, 27, ChatColor.GREEN + "" + ChatColor.BOLD + "Server Selector");

    public void openSelector(Player p) {
        if (p.hasPermission(perms.plasma_serverselector_use)) {
//            Inventory inv = serverSelector;
//            for (int i = 0; i < inv.getSize(); ) {
//                inv.setItem(i, new ItemStack(Material.COBBLESTONE, i));
//                i++;
//            }
            serverSelector.setItem(11, getItemStack(p, "creative"));
            serverSelector.setItem(13, getItemStack(p, "survival"));
            serverSelector.setItem(15, getItemStack(p, "skyblock"));
            p.openInventory(serverSelector);
        }
    }

    public void sendToServer(Player p, String targetServer) {
//        ByteArrayOutputStream b = new ByteArrayOutputStream();
//        DataOutputStream out = new DataOutputStream(b);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(targetServer);
        p.sendPluginMessage(Bukkit.getPluginManager().getPlugin("PlasmaHub"), "BungeeCord", out.toByteArray());
    }

    // > = larger than. < = less than. BOX 2 MUST BE SMALLER THAN BOX 1. EG: Box 1 x: 927, box 2 must be under that.
    private boolean locationIsInCuboid(Player player, Location box1, Location box2) {
        Location loc = player.getLocation();
        if (loc.getX() > box2.getX() && loc.getX() < box1.getX()) {
            if (loc.getY() > box2.getY() && loc.getY() < box1.getY()) {
                if (loc.getZ() > box2.getZ() && loc.getZ() < box1.getZ()) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private ItemStack getItemStack(Player p, String s) {
        if (s.equalsIgnoreCase("Creative")) {
            ItemStack creativeItem = new ItemStack(Material.BEDROCK, 1);
            ItemMeta creativeMeta = creativeItem.getItemMeta();
            creativeMeta.setDisplayName(ChatColor.GRAY + "-=[" + ChatColor.GREEN + " Creative " + ChatColor.GRAY + "]=-");
            List<String> lore = new ArrayList<String>();
            lore.add("");
            lore.add(ChatColor.BLUE + "Go wild and free, build like you can't stop!");
            if (!p.hasPermission(perms.plasma_server_creative)) {
                lore.add(ChatColor.GREEN + " ");
                lore.add(ChatColor.AQUA + "Permission: " + ChatColor.RED + "NO");
                lore.add(ChatColor.GREEN + " ");
            } else {
                lore.add(ChatColor.GREEN + " ");
                lore.add(ChatColor.AQUA + "Permission: " + ChatColor.GREEN + "YES");
                lore.add(ChatColor.GREEN + " ");
            }
            creativeMeta.setLore(lore);
            creativeItem.setItemMeta(creativeMeta);
            return creativeItem;
        } else if (s.equalsIgnoreCase("Survival")) {
            ItemStack survivalItem = new ItemStack(Material.GRASS_BLOCK, 1);
            ItemMeta survivalMeta = survivalItem.getItemMeta();
            survivalMeta.setDisplayName(ChatColor.GRAY + "-=[" + ChatColor.GREEN + " Survival " + ChatColor.GRAY + "]=-");
            List<String> lore = new ArrayList<String>();
            lore.add("");
            lore.add(ChatColor.BLUE + "Gather, Survive, Gather & Survive!");
            if (!p.hasPermission(perms.plasma_server_survival)) {
                lore.add(ChatColor.GREEN + " ");
                lore.add(ChatColor.AQUA + "Permission: " + ChatColor.RED + "NO");
                lore.add(ChatColor.GREEN + " ");
            } else {
                lore.add(ChatColor.GREEN + " ");
                lore.add(ChatColor.AQUA + "Permission: " + ChatColor.GREEN + "YES");
                lore.add(ChatColor.GREEN + " ");
            }
            survivalMeta.setLore(lore);
            survivalItem.setItemMeta(survivalMeta);
            return survivalItem;
        } else if (s.equalsIgnoreCase("Skyblock")) {
            ItemStack skyblockItem = new ItemStack(Material.GLOWSTONE, 1);
            ItemMeta skyblockMeta = skyblockItem.getItemMeta();
            skyblockMeta.setDisplayName(ChatColor.GRAY + "-=[" + ChatColor.GREEN + " Skyblock " + ChatColor.GRAY + "]=-");
            List<String> lore = new ArrayList<String>();
            lore.add("");
            lore.add(ChatColor.BLUE + "Not for those afraid of heights! " + ChatColor.RED + ":(");
            if (!p.hasPermission(perms.plasma_server_skyblock)) {
                lore.add(ChatColor.GREEN + " ");
                lore.add(ChatColor.AQUA + "Permission: " + ChatColor.RED + "NO");
                lore.add(ChatColor.GREEN + " ");
            } else {
                lore.add(ChatColor.GREEN + " ");
                lore.add(ChatColor.AQUA + "Permission: " + ChatColor.GREEN + "YES");
                lore.add(ChatColor.GREEN + " ");
            }
            skyblockMeta.setLore(lore);
            skyblockItem.setItemMeta(skyblockMeta);
            return skyblockItem;
        } else if (s.equalsIgnoreCase("parkourCheckpoint")) {
            ItemStack parkourCheckpointItem = new ItemStack(Material.ENDER_PEARL, 1);
            ItemMeta parkourCheckpointMeta = parkourCheckpointItem.getItemMeta();
            parkourCheckpointMeta.setDisplayName(ChatColor.GREEN + "Parkour Checkpoint");
            parkourCheckpointMeta.setLore(Arrays.asList(ChatColor.AQUA + "Right click me to return to your last checkpoint!"));
            parkourCheckpointItem.setItemMeta(parkourCheckpointMeta);
            return parkourCheckpointItem;
        } else if (s.equalsIgnoreCase("snowball")) {
            ItemStack Snowball = new ItemStack(Material.SNOWBALL, 1);
            ItemMeta SnowballMeta = Snowball.getItemMeta();
            SnowballMeta.setDisplayName(com.sk89q.minecraft.util.commands.ChatColor.GREEN + "Flying Snowball!");
            SnowballMeta.setUnbreakable(true);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "Throw me and travel along the snowball!");
            if (!p.hasPermission(perms.plasma_snowball_use)) {
                lore.add(ChatColor.GREEN + " ");
                lore.add(ChatColor.GREEN + " ");
                lore.add(ChatColor.AQUA + "Permission: " + ChatColor.RED + "NO");
            }
            SnowballMeta.setLore(lore);
            Snowball.setItemMeta(SnowballMeta);
            return Snowball;
        } else {
            Bukkit.broadcastMessage(strings.getMessage("error") + "ServerSelector");
            return new ItemStack(Material.AIR, 1);
        }
    }

    public void checkLevelRankup(Player p) { // EVERY KILL REMOVES 5 EXPERIENCE TO NEXT LEVEL
        if (Main.pfm_pvpExp.get(p.getUniqueId()) == null) {
            Main.pfm_pvpExp.put(p.getUniqueId(), 50);
        }
        if (Main.pfm_pvpLevel.get(p.getUniqueId()) == null) {
            Main.pfm_pvpLevel.put(p.getUniqueId(), 0);
        }
        if (pfm.getExp(p) == -5) {
            pfm.setExp(p, 0);
            return;
        }
        if (pfm.getLevel(p) == 0) {
            if (pfm.getExp(p) == 0) {
                pfm.addLevel(p, 1);
            }
            pfm.setExp(p, 50);
//            sc.updateScoreboard(p);
            return;
        }
        pfm.takeExp(p, 5);
        if (pfm.getLevel(p) >= 10 && (!(pfm.getLevel(p) <= 11))) { // LEVEL 0 - 10
            if (pfm.getExp(p) == 0) {
                pfm.addLevel(p, 1);
                pfm.setExp(p, 50); // 10 kills each level
                sendMessageLevelRankup(p);
//                sc.updateScoreboard(p);
                return;
            }
            return;
        }
        if (!(pfm.getLevel(p) >= 10) && pfm.getLevel(p) >= 11 && (!(pfm.getLevel(p) >= 16))) { // LEVEL 11 - 15
            if (pfm.getExp(p) == 0) {
                pfm.addLevel(p, 1);
                pfm.setExp(p, 100); // 20 kills each level
                sendMessageLevelRankup(p);
//                sc.updateScoreboard(p);
                return;
            }
            return;
        }
        if (!(pfm.getLevel(p) >= 15) && pfm.getLevel(p) >= 16 && (!(pfm.getLevel(p) >= 21))) { // LEVEL 16-20
            if (pfm.getExp(p) == 0) {
                pfm.addLevel(p, 1);
                pfm.setExp(p, 150); // 30 kills each level
                sendMessageLevelRankup(p);
//                sc.updateScoreboard(p);
                return;
            }
            return;
        }
        if (!(pfm.getLevel(p) >= 20) && pfm.getLevel(p) >= 21 && (!(pfm.getLevel(p) >= 26))) { // LEVEL 21-25
            if (pfm.getExp(p) == 0) {
                pfm.addLevel(p, 1);
                pfm.setExp(p, 250); // 50 kills each level
                sendMessageLevelRankup(p);
//                sc.updateScoreboard(p);
                return;
            }
            if (pfm.getLevel(p) == 25) {
                pfm.addMoney(p, 250);
                pfm.addStrength(p, 250);
                p.sendMessage(strings.getMessage("pfm_pvp1o4left"));
            }
            return;
        }
        if (!(pfm.getLevel(p) >= 25) && pfm.getLevel(p) >= 26 && (!(pfm.getLevel(p) >= 31))) { // LEVEL 26-30
            if (pfm.getExp(p) == 0) {
                pfm.addLevel(p, 1);
                pfm.setExp(p, 300); // 60 kills each level
                sendMessageLevelRankup(p);
//                sc.updateScoreboard(p);
                return;
            }
            return;
        }
        if (!(pfm.getLevel(p) >= 30) && pfm.getLevel(p) >= 31 && (!(pfm.getLevel(p) >= 41))) { // LEVEL 31-40
            if (pfm.getExp(p) == 0) {
                pfm.addLevel(p, 1);
                pfm.setExp(p, 375); // 75 kills each level
                sendMessageLevelRankup(p);
//                sc.updateScoreboard(p);
                return;
            }
            return;
        }
        if (!(pfm.getLevel(p) >= 40) && pfm.getLevel(p) >= 41 && (!(pfm.getLevel(p) >= 51))) { // LEVEL 41-50
            if (pfm.getExp(p) == 0) {
                pfm.addLevel(p, 1);
                pfm.setExp(p, 500); // 100 kills each level
                sendMessageLevelRankup(p);
//                sc.updateScoreboard(p);
                return;
            }
            if (pfm.getLevel(p) == 50) {
                pfm.addMoney(p, 500);
                pfm.addStrength(p, 500);
                p.sendMessage(strings.getMessage("pfm_pvp2o4left"));
            }
            return;
        }
        if (!(pfm.getLevel(p) >= 50) && pfm.getLevel(p) >= 51 && (!(pfm.getLevel(p) >= 76))) { // LEVEL 51-75
            if (pfm.getExp(p) == 0) {
                pfm.addLevel(p, 1);
                pfm.setExp(p, 625); // 125 kills each level
                sendMessageLevelRankup(p);
//                sc.updateScoreboard(p);
                return;
            }
            if (pfm.getLevel(p) == 75) {
                pfm.addMoney(p, 750);
                pfm.addStrength(p, 750);
                p.sendMessage(strings.getMessage("pfm_pvp3o4left"));
            }
            return;
        }
        if (!(pfm.getLevel(p) >= 75) && pfm.getLevel(p) >= 76 && (!(pfm.getLevel(p) >= 91))) { // LEVEL 76-90
            if (pfm.getExp(p) == 0) {
                pfm.addLevel(p, 1);
                pfm.setExp(p, 750); // 150 kills each level
                sendMessageLevelRankup(p);
//                sc.updateScoreboard(p);
                return;
            }
            return;
        }
        if (!(pfm.getLevel(p) >= 90) && pfm.getLevel(p) >= 91 && (!(pfm.getLevel(p) >= 96))) { // LEVEL 91-95
            if (pfm.getExp(p) == 0) {
                pfm.addLevel(p, 1);
                pfm.setExp(p, 875); // 175 kills each level
                sendMessageLevelRankup(p);
//                sc.updateScoreboard(p);
                return;
            }
            return;
        }
        if (!(pfm.getLevel(p) >= 90) && pfm.getLevel(p) >= 91 && (!(pfm.getLevel(p) >= 96))) { // LEVEL 95-99
            if (pfm.getExp(p) == 0) {
                pfm.addLevel(p, 1);
                pfm.setExp(p, 1000); // 200 kills each level
                sendMessageLevelRankup(p);
//                sc.updateScoreboard(p);
                return;
            }
            return;
        }
        if (!(pfm.getLevel(p) >= 99) && pfm.getLevel(p) >= 100 && (!(pfm.getLevel(p) >= 101))) { // LEVEL 100
            if (pfm.getExp(p) == 0) {
                pfm.setExp(p, 1500); // 300 kills
                sendMessageLevelRankup(p);
//                sc.updateScoreboard(p);
                p.sendMessage(strings.getMessage("pfm_pvpFinalLevel"));
                pfm.addMoney(p, 750);
                pfm.addStrength(p, 750);
            }
        }
    }

    private void sendMessageLevelRankup(Player p) {
        p.sendMessage(strings.getMessage("pfm_pvpLevelUp"));
        p.sendMessage(strings.getMessage("pfm_pvpnewLevel") + pfm.getLevel(p));
//        p.sendMessage(strings.getMessage("defaultMsgs + "You have gained $5, 5 Strength and 5 Chat Credits!");
    }

}

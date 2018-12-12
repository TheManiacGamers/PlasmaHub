//package me.yeroc.PlasmaHub.minigames.maze;
//
//import com.sk89q.minecraft.util.commands.ChatColor;
//import com.sk89q.minecraft.util.commands.Command;
//import com.sk89q.minecraft.util.commands.CommandContext;
//import me.yeroc.PlasmaHub.Main;
//import me.yeroc.PlasmaHub.listeners.DoubleJump;
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
//import org.bukkit.Location;
//import org.bukkit.Material;
//import org.bukkit.World;
//import org.bukkit.block.Block;
//import org.bukkit.block.BlockState;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.block.Action;
//import org.bukkit.event.player.PlayerInteractEvent;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//
//import java.util.*;
//
///**
// * Created by Corey on 2/12/2018.
// */
//public class OldMazeSwapper implements Listener {
//    private Main plugin;
//    private static OldMazeSwapper instance = new OldMazeSwapper();
//
//    public OldMazeSwapper(Main plugin) {
//        this.plugin = plugin;
//    }
//
//    private OldMazeSwapper() {
//    }
//
//    public static OldMazeSwapper getInstance() {
//        return instance;
//    }
//
//    private List<BlockState> blocks = new ArrayList<BlockState>();
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
//    private HashMap<String, List<BlockState>> mazes = new HashMap<>();
//    private World w = Bukkit.getWorld("world");
//    private Location wandSelection1 = new Location(w, 0, 0, 0);
//    private Location wandSelection2 = new Location(w, 0, 0, 0);
//    private Location selectionDefault = new Location(w, 0, 0, 0);
//    Map<String, Location> locations = new HashMap<String, Location>();
//
//    //get
//    //    Location loc = locations.get(b);
//
//    //set
//    //    locations.put(player.getName(), player.getLocation());
//
//    @EventHandler
//    public void onInteract(PlayerInteractEvent e) {
//        Player p = e.getPlayer();
//        Block b = e.getClickedBlock();
//        if (b == null || b.getType().equals(Material.AIR)) {
//            p.sendMessage(strings.maze_mustSelectBlock);
//            return;
//        }
//        if (p.getItemInHand().equals(returnItem(p, "selector"))) {
//            if (p.hasPermission(perms.maze_create)) {
//                if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
//                    Main.maze_locations.put("1", new Location(w, b.getX(), b.getY(), b.getZ()));
//                    e.setCancelled(true);
//                    p.sendMessage(strings.maze_location1set + ChatColor.GREEN + "X: " + Main.maze_locations.get("1").getX() + ChatColor.RED + " Y: " + Main.maze_locations.get("1").getY() + ChatColor.GREEN + " Z: " + Main.maze_locations.get("1").getZ());
//                } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
//                    Main.maze_locations.put("2", new Location(w, b.getX(), b.getY(), b.getZ()));
//                    e.setCancelled(true);
//                    p.sendMessage(strings.maze_location2set + ChatColor.GREEN + "X: " + Main.maze_locations.get("2").getX() + ChatColor.RED + " Y: " + Main.maze_locations.get("2").getY() + ChatColor.GREEN + " Z: " + Main.maze_locations.get("2").getZ());
//                } else {
//                    p.sendMessage(strings.maze_mustSelectBlock);
//                }
//            } else {
//                p.sendMessage(strings.noPermission);
//            }
//        }
//    }
//
//    @Command(aliases = "mazedelete", desc = "Delete a maze in the mazes file.")
//    public void onMazeDelete(CommandContext args, CommandSender sender) {
//
//    }
//
//    @Command(aliases = "mazecreate", desc = "Create a new maze to the mazes file.")
//    public void onMazeCreate(CommandContext args, CommandSender sender) {
//        if (api.isPlayer(sender)) {
//            Player p = (Player) sender;
//            if (p.hasPermission(perms.maze_create)) {
//                if (args.argsLength() == 0) {
//                    p.getInventory().addItem(returnItem(p, "selector"));
//                    p.updateInventory();
//                    p.sendMessage(strings.maze_givenSelector);
//                    return;
//                }
//                if (args.argsLength() == 1) {
//                    if (Main.maze_locations.get("1").equals(new Location(w, 0, 0, 0))) {
//                        p.sendMessage(strings.maze_selectArea);
//                    } else if (Main.maze_locations.get("2").equals(new Location(w, 0, 0, 0))) {
//                        p.sendMessage(strings.maze_selectArea);
//                    } else {
//                        createMaze(Main.maze_locations.get("1"), Main.maze_locations.get("2"), args.getString(0), p);
//                    }
//                }
//            }
//        }
//    }
//
//    public void resetLocations() {
//        Main.maze_locations.put("1", selectionDefault);
//        Main.maze_locations.put("2", selectionDefault);
//    }
//
//    private void createMaze(Location loc1, Location loc2, String mazeName, Player p) {
//        if (configs.getMazes().get("Mazes." + mazeName) != null) {
//            Bukkit.broadcastMessage(strings.maze_nameTaken);
//            return;
//        }
//
//        int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
//        int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
//
//        int topBlockY = (loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
//        int bottomBlockY = (loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
//
//        int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
//        int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
//
//        for (int x = bottomBlockX; x <= topBlockX; x++) {
//            for (int z = bottomBlockZ; z <= topBlockZ; z++) {
//                for (int y = bottomBlockY; y <= topBlockY; y++) {
//                    BlockState blockState = loc1.getWorld().getBlockAt(x, y, z).getState();
//                    blocks.add(blockState);
//                }
//            }
//        }
//        configs.getMazes().set("Mazes." + mazeName, blocks.toString());
//        mazes.put(mazeName, blocks);
//        configs.saveMazes();
//        p.sendMessage(strings.maze_created + mazeName);
//    }
//
//    public void loadMaze(String s, Player p) {
//        for (String st : configs.getMazes().getStringList("Mazes"))
//            if (s.equalsIgnoreCase(st)) {
//
//            }
//
//    }
//
//    public ItemStack returnItem(Player p, String s) {
//        if (s.equalsIgnoreCase("selector")) {
//            ItemStack SelectorWand = new ItemStack(Material.BLAZE_ROD, 1);
//            ItemMeta SelectorWandMeta = SelectorWand.getItemMeta();
//            SelectorWandMeta.setDisplayName(ChatColor.GREEN + "Maze Area Selection");
//            SelectorWandMeta.setLore(Arrays.asList(ChatColor.GREEN + "Left click for location 1, Right click for location 2 to create a map"));
//            SelectorWand.setItemMeta(SelectorWandMeta);
//            return SelectorWand;
//        } else {
//            p.sendMessage(strings.error);
//            return new ItemStack(Material.AIR, 1);
//        }
//
//    }
//}

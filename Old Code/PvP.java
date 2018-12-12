//package me.yeroc.PlasmaHub.minigames.pvp;
//
//import com.sk89q.minecraft.util.commands.ChatColor;
//import me.yeroc.PlasmaHub.Main;
//import me.yeroc.PlasmaHub.managers.Configs;
//import me.yeroc.PlasmaHub.managers.PermissionsManager;
//import me.yeroc.PlasmaHub.managers.PlayerFileManager;
//import me.yeroc.PlasmaHub.managers.Strings;
//import me.yeroc.PlasmaHub.minigames.maze.Maze;
//import me.yeroc.PlasmaHub.minigames.parkour.Parkour;
//import me.yeroc.PlasmaHub.serverselector.ServerSelector;
//import me.yeroc.PlasmaHub.utils.API;
//import me.yeroc.PlasmaHub.utils.TitleAPI.TitleAPI;
//import me.yeroc.PlasmaHub.utils.rewards.GemsManager;
//import me.yeroc.PlasmaHub.utils.rewards.RewardsManager;
//import org.bukkit.GameMode;
//import org.bukkit.Material;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerItemHeldEvent;
//import org.bukkit.event.player.PlayerRespawnEvent;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//
///**
// * Created by Corey on 28/11/2018.
// */
//public class PvP implements Listener {
//    private Main plugin;
//
//    private static PvP instance = new PvP();
//
//    public PvP(Main plugin) {
//        this.plugin = plugin;
//    }
//
//    private PvP() {
//    }
//
//    public static PvP getInstance() {
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
//
//    //    private ItemStack getItems(String s) {
////
////        if (s.equalsIgnoreCase("air")) {
////            return new ItemStack(Material.AIR, 1);
////        } else {
////            Bukkit.broadcastMessage(strings.error + "PvP");
////            return new ItemStack(Material.AIR, 1);
////        }
////    }
//    @EventHandler
//    public void onDeath(PlayerRespawnEvent e) {
//        api.resetInventory(e.getPlayer());
//        Main.canDoubleJump.put(e.getPlayer().getUniqueId(), "yes");
//    }
//
//    public void addSword(Player p) {
//        if (p.hasPermission(perms.plasma_pvp_use)) {
//            if (p.getGameMode().equals(GameMode.SURVIVAL)) {
//                ItemStack Sword = new ItemStack(Material.DIAMOND_SWORD, 1);
//                ItemMeta SwordMeta = Sword.getItemMeta();
//                SwordMeta.setDisplayName(ChatColor.GREEN + "Enable PvP");
//                SwordMeta.setUnbreakable(true);
//                Sword.setItemMeta(SwordMeta);
//                p.getInventory().setItem(8, Sword);
//            }
//        }
//    }
//
//    @EventHandler
//    public void onPlayerChangeSlot(PlayerItemHeldEvent e) {
//        Player p = e.getPlayer();
//        if (e.getNewSlot() == 8 && (p.getGameMode().equals(GameMode.SURVIVAL))) {
//            if (p.hasPermission(perms.plasma_pvp_use)) {
//                ItemStack Helmet = new ItemStack(Material.IRON_HELMET, 1);
//                ItemMeta HelmetMeta = Helmet.getItemMeta();
//                HelmetMeta.setDisplayName(ChatColor.GREEN + "PVP Hat");
//                HelmetMeta.setUnbreakable(true);
//                Helmet.setItemMeta(HelmetMeta);
//                ItemStack Chestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
//                ItemMeta ChestplateMeta = Chestplate.getItemMeta();
//                ChestplateMeta.setDisplayName(ChatColor.GREEN + "PVP Shirt");
//                ChestplateMeta.setUnbreakable(true);
//                Chestplate.setItemMeta(ChestplateMeta);
//                ItemStack Leggings = new ItemStack(Material.IRON_LEGGINGS, 1);
//                ItemMeta LeggingsMeta = Leggings.getItemMeta();
//                LeggingsMeta.setDisplayName(ChatColor.GREEN + "PVP Shorts (long)");
//                LeggingsMeta.setUnbreakable(true);
//                Leggings.setItemMeta(LeggingsMeta);
//                ItemStack Boots = new ItemStack(Material.IRON_BOOTS, 1);
//                ItemMeta BootsMeta = Boots.getItemMeta();
//                BootsMeta.setDisplayName(ChatColor.GREEN + "PVP Shoes");
//                BootsMeta.setUnbreakable(true);
//                Boots.setItemMeta(BootsMeta);
//                ItemStack Sword = new ItemStack(Material.DIAMOND_SWORD, 1);
//                ItemMeta SwordMeta = Sword.getItemMeta();
//                SwordMeta.setDisplayName(ChatColor.GREEN + "Enable PvP");
//                SwordMeta.setUnbreakable(true);
//                Sword.setItemMeta(SwordMeta);
//                p.getInventory().setHelmet(Helmet);
//                p.getInventory().setChestplate(Chestplate);
//                p.getInventory().setLeggings(Leggings);
//                p.getInventory().setBoots(Boots);
//                TitleAPI.sendActionBar(p, strings.pvp_enabled);
//                p.updateInventory();
//                Main.canDoubleJump.put(p.getUniqueId(), "no");
//
//            }
//        } else {
//            if (e.getPreviousSlot() == 8) {
//                ItemStack air = new ItemStack(Material.AIR, 1);
//                p.getInventory().setHelmet(air);
//                p.getInventory().setChestplate(air);
//                p.getInventory().setLeggings(air);
//                p.getInventory().setBoots(air);
//                TitleAPI.sendActionBar(p, strings.pvp_disabled);
//                p.updateInventory();
//                Main.canDoubleJump.put(p.getUniqueId(), "yes");
//            }
//        }
//    }
//
////    @Override
////    public void run() {
////
////    }
//
//
//}
package me.yeroc.PlasmaHub.utils;

import com.sk89q.minecraft.util.commands.ChatColor;
import me.yeroc.PlasmaHub.Main;
import me.yeroc.PlasmaHub.PlayerListener;
import me.yeroc.PlasmaHub.gadgets.GadgetsMenu;
import me.yeroc.PlasmaHub.managers.PermissionsManager;
import me.yeroc.PlasmaHub.managers.Strings;
import me.yeroc.PlasmaHub.serverselector.ServerSelector;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Corey on 28/11/2018.
 */
public class API {

    private static API instance = new API();
    private Main plugin;

    private API() {

    }

    public static API getInstance() {
        return instance;
    }

    private Strings strings = Strings.getInstance();
    private ServerSelector serverselector = ServerSelector.getInstance();
    private GadgetsMenu gadgets = GadgetsMenu.getInstance();
    private PlayerListener playerListener = PlayerListener.getInstance();
    private PermissionsManager perms = PermissionsManager.getInstance();

    public boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    public boolean hasPermPlayer(Player p, String s) {
        return p.hasPermission(s);
    }

    public boolean hasPermSender(CommandSender sender, String s) {
        return sender.isOp() || sender.hasPermission(s);
    }

    public void resetInventory(Player p) {
        p.getInventory().clear();
        addSword(p);
        addCompass(p);
        addGadgetMenu(p);
        if (p.getItemInHand().getType().equals(Material.DIAMOND_SWORD)) {
            addArmour(p);
        }
        addMusicDisc(p);
        addSnowball(p);
        p.updateInventory();
    }

    private void addMusicDisc(Player p) {
        ItemStack MusicItem = new ItemStack(Material.MUSIC_DISC_CAT, 1);
        ItemMeta MusicMeta = MusicItem.getItemMeta();
        MusicMeta.setDisplayName(ChatColor.AQUA + "Open Jukebox!");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "Right click me to open the Jukebox!");
        if (!p.hasPermission(perms.plasma_music_use)) {
            lore.add(ChatColor.GREEN + " ");
            lore.add(ChatColor.AQUA + "Permission: " + ChatColor.RED + "NO");
            lore.add(ChatColor.GREEN + " ");
        } else {
            lore.add(ChatColor.GREEN + " ");
            lore.add(ChatColor.AQUA + "Permission: " + ChatColor.GREEN + "YES");
            lore.add(ChatColor.GREEN + " ");
        }
        MusicMeta.setLore(lore);
        MusicItem.setItemMeta(MusicMeta);
        p.getInventory().setItem(2, MusicItem);
    }

    private void addArmour(Player p) {
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
        p.getInventory().setHelmet(Helmet);
        p.getInventory().setChestplate(Chestplate);
        p.getInventory().setLeggings(Leggings);
        p.getInventory().setBoots(Boots);
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

    public void resetPlayerHashMap(Player p) {
        Main.canDoubleJump.put(p.getUniqueId(), "yes");
        Main.parkour_isInParkour.put(p.getUniqueId(), "no");
        Main.parkour_playerCheckpoints.put(p.getUniqueId(), "zero");
        Main.maze_isInMaze.put(p.getUniqueId(), "no");
        Main.kotl_playerInRegion.put(p.getUniqueId(), "no");
        Main.kotl_toldInRegion.put(p.getUniqueId(), "no");
    }

    private void addSword(Player p) {
        if (p.getGameMode().equals(GameMode.SURVIVAL)) {
            ItemStack Sword = new ItemStack(Material.DIAMOND_SWORD, 1);
            ItemMeta SwordMeta = Sword.getItemMeta();
            SwordMeta.setDisplayName(com.sk89q.minecraft.util.commands.ChatColor.GREEN + "Enable PvP");
            SwordMeta.setUnbreakable(true);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "Hold me to enable PvP and give yourself armour!");
            if (!p.hasPermission(perms.plasma_pvp_use)) {
                lore.add(ChatColor.GREEN + " ");
                lore.add(ChatColor.AQUA + "Permission: " + ChatColor.RED + "NO");
            } else {
                lore.add(ChatColor.GREEN + " ");
                lore.add(ChatColor.AQUA + "Permission: " + ChatColor.GREEN + "YES");
            }
            SwordMeta.setLore(lore);
            Sword.setItemMeta(SwordMeta);
            p.getInventory().setItem(8, Sword);
        }
    }

    private void addGadgetMenu(Player p) {
        ItemStack Gadget = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta GadgetMeta = Gadget.getItemMeta();
        GadgetMeta.setDisplayName(org.bukkit.ChatColor.AQUA + "Gadgets Menu");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Right click to open the Gadgets Menu!");
        lore.add(ChatColor.GRAY + "Type /gems to see your gem amount!");
        if (!p.hasPermission(perms.plasma_gadgetsmenu_use) || (!p.hasPermission("gadgetsmenu.menuselector"))) {
            lore.add(ChatColor.GREEN + " ");
            lore.add(ChatColor.AQUA + "Permission: " + ChatColor.RED + "NO");
            lore.add(ChatColor.GREEN + " ");
        } else {
            lore.add(ChatColor.GREEN + " ");
            lore.add(ChatColor.AQUA + "Permission: " + ChatColor.GREEN + "YES");
            lore.add(ChatColor.GREEN + " ");
        }
        GadgetMeta.setLore(lore);
        Gadget.setItemMeta(GadgetMeta);
        p.getInventory().setItem(4, Gadget);
    }

    private void addCompass(Player p) {
        ItemStack Compass = new ItemStack(Material.COMPASS, 1);
        ItemMeta CompassMeta = Compass.getItemMeta();
        CompassMeta.setDisplayName(com.sk89q.minecraft.util.commands.ChatColor.GREEN + "Server Selector");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.RED + "Right click to select a server!");
        if (!p.hasPermission(perms.plasma_serverselector_use) || (!p.hasPermission("music.command"))) {
            lore.add(ChatColor.GREEN + " ");
            lore.add(ChatColor.AQUA + "Permission: " + ChatColor.RED + "NO");
            lore.add(ChatColor.GREEN + " ");
        } else {
            lore.add(ChatColor.GREEN + " ");
            lore.add(ChatColor.AQUA + "Permission: " + ChatColor.GREEN + "YES");
            lore.add(ChatColor.GREEN + " ");
        }
        CompassMeta.setLore(lore);
        Compass.setItemMeta(CompassMeta);
        p.getInventory().setItem(0, Compass);
    }

}

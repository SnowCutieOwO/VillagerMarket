package bestem0r.villagermarket.menus;

import bestem0r.villagermarket.VMPlugin;
import bestem0r.villagermarket.items.MenuItem;
import bestem0r.villagermarket.shops.VillagerShop;
import bestem0r.villagermarket.utilities.Color;
import bestem0r.villagermarket.utilities.ColorBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class SellShopMenu {

    public static Inventory create(VillagerShop villagerShop) {
        Inventory inventory = Bukkit.createInventory(null, 9, ColorBuilder.color("menus.sell_shop.title"));

        FileConfiguration mainConfig = VMPlugin.getInstance().getConfig();
        String priceHalved = String.valueOf((double) villagerShop.getCost() * (mainConfig.getDouble("refund_percent") / 100));

        MenuItem cancel = new MenuItem.Builder(Material.RED_TERRACOTTA)
                .nameFromPath("menus.sell_shop.items.no_cancel")
                .build();

        MenuItem glassPane = new MenuItem.Builder(Material.BLUE_STAINED_GLASS_PANE)
                .name(" ")
                .build();
        MenuItem confirm = new MenuItem.Builder(Material.LIME_TERRACOTTA)
                .nameFromPath("menus.sell_shop.items.yes_confirm.name")
                .lore(new Color.Builder().path("menus.sell_shop.items.yes_confirm.lore").replace("%amount%", priceHalved).buildLore())
                .build();


        ItemStack[] sellItems = {
                glassPane,
                glassPane,
                glassPane,
                confirm,
                glassPane,
                cancel,
                glassPane,
                glassPane,
                glassPane
        };

        inventory.setContents(sellItems);
        return inventory;
    }
}
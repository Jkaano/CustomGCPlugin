package gc.customplugin.Items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SafariNet{

	public ItemStack safariNet = new ItemStack(Material.LEASH, 1);
	
	public SafariNet(){
		
		ItemMeta meta = getMetaData();
		List<String> lore = new ArrayList<String>();
		
		lore.add(ChatColor.DARK_PURPLE + "Captures entities in lasso");
		lore.add("Held Mob: None");
		
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.GOLD + "Safari Net");
		
		safariNet.setItemMeta(meta);
		
	}
	
	public ItemStack getSafariNet(){
		return safariNet;
	}
	
	public ItemMeta getMetaData(){
		
		ItemMeta met = safariNet.getItemMeta();
		return met;
		
	}
	
}

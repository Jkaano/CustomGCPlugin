package gc.customplugin.Items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SafariNet implements Listener{

	public ItemStack safariNet = new ItemStack(Material.LEASH, 1);
	private Player player;
	
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
	
	@SuppressWarnings(value = "deprecation")
	@EventHandler
	public void onLeash(PlayerInteractEntityEvent e){
		
		player = e.getPlayer();
		ItemStack heldItem = player.getItemInHand();
		
		player.sendMessage("You tried to leash an entity");
		
		if(heldItem.hasItemMeta()){
			ItemMeta meta = heldItem.getItemMeta();
		}
		
	}
	
}

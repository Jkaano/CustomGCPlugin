package gc.customplugin.Items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SafariNet implements Listener{

	public ItemStack safariNet = new ItemStack(Material.LEASH, 1);
	private Player player;
	private EntityType heldMob = null;
	
	public SafariNet(){
		
		ItemMeta meta = getMetaData();
		List<String> lore = new ArrayList<String>();
		
		lore.add(ChatColor.DARK_PURPLE + "Captures entities in lasso");
		lore.add("Held Mob: " + heldMob);
		
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
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onLeash(PlayerInteractEntityEvent e){
		
		player = e.getPlayer();
		ItemStack item = player.getItemInHand();
		Entity entity = e.getRightClicked();
		ItemMeta meta;
		List<String> lore;
		
		EntityType type = entity.getType();
		
		if(type == type.UNKNOWN){
			player.sendMessage("Entity is mypet");
			e.setCancelled(true);
		}else{
			player.sendMessage("Known mob: " + type);
			if(item.hasItemMeta()){
				player.sendMessage("Item in hand has meta");
				meta = item.getItemMeta();
				if(meta.hasLore()){
					player.sendMessage("Item in hand has lore");
					lore = meta.getLore();
					if(lore.contains(ChatColor.DARK_PURPLE + "Captures entities in lasso")){
						player.sendMessage("Captured in safari net");
					}
				}
			}
			
		}
		
		
	}
	
}

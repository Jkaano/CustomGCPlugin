package gc.customplugin.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;

public class LayEggEvent implements Listener{
	
	@EventHandler
	public void onLayEgg(ItemSpawnEvent e){
		
		Item item = e.getEntity();
		ItemStack stack = item.getItemStack();
		Material mat = stack.getType();
		
		if(mat == Material.EGG){

			List<Entity> nearbyEnt = new ArrayList<Entity>();
			
			nearbyEnt.addAll(item.getNearbyEntities(10.0, 10.0, 10.0));
			
			for(int i = 0; i < nearbyEnt.size(); i++){
				
				if(nearbyEnt.get(i) instanceof Chicken){
					stack.setType(Material.DIAMOND_AXE);
				}
				
			}
		}
		
	}
	
}
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
		int nChick = 0;
		boolean change = false;
		
		if(mat == Material.EGG){

			List<Entity> nearbyEnt = new ArrayList<Entity>();
			
			nearbyEnt.addAll(item.getNearbyEntities(10.0, 10.0, 10.0));
			
			for(int i = 0; i < nearbyEnt.size(); i++){
				
				if(nearbyEnt.get(i) instanceof Chicken){
					nChick++;
					if(nChick > 1){
						break;
					}
				}
				
			}
			
			if(nChick == 1){
				change = true;
			}else{
				change = false;
			}
			
			if(change){
				nearbyEnt.clear();
				nearbyEnt.addAll(item.getNearbyEntities(0.001, 0.001, 0.001));
				nChick = 0;
				for(int i = 0; i < nearbyEnt.size(); i++){
					
					if(nearbyEnt.get(i) instanceof Chicken){
						nChick++;
						if(nChick > 1){
							break;
						}
					}
					
				}
				
				if(nChick == 1){
					stack.setType(Material.DIAMOND_AXE);
				}
				
			}
			
		}
		
	}
	
}

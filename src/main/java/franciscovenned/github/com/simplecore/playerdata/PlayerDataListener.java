package franciscovenned.github.com.simplecore.playerdata;

import franciscovenned.github.com.simplecore.MainClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataListener implements Listener {

    public static Map<UUID, PlayerData> dataMap = new HashMap<>();

    @EventHandler
    public void asyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event){

        PlayerData playerData = MainClass.getData(event.getUniqueId());

        playerData.load();

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){

        PlayerData playerData = MainClass.getData(event.getPlayer().getUniqueId());

        playerData.setLastlocation(event.getPlayer().getLocation());

        playerData.save();

    }

    @EventHandler
    public void onDeathQuit(PlayerDeathEvent event){


        Player killer = (Player) event.getEntity().getKiller();

        Player player = (Player) event.getEntity();

        PlayerData playerData = MainClass.getData(player.getUniqueId());

        playerData.setDeaths(killer.getLocale());
    }

    @EventHandler
    void onXPQuit(PlayerExpChangeEvent event){

        Player player = (Player) event.getPlayer();

        PlayerData playerData = MainClass.getData(player.getUniqueId());

        playerData.setXp(event.getAmount());
    }
}

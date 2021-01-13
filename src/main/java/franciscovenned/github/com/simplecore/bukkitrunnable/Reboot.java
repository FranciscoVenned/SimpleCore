package franciscovenned.github.com.simplecore.bukkitrunnable;

import franciscovenned.github.com.simplecore.MainClass;
import franciscovenned.github.com.simplecore.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class Reboot {

    public static void reboot() {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cThe server restart in 5 seconds"));
        new BukkitRunnable() {
            @Override
            public void run(){
                Bukkit.getServer().shutdown();
            }
        }.runTaskLater(MainClass.getInstance(), 20 * 5);
    }
}

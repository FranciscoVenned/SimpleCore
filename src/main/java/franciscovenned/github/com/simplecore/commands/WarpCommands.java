package franciscovenned.github.com.simplecore.commands;

import franciscovenned.github.com.simplecore.MainClass;
import franciscovenned.github.com.simplecore.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WarpCommands implements CommandExecutor {

    private final MainClass plugin;

    public WarpCommands(MainClass plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            TextUtils.send(sender, "&c-------[Warp Help]-------");
            TextUtils.send(sender, "/warp setwarp <name>");
            TextUtils.send(sender, "/warp <name>");
            TextUtils.send(sender, "&c------------------------");
        } else if (args[0].equalsIgnoreCase("setwarp")){
            FileConfiguration config = MainClass.getInstance().getConfig();
            Player player = (Player) sender;

            Location playerlocation = player.getLocation();

            if (args.length < 3){
                TextUtils.send(player, "Name Warp for example /warp setspawn Lobby");
                return true;
            }
            String warpname = args[1];

            plugin.getConfig().set(warpname, "Spawn.world" + playerlocation.getWorld().getName());
            plugin.getConfig().set(warpname, "Spawn.X" + playerlocation.getX());
            plugin.getConfig().set(warpname, "Spawn.Y" + playerlocation.getY());
            plugin.getConfig().set(warpname, "Spawn.Z" + playerlocation.getZ());
            plugin.getConfig().set(warpname, "Spawn.yaw" + playerlocation.getYaw());
            plugin.getConfig().set(warpname, "Spawn.pitch" + playerlocation.getPitch());

            TextUtils.send(sender, "Warp Save");



            plugin.saveConfig();
        }
        else if (sender instanceof Player) {

            FileConfiguration config = plugin.getConfig();
            Player player = (Player) sender;

            if (args.length < 2){
                TextUtils.send(player, "Name Warp for example /warp spawn");
                return true;
            }

            String warpname = args[1];
            if(!config.contains(warpname)){
              TextUtils.send(player, "Warp No Exist");
              return true;
            }
            String world = plugin.getConfig().getString(warpname, "Spawn.world");
            double x = plugin.getConfig().getDouble(warpname, Double.parseDouble("Spawn.X"));
            double y = plugin.getConfig().getDouble(warpname, Double.parseDouble("Spawn.Y"));
            double z = plugin.getConfig().getDouble(warpname, Double.parseDouble("Spawn.Z"));
            float yaw = (float) plugin.getConfig().getDouble(warpname, Float.parseFloat("Spawn.yaw"));
            float pitch = (float) plugin.getConfig().getDouble(warpname, Float.parseFloat("Spawn.pitch"));

            Location location = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);

            new BukkitRunnable() {
                @Override
                public void run(){



                    String world = plugin.getConfig().getString(warpname, "Spawn.world");
                    double x = MainClass.getInstance().getConfig().getDouble(warpname, Double.parseDouble("Spawn.X"));
                    double y = plugin.getConfig().getDouble(warpname, Double.parseDouble("Spawn.Y"));
                    double z = plugin.getConfig().getDouble(warpname, Double.parseDouble("Spawn.Z"));
                    float yaw = (float) plugin.getConfig().getDouble(warpname, Float.parseFloat("Spawn.yaw"));
                    float pitch = (float) plugin.getConfig().getDouble(warpname, Float.parseFloat("Spawn.pitch"));

                    Location location = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);

                    player.teleport(location);
                }
            }.runTaskLater(MainClass.getInstance(), 20 * 5);



        }
        return false;
    }
}

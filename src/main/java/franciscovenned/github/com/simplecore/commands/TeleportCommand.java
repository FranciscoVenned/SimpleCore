package franciscovenned.github.com.simplecore.commands;

import franciscovenned.github.com.simplecore.MainClass;
import franciscovenned.github.com.simplecore.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportCommand implements CommandExecutor {

    private final MainClass plugin;

    public TeleportCommand(MainClass plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            TextUtils.send(sender, "&c-------[Teleportation Help]-------");
            TextUtils.send(sender, "/tp coords <x> <y> <z>");
            TextUtils.send(sender, "/tp <nickname>");
            TextUtils.send(sender, "/tp <world>");
            TextUtils.send(sender, "/tp setspawn");
            TextUtils.send(sender, "/tp spawn");
            TextUtils.send(sender, "&c------------------------");
        } else if (args[0].equalsIgnoreCase("setspawn")) {

            FileConfiguration config = MainClass.getInstance().getConfig();
            Player player = (Player) sender;
            if (sender instanceof ConsoleCommandSender) {
                TextUtils.send(sender, "Only Player");
                return true;
            }
            Location playerlocation = player.getLocation();

            plugin.getConfig().set("Spawn.world", playerlocation.getWorld().getName());
            plugin.getConfig().set("Spawn.X", playerlocation.getX());
            plugin.getConfig().set("Spawn.Y", playerlocation.getY());
            plugin.getConfig().set("Spawn.Z", playerlocation.getZ());
            plugin.getConfig().set("Spawn.yaw", playerlocation.getYaw());
            plugin.getConfig().set("Spawn.pitch", playerlocation.getPitch());

            TextUtils.send(sender, "El Spawn fue guardado con exito");


            plugin.saveConfig();

            return true;


        } else if (args[0].equalsIgnoreCase("spawn")) {
            FileConfiguration config = MainClass.getInstance().getConfig();
            Player player = (Player) sender;
            World world = Bukkit.getWorld(config.getString("Spawn.world"));
            double x = config.getDouble("Spawn.X");
            double Y = config.getDouble("Spawn.Y");
            double Z = config.getDouble("Spawn.Z");


            float yaw = (float) config.getDouble("Spawn.yaw");
            float pitch = (float) config.getDouble("Spawn.pitch");
            Location spawn = new Location(world, x, Y, Z, yaw, pitch);
            if (sender instanceof ConsoleCommandSender) {
                TextUtils.send(sender, "Only Player");
                return true;
            }
            if (spawn == null) {
                TextUtils.send(sender, "No hay ningun spawn asignado");
                return true;
            }
            player.teleport(spawn);
            TextUtils.send(sender, "Has sido enviado al Spawn");
            player.playSound(spawn, "BLOCK_ANVIL_BREAK ", 10, 10);
        }

        else if (sender instanceof Player){
            Player player = (Player) sender;

            if(args.length == 0){
                TextUtils.send(sender, "&c&l Debes ingresar el nombre del jugador");
                TextUtils.send(sender, "Prueba usando /tp <player>");
                TextUtils.send(sender, "Tambien puedes traerlo con /tp <player> <otherplayer>");
            } else if(args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);

                try {
                    player.teleport(target.getLocation());
                }catch (NullPointerException e){
                    TextUtils.send(sender, "No existe el Jugador");
                }

            } else if(args.length == 2){
                Player playertosend = Bukkit.getPlayer(args[0]);
                Player target = Bukkit.getPlayer(args[1]);
                try {
                    playertosend.teleport(target.getLocation());
                }
                catch (NullPointerException e){
                    TextUtils.send(sender, "No existe el Jugador");
                }
            } else if(args.length == 4){
                World world = Bukkit.getWorld(args[0]);
                double x = Integer.parseInt(args[1]);
                double y = Integer.parseInt(args[2]);
                double z = Integer.parseInt(args[3]);

                Location loc = new Location(world, x, y, z);

                player.teleport(loc);
            }

        }


        return true;
    }
}

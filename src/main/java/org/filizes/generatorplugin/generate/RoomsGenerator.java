package org.filizes.generatorplugin.generate;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.schematic.SchematicFormat;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Singleton
public class RoomsGenerator implements Listener {

    private static final String[] ZONE_NAMES = {"litezona", "hardzona", "office"};
    private final Map<String, List<File>> zoneSchematics = new HashMap<>();
    private final Map<Location, File> generatedRooms = new HashMap<>();
    private final Plugin plugin;

    @Inject
    public RoomsGenerator(Plugin plugin) {
        this.plugin = plugin;
        loadSchematics();
    }

    private void loadSchematics() {
        for (String zoneName : ZONE_NAMES) {
            File zoneDir = new File(plugin.getDataFolder(), zoneName);
            if (!zoneDir.exists()) {
                zoneDir.mkdirs();
            }
            List<File> schematics = new ArrayList<>();
            for (File file : Objects.requireNonNull(zoneDir.listFiles())) {
                if (file.getName().endsWith(".schematic")) {
                    schematics.add(file);
                }
            }
            zoneSchematics.put(zoneName, schematics);
        }
    }

    public void clearGeneratedRooms(World world) {
        if (generatedRooms.isEmpty()) {
            Bukkit.getLogger().info("Нет сгенерированных комнат, которые нужно очистить.");
            return;
        }

        try {
            EditSession editSession = new EditSession(BukkitUtil.getLocalWorld(world), Integer.MAX_VALUE);

            for (Map.Entry<Location, File> entry : generatedRooms.entrySet()) {
                Location location = entry.getKey();
                File schematic = entry.getValue();
                SchematicFormat format = SchematicFormat.getFormat(schematic);
                if (format == null) {
                    Bukkit.getLogger().severe("Неизвестный формат схемы для очистки: " + schematic.getName());
                    continue;
                }

                CuboidClipboard clipboard = format.load(schematic);
                BlockVector origin = new BlockVector(location.getBlockX(), location.getBlockY(), location.getBlockZ());

                clipboard.paste(editSession, origin, true);
            }

            generatedRooms.clear();
            Bukkit.getLogger().info("Все сгенерированные комнаты были очищены.");
        } catch (Exception e) {
            Bukkit.getLogger().severe("Ошибка при очистке сгенерированных комнат: " + e.getMessage());
        }
    }

    public void clearGeneratedRooms(Player player) {
        clearGeneratedRooms(player.getWorld());
        player.sendMessage("Все сгенерированные комнаты были очищены.");
    }



    public void generateRooms(World world, Player player) {
        List<Sign> signs = findBuildSigns(world);

        if (signs.isEmpty()) {
            if (player != null) {
                player.sendMessage("Никаких знаков, найденных в мире.");
            } else {
                Bukkit.getLogger().info("Никаких знаков, найденных в мире.");
            }
            return;
        }

        Collections.shuffle(signs);

        Set<File> usedSchematics = new HashSet<>();

        for (Sign sign : signs) {
            String zoneName = sign.getLine(0).substring(6);
            List<File> schematics = zoneSchematics.get(zoneName);
            if (schematics != null && !schematics.isEmpty()) {
                List<File> availableSchematics = new ArrayList<>(schematics);
                availableSchematics.removeAll(usedSchematics);

                if (availableSchematics.isEmpty()) {
                    if (player != null) {
                        player.sendMessage("Для зоны больше не доступны уникальные схемы: " + zoneName);
                    } else {
                        Bukkit.getLogger().info("Для зоны больше не доступны уникальные схемы: " + zoneName);
                    }
                    continue;
                }

                File schematic = availableSchematics.get(new Random().nextInt(availableSchematics.size()));
                generateRoom(sign.getLocation(), schematic, player);
                usedSchematics.add(schematic);
                generatedRooms.put(sign.getLocation(), schematic);
            } else {
                if (player != null) {
                    player.sendMessage("Не найдено схем для зоны: " + zoneName);
                } else {
                    Bukkit.getLogger().info("Не найдено схем для зоны: " + zoneName);
                }
            }
        }
    }

    private void generateRoom(Location location, File schematic, Player player) {
        try {
            SchematicFormat format = SchematicFormat.getFormat(schematic);
            if (format == null) {
                throw new IOException("Неизвестный формат схемы.");
            }

            EditSession editSession = new EditSession(BukkitUtil.getLocalWorld(location.getWorld()), Integer.MAX_VALUE);
            CuboidClipboard clipboard = format.load(schematic);

            List<SignData> savedSigns = saveSigns(location, clipboard);

            clipboard.paste(editSession, new BlockVector(location.getBlockX(), location.getBlockY(), location.getBlockZ()), false);

            if (player != null) {
                player.sendMessage("Комната успешно сгенерирована!");
            } else {
                Bukkit.getLogger().info("Комната успешно сгенерирована на " + location);
            }

            restoreSigns(savedSigns, location.getWorld());

        } catch (Exception e) {
            if (player != null) {
                player.sendMessage("Схема загрузки ошибок: " + e.getMessage());
            } else {
                Bukkit.getLogger().severe("Схема загрузки ошибок: " + e.getMessage());
            }
        }
    }

    private List<SignData> saveSigns(Location location, CuboidClipboard clipboard) {
        List<SignData> signs = new ArrayList<>();
        BlockVector origin = new BlockVector(location.getBlockX(), location.getBlockY(), location.getBlockZ());

        for (int x = 0; x < clipboard.getWidth(); x++) {
            for (int y = 0; y < clipboard.getHeight(); y++) {
                for (int z = 0; z < clipboard.getLength(); z++) {
                    Block block = location.getWorld().getBlockAt(
                            origin.getBlockX() + x,
                            origin.getBlockY() + y,
                            origin.getBlockZ() + z
                    );

                    if (block.getState() instanceof Sign) {
                        Sign sign = (Sign) block.getState();
                        signs.add(new SignData(sign.getLocation(), sign.getLines()));
                    }
                }
            }
        }

        return signs;
    }


    private void restoreSigns(List<SignData> signs, World world) {
        for (SignData signData : signs) {
            Block block = world.getBlockAt(signData.getLocation());
            block.setType(Material.SIGN_POST);
            if (block.getState() instanceof Sign) {
                Sign sign = (Sign) block.getState();
                for (int i = 0; i < signData.getLines().length; i++) {
                    sign.setLine(i, signData.getLines()[i]);
                }
                sign.update();
            }
        }
    }

    private List<Sign> findBuildSigns(World world) {
        List<Sign> signs = new ArrayList<>();
        for (Chunk chunk : world.getLoadedChunks()) {
            for (BlockState blockState : chunk.getTileEntities()) {
                if (blockState instanceof Sign) {
                    Sign sign = (Sign) blockState;
                    if (sign.getLine(0).startsWith("BUILD#")) {
                        signs.add(sign);
                    }
                }
            }
        }
        return signs;
    }
}


package me.cxmilo.parkour.storage.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.cxmilo.parkour.storage.Storage;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.logging.Level;

public class JsonStorage<O>
        implements Storage<O> {

    private static final Gson GSON = new Gson();

    private final JavaPlugin plugin;
    private final Class<O> clazz;
    private final File fileFolder;

    public JsonStorage(Class<O> clazz, JavaPlugin plugin) {
        this.plugin = plugin;
        this.clazz = clazz;
        this.fileFolder = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + clazz.getSimpleName());

        if (fileFolder.mkdir()) {
            plugin.getLogger().log(Level.FINE, "Successfully fileFolder '" + fileFolder.getName() + "' created!");
        }
    }

    @Override
    public void save(String identifier, O object) {
        File file = new File(fileFolder, identifier + ".json");

        try (Writer writer = new BufferedWriter(new FileWriter(file))) {

            if (file.createNewFile()) {
                plugin.getLogger().log(Level.INFO, "Successfully file '" + file.getName() + "' created");
            }

            writer.write(GSON.toJson(object, object.getClass()));
            writer.flush();
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Unable to save the fileFolder '" + fileFolder.getName() + "'", e);
        }
    }

    @Override
    public O get(String identifier) {
        File file = new File(fileFolder, identifier + ".json");

        try (Reader reader = new BufferedReader(new FileReader(file))) {
            return GSON.fromJson(reader, clazz);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Unable to get the fileFolder '" + fileFolder.getName() + "'", e);
        }

        return null;
    }

    public File getFileFolder() {
        return fileFolder;
    }
}
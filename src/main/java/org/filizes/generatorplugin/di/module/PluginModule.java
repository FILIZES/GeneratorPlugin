package org.filizes.generatorplugin.di.module;

import dagger.Module;
import dagger.Provides;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.filizes.generatorplugin.Main;

import javax.inject.Singleton;
import java.io.File;

@Module
public class PluginModule {

    @Singleton
    @Provides
    public Plugin providePlugin(Main GeneratePlugin) {
        return GeneratePlugin;
    }

    @Singleton
    @Provides
    public JavaPlugin provideJavaPlugin(Main GeneratePlugin) {
        return GeneratePlugin;
    }

    @Singleton
    @Provides
    public File provideDataFolder(Main plugin) {
        return plugin.getDataFolder();
    }

}

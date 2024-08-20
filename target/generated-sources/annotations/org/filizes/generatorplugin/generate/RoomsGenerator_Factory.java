package org.filizes.generatorplugin.generate;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.bukkit.plugin.Plugin;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class RoomsGenerator_Factory implements Factory<RoomsGenerator> {
  private final Provider<Plugin> pluginProvider;

  public RoomsGenerator_Factory(Provider<Plugin> pluginProvider) {
    this.pluginProvider = pluginProvider;
  }

  @Override
  public RoomsGenerator get() {
    return newInstance(pluginProvider.get());
  }

  public static RoomsGenerator_Factory create(Provider<Plugin> pluginProvider) {
    return new RoomsGenerator_Factory(pluginProvider);
  }

  public static RoomsGenerator newInstance(Plugin plugin) {
    return new RoomsGenerator(plugin);
  }
}

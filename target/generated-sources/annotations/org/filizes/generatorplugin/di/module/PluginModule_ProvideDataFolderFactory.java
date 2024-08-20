package org.filizes.generatorplugin.di.module;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.io.File;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.filizes.generatorplugin.Main;

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
public final class PluginModule_ProvideDataFolderFactory implements Factory<File> {
  private final PluginModule module;

  private final Provider<Main> pluginProvider;

  public PluginModule_ProvideDataFolderFactory(PluginModule module, Provider<Main> pluginProvider) {
    this.module = module;
    this.pluginProvider = pluginProvider;
  }

  @Override
  public File get() {
    return provideDataFolder(module, pluginProvider.get());
  }

  public static PluginModule_ProvideDataFolderFactory create(PluginModule module,
      Provider<Main> pluginProvider) {
    return new PluginModule_ProvideDataFolderFactory(module, pluginProvider);
  }

  public static File provideDataFolder(PluginModule instance, Main plugin) {
    return Preconditions.checkNotNullFromProvides(instance.provideDataFolder(plugin));
  }
}

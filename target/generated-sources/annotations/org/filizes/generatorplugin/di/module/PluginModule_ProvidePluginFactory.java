package org.filizes.generatorplugin.di.module;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.bukkit.plugin.Plugin;
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
public final class PluginModule_ProvidePluginFactory implements Factory<Plugin> {
  private final PluginModule module;

  private final Provider<Main> generatePluginProvider;

  public PluginModule_ProvidePluginFactory(PluginModule module,
      Provider<Main> generatePluginProvider) {
    this.module = module;
    this.generatePluginProvider = generatePluginProvider;
  }

  @Override
  public Plugin get() {
    return providePlugin(module, generatePluginProvider.get());
  }

  public static PluginModule_ProvidePluginFactory create(PluginModule module,
      Provider<Main> generatePluginProvider) {
    return new PluginModule_ProvidePluginFactory(module, generatePluginProvider);
  }

  public static Plugin providePlugin(PluginModule instance, Main GeneratePlugin) {
    return Preconditions.checkNotNullFromProvides(instance.providePlugin(GeneratePlugin));
  }
}

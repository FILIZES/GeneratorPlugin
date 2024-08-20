package org.filizes.generatorplugin.di.module;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.bukkit.plugin.java.JavaPlugin;
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
public final class PluginModule_ProvideJavaPluginFactory implements Factory<JavaPlugin> {
  private final PluginModule module;

  private final Provider<Main> generatePluginProvider;

  public PluginModule_ProvideJavaPluginFactory(PluginModule module,
      Provider<Main> generatePluginProvider) {
    this.module = module;
    this.generatePluginProvider = generatePluginProvider;
  }

  @Override
  public JavaPlugin get() {
    return provideJavaPlugin(module, generatePluginProvider.get());
  }

  public static PluginModule_ProvideJavaPluginFactory create(PluginModule module,
      Provider<Main> generatePluginProvider) {
    return new PluginModule_ProvideJavaPluginFactory(module, generatePluginProvider);
  }

  public static JavaPlugin provideJavaPlugin(PluginModule instance, Main GeneratePlugin) {
    return Preconditions.checkNotNullFromProvides(instance.provideJavaPlugin(GeneratePlugin));
  }
}

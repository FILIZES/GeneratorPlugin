package org.filizes.generatorplugin.di.component;

import dagger.BindsInstance;
import dagger.Component;
import org.filizes.generatorplugin.Main;
import org.filizes.generatorplugin.di.module.PluginModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {PluginModule.class})
public interface PluginComponent {
    public void inject(Main plugin);

    @Component.Factory
    public interface Factory {
        public PluginComponent create(@BindsInstance Main plugin);
    }
}

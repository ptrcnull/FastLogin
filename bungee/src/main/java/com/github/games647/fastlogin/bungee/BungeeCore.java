package com.github.games647.fastlogin.bungee;

import com.github.games647.fastlogin.core.FastLoginCore;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.File;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Logger;

import net.md_5.bungee.api.scheduler.GroupedThreadFactory;

public class BungeeCore extends FastLoginCore {

    private final FastLoginBungee plugin;

    public BungeeCore(FastLoginBungee plugin) {
        this.plugin = plugin;
    }

    @Override
    public File getDataFolder() {
        return plugin.getDataFolder();
    }

    @Override
    public Logger getLogger() {
        return plugin.getLogger();
    }

    @Override
    public ThreadFactory getThreadFactory() {
        String pluginName = plugin.getDescription().getName();
        return new ThreadFactoryBuilder()
                .setNameFormat(pluginName + " Database Pool Thread #%1$d")
                //Hikari create daemons by default
                .setDaemon(true)
                .setThreadFactory(new GroupedThreadFactory(plugin, pluginName)).build();
    }
}
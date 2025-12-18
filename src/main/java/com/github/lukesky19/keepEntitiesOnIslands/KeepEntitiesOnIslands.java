/*
    KeepEntitiesOnIslands is a simple plugin to prevent entities from leaving player's islands.
    Copyright (C) 2025 lukeskywlker19

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.github.lukesky19.keepEntitiesOnIslands;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * The entry point to the plugin.
 */
public final class KeepEntitiesOnIslands extends JavaPlugin {
    /**
     * Registers the plugin's listener when the plugin is enabled.
     */
    @Override
    public void onEnable() {
        // Register Listener
        this.getServer().getPluginManager().registerEvents(new EntityMoveListener(), this);
    }
}
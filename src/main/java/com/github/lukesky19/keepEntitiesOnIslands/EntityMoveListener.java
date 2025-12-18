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

import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.managers.IslandsManager;

import java.util.Optional;

/**
 * This class listens to {@link EntityMoveEvent}s and cancels them if the entity is exiting an island.
 */
public class EntityMoveListener implements Listener {
    private final @NotNull IslandsManager islandsManager = BentoBox.getInstance().getIslandsManager();

    /**
     * Constructor
     */
    public EntityMoveListener() {}

    /**
     * Listens to when an entity exits an island cancels the movement event.
     * Ignores players.
     * @param entityMoveEvent An {@link EntityMoveEvent}.
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityMove(EntityMoveEvent entityMoveEvent) {
        Entity entity = entityMoveEvent.getEntity();
        // Ignore players
        if(entity instanceof Player) return;

        // Is the entity exiting an island?
        if(!isEntityExitingIsland(entityMoveEvent.getFrom(), entityMoveEvent.getTo())) return;

        // Cancel the event
        entityMoveEvent.setCancelled(true);
    }

    /**
     * Is the entity exiting an island? (Is the from location on an island, but the to location is not)
     * @param from The current location of the entity.
     * @param to The location the entity is moving to.
     * @return true if exiting an island, otherwise false.
     */
    private boolean isEntityExitingIsland(@NotNull Location from, @NotNull Location to) {
        Optional<Island> fromIsland = islandsManager.getProtectedIslandAt(from);
        Optional<Island> toIsland = islandsManager.getProtectedIslandAt(to);
        if(fromIsland.isEmpty()) return false;
        return toIsland.isEmpty();
    }
}
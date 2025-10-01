package com.example.heavierhorizons.item;


import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class SteelMagnetItem extends Item {

    public SteelMagnetItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        Vec3d center = user.getPos();
        int distance = 4;

        List<Entity> entities = world.getOtherEntities(user, new Box(center.subtract(new Vec3d(distance, distance, distance)), center.add(new Vec3d(distance, distance, distance))));

        for (Entity ent : entities) {
            if (ent.getType() == EntityType.ITEM) {
                ent.setPosition(center);
            }
        }

        return ActionResult.SUCCESS;
    }

}

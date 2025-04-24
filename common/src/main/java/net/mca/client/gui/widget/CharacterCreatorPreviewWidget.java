package net.mca.client.gui.widget;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.LivingEntity;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

public class CharacterCreatorPreviewWidget {

    private final LivingEntity entity;
    private final int x;
    private final int y;
    private final int size;
    private final float yaw;
    private final float pitch;

    public CharacterCreatorPreviewWidget(LivingEntity entity, int x, int y, int size, float yaw, float pitch) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.size = size;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public void render(DrawContext context) {
        Quaternionf rotationYaw = new Quaternionf().rotateY((float) Math.toRadians(yaw + 180));
        Quaternionf rotationPitch = new Quaternionf().rotateX((float) Math.toRadians(pitch)).rotateZ((float) Math.PI); // Flip Z

        float originalBodyYaw = entity.bodyYaw;
        float originalYaw = entity.getYaw();
        float originalPitch = entity.getPitch();
        float originalPrevHeadYaw = entity.prevHeadYaw;
        float originalHeadYaw = entity.headYaw;

        entity.bodyYaw = yaw;
        entity.setYaw(yaw);
        entity.setPitch(pitch);
        entity.prevHeadYaw = yaw;
        entity.headYaw = yaw;

        context.getMatrices().push();
        context.getMatrices().translate((double)x, (double)y, 50.0);
        context.getMatrices().multiplyPositionMatrix(new Matrix4f().scaling(size, size, -size));
        context.getMatrices().multiply(rotationYaw.mul(rotationPitch));

        DiffuseLighting.method_34742();
        EntityRenderDispatcher dispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
        dispatcher.setRenderShadows(false);

        dispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, context.getMatrices(), context.getVertexConsumers(), 15728880);


        context.draw();
        dispatcher.setRenderShadows(true);
        context.getMatrices().pop();
        DiffuseLighting.enableGuiDepthLighting();

        entity.bodyYaw = originalBodyYaw;
        entity.setYaw(originalYaw);
        entity.setPitch(originalPitch);
        entity.prevHeadYaw = originalPrevHeadYaw;
        entity.headYaw = originalHeadYaw;
    }
}
package kipster.nt.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkProviderServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Shadow private MinecraftServer server;

    @Shadow @Final private WorldServer[] worlds;

    private Map<Long, Chunk> chunkCache = new ConcurrentHashMap<>();
    private ExecutorService optimizeExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Inject(method = "updateTimeLightAndEntities", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/chunk/Chunk;populateChunk()V"), cancellable = true)
    private void optimizeChunkPopulation(CallbackInfo ci) {
        ci.cancel(); // Cancel the original synchronous population

        // Iterate over loaded chunks
        for (WorldServer world : worlds) {
            // Get the loaded chunks for this world
            List<Chunk> chunks = (List<Chunk>) world.getPersistentChunks();

            // For each unpopulated chunk
            for (Chunk chunk : chunks) {
                if (!chunk.isPopulated()) {
                    // Populate it asynchronously along with adjacent chunks
                    populateChunkAsync(chunk);
                    preloadAdjacentChunks(chunk);
                }
            }
        }
    }

    private void populateChunkAsync(Chunk chunk) {
        server.addScheduledTask(() -> {
            if (!chunk.isPopulated()) {
                chunk.generateSkylightMap();
                // Perform additional chunk population logic here
            }
        });
    }

    private void preloadAdjacentChunks(Chunk chunk) {
        int chunkX = chunk.x;
        int chunkZ = chunk.z;

        int radius = 2; // Rayon of 2 chunks

        // Preload neighboring chunks
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                if (Math.abs(dx) + Math.abs(dz) <= radius) {
                    int adjacentX = chunkX + dx;
                    int adjacentZ = chunkZ + dz;
                    Chunk adjacentChunk = chunk.getWorld().getChunkProvider().getLoadedChunk(adjacentX, adjacentZ);
                    if (adjacentChunk != null && !adjacentChunk.isPopulated()) {
                        populateChunkAsync(adjacentChunk);
                    }
                }
            }
        }
    }

    @Redirect(method = "getChunkFromChunkCoords", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/gen/ChunkProviderServer;loadChunk(II)Lnet/minecraft/world/chunk/Chunk;"))
    private Chunk redirectLoadChunk(ChunkProviderServer chunkProviderServer, int x, int z) {
        Chunk chunk = chunkProviderServer.loadChunk(x, z);

        if (chunk != null && !chunk.isPopulated()) {
            populateChunkAsync(chunk);
            preloadAdjacentChunks(chunk);
        }

        return chunk;
    }

    @Inject(method = "onModuleStop", at = @At("HEAD"))
    private void onStop(CallbackInfo ci) {
        this.shutdown();
    }

    public void shutdown() {
        optimizeExecutor.shutdown();
        try {
            if (!optimizeExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                optimizeExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            optimizeExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
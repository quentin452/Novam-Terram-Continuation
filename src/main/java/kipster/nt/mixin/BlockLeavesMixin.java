package kipster.nt.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Mixin(BlockLeaves.class)
public class BlockLeavesMixin {
    private ExecutorService parallelExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        parallelExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Inject(method = "updateTick", at = @At("HEAD"), cancellable = true)
    private void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand, CallbackInfo ci) {
        // Réduire les lookups dans le WorldIn en utilisant une variable locale
        World world = worldIn;

        // Utiliser une taille de lot pour les boucles
        int batchSize = 100;

        // Obtenir les coordonnées du bloc et des blocs voisins
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        // Vérifier si l'état des blocs adjacents doit être mis à jour
        boolean shouldUpdateNeighbors = false;

        if (state.getValue(BlockLeaves.CHECK_DECAY)) {
            shouldUpdateNeighbors = true;
        }
        // Diviser l'opération updateTick en plusieurs tâches
        List<Callable<Void>> tasks = new ArrayList<>();

        tasks.add(() -> {
            // Obtenir le bloc à mettre à jour
            Block block = world.getBlockState(pos).getBlock();

            for (int i = 0; i < batchSize; i++) {
                int index = i;  // Capture la valeur de i pour chaque itération
                Runnable task = () -> {
                    // Mettre à jour le bloc dans cette itération
                        block.updateTick(world, pos, state, rand);
                };
                parallelExecutor.submit(task);
            }

            return null;
        });

        if (shouldUpdateNeighbors) {
            // Tâche 2 : Mettre à jour les blocs voisins
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        BlockPos neighborPos = new BlockPos(x + dx, y + dy, z + dz);
                        IBlockState neighborState = world.getBlockState(neighborPos);

                        tasks.add(() -> {
                            // Mettre à jour le bloc voisin si son état change
                            // ...
                            return null;
                        });
                    }
                }
            }
        }

        // Soumettre les tâches à l'ExecutorService pour les exécuter en parallèle
        try {
            parallelExecutor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ci.cancel();
    }
}

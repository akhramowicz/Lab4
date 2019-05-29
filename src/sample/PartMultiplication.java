package sample;


import com.sun.marlin.stats.Monitor;

public class PartMultiplication extends Thread {
    private int partVector1;
    private int partVector2;
    //boolean isReady = false;
    //int resulting = 0;
    //private Main main;
    private Integer[] vector1;
    private Integer[] vector2;
    private Integer[] result;
    private int index;
    public PartMultiplication(Integer[] vector1, Integer[] vector2, int index, Integer[] result) {
        this.vector1 = vector1;
        this.vector2 = vector2;
        this.index = index;
        this.result = result;
    }
    public int sendPartVector1(int index) {
        return vector1[index];
    }
    public int sendPartVector2(int index) { return vector2[index]; }
    @Override
    public void run()
    {
        System.out.println(partVector1);
        for (int index=0; index<Integer.min(vector1.length, vector2.length); index++) {
            partVector1 = sendPartVector1(index);
            partVector2 = sendPartVector2(index);
            result[index] = getResult();
        }
    }
    public int getResult(){
        return partVector1*partVector2;
    }



    /*public static Integer[] partMultiplication(Integer[] vector1, Integer[] vector2)
            throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        List<Future<Integer>> futures = new ArrayList<>();
        int min = Integer.min(vector1.length, vector2.length);
        int max = Integer.max(vector1.length, vector2.length);
        Integer[] result = new Integer[max];
        for(int i = 0; i < min; i++){
            final int i_fin = i;
            futures.add(CompletableFuture.supplyAsync(() -> vector1[i_fin] * vector2[i_fin], executor));
        }
        int id = 0;
        for (Future<Integer> future : futures) {
            result [id++] = future.get();
        }
        executor.shutdown();
        for(int i = id; i < max; i++){ result[i] = 0;}
        return result;
    }*/
}

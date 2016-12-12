import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sowmyaparameshwara on 9/28/16.
 */
public class CartesianProductAlgorithm {

    static String fileName1 = "Table1.txt";
    static String fileName2 = "Table2.txt";
    static String outputFile = "Output.txt";
    static List<String> table1,table2;
    long tableLength1, tableLength2;


    public static void main(String[] args) throws Exception {
        //writeInputData();
        long start = System.currentTimeMillis();
        CartesianProductAlgorithm cartesianProductAlgorithm = new CartesianProductAlgorithm();
        cartesianProductAlgorithm.compute();
        long end = System.currentTimeMillis();
        System.out.println("Time taken : "+(end-start));
    }

    public static void writeInputData() throws Exception {
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName1, true));
        for(int i=0; i<10; i++){
            printWriter.write("Sowmya Table 1 line "+ i + "\n");
        }
        printWriter.flush();
        printWriter.close();
        printWriter = new PrintWriter(new FileWriter(fileName2, true));
        for(int i=0; i<10; i++){
            printWriter.write("Sowmya Table 2 line " + i + "\n");
        }
        printWriter.flush();
        printWriter.close();
    }


    private  void compute() throws InterruptedException, IOException {
        ReadRunnable runnable1 = new ReadRunnable(fileName1); //Reads input from file 1
        ReadRunnable runnable2 = new ReadRunnable(fileName2); //Reads input from file 2
        Thread thread1 = new Thread(runnable1,"thread1");
        Thread thread2 = new Thread(runnable2,"thread2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        //Once both input files are read the cartesian product will be parsed
        tableLength1 = table1.size();
        tableLength2 = table2.size();
        parseOutput();
    }

    private void parseOutput() throws IOException {
        File file = new File(outputFile);
        file.delete();
        try{
        BufferedWriter writer = new BufferedWriter( new FileWriter( outputFile));
        long outputLength = (long)tableLength1*tableLength2;
        for(int i = 0 ; i <outputLength;i++){
            int indexTable1 = (int) (i/tableLength2); // tuple to be picked from table 1
            int indexTable2 = (int) (i%tableLength2); // tuple to be picked from table 2
            writer.write("("+ table1.get(indexTable1)+","+table2.get(indexTable2)+") \n");
        }
        writer.flush();
        writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runnable for reading data from a file
     */
    public static class ReadRunnable implements Runnable {

        String fileName;

        ReadRunnable(String fileName){
            this.fileName = fileName;
        }

        public synchronized void run() {
            List<String> list = new ArrayList<String>();
            try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
                list = br.lines().collect(Collectors.toList());
                br.close();
                if(Thread.currentThread().getName().equals("thread1")){
                    CartesianProductAlgorithm.table1 = list;
                }else{
                    CartesianProductAlgorithm.table2 = list;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}

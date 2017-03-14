import java.util.*;
import java.io.*;

public class Main
{
<<<<<<< HEAD
    
    
    /// Prints the commandline instructions.
    public static void main(String[] args) throws IOException
    {
        //load test files
        
        bp();
        
        
    }
    
=======
    /// Prints the commandline instructions.
    public static void main(String[] args) throws IOException
    {
        //load test files

        bp();
        
        
        System.out.print(sigmoid(2));
    }
    
>>>>>>> origin/master
    public static void bp() throws IOException { // backpropagation function
        
        //File file = new File("file.txt");
        //BufferedReader reader = null;
        
        
        double w1[][] = new double[3][15361]; // #input * #hidden nodes
        double w2[][] = new double[2][4]; // #hidden nodes * #finals nodes (2)
        
        double z1[] = new double[4]; // #hidden nodes
        double z2[] = new double[2]; // #final nodes
        
        double a1[] = new double[4]; // #hidden nodes
<<<<<<< HEAD
        double a2[] = new double[2]; // #final nodes      a2[0] = 1 if male    a2[1] = 1 if female
=======
        double a2[] = new double[2]; // #final nodes
>>>>>>> origin/master
        
        double l1[] = new double[3]; // learning error for hidden layer
        double l2[] = new double[2]; // learning error for the final layer
        
        double learnRate = .05;
        
<<<<<<< HEAD
        //load Male folder
        final File maleFolder = new File ("Male/");
        File [] maleFiles = maleFolder.listFiles();
        //load Female folder
        final File femaleFolder = new File ("Female/");
        File [] femaleFiles = femaleFolder.listFiles();
        //load Test folder
        final File testFolder = new File ("Test/");
        File [] testFiles = testFolder.listFiles();
        
        File [] currentFiles = maleFiles;
        int[] input = new int[128*120];
        int output[][] = new int [currentFiles.length][2];
    
        for (int i = 1; i < currentFiles.length-1; ++i){ // for each training image
            
            //set output
            if(currentFiles == maleFiles){
                output[i][0] = 1;
                output[i][1] = 0;
            }
            else{
                output[i][0] = 0;
                output[i][1] = 1;
            }
                
            
            //read in image array
            System.out.println(maleFiles[i]);
            FileReader fr = new FileReader("Male/"+maleFiles[i].getName());
            //BufferedReader br = new BufferedReader(fr);
            Scanner s = new Scanner(fr);
            for (int j = 0; j < input.length; j++){
                input[j] = s.nextInt();
            }
            
=======
        final File folder = new File ("Male/");
        File [] files = folder.listFiles();
        System.out.print(files[4]);
        
        int numTrain = 100;
        for (int i = 0; i < numTrain; ++i){ // for each training image
            System.out.println(files[i]);
            FileReader fr = new FileReader("Male/"+files[i].getName());
            BufferedReader br = new BufferedReader(fr);
            int y [] = new int [100];
            // int input [][] = new int [120][128];
                
            int[][] input = new int[1][128*120];
            for(int j = 0; j < 1; j++){
                Scanner s = new Scanner(fr);
                System.out.println(input[0].length);
                for (int z = 0; z < input[0].length; z++){
                    input[j][z] = s.nextInt();
                }
            }
            

            y[i] = 0; // temporary
>>>>>>> origin/master
            
            
            for (int j = 0; j < input.length; j++) {
                z1[j%3] += w1[j % 3][j/3];
            }
            
            /*
<<<<<<< HEAD
             for(int j = 0; j < w1.length; j++){
             for(int x = 0; x < w1[0].length - 1; x++){
             z1[j] += w1[j][x]  * input[i][x];
             }
             z1[j] += w1[j][15360]; //add a0 bias
             } */
=======
            for(int j = 0; j < w1.length; j++){
                for(int x = 0; x < w1[0].length - 1; x++){
                    z1[j] += w1[j][x]  * input[i][x];
                }
                z1[j] += w1[j][15360]; //add a0 bias
            } */
>>>>>>> origin/master
            
            for (int j = 0; j < z1.length - 1; j++){ // compute a(l) for all layers l
                a1[j] = sigmoid(z1[j]); // apply sigmoid function to z1
            }
            a1[3] = .5; // initialize a1 bias constant in hidden layer ???CHECK????
            
            for(int j = 0; j < w2.length; j++){
                for(int x = 0; x < w2[0].length; x++){
                    z2[j] += w2[j][x] * a1[x];  // uses weights in second layer to produce z2
                }
            }
            
            for (int k = 0; k < z2.length; ++k){
                a2[k] = sigmoid(z2[k]);
            }
            
            l2[0] = a2[0] - output[i][0];
            l2[1] = a2[1] - output[i][1]; // compute error in final layer
            
            
            for(int q = 0; q < l1.length; q++){ //compute error for hidden layer
                l1[q] = (w2[0][q]*l2[0] + w2[1][q]*l2[1]) * a1[q] * (1-a1[q]);
            }
            
            for(int j = 0; j < w1.length; j++){ // update weights for w1
                for(int x = 0; x < w1[0].length; x++){
                    w1[j][x] -= learnRate * (a1[j] * l1[j]);
                }
            }
            
            for(int j = 0; j < w2.length; j++){ // update weights for w2
                for(int x = 0; x < w2[0].length; x++){
                    w2[j][x] -= learnRate * (a2[j] * l2[j]);
                }
            }
            
            // compute error for all hidden layers
            // updates weights using heuristic optimization
            // w = w - (learning rate)*(a(l-1)* lambda(l))
        }//end training loop
        
    }
    
    
    public static double sigmoid (double z) { // ACTIVATION FUNCTION
        return 1/(1 + Math.pow(2.7182818285, z));
    }
}
<<<<<<< HEAD
=======

>>>>>>> origin/master

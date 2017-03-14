import java.util.*;
import java.io.*;

public class Main
{
    public static double [][] w1;
    public static double [][] w2;
    
    
    /// Prints the commandline instructions.
    public static void main(String[] args) throws IOException
    {
        //load Male folder
        final File maleFolder = new File ("Male/");
        File [] maleFiles = maleFolder.listFiles();
        int maleOutput[][] = new int [maleFiles.length][2];
        //load Female folder
        final File femaleFolder = new File ("Female/");
        File [] femaleFiles = femaleFolder.listFiles();
        int femaleOutput[][] = new int [femaleFiles.length][2];
        //load Test folder
        final File testFolder = new File ("Test/");
        File [] testFiles = testFolder.listFiles();
        int testOutput[][] = new int [testFiles.length][2];
        
        //we need a way to combine male and female folders and shuffle training data
        
        //initialize outputs
        for(int i = 0; i < maleFiles.length; i++){
            maleOutput[i][0] = 1;
            maleOutput[i][1] = 0;
        }
        for(int i = 0; i < femaleFiles.length; i++){
            femaleOutput[i][0] = 0;
            femaleOutput[i][1] = 1;
        }
        
        //initialize weights;
        w1 = new double[3][15361]; // #input * #hidden nodes
        w2 = new double[2][4]; // #hidden nodes * #finals nodes (2)
        for(int i = 0; i < w1.length; i++){
            for(int j = 0; j < w1[0].length; j++){
                w1[i][j] = Math.random();
            }
        }
        for(int i = 0; i < w2.length; i++){
            for(int j = 0; j < w2[0].length; j++){
                w2[i][j] = Math.random();
            }
        }
        
        bp("Female/", femaleFiles, femaleOutput);
        bp("Male/", maleFiles, maleOutput);
        
        System.out.print("\n\n");
        for(int i = 0; i < w2.length; i++){
            for(int j = 0; j < w2[0].length; j++){
                System.out.print(w2[i][j]+" ");
            }
            System.out.print("\n");
        }
        
        
        
        test("Test/", testFiles);
        
    }
    

    public static void bp(String dir, File[] files, int[][] output) throws IOException { // backpropagation function
        
        double z1[] = new double[4]; // #hidden nodes
        double z2[] = new double[2]; // #final nodes
        
        double a1[] = new double[4]; // #hidden nodes
        double a2[] = new double[2]; // #final nodes      a2[0] = 1 if male    a2[1] = 1 if female
        
        double l1[] = new double[3]; // learning error for hidden layer
        double l2[] = new double[2]; // learning error for the final layer
        
        
        double learnRate = .05;
        

        int[] input = new int[128*120];
        
    
        for (int i = 1; i < files.length-1; ++i){ // for each training image
            System.out.println("new image\n");
            //read in image array
            //FileReader fr = new FileReader("Male/"+maleFiles[i].getName());
            FileReader fr = new FileReader(dir+files[i].getName());
            //BufferedReader br = new BufferedReader(fr);
            Scanner s = new Scanner(fr);
            for (int j = 0; j < input.length; j++){
                input[j] = s.nextInt();
            }
            
    
            //compute z1
            System.out.println("Computing z1");
            for(int j = 0; j < w1.length; j++){
                for(int x = 0; x < w1[0].length - 1; x++){
                    z1[j] += w1[j][x]  * input[x];
                }
                z1[j] += w1[j][15360]; //add a0 bias
                System.out.print(z1[j]+" ");
            }
            
            
            // compute a(l) for all layers
            System.out.println("\nComputing a1");
            for (int j = 0; j < z1.length - 1; j++){
                a1[j] = sigmoid(z1[j]); // apply sigmoid function to z1
                System.out.print(a1[j]+" ");
            }
            a1[3] = Math.random(); // initialize a1 bias constant in hidden layer ???CHECK????
            
            
            //compute z2
            System.out.println("\nComputing z2");
            for(int j = 0; j < w2.length; j++){
                for(int x = 0; x < w2[0].length; x++){
                    z2[j] += w2[j][x] * a1[x];  // uses weights in second layer to produce z2
                    System.out.print(z2[j]+" ");
                }
            }
            
            System.out.println("\nComputing a1");
            for (int k = 0; k < z2.length; ++k){
                a2[k] = sigmoid(z2[k]);
                System.out.print(a1[k]+" ");
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
            
            System.out.println("\nUpdating w2");
            for(int j = 0; j < w2.length; j++){ // update weights for w2
                for(int x = 0; x < w2[0].length; x++){
                    w2[j][x] -= learnRate * (a2[j] * l2[j]);
                    System.out.println(w2[j][x]+" ");
                }
            }
            
            // compute error for all hidden layers
            // updates weights using heuristic optimization
            // w = w - (learning rate)*(a(l-1)* lambda(l))
            
            //reset z1, z2, a1, a2
            for(int j = 0; j < z1.length; j++){
                z1[j] = 0;
                a1[j] = 0;
            }
            for(int j = 0; j < z2.length; j++){
                z2[j] = 0;
                a2[j] = 0;
            }
            
        }//end training loop
        
    }
    
    public static void test(String dir, File[] files) throws IOException {
        double z1[] = new double[4]; // #hidden nodes
        double z2[] = new double[2]; // #final nodes
        
        double a1[] = new double[4]; // #hidden nodes
        double a2[] = new double[2]; // #final nodes      a2[0] = 1 if male    a2[1] = 1 if female

        
        int[] input = new int[128*120];
        
        
        for (int i = 1; i < files.length-1; ++i){ // for each training image
            //read in image array
            FileReader fr = new FileReader(dir+files[i].getName());
            Scanner s = new Scanner(fr);
            for (int j = 0; j < input.length; j++){
                input[j] = s.nextInt();
            }
            
            
            //compute z1
            for(int j = 0; j < w1.length; j++){
                for(int x = 0; x < w1[0].length - 1; x++){
                    z1[j] += w1[j][x]  * input[x];
                }
                z1[j] += w1[j][15360]; //add a0 bias
            }
            
            
            // compute a(l) for all layers
            for (int j = 0; j < z1.length - 1; j++){
                a1[j] = sigmoid(z1[j]); // apply sigmoid function to z1
            }
            a1[3] = Math.random(); // initialize a1 bias constant in hidden layer ???CHECK????
            
            
            //compute z2
            for(int j = 0; j < w2.length; j++){
                for(int x = 0; x < w2[0].length; x++){
                    z2[j] += w2[j][x] * a1[x];  // uses weights in second layer to produce z2
                }
            }
            
            for (int k = 0; k < z2.length; ++k){
                a2[k] = sigmoid(z2[k]);
            }
            
            //results
            if(a2[0] >= a2[1]){ //predicts male
                System.out.print("Prediction: Male  Actually: ");
                System.out.println(files[i].getName());
            }
            else{//predicts female
                System.out.print("Prediction: Female  Actually: ");
                System.out.println(files[i].getName());
            }
            
            
            //reset z1, z2, a1, a2
            for(int j = 0; j < z1.length; j++){
                z1[j] = 0;
                a1[j] = 0;
            }
            for(int j = 0; j < z2.length; j++){
                z2[j] = 0;
                a2[j] = 0;
            }
            
        }//end training loop
    }
    
    
    public static double sigmoid (double z) { // ACTIVATION FUNCTION
        return 1/(1 + Math.pow(2.7182818285, -z));
    }
}

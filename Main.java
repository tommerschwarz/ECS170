public class Main
{
        /// Prints the commandline instructions.
        public static void main(String[] args)
        {
            
            bp();
            
            
            System.out.print(sigmoid(2));
        }
    
    public static void bp() { // backpropagation function
        
        //File file = new File("file.txt");
        //BufferedReader reader = null;
        
        int w1[] = new int[46080]; // #input * #hidden nodes
        int w2[] = new int[6]; // #hidden nodes * #finals nodes (2)
        
        int z1[] = new int[3]; // #hidden nodes
        int z2[] = new int[2]; // #final nodes
        
        int a1[] = new int[3]; // #hidden nodes
        int a2[] = new int[2]; // #final nodes
        
        double l1[] = new double[3]; // learning error for hidden layer
        double l2[] = new double[2]; // learning error for the final layer
        
        double learnRate = .05;
        
        int numTrain = 100;
        for (int i = 0; i < 100; ++i){ // for each training image
            for (int j = 0; j < w1.length; ++j)
                z1 [j % 3] += w1[j]* (VALUE IN X1);
                        // set a1 to xk, each value in a1 is a sum of all x values, multiplied by the weights leading to that hidden node
                
            for (int h = 0; h < z1.length; ++h){ // compute a(l) for all layers l
                a1 [h] = sigmoid(z1[h]); //multiply by weights and add up
            }
            
            for (int m = 0; m < w2.length; ++m)
                z2 [m % 2] += w2[j]* a1[(m*3)/3]; // CHECK THIS
            
            for (int k = 0; k < z2.length; ++k){
                a2[k] = sigmoid(z2[k]);
            }
            
            l2[i] = a2[i] - y[i]; // compute error in final error
            
            
                        // compute error for all hidden layers
                        // updates weights using heuristic optimization
                        // w = w - (learning rate)*(a(l-1)* lambda(l))
        }
        
    }
    
    
    public static double sigmoid (double z) { // ACTIVATION FUNCTION
        return 1/(1 + Math.pow(2.7182818285, z));
    }
}



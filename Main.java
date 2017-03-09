public class Main
{
        /// Prints the commandline instructions.
        public static void main(String[] args)
        {
            int w1[] = new int[46080]; // #input * #hidden nodes
            int w2[] = new int[6]; // #hidden nodes * #finals nodes (2)
            
            int z1[] = new int[3]; // #hidden nodes
            int z2[] = new int[2]; // #final nodes
            
            int a1[] = new int[3]; // #hidden nodes
            int a2[] = new int[2]; // #final nodes
            
            bp();
            
            
            System.out.print(sigmoid(2));
        }
    
    public static void bp(File x, double y) { // backpropagation function
        
        File file = new File("file.txt");
        BufferedReader reader = null;
        
        int numTrain = 100;
        for (int i = 0; i < 100; ++i){ // for each training image
        
                        // set a1 to xk
                        // compute a(l) for all layers l
                        // compute error in final error
                        // compute error for all hidden layers
                        // updates weights using heuristic optimization
                        // w = w - (learning rate)*(a(l-1)* lambda(l))
        }
        
    }
    
    
    public static double sigmoid (double z) { // ACTIVATION FUNCTION
        return 1/(1 + Math.pow(2.7182818285, z));
    }
}



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
        
        double w1[] = new double[46080]; // #input * #hidden nodes
        double w2[] = new double[6]; // #hidden nodes * #finals nodes (2)
        
        double z1[] = new double[3]; // #hidden nodes
        double z2[] = new double[2]; // #final nodes
        
        double a1[] = new double[3]; // #hidden nodes
        double a2[] = new double[2]; // #final nodes
        
        double l1[] = new double[3]; // learning error for hidden layer
        double l2[] = new double[2]; // learning error for the final layer
        
        double learnRate = .05;
        
        int numTrain = 100;
        for (int i = 0; i < 100; ++i){ // for each training image
            for (int j = 0; j < w1.length; ++j)
                z1 [j % 3] += w1[j]* (VALUE IN X1);
                        // set a1 to xk, each value in a1 is a sum of all x values, multiplied by the weights leading to that hidden node
                
            for (int h = 0; h < z1.length; ++h){ // compute a(l) for all layers l
                a1 [h] = sigmoid(z1[h]); // apply sigmoid function to z1
            }
            
            for (int m = 0; m < w2.length; ++m)
                z2 [m % 2] += w2[j]* a1[m % 3]; // uses weights in second layer to produce z2
            
            for (int k = 0; k < z2.length; ++k){
                a2[k] = sigmoid(z2[k]);
            }
            
            l2[0] = a2[0] - y[i];
            l2[1] = a2[1] - y[i]; // compute error in final layer
            
            
            for (int n = 0; n < 3; ++n) {
                for (int o = 0; o < 6; ++o){
                    l1[n] *= w2[o/2] * l2[n % 2] * a1[n] * (1 - a[n]); // compute error for hidden layer
                }
            }
            
            for (int p = 0; p < w1.length; ++p){ // update weights for w1
                w1[p] -= learnRate * (a1[p % 3] * l1[p % 3]);
            }
            
            for (int q = 0; q < w2.length; ++q){ // update weights for w2
                w2[q] -= learnRate * (a2[q % 2] * l2[q % 2])
            }
            
                        // compute error for all hidden layers
                        // updates weights using heuristic optimization
                        // w = w - (learning rate)*(a(l-1)* lambda(l))
        }
        
    }
    
    
    public static double sigmoid (double z) { // ACTIVATION FUNCTION
        return 1/(1 + Math.pow(2.7182818285, z));
    }
}



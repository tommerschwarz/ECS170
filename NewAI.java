public class NewAI extends AIModule
{
	/*public void getNextMove(final GameStateModule game)
	{
        int n;
        int val = -10001;
        int move = 0;
        int i = 0;
        
        for (i = 0; i < 7; ++i) {
            n = minimax(game, 1, 0);
            if ( -n > val){
                val = -n;
                move = i;
            }
            game.unMakeMove();
        }
        //System.out.print(i);
        chosenMove = move + 1;
	}*/
    public void getNextMove(final GameStateModule game)
    {
        int score;
        int[] values = new int[game.getWidth()];
        for(int i = 0; i < game.getWidth(); i++)
        {
            if (game.canMakeMove(i))
            {
                game.makeMove(i);
                score = minimax(game, 1, 0);
                values[i] = score;
                game.unMakeMove();
            }
        }
        System.out.print("\n\nNEW TURN:\n");
        int bestmove = -1;
        int bestscore = -10000;
        for(int i = 0; i < game.getWidth(); i++)
        {
            System.out.print(values[i]+"\n");
            game.makeMove(i);
            if (game.isGameOver()){
                bestmove = i;
                break;
            }
            game.unMakeMove();
            if(values[i] > bestscore && game.canMakeMove(i)) {
                bestscore = values[i];
                bestmove = i;}
        }
        System.out.print("bestmove:"+bestmove);
       
        
        chosenMove = bestmove;
    }
    
    
    
    /*public int minimax(final GameStateModule game, int depth) {
        if (depth == 3){
            return heuristic(game);
        }

        int currentPlayer = game.getActivePlayer();
        int[] values = new int[game.getWidth()];
        int n;
        int best = -1000;

        for (int i = 0; i < values.length; ++i){
            if (!game.canMakeMove(i)) {
                values[i] = -Integer.MAX_VALUE; }
            // make move for each column and run minimax
            else {
                game.makeMove(i);
                //values[i] = heuristic(game);
                n = minimax(game, depth);
                //values[i] = minimax(game,depth);
                
                if (-n > best){
                    best = -n;
                }
                
                game.unMakeMove();
            }
        }
        
        depth++;
        
        return best;
        
        /*
        int j = 0;
        int best = -10000;
        int bestpos = 0;
        while (j < 7) {
            System.out.print(values[j]+" ");
            if (values[j] > best) {
                best = values[j];
                bestpos = j;
            }
            j++;
        }
        System.out.print("\n"+bestpos+"\n");
        return bestpos;
    } */
    
    public int minimax(final GameStateModule game, int player, int depth)
    {
        int width = game.getWidth();
        int oppscore;
        int score;
        int scorepos = -1;
        
        //check if draw
        if(game.getCoins() == game.getWidth() * game.getHeight()){
            return 0; }
        
        //check if game ending move
        if(isWinningMove(game))
        {
            if(player == 1){
                return 999;}
            else {
                return -999; }
            
        }
        
        //check if max depth
        if(depth == 3) {
            return heuristic(game);}
        
        //MAX
        if(player == 1)
        {
            score = -9999;
            for(int i = 0; i < 7; ++i)
            {
                if (game.canMakeMove(i))
                {
                    game.makeMove(i);

                    oppscore = minimax(game, player*-1, depth + 1);
                    
                    //System.out.print(i+"-"+oppscore+" ");
                    
                    if(oppscore > score) {
                        score = oppscore;
                        scorepos = i;}
                    game.unMakeMove();
                }
            }
           // System.out.print("\nNEW LEVEL\n");

        }
        
        //MIN
        else
        {
            score = 9999;
            for(int i = 0; i < 7; ++i)
            {
                if (game.canMakeMove(i))
                {
                    game.makeMove(i);
                    oppscore = minimax(game, player*-1, depth + 1);
                    if(oppscore < score) {
                        System.out.print("HEREHEREHERE");
                        score = oppscore;
                        scorepos = i;}
                    game.unMakeMove();
                }
            }
        }
       // System.out.print("RETURNED:"+scorepos+"-"+score+"\n\n");
        return score;
    }
    
    
    /*
    public int heuristic (final GameStateModule game){
        int result = 0;
        int oppo;
        int [][] threats  = {{2,2,2,3,3,3},{3,4,4,6,5,4},{4,6,8,9,7,5},{6,8,10,11,9,7},{4,6,8,9,7,5},{3,4,4,6,5,4},{2,2,2,3,3,3}};
        
        int currentPlayer = game.getActivePlayer();
        if (currentPlayer == 1){
            oppo = 2; }
        else {
            oppo = 1; }
        
       for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 6; ++j){
                if (game.getAt(i,j) == currentPlayer){
                    result = result + threats[i][j];
                }
                if (game.getAt(i,j) == oppo){
                    result = result - threats[i][j];
                }
            }
        }
        

        for (int i = 0; i < 7; ++i){
            /*if (isWinningMove(game)){
                return 999;
            } */
         /*   if (!game.canMakeMove(i)){
                return -999;
            }
            if (threats[i][game.getHeightAt(i)] > result) {
                result = result + threats[i][game.getHeightAt(i)];
            }
        }
        //System.out.print("RESULT:"+result+"\n");
        
        return result;
                         }
    boolean oppoWin (final GameStateModule game) {
        
        for (int i = 0; i < 7; i++) {
            if (game.getHeightAt(i) > 3) {
                if ( (game.getAt(i,game.getHeightAt(i) - 1) == oppo) && (game.getAt(i,game.getHeightAt(i) - 2) == oppo) && (game.getAt(i,game.getHeightAt(i)) - 3 == oppo))
                    return true;
                //diag up-right
                if ( i >= 3 )
                    if ( (game.getAt(i - 1, game.getHeightAt(i) - 1) == oppo) && (game.getAt(i - 2, game.getHeightAt(i) - 2) == oppo) && (game.getAt(i - 3, game.getHeightAt(i) - 3) == oppo) )
                        return true;
            } }
        // check horizontal
        
        for (int i = 0; i <= game.getWidth() - 3; i++) {
            if ( (game.getAt(i + 1, game.getHeightAt(i)) == oppo) && (game.getAt(i + 2, game.getHeightAt(i)) == oppo) && (game.getAt(i + 3, game.getHeightAt(i)) == oppo))
                return true;
            //check up-left
            if (game.getHeightAt(i) >= 3)
                if ( (game.getAt(i + 1, game.getHeightAt(i) - 1) == oppo) && (game.getAt(i + 2, game.getHeightAt(i) - 2) == oppo) && (game.getAt(i + 3, game.getHeightAt(i) - 3) == oppo) )
                    return true;
            
        }
        
        return false;
        
    } */
    
    
    ////////
    
    
    public int heuristic (final GameStateModule game)
    {
        int player = game.getActivePlayer();
        int oppo;
        if (player == 1){
            oppo = 2;
        }
        else {
            oppo = 1;
        }
        
        int threat;
        if(player == 1)
            threat = 1;
        else
            threat = 0;
        //if player = 1 then favor odd threats
        //if player = 2 then favor even threats
        //determined by row % 2
        
        int score = 0;
        
        //initialize threats value table
        int [][]threats = new int[7][6];
        for(int i = 0; i < game.getWidth(); i++)
        {
            for(int j = 0; j < game.getHeight(); j++)
            {
                threats[i][j] = 0;
            }
        }
        
        //check horizontals
        for(int i = 0; i < game.getWidth() - 3; i++)
        {
            for(int j = 0; j < game.getHeight(); j++)
            {
                //if blocked, continue
                if(game.getAt(i,j)==oppo || game.getAt(i+1,j)==oppo || game.getAt(i+2,j)==oppo || game.getAt(i+3,j)==oppo)
                    continue;
                else
                {
                    int count = 0;
                    //checks how many coins are in this block
                    for(int q = 0; q < 4; q++)
                    {
                        if(game.getAt(i+q,j)==player) count++;
                    }
                    //add to position's value
                    for(int q = 0; q < 4; q++)
                    {
                        //if empty position, add to values array; else do nothing
                        if(game.getAt(i+q,j)==0)
                            threats[i+q][j] += 10^(count+(threat+j)%2);// + dosomethingforthreat);
                            }
                }
            }
        }
        
        //check for right incr diagonals
        for(int i = 0; i < game.getWidth() - 3; i++)
        {
            for(int j = 0; j < game.getHeight()-3; j++)
            {
                //if blocked, continue
                if(game.getAt(i,j)==oppo || game.getAt(i+1,j+1)==oppo || game.getAt(i+2,j+2)==oppo || game.getAt(i+3,j+3)==oppo)
                    continue;
                else
                {
                    int count = 0;
                    //checks how many coins are in this block
                    for(int q = 0; q < 4; q++)
                    {
                        if(game.getAt(i+q,j+q)==player) count++;
                    }
                    //add to position's value
                    for(int q = 0; q < 4; q++)
                    {
                        //if empty position, add to values array; else do nothing
                        if(game.getAt(i+q,j+q)==0)
                            threats[i+q][j+q] += 10^(count+(threat+j)%2);// + dosomethingforthreat);
                    }
                }
            }
        }
        
        //check for left diagonals
        for(int i = 3; i < game.getWidth(); i++)
        {
            for(int j = 0; j < game.getHeight()-3; j++)
            {
                //if blocked, continue
                if(game.getAt(i,j)==oppo || game.getAt(i-1,j+1)==oppo || game.getAt(i-2,j+2)==oppo || game.getAt(i-3,j+3)==oppo)
                    continue;
                else
                {
                    int count = 0;
                    //checks how many coins are in this block
                    for(int q = 0; q < 4; q++)
                    {
                        if(game.getAt(i-q,j+q)==player) count++;
                    }
                    //add to position's value
                    for(int q = 0; q < 4; q++)
                    {
                        //if empty position, add to values array; else do nothing
                        if(game.getAt(i-q,j+q)==0)
                            threats[i-q][j+q] += 10^(count+(threat+j)%2); // + dosomethingforthreat);
                    }
                }
            }
        }
        
        //check for verticals
        for(int i = 0; i < game.getWidth(); i++)
        {
            for(int j = 3; j < game.getHeight(); j++)
            {
                //if blocked, continue
                if(game.getAt(i,j)==oppo || game.getAt(i,j-1)==oppo || game.getAt(i,j-2)==oppo || game.getAt(i,j-3)==oppo)
                    continue;
                else
                {
                    int count = 0;
                    //checks how many coins are in this block
                    for(int q = 0; q < 4; q++)
                    {
                        if(game.getAt(i,j-q)==player) count++;
                    }
                    //if empty position, add to values array; else do nothing
                    if(game.getAt(i,j)==0) 
                        threats[i][j] += 10^count;
                }
            }
        }
        
        for(int i = 0; i < game.getWidth(); i++)
        {
            for(int j = 0; j < game.getHeight(); j++)
            {
                score += threats[i][j];
            }
        }

        return -score;
    }
    
    
    
////////////
    
    
    
    boolean isWinningMove (final GameStateModule game) {
        
        int currentPlayer = game.getActivePlayer();
        int oppo;
        if (currentPlayer == 1){
            oppo = 2;
        }
        else {
            oppo = 1;
        }
        
        // check vertical
        
        for (int i = 0; i < 7; i++) {
            if (game.getHeightAt(i) > 3) {
                if ( (game.getAt(i,game.getHeightAt(i) - 1) == currentPlayer) && (game.getAt(i,game.getHeightAt(i) - 2) == currentPlayer) && (game.getAt(i,game.getHeightAt(i)) - 3 == currentPlayer))
                    return true;
                //diag up-right
                if ( i >= 3 )
                    if ( (game.getAt(i - 1, game.getHeightAt(i) - 1) == currentPlayer) && (game.getAt(i - 2, game.getHeightAt(i) - 2) == currentPlayer) && (game.getAt(i - 3, game.getHeightAt(i) - 3) == currentPlayer) )
                        return true;
            } }
        // check horizontal
        
        for (int i = 0; i <= game.getWidth() - 3; i++) {
            if ( (game.getAt(i + 1, game.getHeightAt(i)) == currentPlayer) && (game.getAt(i + 2, game.getHeightAt(i)) == currentPlayer) && (game.getAt(i + 3, game.getHeightAt(i)) == currentPlayer))
                return true;
            //check up-left
            if (game.getHeightAt(i) >= 3)
                if ( (game.getAt(i + 1, game.getHeightAt(i) - 1) == currentPlayer) && (game.getAt(i + 2, game.getHeightAt(i) - 2) == currentPlayer) && (game.getAt(i + 3, game.getHeightAt(i) - 3) == currentPlayer) )
                    return true;
            
        }
        // CHECK FOR OPPONENTS WINNING MOVE
        
        for (int i = 0; i < 7; i++) {
            if (game.getHeightAt(i) > 3) {
                if ( (game.getAt(i,game.getHeightAt(i) - 1) == oppo) && (game.getAt(i,game.getHeightAt(i) - 2) == oppo) && (game.getAt(i,game.getHeightAt(i)) - 3 == oppo))
                    return true;
                //diag up-right
                if ( i >= 3 )
                    if ( (game.getAt(i - 1, game.getHeightAt(i) - 1) == oppo) && (game.getAt(i - 2, game.getHeightAt(i) - 2) == oppo) && (game.getAt(i - 3, game.getHeightAt(i) - 3) == oppo) )
                        return true;
            } }
        // check horizontal
        
        for (int i = 0; i <= game.getWidth() - 3; i++) {
            if ( (game.getAt(i + 1, game.getHeightAt(i)) == oppo) && (game.getAt(i + 2, game.getHeightAt(i)) == oppo) && (game.getAt(i + 3, game.getHeightAt(i)) == oppo))
                return true;
            //check up-left
            if (game.getHeightAt(i) >= 3)
                if ( (game.getAt(i + 1, game.getHeightAt(i) - 1) == oppo) && (game.getAt(i + 2, game.getHeightAt(i) - 2) == oppo) && (game.getAt(i + 3, game.getHeightAt(i) - 3) == oppo) )
                    return true;
            
        }
        
        return false;
    }

}


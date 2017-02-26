public class NewAI extends AIModule
{
	public void getNextMove(final GameStateModule game)
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
        }
        //System.out.print(i);
        chosenMove = move;
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
            for(int i = 0; i < width; ++i)
            {
                if (game.canMakeMove(i))
                {
                    game.makeMove(i);
                    oppscore = minimax(game, player*-1, depth + 1);
                    if(oppscore > score) {
                        score = oppscore;
                        scorepos = i;}
                    game.unMakeMove();
                }
            }

        }
        
        //MIN
        else
        {
            score = 9999;
            for(int i = 0; i < width; ++i)
            {
                if (game.canMakeMove(i))
                {
                    game.makeMove(i);
                    oppscore = minimax(game, player*-1, depth + 1);
                    if(oppscore < score) {
                        score = oppscore;
                        scorepos = i;}
                    game.unMakeMove();
                }
            }
        }
        
        return scorepos - 1;
    }
    
    public int heuristic (final GameStateModule game){
        int result = 0;
        int oppo;
        int currentPlayer = game.getActivePlayer();
        if (currentPlayer == 1){
            oppo = 2; }
        else {
            oppo = 1; }
        // check horizontals
        for (int i = 0; i <= 3; ++i){
            for (int j = 0; j < 7; ++j) {
                int[] horiz = new int[4];
                int[] vert = new int[4];
                horiz[0] = game.getAt(i,j);
                horiz[1] = game.getAt(i+1,j);
                horiz[2] = game.getAt(i+2,j);
                horiz[3] = game.getAt(i+3,j);
                vert[0] = game.getAt(i,j);
                vert[1] = game.getAt(i,j+1);
                vert[2] = game.getAt(i,j+2);
                vert[3] = game.getAt(i,j+3);


                if ((game.getAt(i,j) == currentPlayer || game.getAt(i,j) == 0) && (game.getAt(i + 1,j) == currentPlayer || game.getAt(i + 1,j) == 0)  && (game.getAt(i + 2,j) == currentPlayer || game.getAt(i + 2,j) == 0)  && (game.getAt(i + 3,j) == currentPlayer || game.getAt(i + 3,j) == 0)) {
                    int count = 0;
                    int pc = 0;
                    while (count < 4){
                        if (horiz[count] == currentPlayer)
                            pc++;
                        count++; }
                    
                    result = result + pc*5 +1; }

                
                
                if ((game.getAt(i,j) == oppo || game.getAt(i,j) == 0) && (game.getAt(i + 1,j) == oppo || game.getAt(i + 1,j) == 0)  && (game.getAt(i + 2,j) == oppo || game.getAt(i + 2,j) == 0)  && (game.getAt(i + 3,j) == oppo || game.getAt(i + 3,j) == 0)) {
                    
                    int count = 0;
                    int pc = 0;
                    while (count < 4) {
                        if (vert[count] == currentPlayer)
                            pc++;
                        count++; }
                    
                    result = result - (pc*5 +1); }
                
 
                

            }
        }
        
        // check verticals
        for (int i = 0; i < 7; ++i){
            for (int j = 0; j <= 3; ++j) {
                
                if ((game.getAt(i,j) == currentPlayer || game.getAt(i,j) == 0) && (game.getAt(i,j+1) == currentPlayer || game.getAt(i,j+1) == 0)  && (game.getAt(i,j+2) == currentPlayer || game.getAt(i,j+2) == 0)  && (game.getAt(i,j+3) == currentPlayer || game.getAt(i,j+3) == 0)) {
                    result++; }

                if ((game.getAt(i,j) == oppo || game.getAt(i,j) == 0) && (game.getAt(i,j+1) == oppo || game.getAt(i,j+1) == 0)  && (game.getAt(i,j+2) == oppo || game.getAt(i,j+2) == 0)  && (game.getAt(i,j+3) == oppo || game.getAt(i,j+3) == 0)) {
                    result--; }
            }
        }
        
        return result;
                         }
    
    boolean isWinningMove (final GameStateModule game) {
        
        int currentPlayer = game.getActivePlayer();
        
        // check vertical
        
        for (int i = 0; i <= game.getHeight() - 3; i++) {
            if ( (game.getAt(i,game.getHeightAt(i)) - 1 == currentPlayer) && (game.getAt(i,game.getHeightAt(i)) - 2 == currentPlayer) && (game.getAt(i,game.getHeightAt(i)) - 3 == currentPlayer))
                return true;
            //diag up-right
            if ( i >= 3 )
                if ( (game.getAt(i - 1, game.getHeightAt(i) - 1) == currentPlayer) && (game.getAt(i - 2, game.getHeightAt(i) - 2) == currentPlayer) && (game.getAt(i - 3, game.getHeightAt(i) - 3) == currentPlayer) )
                    return true;
        }
        // check horizontal
        
        for (int i = 0; i <= game.getWidth() - 3; i++) {
            if ( (game.getAt(i + 1, game.getHeightAt(i)) == currentPlayer) && (game.getAt(i + 2, game.getHeightAt(i)) == currentPlayer) && (game.getAt(i + 3, game.getHeightAt(i)) == currentPlayer))
                return true;
            //check up-left
            if (game.getHeightAt(i) >= 3)
                if ( (game.getAt(i + 1, game.getHeightAt(i) - 1) == currentPlayer) && (game.getAt(i + 2, game.getHeightAt(i) - 2) == currentPlayer) && (game.getAt(i + 3, game.getHeightAt(i) - 3) == currentPlayer) )
                    return true;
            
        }
        return false;
    }

    
    
}

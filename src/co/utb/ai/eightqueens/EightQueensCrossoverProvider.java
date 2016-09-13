/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.utb.ai.eightqueens;

import co.mechanism.optimizers.evolutionary.Individual;
import co.mechanism.optimizers.evolutionary.genetic.OnePointCrossoverProvider;
import co.mechanism.utils.MersenneTwisterFast;
import co.mechanism.utils.Utils;
import java.util.List;
import java.util.Random;

/**
 *
 * @author labsoftware13
 */
public class EightQueensCrossoverProvider extends OnePointCrossoverProvider {

    @Override
    public List<Individual> mate(Individual firstParent, Individual secondParent) {
        //System.out.println("padres:");
        //Eightqueens.PrintBoard(firstParent.getPosition());
        
        //Eightqueens.PrintBoard(secondParent.getPosition());


        List<Individual> offspring = super.mate(firstParent, secondParent); 
        
        //Simple reparing heuristic
        Individual board1 = offspring.get(0);
        Individual board2 = offspring.get(1);
        int numberOfQueens = getNumberOfQueens(board1);
        if (numberOfQueens > 8) {
            removeQueens(board1, numberOfQueens);
        } else {
            if (numberOfQueens < 8) {
                addQueens(board1, numberOfQueens);
            }
        }
        
        numberOfQueens = getNumberOfQueens(board2);
        if (numberOfQueens > 8) {
            removeQueens(board2, numberOfQueens);
        } else {
            if (numberOfQueens < 8) {
                addQueens(board2, numberOfQueens);
            }
        }
        //System.out.println("hijos:");
        //Eightqueens.PrintBoard(offspring.get(0).getPosition());
        //Eightqueens.PrintBoard(offspring.get(1).getPosition());
        return offspring;
    }
    
    private int getNumberOfQueens(Individual board) {
        int numberOfQueens = 0;
        for (Double d : board.getPosition()) {
            if (d == 1.0) numberOfQueens++;
        }
        return numberOfQueens;
    }
    
    private void removeQueens(Individual board, int numberOfQueens) {
        MersenneTwisterFast rng = Utils.getMTInstance();
        while(numberOfQueens > 8) {
            int queenToRemove = rng.nextInt(numberOfQueens) + 1;
            int queensSeen = 0;
            for(int i = 0; i < 64; i++) {
                if (board.getPosition().get(i) == 1.0) {
                    queensSeen++;
                    if (queensSeen == queenToRemove) {
                        board.getPosition().set(i, 0.0);
                        numberOfQueens--;
                        break;
                    }
                }
            }
            
        }
    }
    
    private void addQueens(Individual board, int numberOfQueens) {
        Random rng = new Random();
        while(numberOfQueens < 8) {
            int queenPosition;
            while(board.getPosition().get(queenPosition = rng.nextInt(64)) != 0.0);
            board.getPosition().set(queenPosition, 1.0);
            numberOfQueens++;
        }
    }
    
}

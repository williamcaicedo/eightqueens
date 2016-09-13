/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.utb.ai.eightqueens;

import co.mechanism.optimizers.evolutionary.Individual;
import co.mechanism.optimizers.evolutionary.genetic.ElitismReplacementProvider;
import co.mechanism.optimizers.evolutionary.genetic.GeneticOptimizer;
import co.mechanism.optimizers.evolutionary.genetic.SUSWithRankSelectionProvider;
import co.utb.ai.eightqueens.ui.ChessBoard;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author labsoftware13
 */
public class Eightqueens {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        List<Double> min = Collections.nCopies(64, Double.valueOf(0));
        List<Double> max = Collections.nCopies(64, Double.valueOf(1));
        GeneticOptimizer genetic = new GeneticOptimizer(200, 0.5, min, max,
                new CostFunction(),
                new SUSWithRankSelectionProvider(1, 200),
                new EightQueensCrossoverProvider(),
                new ElitismReplacementProvider(),
                new EightQueensMutationProvider(),
                new EightQueensRandomBoardProvider());

        for (int i = 0; i < 3000; i++) {
            System.out.println("Generación " + (i + 1) +  ":");
            genetic.evolve();
            
//            if ((i+1)%100==0) {
//                System.out.println();
//                System.out.println("Generación " + (i + 1) +  ":");
//                PrintBoard(genetic.getBestSolution().getPosition());
//            }
                
            
            System.out.println("Costo: " + genetic.getBestSolution().getValue());
            
            
        }
        Individual sol = ((Individual) genetic.getBestSolution()).clone();
        System.out.println("Costo: " + genetic.getBestSolution().getValue());
        //sol.getPosition().stream().forEach(d -> System.out.println(d));
        PrintBoard(sol.getPosition());
        new ChessBoard(sol.getPosition()).setVisible(true);
    }
    
    public static void PrintBoard(List<Double> board) {
        int i = 0;
        for(Double d : board) {
            if (i%8 == 0) {
                System.out.println();
            }
            if (d == 0.0) System.out.print("_" + " "); else System.out.print("*" + " ");
            i++;
        }
        System.out.println();
    }

}

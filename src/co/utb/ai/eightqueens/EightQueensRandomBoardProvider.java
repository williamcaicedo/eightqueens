/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.utb.ai.eightqueens;

import co.mechanism.core.AbstractOptimizer;
import co.mechanism.core.ISearchAgent;
import co.mechanism.optimizers.evolutionary.genetic.IPositionProvider;
import co.mechanism.utils.MersenneTwisterFast;
import co.mechanism.utils.Utils;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author william
 */
public class EightQueensRandomBoardProvider implements IPositionProvider {

    private MersenneTwisterFast mt;

    public EightQueensRandomBoardProvider() {
        this.mt = Utils.getMTInstance();
    }

    @Override
    @SuppressWarnings("empty-statement")
    public List<Double> getRandomPosition(AbstractOptimizer<? extends ISearchAgent> opt) {
        Double[] d = new Double[opt.getLowerBound().size()];
        List<Double> temp = Arrays.asList(d);
        Collections.fill(temp, 0.0);
        int pos;
        //put 8 queens in the board randomly
        for (int j = 0; j < 8; j++) {
            while (temp.get(pos = mt.nextInt(64)) == 1.0);
            temp.set(pos, 1.0);
        }
        //Eightqueens.PrintBoard(temp);
        return temp;
    }

}

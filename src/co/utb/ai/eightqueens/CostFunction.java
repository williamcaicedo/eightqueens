/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.utb.ai.eightqueens;

import co.mechanism.core.ICostFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author labsoftware13
 */
public class CostFunction implements ICostFunction {

    @Override
    public double evaluate(List<Double> inputs) throws Exception {

        //Eightqueens.PrintBoard(inputs);
        double[][] board = new double[8][8];
        List<Integer> queenx = new ArrayList<>();
        List<Integer> queeny = new ArrayList<>();
        int pos = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = inputs.get(pos);
                pos++;
            }
        }
        int attackingQueens = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1) {
                    queenx.add(i);
                    queeny.add(j);
                }
            }
        }

        for (int i = 0; i < 7; i++) {
            int qx1 = queenx.get(i);
            int qy1 = queeny.get(i);
            for (int j = i+1; j < 8; j++) {
                int qx2 = queenx.get(j);
                int qy2 = queeny.get(j);
                if (qx1 == qx2 || qy1 == qy2) {
                    attackingQueens++;
                } else {
                    int alpha = qx2 - qx1;
                    if (qy2 == qy1 + alpha) {
                        attackingQueens++;
                    } else if (qx1 + qy1 == qx2 + qy2) {
                        attackingQueens++;
                    }
                }
            }
        }
        return attackingQueens;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.utb.ai.eightqueens;

import co.mechanism.core.AbstractOptimizer;
import co.mechanism.core.DefaultSearchAgent;
import co.mechanism.core.IMutationProvider;
import co.mechanism.core.ISearchAgent;
import co.mechanism.utils.MersenneTwisterFast;
import co.mechanism.utils.Utils;

/**
 *
 * @author william
 */
public class EightQueensMutationProvider implements IMutationProvider{

    @Override
    @SuppressWarnings("empty-statement")
    public ISearchAgent mutate(ISearchAgent agent, 
            AbstractOptimizer<? extends DefaultSearchAgent> optimizer) {
        //Eightqueens.PrintBoard(agent.getPosition());
        MersenneTwisterFast rng = Utils.getMTInstance();
        //Add a queen randomly
        int queenPosition;
        while(agent.getPosition().get(queenPosition = rng.nextInt(64)) != 0.0);
        agent.getPosition().set(queenPosition, 1.0);
        
        
        //Remove a queen randomly
        int queenToRemove = rng.nextInt(8) + 1;
            int queensSeen = 0;
            for(int i = 0; i < 64; i++) {
                if (agent.getPosition().get(i) == 1.0) {
                    queensSeen++;
                    if (queensSeen == queenToRemove) {
                        agent.getPosition().set(i, 0.0);
                        break;
                    }
                }
            }
            
        //Eightqueens.PrintBoard(agent.getPosition());
        
        return agent;
            
        
    }
    
}

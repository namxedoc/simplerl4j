package org.rl.simple.agent;

import org.rl.simple.env.Action;
import org.rl.simple.env.DispersedEnviroment;
import org.rl.simple.env.Reward;
import org.rl.simple.env.State;

import java.util.Collections;

/**
 * qlearning 又叫 sarsamax  和  sarsa 就差一点点
 */
public class QLearningAgent extends SarsaAgent {

    public QLearningAgent(DispersedEnviroment enviroment) {
        super(enviroment);
    }

    public QLearningAgent(DispersedEnviroment enviroment, double epsilonStart, double epsilonDecay,
                          double epsilonMin, double learningRate, double gamma) {
        super(enviroment, epsilonStart, epsilonDecay, epsilonMin, learningRate, gamma);
    }


    @Override
    protected void updateQtable(State state, State nextState, Action action, Action nextAction, Reward reward, boolean done) {

        Double qValue = this.qtable.get(state).get(action.getCode());
        // 最重要的公式
        Double afterUpdateQValue = qValue + this.learningRate *
                (reward.getReward() + this.gamma * Collections.max(this.qtable.getOrDefault(nextState, initActionRowList())) - qValue);
        this.qtable.get(state).set(action.getCode(), afterUpdateQValue);
    }
}

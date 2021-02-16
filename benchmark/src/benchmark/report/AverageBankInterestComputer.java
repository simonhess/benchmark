/*
 * JMAB - Java Macroeconomic Agent Based Modeling Toolkit
 * Copyright (C) 2013 Alessandro Caiani and Antoine Godin
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */
package benchmark.report;


import jmab.agents.InterestRateSetterWithTargets;
import jmab.population.MacroPopulation;
import jmab.report.MacroVariableComputer;
import jmab.simulations.MacroSimulation;
import net.sourceforge.jabm.Population;
import net.sourceforge.jabm.agent.Agent;

/**
 * @author Simon Hess
 * This Computer computes the average interest rate of all banks for the given market. 
 */
public class AverageBankInterestComputer implements MacroVariableComputer {

	private int banksId;
	private int mktId;
	
	
	/* (non-Javadoc)
	 * @see jmab.report.MicroMultipleVariablesComputer#computeVariables(jmab.simulations.MacroSimulation)
	 */
	@Override
	public double computeVariable(MacroSimulation sim) {
		MacroPopulation macroPop = (MacroPopulation) sim.getPopulation();
		Population pop = macroPop.getPopulation(banksId);
		double interestSum = 0;
		for (Agent i:pop.getAgents()){
			InterestRateSetterWithTargets bank= (InterestRateSetterWithTargets) i;
			interestSum += bank.getInterestRate(mktId);
		}
		return interestSum/pop.getSize();
	}

	/**
	 * @return the banksId
	 */
	public int getBanksId() {
		return banksId;
	}
	
	/**
	 * @param banksId the banksId to set
	 */
	public void setBanksId(int banksId) {
		this.banksId = banksId;
	}

	/**
	 * @return the mktId
	 */
	public int getMktId() {
		return mktId;
	}

	/**
	 * @param mktId the mktId to set
	 */
	public void setMktId(int mktId) {
		this.mktId = mktId;
	}

	
}

package com.onurcansever.vacationplan;

public class Budget {
    private double currentBudget;
    private double totalCost;

    public Budget(double currentBudget, double totalCost) {
        this.currentBudget = currentBudget;
        this.totalCost = totalCost;
    }

    public double getCurrentBudget() {
        return currentBudget;
    }

    public double getTotalCost() {
        return totalCost;
    }

}

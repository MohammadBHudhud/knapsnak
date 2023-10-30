package master.ai.knapsnak.apis;

import master.ai.knapsnak.algorithm.Details;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class SolutionService {

    private int bestCost;
    private ArrayList<String> bestSolution;
    Set<ArrayList<String>> allSolutions = new HashSet<>();
    private boolean isFirstSolution = true;


    public  ArrayList<String> generateNewSolution(Details details){
        int currentWeight = details.getAllItemsWeight();
        ArrayList<String> newSolution = (ArrayList<String>) details.getAllItems().clone();
        int randomNumber;

        for(int itemsToRemove= getRandomNumber(newSolution.size()), i=0; i<itemsToRemove; i++){
            randomNumber = getRandomNumber(newSolution.size());
            int weightOfRemovedItem = details.getItemsInfo().get(newSolution.get(randomNumber)).getWeight();
            newSolution.remove(randomNumber);
            currentWeight-=weightOfRemovedItem;
        }

        while(currentWeight > details.getWeight() && newSolution.size()>0){
            randomNumber = getRandomNumber(newSolution.size());
            int weightOfRemovedItem = details.getItemsInfo().get(newSolution.get(randomNumber)).getWeight();
            newSolution.remove(randomNumber);
            currentWeight-=weightOfRemovedItem;
        }
        return newSolution;
    }

    public ArrayList<String> getBestSoultion(){
        return this.bestSolution;
    }

    public int getRandomNumber(int max){
        Random random = new Random();
        return random.nextInt(max);
    }

    public int computeSolutionCost (Details details, ArrayList<String> solution){
        int cost = 0;
        for(int i =0; i<solution.size();i++){
            String itemName = solution.get(i);
            cost+= details.getItemsInfo().get(itemName).getValue();
        }
        return cost;
    }

    public double computeAcceptence(int newCost, double temperature){
        return Math.exp((newCost-bestCost) / temperature);
    }


    public void addSolution(Details details, double currentTempreture){

        ArrayList<String> sol = generateNewSolution(details);
        int cost = computeSolutionCost(details, sol);

        if(isFirstSolution){
            bestSolution = (ArrayList<String>) sol.clone();
            bestCost = cost;
            isFirstSolution = false;
        }else{
            if(checkIfThisSolutionProducedBefore(sol)){
                return;
            }

            if(checkSolutionAccepted(cost, currentTempreture)){
                bestCost = cost;
                bestSolution  = (ArrayList<String>) sol.clone();
                allSolutions.add((ArrayList<String>) sol.clone());
            }
        }

    }

    public boolean checkSolutionAccepted(int cost, double currentTempreture){
        boolean solutionAccepted = false;
        if (isTheNewCostIsBetter(cost)){
            solutionAccepted = true;
        }else{
            if(computeAcceptence(cost,currentTempreture) > getRandomNumber(1)){
                solutionAccepted = true;
            }
        }

        return solutionAccepted;
    }

    public boolean checkIfThisSolutionProducedBefore(ArrayList<String> sol){
        if(sol.isEmpty()){
            return true;
        }
        return allSolutions.contains(sol);
    }

    public  boolean isTheNewCostIsBetter(int newCost){
        return newCost > bestCost;
    }
}

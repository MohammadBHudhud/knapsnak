package master.ai.knapsnak.algorithm;

import java.util.ArrayList;
import java.util.HashMap;


public class Details {

    HashMap<String, Item> itemsInfo = new HashMap<>();
    ArrayList<String> allItems = new ArrayList();
    int allItemsWeight = 0;
    int weight ;
    double T;
    int numberOfIteration;

    public HashMap<String, Item> getItemsInfo() {
        return itemsInfo;
    }

    public void setItemsInfo(HashMap<String, Item> itemsInfo) {
        this.itemsInfo = itemsInfo;
    }

    public ArrayList<String> getAllItems() {
        return allItems;
    }

    public void setAllItems(ArrayList<String> allItems) {
        this.allItems = allItems;
    }

    public int getAllItemsWeight() {
        return allItemsWeight;
    }

    public void setAllItemsWeight(int allItemsWeight) {
        this.allItemsWeight = allItemsWeight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getT() {
        return T;
    }

    public void setT(double t) {
        T = t;
    }

    public int getNumberOfIteration() {
        return numberOfIteration;
    }

    public void setNumberOfIteration(int numberOfIteration) {
        this.numberOfIteration = numberOfIteration;
    }
}

package master.ai.knapsnak.apis;

import master.ai.knapsnak.algorithm.Details;
import master.ai.knapsnak.algorithm.Item;
import master.ai.knapsnak.models.algOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlgService {

    private SolutionService solutionService;

    private Details details;

    public List<algOutput> getItemsResults(List<Item> input, int numberOfIteration, int weight){
        setInitailValues(numberOfIteration, weight);
        addItems(details, input);
        startIterations(details);
        return prepareResults(input);
    }

    public List<algOutput> prepareResults(List<Item> input){
        ArrayList<String> inBag = solutionService.getBestSoultion();
        List<algOutput> resutls = new ArrayList<>();

        input.stream().forEach(item->{
            algOutput output = new algOutput();
            output.setName(item.getName());
            output.setValue(item.getValue());
            output.setWeight(item.getWeight());
            output.setInbag(inBag.contains(item.getName()));

            resutls.add(output);
        });

        return resutls;

    }

    public void setInitailValues( int numberOfIteration, int weight){
        solutionService = new SolutionService();
        details = new Details();
        details.setWeight(weight);
        details.setAllItemsWeight(0);
        details.setT(1000);
        details.setNumberOfIteration(numberOfIteration);
    }


    public void startIterations(Details details){

        double tempreture = details.getT() ;
        solutionService.addSolution(details, tempreture);

        int iterNumber=0;

        while(iterNumber<details.getNumberOfIteration()){
            solutionService.addSolution(details, tempreture);
            tempreture = cooling(tempreture,iterNumber++);
        }
    }

    public  double cooling(double tempreture, double iterNumber){
        return details.getT()/(Math.log(iterNumber)+0.0000001);
    }



    public void addItems(Details details, List<Item> input){
        input.stream().forEach(item->{
            details.getItemsInfo().put(item.getName(), item);
            details.getAllItems().add(item.getName());
            details.setAllItemsWeight(details.getAllItemsWeight()+ item.getWeight());
        });
    }
}

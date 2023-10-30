package master.ai.knapsnak.apis;

import master.ai.knapsnak.algorithm.Item;
import master.ai.knapsnak.models.algOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class controller {

    @Autowired
    private AlgService algService;

    @GetMapping("/run/{iter}/{weight}")
    public List<algOutput> run(@RequestBody List<Item> input, @PathVariable int iter, @PathVariable int weight){
        return algService.getItemsResults(input, iter, weight);
    }
}

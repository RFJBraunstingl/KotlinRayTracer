package dev.rfj.steps;

import dev.rfj.domain.TupleStore;
import io.cucumber.java.en.Given;

public class TupleSteps {

    private TupleStore tupleStore;

    @Given("{string} <- tuple\\({double}, {double}, {double}, {double})")
    public void createTuple(String name, double x, double y, double z, double w) {
        //tupleStore.put(name, new Tuple(x, y, z, w));
    }
}

package tdd.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzzExecutor {
    private Integer _count;
    private List<FizzBuzzValue> _results;
    public List<FizzBuzzValue> getResults() {
        return _results;
    }

    public FizzBuzzExecutor(Integer count) {
        _count = count;
        _results = new ArrayList<>();
    }

    public void executeByCount() {
        for (int i = 0; i <= _count; ++i) {
            FizzBuzzValue value = FizzBuzzValue.makeFizzBuzzValue(i);
            _results.add(value);
        }
    }

    public static FizzBuzzValue reduce(Expression source) {
        return source.reduce();
    }
}

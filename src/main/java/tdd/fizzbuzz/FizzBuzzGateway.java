package tdd.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzzGateway {
    private List<FizzBuzzValue> _results;
    private Expression _accumulated;
    private List<Expression> _sources;

    public FizzBuzzGateway(Integer count) {
        Integer _count = count;
        _results = new ArrayList<>();
        _sources = new ArrayList<>();

        for (int i = 0; i <= _count; ++i) {
            FizzBuzzValue value = FizzBuzzValue.makeFizzBuzzValue(i);
            _results.add(value);
        }
    }

    public List<FizzBuzzValue> getResults() {
        return _results;
    }

    public void setSources(Expression source) {
        if (_accumulated == null) {
            _accumulated = source;
        } else {
            source = new FizzBuzzValueAccumulate(_accumulated, source);
            _sources.add(source);
            _accumulated = source;
        }
    }

    public FizzBuzzValue reduce() {
        FizzBuzzValue value = null;

        if (_sources.isEmpty())
            return _accumulated.reduce();

        for (Expression source : _sources)
            value = source.reduce();

        return value;
    }
}

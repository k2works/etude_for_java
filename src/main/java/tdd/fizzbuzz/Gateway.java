package tdd.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

public class Gateway {
    private List<Value> _results;
    private Expression _accumulated;
    private List<Expression> _sources;

    public Gateway(Integer count) {
        Integer _count = count;
        _results = new ArrayList<>();
        _sources = new ArrayList<>();

        for (int i = 0; i <= _count; ++i) {
            Value value = Value.makeFizzBuzzValue(i);
            _results.add(value);
        }
    }

    public List<Value> getResults() {
        return _results;
    }

    public void setSources(Expression source) {
        if (_accumulated == null) {
            _accumulated = source;
        } else {
            source = new Accumulate(_accumulated, source);
            _sources.add(source);
            _accumulated = source;
        }
    }

    public Value reduce() {
        Value value = null;

        if (_sources.isEmpty())
            return _accumulated.reduce();

        for (Expression source : _sources)
            value = source.reduce();

        return value;
    }
}

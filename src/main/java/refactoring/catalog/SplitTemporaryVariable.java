package refactoring.catalog;

public class SplitTemporaryVariable {
    private double _primaryForce;
    private double _secondaryForce;
    private double _mass;
    private int _delay;

    SplitTemporaryVariable(double primaryForce, double secondaryForce, double mass, int delay) {
        _primaryForce = primaryForce;
        _secondaryForce = secondaryForce;
        _mass = mass;
        _delay = delay;
    }

    double getDistanceTravelled(int time) {
        double result;
        double acc = _primaryForce / _mass;
        int primaryTime = Math.min(time, _delay);
        result = 0.5 * acc * primaryTime * primaryTime;
        int secondaryTime = time - _delay;
        if (secondaryTime > 0) {
            double primaryVel = acc * _delay;
            acc = (_primaryForce + _secondaryForce) / _mass;
            result += primaryVel * secondaryTime + 0.5 * acc * secondaryTime * secondaryTime;
        }
        return result;
    }
}
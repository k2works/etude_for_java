package refactoring.videorental;

public class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String _title;
    private Price _price;

    public Movie(String title, int priceCode) throws IllegalAccessException {
        _title = title;
        setPriceCode(priceCode);
    }

    public int getPriceCode() {
        return _price.getPriceCode();
    }

    public void setPriceCode(int arg) throws IllegalAccessException {
        switch (arg) {
            case REGULAR:
                _price = new RegularPrice();
                break;
            case CHILDRENS:
                _price = new ChildrensPrice();
                break;
            case NEW_RELEASE:
                _price = new NewReleasePrice();
                break;
            default:
                throw new IllegalAccessException("不正な料金コード");
        }
    }

    public String getTitle() {
        return _title;
    }
    double getCharge(int daysRented) {
        return _price.getCharge(daysRented);
    }
    int getFrequentRenterPoints(int daysRented) {
        if((getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1)
            return 2;
        else
            return 1;
    }
}

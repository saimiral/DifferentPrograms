package prototype;

public class Filters {
    private String make, model;
    private Integer year, cost, days;

    public Filters(String make, String model, Integer year, Integer cost, Integer days) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.cost = cost;
        this.days = days;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
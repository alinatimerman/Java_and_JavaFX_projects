package Model;

public class Activity implements Identifiable<Integer>{
    private Integer Id;
    private String name;
    private int min_age;
    private int max_age;
    private String date;

    public Activity(Integer id, String name, int min_age, int max_age, String date) {
        this.Id = id;
        this.name = name;
        this.min_age = min_age;
        this.max_age = max_age;
        this.date = date;
    }

    public Activity(String name, int min_age, int max_age, String date) {
        this.name = name;
        this.min_age = min_age;
        this.max_age = max_age;
        this.date = date;
    }
    public Activity(Integer Id){
        this.name = "";
        this.min_age = 0;
        this.max_age = 0;
        this.date = "";
    }



    @Override
    public Integer getId() {
        return Id;
    }

    @Override
    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMin_age() {
        return min_age;
    }

    public void setMin_age(int min_age) {
        this.min_age = min_age;
    }

    public int getMax_age() {
        return max_age;
    }

    public void setMax_age(int max_age) {
        this.max_age = max_age;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuilder oString = new StringBuilder();
        oString.append(Id).append("; ").append(name).append("; ").append(min_age).append("; ").append(max_age).append("; ").append(date).append("; ");
        return oString.toString();
    }


}

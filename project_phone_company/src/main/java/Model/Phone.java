package Model;

public class Phone implements Identifiable<Integer> {
    private Integer Id;
    private String name;
    private int year;
    private float price;

//------------------------------------CONSTRUCTORS-----------------------------------//

    public Phone(Integer id, String name, int year, float price) {
        Id = id;
        this.name = name;
        this.year = year;
        this.price = price;
    }
    public Phone( String name, int year, float price) {
        this.name = name;
        this.year = year;
        this.price = price;
    }
    public Phone(Integer id){
        this.Id=id;
        this.name="";
        this.year=0;
        this.price=0;

    }

    //-----------------------------getters and setters--------------------------//
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    //-------------------------toString------------------------

    @Override
    public String toString() {
        final StringBuilder pString = new StringBuilder();
        pString.append(Id).append("; ").append(name).append("; ").append(year).append("; ").append(price).append("; ");
        return pString.toString();
    }
}

package Model;

public class Order implements Identifiable<Integer>{
   private Integer Id;
   private int phone_id;
   private String name;
   private String date;


    //----------------------------------constructors--------------------------------------//

    public Order(Integer id, int phone_id, String name, String date) {
        Id = id;
        this.phone_id = phone_id;
        this.name = name;
        this.date = date;
    }
    public Order( int phone_id, String name, String date) {
        //Id = id;
        this.phone_id = phone_id;
        this.name = name;
        this.date = date;
    }
    public Order(int id){
        this.phone_id=0;
        this.name="";
        this.date="";
    }

    //---------------------------------getters and setters-------------------------//
    @Override
    public Integer getId() {
        return Id;
    }

    @Override
    public void setId(Integer id) {
        Id = id;
    }

    public int getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //-----------------------------------tostring----------------------------//
    @Override
    public String toString() {
        final StringBuilder oString = new StringBuilder();
        oString.append(Id).append("; ").append(phone_id).append("; ").append(name).append("; ").append(date).append("; ");
        return oString.toString();
    }
}

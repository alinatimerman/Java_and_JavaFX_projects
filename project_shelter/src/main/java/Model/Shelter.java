package Model;

public class Shelter implements Identifiable<Integer>{
    private Integer Id;
    private String name;
    private String address;
    private int volunteers;
    private int animals;
    private int employees;

    public Shelter(Integer id, String name, String address, int volunteers, int animals, int employees) {
        this.Id = id;
        this.name = name;
        this.address = address;
        this.volunteers = volunteers;
        this.animals = animals;
        this.employees=employees;
    }

    public Shelter(String name, String address, int volunteers, int animals, int employees) {
        this.name = name;
        this.address = address;
        this.volunteers = volunteers;
        this.animals = animals;
        this.employees = employees;
    }
    public Shelter(Integer Id){
        this.name = "";
        this.address = "";
        this.volunteers = 0;
        this.animals = 0;
        this.employees = 0;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(int volunteers) {
        this.volunteers = volunteers;
    }

    public int getAnimals() {
        return animals;
    }

    public void setAnimals(int animals) {
        this.animals = animals;
    }
    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }



    @Override
    public String toString() {
        final StringBuilder oString = new StringBuilder();
        oString.append(Id).append("; ").append(name).append("; ").append(address).append("; ").append(volunteers).append("; ").append(animals).append("; ").append(employees).append("; ");
        return oString.toString();
    }



}

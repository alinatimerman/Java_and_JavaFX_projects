package Model;

public class Animal implements Identifiable<Integer>{
    private Integer Id;
    private String name;
    private int shelter_id;
    private int age;
    private String species;

    public Animal(Integer Id, String name, int shelter_id, int age, String species) {
        this.Id = Id;
        this.name = name;
        this.shelter_id = shelter_id;
        this.age = age;
        this.species=species;
    }

    public Animal(String name, int shelter_id, int age, String species) {
        this.name = name;
        this.shelter_id = shelter_id;
        this.age = age;
        this.species=species;
    }

    public Animal(Integer Id) {
        this.name = "";
        this.shelter_id = 0;
        this.age = 0;
        this.species="";
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

    public int getShelter_id() {
        return shelter_id;
    }

    public void setShelter_id(int shelter_id) {
        this.shelter_id = shelter_id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
    public int getAge(){
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuilder oString = new StringBuilder();
        oString.append(Id).append("; ").append(name).append("; ").append(shelter_id).append("; ").append(age).append("; ").append(species).append("; ");
        return oString.toString();
    }

}

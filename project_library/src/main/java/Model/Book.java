package Model;

public class Book implements Identifiable<Integer>{
    private Integer Id;
    private String name;
    private String author;
    private int price;
    private int rating;
    private Integer year;

    public Book(Integer id, String name, String author, int price, int rating, Integer year) {
        this.Id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.rating = rating;
        this.year = year;
    }

    public Book(String name, String author, int price, int rating, Integer year) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.rating = rating;
        this.year = year;
    }
    public Book(Integer Id){
        this.name = "";
        this.author = "";
        this.price = 0;
        this.rating = 0;
        this.year = 0;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        final StringBuilder oString = new StringBuilder();
        oString.append(Id).append("; ").append(name).append("; ").append(author).append("; ").append(price).append("; ").append(rating).append("; ").append(year).append("; ");
        return oString.toString();
    }


}

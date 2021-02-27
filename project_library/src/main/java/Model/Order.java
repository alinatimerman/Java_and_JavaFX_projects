package Model;

public class Order implements Identifiable<Integer>{
    private Integer Id;
    private int book_id;
    private String customer;
    private String date;
    private String status;

    public Order(Integer Id, int book_id,String customer, String date, String status) {
        this.Id = Id;
        this.book_id=book_id;
        this.customer=customer;
        this.date=date;
        this.status=status;
    }
    public Order(int book_id,String customer, String date, String status) {
        this.book_id=book_id;
        this.customer=customer;
        this.date=date;
        this.status=status;
    }


    public Order(Integer Id) {
        this.book_id = 0;
        this.customer = "";
        this.date = "";
        this.status="";
    }


    @Override
    public Integer getId() {
        return Id;
    }

    @Override
    public void setId(Integer id) {
        Id = id;
    }
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        final StringBuilder oString = new StringBuilder();
        oString.append(Id).append("; ").append(book_id).append("; ").append(customer).append("; ").append(date).append("; ").append(status).append("; ");
        return oString.toString();
    }


}

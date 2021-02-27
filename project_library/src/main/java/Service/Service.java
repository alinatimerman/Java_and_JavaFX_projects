package Service;

import Model.Book;
import Model.Order;
import Repository.*;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private BookRepository bookRepository;
    private OrderRepository orderRepository;

    public Service(BookRepository bookRepository, OrderRepository orderRepository){
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
    }

//---------------------------------Books-----------------------------------------------------------//
public List<Book> getBookList(){
    return new ArrayList<>(bookRepository.getAll());
}

//-----------------------------------CRUD----------------------------------------------------------//

    public int addBook(String name,String author,int price, int rating, int year) throws ServiceException{
        try{
            //verifies if the rating is under 10
            Book p = new Book(name,author,price,rating,year);
            if (p.getRating()>10)
                throw new ServiceException("The rating must be under 10");
            else
                bookRepository.add(p);
            return p.getId();
        }catch (RepositoryException exception){
            throw new ServiceException("Error adding book: " + exception);
        }

    }

    public int getBookIdGenerator() {
        if (bookRepository instanceof BookInFileRepository) {
            return ((BookInFileRepository) bookRepository).getIdGenerator();
        }
        else return 0;
    }

    //---------------------------------Order-----------------------------------------------------------//
    public List<Order> getOrderList(){
        return new ArrayList<>(orderRepository.getAll());
    }

//-----------------------------------CRUD----------------------------------------------------------//

    public int addOrder(int book_id,String customer, String date, String status) throws ServiceException{
        try{
            /*
            Verifies if the book exists
             */
            if (bookRepository.findById(book_id) == null)
                throw new ServiceException("This book does not exist");

            /*
            Verifies if the age of the child is ok for this enrollment

            WG winterGameObj = bookRepository.findById(wg_id);
            if (winterGameObj.getMax_age() < age)
                throw new ServiceException("The child is too old");
            if(winterGameObj.getMin_age() > age)
                throw new ServiceException("The child is too young");
*/

            Order o = new Order(book_id,customer,date,status);
            orderRepository.add(o);
            return o.getId();
        }catch (RepositoryException exception){
            throw new ServiceException("Error adding order: " + exception);
        }

    }

    public int getOrderIdGenerator() {
        if (orderRepository instanceof OrderInFileRepository) {
            return ((OrderInFileRepository) orderRepository).getIdGenerator();
        }
        else return 0;
    }


}

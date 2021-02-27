package Service;

import Model.Order;
import Model.Phone;
import Repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

    public class Service {
        private PhoneRepository phoneRepository;
        private OrderRepository orderRepository;

        public Service(PhoneRepository phoneRepository, OrderRepository orderRepository){
            this.phoneRepository = phoneRepository;
            this.orderRepository = orderRepository;
        }

//---------------------------------Phone-----------------------------------------------------------//
    public List<Phone> getPhoneList(){
        return new ArrayList<>(phoneRepository.getAll());
    }

//-----------------------------------CRUD----------------------------------------------------------//

        public int addPhone(String name, int year, float price) throws ServiceException{
            try{
                Phone p = new Phone(name, year,price);
                phoneRepository.add(p);
                return p.getId();
            }catch (RepositoryException exception){
                throw new ServiceException("Error adding phone: " + exception);
            }

        }

        public int getPhoneIdGenerator() {
            if (phoneRepository instanceof PhoneInFileRepository) {
                return ((PhoneInFileRepository) phoneRepository).getIdGenerator();
            }
            else return 0;
        }

        //---------------------------------Order-----------------------------------------------------------//
        public List<Order> getOrderList(){
            return new ArrayList<>(orderRepository.getAll());
        }

//-----------------------------------CRUD----------------------------------------------------------//

        public int addOrder(int phone_id, String name, String date) throws ServiceException{
            try{
                Order o = new Order(phone_id,name,date);
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

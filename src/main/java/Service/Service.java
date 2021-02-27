package Service;

import Model.Registration;
import Model.Activity;
import Repository.*;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private ActivityRepository activityRepository;
    private RegistrationRepository registrationRepository;

    public Service(ActivityRepository activityRepository, RegistrationRepository registrationRepository){
        this.activityRepository = activityRepository;
        this.registrationRepository = registrationRepository;
    }

//---------------------------------Activity--------------------------------------------------------------------//
public List<Activity> getActivityList(){
    return new ArrayList<>(activityRepository.getAll());
}

//-----------------------------------CRUD----------------------------------------------------------//

    public int addActivity(String name,int min_age, int max_age, String date) throws ServiceException{
        try{
            Activity p = new Activity(name,min_age,max_age,date);
            activityRepository.add(p);
            return p.getId();
        }catch (RepositoryException exception){
            throw new ServiceException("Error adding activity: " + exception);
        }

    }

    public int getActivityIdGenerator() {
        if (activityRepository instanceof ActivityInFileRepository) {
            return ((ActivityInFileRepository) activityRepository).getIdGenerator();
        }
        else return 0;
    }

    //---------------------------------Registration-----------------------------------------------------------//
    public List<Registration> getRegistrationList(){
        return new ArrayList<>(registrationRepository.getAll());
    }

//-----------------------------------CRUD----------------------------------------------------------//

    public int addRegistration(String person, int age, String phone, int activity_id) throws ServiceException{
        try{
            /*
            Verifies if the activity exists
             */
            if (activityRepository.findById(activity_id) == null)
                throw new ServiceException("This activity does not exist");

            /*
            Verifies if the age of the person is ok for this activity
             */
            Activity activityObj = activityRepository.findById(activity_id);
            if (activityObj.getMax_age() < age)
                throw new ServiceException("The person is too old");
            if(activityObj.getMin_age() > age)
                throw new ServiceException("The person is too young");


            Registration o = new Registration(person,age,phone,activity_id);
            registrationRepository.add(o);
            return o.getId();
        }catch (RepositoryException exception){
            throw new ServiceException("Error adding registration: " + exception);
        }

    }

    public int getRegistrationIdGenerator() {
        if (registrationRepository instanceof RegistrationInFileRepository) {
            return ((RegistrationInFileRepository) registrationRepository).getIdGenerator();
        }
        else return 0;
    }


}

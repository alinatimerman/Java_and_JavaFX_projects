package Service;


import Model.Animal;
import Model.Shelter;
import Repository.*;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private ShelterRepository shelterRepository;
    private AnimalRepository animalRepository;

    public Service(ShelterRepository shelterRepository, AnimalRepository animalRepository){
        this.shelterRepository = shelterRepository;
        this.animalRepository = animalRepository;
    }

//---------------------------------shelter-----------------------------------------------------------//
public List<Shelter> getShelterList(){
    return new ArrayList<>(shelterRepository.getAll());
}

//-----------------------------------CRUD----------------------------------------------------------//

    public int addShelter(String name, String address, int volunteers, int animals, int employees) throws ServiceException{
        try{
            Shelter p = new Shelter(name,address,volunteers,animals,employees);
            shelterRepository.add(p);
            return p.getId();
        }catch (RepositoryException exception){
            throw new ServiceException("Error adding shelter: " + exception);
        }

    }

    public int getShelterIdGenerator() {
        if (shelterRepository instanceof ShelterInFileRepository) {
            return ((ShelterInFileRepository) shelterRepository).getIdGenerator();
        }
        else return 0;
    }

    //---------------------------------Animal-----------------------------------------------------------//
    public List<Animal> getAnimalList(){
        return new ArrayList<>(animalRepository.getAll());
    }

//-----------------------------------CRUD----------------------------------------------------------//

    public int addAnimal(String name, int shelter_id, int age, String species) throws ServiceException{
        try{
            /*
            Verifies if the shelter exists
             */
            if (shelterRepository.findById(shelter_id) == null)
                throw new ServiceException("This shelter does not exist");

            /*
            Verifies if the age of the child is ok for this enrollment

            WG winterGameObj = shelterRepository.findById(species);
            if (winterGameObj.getMax_age() < shelter_id)
                throw new ServiceException("The child is too old");
            if(winterGameObj.getMin_age() > shelter_id)
                throw new ServiceException("The child is too young");*/


            Animal o = new Animal(name,shelter_id,age,species);
            animalRepository.add(o);
            return o.getId();
        }catch (RepositoryException exception){
            throw new ServiceException("Error adding animal: " + exception);
        }

    }

    public int getAnimalIdGenerator() {
        if (animalRepository instanceof AnimalInFileRepository) {
            return ((AnimalInFileRepository) animalRepository).getIdGenerator();
        }
        else return 0;
    }


}

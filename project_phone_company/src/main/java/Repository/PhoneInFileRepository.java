package Repository;

import Model.Phone;

import java.io.*;


public class PhoneInFileRepository extends PhoneInMemoRepository{
    private String file;
    private static int idGenerator = 0;

    public PhoneInFileRepository(String file){
        this.file = file;
        retrievePhone();
    }

    private void retrievePhone() {
        try(BufferedReader buffer = new BufferedReader(new FileReader(file))){
            String line = buffer.readLine();
            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid Value for idGenerator");
            }
            while((line = buffer.readLine()) != null){
                String[] attributes = line.split("; ");
                if (attributes.length != 4){
                    System.out.println(line);
                    System.out.println(attributes.length);
                    System.err.println("Invalid line ..." + line);
                    continue;
                }
                try{
                    int id = Integer.parseInt(attributes[0]);
                    int year = Integer.parseInt(attributes[2]);
                    float price = Integer.parseInt(attributes[3]);
                    Phone p = new Phone(id, attributes[1], year, price);
                    super.add(p);
                }catch(NumberFormatException ex){
                    System.err.println("Error converting number");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }catch(IOException exception){
            throw new RepositoryException("Reading error " + exception);
        }

    }

    private void storePhone() {
        try(BufferedWriter buffer = new BufferedWriter(new FileWriter(file))){
            buffer.write("" + idGenerator);
            buffer.newLine();
            for(Phone p : findAll()){
                buffer.write(p.toString());
                buffer.newLine();
            }
        } catch (IOException exception) {
            throw new RepositoryException("Writing error " + exception);
        }
    }

    @Override
    public Phone add(Phone p){
        p.setId(getNextId());
        super.add(p);
        storePhone();
        return p;
    }

    @Override
    public void delete(Phone p){
        super.delete(p);
        storePhone();
    }

    @Override
    public void update(Integer id, Phone p){
        super.update(id, p);
        storePhone();
    }

    public int getNextId(){
        return idGenerator++;
    }

    public int getIdGenerator(){
        return idGenerator;
    }
}



package Repository;

import Model.Book;

import java.io.*;


public class BookInFileRepository extends BookInMemoRepository{
    private String file;
    private static int idGenerator = 0;

    public BookInFileRepository(String file){
        this.file = file;
        retrieveBook();
    }

    private void retrieveBook() {
        try(BufferedReader buffer = new BufferedReader(new FileReader(file))){
            String line = buffer.readLine();
            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid Value for idGenerator");
            }
            while((line = buffer.readLine()) != null){
                String[] attributes =
                        line.split("; ");
                if (attributes.length != 6){
                    System.out.println(line);
                    System.out.println(attributes.length);
                    System.err.println("Invalid line ..." + line);
                    continue;
                }
                try{
                    int id = Integer.parseInt(attributes[0]);
                    int price = Integer.parseInt(attributes[3]);
                    int rating = Integer.parseInt(attributes[4]);
                    int year=Integer.parseInt(attributes[5]);
                    Book p = new Book(id,attributes[1],attributes[2],price,rating,year);
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

    private void storeBook() {
        try(BufferedWriter buffer = new BufferedWriter(new FileWriter(file))){
            buffer.write("" + idGenerator);
            buffer.newLine();
            for(Book p : findAll()){
                buffer.write(p.toString());
                buffer.newLine();
            }
        } catch (IOException exception) {
            throw new RepositoryException("Writing error " + exception);
        }
    }

    @Override
    public Book add(Book p){
        p.setId(getNextId());
        super.add(p);
        storeBook();
        return p;
    }

    @Override
    public void delete(Book p){
        super.delete(p);
        storeBook();
    }

    @Override
    public void update(Integer id, Book p){
        super.update(id, p);
        storeBook();
    }

    public int getNextId(){
        return idGenerator++;
    }

    public int getIdGenerator(){
        return idGenerator;
    }
}



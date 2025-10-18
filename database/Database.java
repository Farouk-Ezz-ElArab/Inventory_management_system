package database;
import product.Product;

import java.io.*;
import java.util.*;

public abstract class Database<T extends Record> { // di ya3ny any class hy extend el class di hy specify an type for T (EmployeeUser or Product or CustomerProduct) 3ashan a3ml database 3la ay 7aga
    protected ArrayList<T> records = new ArrayList<>();
    String fileName;

    public Database(String fileName) {
        this.fileName = fileName;
    }

    public abstract T createRecordFrom(String line);

   // public abstract String getLineRepresentation(T record);// 34an a7ot el records as a string in format of each data

    public ArrayList<T> returnAllRecords() {
        return records;
    }
    //public abstract String getSearchKey(T record); // 3ashan a3rf a3ml search 3la ay 7aga f ay database

    public void readFromFile() {
        records.clear(); // 34an law nadet el function aktar mn marra ma y3mlsh duplicate
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                records.add(createRecordFrom(line));
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + fileName);
        }
    }

    public void insertRecord(T record) {
        records.add(record);
    }
    public void deleteRecord(String key) {
        records.removeIf(record -> record.getSearchKey().equals(key));// dy badal ma a3ml loop 3la kol el records w a3ml compare 3la kol wa7da w a delete elly el key bta3ha yb2a equal lel key elly ana 3ayzo
    }

    public boolean contains(String key){
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getSearchKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    public T getRecord(String key){
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getSearchKey().equals(key)){
                return records.get(i);
            }
        }
        return null;
    }
    public void saveToFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for(int i = 0; i < records.size(); i++){
                bufferedWriter.write(records.get(i).lineRepresentation());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + fileName);
        }
    }
    public boolean isIdUsed(String id) {
        for (T p : records) {
            if (p.getSearchKey().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}


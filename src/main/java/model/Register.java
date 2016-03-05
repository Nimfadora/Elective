package model;

import java.util.LinkedList;
import java.util.List;

public class Register {
    private String courseName;
    private List<Record> records;

    public Register() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void addRecord(Record record){
        if(records == null)
            records = new LinkedList<>();
        records.add(record);
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

}

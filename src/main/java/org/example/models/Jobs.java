package org.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Jobs {
    private int job_id;
    private String job_title;
    private double min_salary;
    private double max_salary;

    public Jobs(int job_id, String job_title, double min_salary, double max_salary){
        this.job_id=job_id;
        this.job_title=job_title;
        this.max_salary=max_salary;
        this.min_salary=min_salary;
    }


    public Jobs(ResultSet rs) throws SQLException {
        job_id=rs.getInt(1);
        job_title=rs.getString(2);
        max_salary=rs.getDouble(3);
        min_salary=rs.getDouble(4);
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "job_id=" + job_id +
                ", job_title='" + job_title + '\'' +
                ", min_salary=" + min_salary +
                ", max_salary=" + max_salary +
                '}';
    }



    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public double getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(double min_salary) {
        this.min_salary = min_salary;
    }

    public double getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(double max_salary) {
        this.max_salary = max_salary;
    }
}

package org.example.dto;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@XmlRootElement
public class JobsDto {

    private int jobId;
private String job_title;
    private double minSalary;
    private double maxSalary;

    private ArrayList<LinkDto> links = new ArrayList<>();

    public JobsDto() {
    }


    public JobsDto(int jobId, double minSalary, double maxSalary,String job_title) {
        this.jobId = jobId;
       this.job_title=job_title;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public JobsDto(ResultSet rs) throws SQLException {
        this.jobId = rs.getInt("job_id");

        this.minSalary = rs.getDouble("min_salary");
        this.maxSalary = rs.getDouble("max_salary");
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }





    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public ArrayList<LinkDto> getLinks() {
        return links;
    }

    public void addLink(String url, String rel) {
        LinkDto link = new LinkDto();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

    @Override
    public String toString() {
        return "JobsDto{" +
                "maxSalary=" + maxSalary +
                ", minSalary=" + minSalary +
                ", job_title='" + job_title + '\'' +
                ", jobId=" + jobId +
                '}';
    }
}

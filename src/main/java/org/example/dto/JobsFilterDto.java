package org.example.dto;

import jakarta.ws.rs.QueryParam;

public class JobsFilterDto {

   private @QueryParam("min_salary") Double min_salary;
    private @QueryParam("limit") Integer limit;
    private  @QueryParam("offset") int offset;

    public Double getMin_salary() {
        return min_salary != null ? min_salary : 0.0; // Return a default value, such as 0.0, when min_salary is null
    }


    public void setMin_salary(Double min_salary) {
        this.min_salary = min_salary;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}

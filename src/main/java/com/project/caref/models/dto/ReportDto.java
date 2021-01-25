package com.project.caref.models.dto;

import java.util.List;

public class ReportDto {

    private List<Long> user;

    public String getReportContenue() {
        return reportContenue;
    }

    public void setReportContenue(String reportContenue) {
        this.reportContenue = reportContenue;
    }

    private String reportContenue;

    public ReportDto(String reportContenue) {
        this.reportContenue = reportContenue;
    }

    public ReportDto() {}

    public List<Long> getUser() {
        return user;
    }

}

package com.project.caref.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Report extends AbstractIforce5Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getReportContenue() {
        return reportContenue;
    }

    public void setReportContenue(String reportContenue) {
        this.reportContenue = reportContenue;
    }

    @Size(max = 255)
    private String reportContenue;

    @DateTimeFormat
    private Date date;

    @OneToMany(
            mappedBy = "report",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<UserReport> Userreports = new HashSet<>();

    public Report() {
    }

    public Report(String reportContenue, Date date) {
        this.reportContenue = reportContenue;
        this.date = date;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

}

package com.project.caref.models;

import javax.persistence.*;

@Entity
@Table(name="user_report")
public class UserReport {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id",
            referencedColumnName = "id")
    private Report report;

    public UserReport(Long id, User user, Report report) {
        this.id = id;
        this.user = user;
        this.report = report;
    }

    public UserReport(){}


}

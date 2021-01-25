package com.project.caref.repository;

import com.project.caref.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    public Report findById(long id);

    public List<Report> findAll();
}

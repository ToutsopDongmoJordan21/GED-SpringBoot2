package com.project.caref.service;

import com.project.caref.models.Report;
import com.project.caref.models.User;
import com.project.caref.models.UserReport;
import com.project.caref.models.dto.ReportDto;
import com.project.caref.repository.ReportRepository;
import com.project.caref.repository.UserReportRepository;
import com.project.caref.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReportDetailsService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserReportRepository userReportRepository;

    public Report save(ReportDto report) {
        Report newReport = new Report();
        newReport.setReportContenue(report.getReportContenue());
        newReport.setDate(new Date());

        newReport = reportRepository.save(newReport);

        if(!report.getUser().isEmpty()) {
            Report finalNewReport = newReport;
            report.getUser().forEach(usrId -> {
                User user = userRepository.getOne(usrId);
                UserReport userReport = new UserReport();
                userReport.setUser(user);
                userReport.setReport(finalNewReport);
                userReportRepository.save(userReport);

            });
        }
        return reportRepository.getOne(newReport.getId());
    }
}

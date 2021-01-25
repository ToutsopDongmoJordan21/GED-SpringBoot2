package com.project.caref.controllers;

import com.project.caref.exeption.ResourceNotFoundException;
import com.project.caref.models.Report;
import com.project.caref.models.dto.ReportDto;
import com.project.caref.repository.ReportRepository;
import com.project.caref.service.ReportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ReportController {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportDetailsService reportDetailsService;

    @GetMapping("/report")
    public List<Report> getAllReport() {
        return reportRepository.findAll();
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable(value = "id") Long reportId)
        throws ResourceNotFoundException {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Garage not found for this id ::" +reportId));
        return ResponseEntity.ok().body(report);
    }

    @PostMapping("/report")
    public ResponseEntity<?> saveReport(@Valid @RequestBody ReportDto report) throws Exception {
        return ResponseEntity.ok(reportDetailsService.save(report));
    }
    
    @DeleteMapping("/report/{id}")
    public Map<String, Boolean> deleteReport(@PathVariable (value = "id") Long reportId)
        throws  ResourceNotFoundException {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found for this id" + reportId));

        reportRepository.delete(report);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Report was successful delete", Boolean.TRUE);
        return response;
    }

    @PutMapping("/report/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable(value = "id") Long reportId,
                                               @RequestBody Report reportDetails) throws ResourceNotFoundException {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("garage not found for this id " +reportId));

        report.setReportContenue(reportDetails.getReportContenue());
        report.setDate(new Date());
        final Report updateReport = reportRepository.save(report);
        return ResponseEntity.ok(updateReport);
    }


}


package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Report;
import com.example.studentmanagement.repository.ReportRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    public List<Report> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return reportRepository.findAll();
        }
        return reportRepository.search(keyword);
    }

    public Optional<Report> findById(Long id) {
        return reportRepository.findById(id);
    }

    public Report save(Report report) {
        return reportRepository.save(report);
    }

    public void deleteById(Long id) {
        reportRepository.deleteById(id);
    }

    public long count() {
        return reportRepository.count();
    }
}

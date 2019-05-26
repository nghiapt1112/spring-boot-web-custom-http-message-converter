package com.example.demo.custom.converter;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReportController {
    private List<Report> reports = new ArrayList<>();

    @RequestMapping(value = "/reports",
            method = RequestMethod.POST,
            consumes = "text/report")
    public String handleRequest(@RequestBody Report report) {
        report.setId(reports.size() + 1);
        reports.add(report);
        return "report saved: " + report;
    }

    @GetMapping(value = "/reports/{id}", produces = "text/report")
    public Report reportById(@PathVariable("id") int reportId) {
//        if (reportId > reports.size()) {
//            throw new RuntimeException("No found for the id :" + reportId);
//        }
//        return reports.get(reportId - 1);

        Report report = new Report();
        report.setContent("report content ");
        report.setId(12);
        report.setReportName("report name");

        return report;
    }
}
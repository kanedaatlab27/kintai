package com.example.kintai;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
public class AttendanceController {
    private final AttendanceRepository repo;

    public AttendanceController(AttendanceRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("records", repo.findAllByOrderByClockInDesc());
        return "index";
    }

    @PostMapping("/clock-in")
    public String clockIn(@RequestParam String employeeCode) {
        String code = employeeCode.trim();
        // 空欄や、退勤してない状態での二重出勤は無視
        if (!code.isEmpty()
                && repo.findFirstByEmployeeCodeAndClockOutIsNullOrderByClockInDesc(code).isEmpty()) {
            Attendance a = new Attendance();
            a.setEmployeeCode(code);
            a.setClockIn(LocalDateTime.now());
            repo.save(a);
        }
        return "redirect:/";
    }

    @PostMapping("/clock-out")
    public String clockOut(@RequestParam String employeeCode) {
        repo.findFirstByEmployeeCodeAndClockOutIsNullOrderByClockInDesc(employeeCode.trim())
            .ifPresent(a -> {
                a.setClockOut(LocalDateTime.now());
                repo.save(a);
            });
        return "redirect:/";
    }
}

package com.example.kintai;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class ShiftController {
    private final ShiftRepository repo;

    public ShiftController(ShiftRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/shifts")
    public String list(Model model) {
        model.addAttribute("shifts", repo.findAllByOrderByShiftDateAscStartTimeAsc());
        return "shifts";
    }

    @PostMapping("/shifts")
    public String save(@RequestParam String employeeCode,
                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate shiftDate,
                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {
        String code = employeeCode.trim();
        // 空欄や、終了が開始より前のときは登録しない
        if (!code.isEmpty() && endTime.isAfter(startTime)) {
            // 同じ人・同じ日があれば上書き、なければ新規
            Shift s = repo.findByEmployeeCodeAndShiftDate(code, shiftDate).orElseGet(Shift::new);
            s.setEmployeeCode(code);
            s.setShiftDate(shiftDate);
            s.setStartTime(startTime);
            s.setEndTime(endTime);
            repo.save(s);
        }
        return "redirect:/shifts";
    }

    @PostMapping("/shifts/{id}/delete")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/shifts";
    }
}
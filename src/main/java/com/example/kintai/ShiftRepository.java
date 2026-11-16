package com.example.kintai;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findAllByOrderByShiftDateAscStartTimeAsc();
    Optional<Shift> findByEmployeeCodeAndShiftDate(String employeeCode, LocalDate shiftDate);
}
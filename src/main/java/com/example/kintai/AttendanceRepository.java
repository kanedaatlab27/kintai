package com.example.kintai;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAllByOrderByClockInDesc();
    Optional<Attendance> findFirstByEmployeeCodeAndClockOutIsNullOrderByClockInDesc(String employeeCode);
}
package com.example.kintai;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity

public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeCode;   // 匿名ID（例: A001）
    private LocalDateTime clockIn;
    private LocalDateTime clockOut;

    public Long getId() { return id; }
    public String getEmployeeCode() { return employeeCode; }
    public void setEmployeeCode(String employeeCode) { this.employeeCode = employeeCode; }
    public LocalDateTime getClockIn() { return clockIn; }
    public void setClockIn(LocalDateTime clockIn) { this.clockIn = clockIn; }
    public LocalDateTime getClockOut() { return clockOut; }
    public void setClockOut(LocalDateTime clockOut) { this.clockOut = clockOut; }
}

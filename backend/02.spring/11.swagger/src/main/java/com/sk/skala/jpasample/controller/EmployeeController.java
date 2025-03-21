package com.sk.skala.jpasample.controller;

import com.sk.skala.jpasample.model.Department;
import com.sk.skala.jpasample.model.Employee;
import com.sk.skala.jpasample.service.DepartmentService;
import com.sk.skala.jpasample.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }


    @GetMapping("/departments/{departmentId}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(
            @Parameter(description = "부서 ID", example = "1") @PathVariable Long departmentId) {
        return ResponseEntity.ok(employeeService.getEmployeesByDepartment(departmentId));
    }

    @PostMapping("/departments/{departmentId}/employees")
    public ResponseEntity<Employee> addEmployee(
            @RequestBody Employee employee,
            @Parameter(description = "부서 ID", example = "1") @PathVariable Long departmentId) {
        return ResponseEntity.ok(employeeService.addEmployee(employee, departmentId));
    }

    @PutMapping("/employees/{id}/department/{departmentId}")
    public ResponseEntity<Employee> updateEmployee(
            @Parameter(description = "직원 ID", example = "1") @PathVariable Long id,
            @RequestBody Employee employee,
            @Parameter(description = "부서 ID", example = "1") @PathVariable Long departmentId) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee, departmentId));
    }
}

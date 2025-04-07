package com.demo.employeeManagement.controller;

import com.demo.employeeManagement.dto.ApiResponse;
import com.demo.employeeManagement.dto.EmployeeDTO;
import com.demo.employeeManagement.dto.EmployeeNameAndIdDTO;
import com.demo.employeeManagement.dto.EmployeeResponse;
import com.demo.employeeManagement.exception.EMException;
import com.demo.employeeManagement.service.EmployeeService;
import com.demo.employeeManagement.util.PagingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PagingUtils pagingUtils;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<EmployeeDTO>> save(@RequestBody EmployeeDTO employeeDTO) {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            apiResponse.setResponse(employeeService.create(employeeDTO));
            apiResponse.setMessage("Created Employee Successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (EMException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setMessage(e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Error Occurred While Creating Employee : " + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<ApiResponse<EmployeeDTO>> findById(@RequestParam(value = "id") String id) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setResponse(employeeService.getEmployeeById(id));
            apiResponse.setMessage("Employee fetched successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (EMException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setMessage(e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Failed To Fetch Employee: " + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Boolean>> deleteById(@RequestParam(value = "id") String id) throws EMException {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setResponse(employeeService.delete(id));
            apiResponse.setMessage("Employee Deleted Successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (EMException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setSuccess(Boolean.FALSE);
            apiResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Failed to Delete Employee:" + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<ApiResponse<EmployeeResponse>> findAll(@RequestParam(required = false) Integer page,
                                                                 @RequestParam(required = false) Integer pageLength) {
        ApiResponse apiResponse = new ApiResponse();
        final Pageable pageable = PageRequest.of(pagingUtils.getNextPage(page), pagingUtils.getPageLength(pageLength));
        try {
            apiResponse.setResponse(employeeService.getAllEmployee(pageable));
            apiResponse.setMessage("Employee Fetched Successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (EMException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setMessage(e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Error Occured While Fetching Employee: " + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @GetMapping("/employees-name-id")
    public ResponseEntity<ApiResponse<List<EmployeeNameAndIdDTO>>> getEmployees(
            @RequestParam(value = "lookup", required = false) String lookup) {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            if ("true".equalsIgnoreCase(lookup)) {
                apiResponse.setResponse(employeeService.getEmployeeNamesAndIds());
            }
                apiResponse.setMessage("Department Details Fetched Successfully");
                apiResponse.setSuccess(Boolean.TRUE);
                return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

            } catch(EMException e){
                apiResponse.setErrorCode(e.getCode());
                apiResponse.setSuccess(Boolean.FALSE);
                apiResponse.setMessage(e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
            } catch(Exception e){
                e.printStackTrace();
                apiResponse.setMessage("Error occurred While Fetching Department Details:" + e.getMessage());
                apiResponse.setSuccess(Boolean.FALSE);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
            }
        }
    }

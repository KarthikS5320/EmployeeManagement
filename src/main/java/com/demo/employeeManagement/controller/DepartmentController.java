package com.demo.employeeManagement.controller;

import com.demo.employeeManagement.dto.ApiResponse;
import com.demo.employeeManagement.dto.DepartmentDTO;
import com.demo.employeeManagement.exception.EMException;
import com.demo.employeeManagement.service.DepartmentService;
import com.demo.employeeManagement.util.PagingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    PagingUtils pagingUtils;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<DepartmentDTO>> create(@RequestBody DepartmentDTO departmentDTO) throws EMException {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            apiResponse.setResponse(departmentService.create(departmentDTO));
            apiResponse.setMessage("Created Department Successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (EMException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setMessage(e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Error Occured While Creating Department : " + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<ApiResponse<DepartmentDTO>> findById(@RequestParam(value = "id") String id) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setResponse(departmentService.findById(id));
            apiResponse.setMessage("Department fetched successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (EMException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setMessage(e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Failed To Fetch Department : " + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<ApiResponse<List<DepartmentDTO>>> findAll(@RequestParam(required = false) Integer page,
                                                                    @RequestParam(required = false) Integer pageLength) {
        ApiResponse apiResponse = new ApiResponse();
        final Pageable pageable = PageRequest.of(pagingUtils.getNextPage(page), pagingUtils.getPageLength(pageLength));
        try {
            apiResponse.setResponse(departmentService.findAllDepartment(pageable));
            apiResponse.setMessage("Department List Fetched Successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (EMException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setMessage(e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Error Occured While Fetching Department List: " + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Boolean>> deleteById(@RequestParam(value = "id") String id) throws EMException {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setResponse(departmentService.delete(id));
            apiResponse.setMessage(" Department Deleted Successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (EMException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setSuccess(Boolean.FALSE);
            apiResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Failed to Delete Department:" + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }
    @GetMapping("/department-with-employees")
    public ResponseEntity<ApiResponse<DepartmentDTO>> getDepartmentWithEmployees(@RequestParam(value = "departmentId") String departmentId,
                                                               @RequestParam(value = "expand", required = false) String expand) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if ("employee".equalsIgnoreCase(expand)) {
                apiResponse.setResponse(departmentService.getDepartmentWithEmployees(departmentId));
                apiResponse.setMessage("Department with employees fetched successfully");
            } else {
                apiResponse.setResponse(departmentService.getDepartmentWithEmployees(departmentId));
                apiResponse.setMessage("Department fetched successfully");
            }
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (EMException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setMessage(e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Failed To Fetch Department : " + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }
}

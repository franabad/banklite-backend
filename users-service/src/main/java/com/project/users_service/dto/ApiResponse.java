package com.project.users_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ApiResponse<T> {

    private List<T> data;
    private int totalElements;
}

package com.vertace.javapostgre.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericDataPagedResponse {
    private PageResponse pageResponse;
    private List<?> data;
}

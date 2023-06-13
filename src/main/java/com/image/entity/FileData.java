package com.image.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity(name = "FILE_DATA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileData_Id;

    @NotBlank(message = "Name is a required Field")
    private String name;

    @NotBlank(message = "Type is a required Field")
    private String type;

    @NotBlank(message = "Filepath is a required field")
    private String filePath;
}
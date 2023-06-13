package com.image.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "IMAGE_DATA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageData_Id;

    @NotBlank(message = "Name is a required Field")
    private String name;

    @NotBlank(message = "Type is a required Field")
    private String type;

    @Lob
    @Column(name = "IMAGEDATA", length = 1000)
    private byte[] imageData;
}

package dev.emreozkpln.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponseDto {

    private Integer id;
    private String schoolName;
    private String firstname;
    private String lastname;
}

package dev.emreozkpln.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    @NotNull
    private Integer school_id;
    @NotNull
    private String studentNo;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
}

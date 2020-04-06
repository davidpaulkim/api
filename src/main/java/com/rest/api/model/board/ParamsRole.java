package com.rest.api.model.board;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ParamsRole {
    @NotEmpty
    @Size(min = 2, max = 50)
    @ApiModelProperty(value = "역할이름", required = true)
    private String roleName;
}

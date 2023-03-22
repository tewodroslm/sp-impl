package com.tedspsecuritydemo.spsecurity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyResponseDto {

    public String companyName;
    public String companyId;

}

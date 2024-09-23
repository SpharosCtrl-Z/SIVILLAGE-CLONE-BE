package com.academy.sivillageclonebe.admin.dto;

import com.academy.sivillageclonebe.admin.vo.MiddleCategoryResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MiddleCategoryResponseDto {

    private String middleCategoryName;
    private String topCategoryName;

    public MiddleCategoryResponseVo toVo() {
        return MiddleCategoryResponseVo.builder()
                .middleCategoryName(middleCategoryName)
                .topCategoryName(topCategoryName)
                .build();
    }
}

package com.sf.common.web.model.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ky
 * @description
 * @date 2024-04-25 21:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonPage<T> {

    private List<T> record;
    private Long total;

    public static <T> CommonPage<T> restPage(List<T> list) {
        return new CommonPage<T>(list, Long.valueOf(String.valueOf(list.size())));
    }

    public static <T> CommonPage<T> restPage(IPage<T> page) {
        return new CommonPage<T>(page.getRecords(), page.getTotal());
    }
}

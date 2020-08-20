package com.huiaong.bulbasaur.common.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseBean implements Serializable {

    private Long id;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private Integer delFlag;

}

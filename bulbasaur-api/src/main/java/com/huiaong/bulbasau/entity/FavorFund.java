package com.huiaong.bulbasau.entity;

import com.huiaong.bulbasaur.common.base.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class FavorFund extends BaseBean implements Serializable {

    private Long userId;

    private String fundCode;
}

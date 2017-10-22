package com.lkf.job.core.biz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by liukaifeng on 17/10/22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistryParam implements Serializable {
    private static final long serialVersionUID = 42L;

    private String registGroup;
    private String registryKey;
    private String registryValue;
}

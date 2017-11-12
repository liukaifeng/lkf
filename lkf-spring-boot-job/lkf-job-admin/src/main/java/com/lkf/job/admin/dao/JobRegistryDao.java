package com.lkf.job.admin.dao;

import com.lkf.job.admin.core.model.JobRegistry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author lkf
 * @date 16/9/30
 */
public interface JobRegistryDao {

    public int removeDead(@Param("timeout") int timeout);

    public List<JobRegistry> findAll(@Param("timeout") int timeout);

    public int registryUpdate(@Param("registryGroup") String registryGroup,
                              @Param("registryKey") String registryKey,
                              @Param("registryValue") String registryValue);

    public int registrySave(@Param("registryGroup") String registryGroup,
                            @Param("registryKey") String registryKey,
                            @Param("registryValue") String registryValue);

    public int registryDelete(@Param("registryGroup") String registGroup,
                              @Param("registryKey") String registryKey,
                              @Param("registryValue") String registryValue);

}

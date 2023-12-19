#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

/*-
 * ${symbol_pound}%L
 * ${artifactId}
 * %%
 * Copyright (C) 2022 xfeel
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ${symbol_pound}L%
 */



import ${package}.domain.*;
import ${package}.exception.ApiVendorException;
import ${package}.constant.I18nExceptionConstants;
import ${package}.mapper.ApiVendorServiceBeanMapper;
import ${package}.dao.repository.ApiVendorRepository;
import ${package}.constant.ApiResultCode;
import ${package}.constant.GlobalConstants;
import ${package}.dao.entity.ApiVendor;
import ${package}.service.ApiVendorService;
import com.xfeel.support.jpa.utility.JpaUtils;
import com.xfeel.support.common.domain.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Api供应商Service实现
 *
 * @author Admin
 */
@Slf4j
@Service
public class ApiVendorServiceImpl implements ApiVendorService {

    private final ApiVendorRepository apiVendorRepository;
    private final ApiVendorServiceBeanMapper apiVendorServiceBeanMapper;
    public ApiVendorServiceImpl(ApiVendorRepository apiVendorRepository, ApiVendorServiceBeanMapper apiVendorServiceBeanMapper) {
        this.apiVendorRepository = apiVendorRepository;
        this.apiVendorServiceBeanMapper = apiVendorServiceBeanMapper;
    }

    @Override
    public Page<ApiVendorResult> queryPage(ApiVendorListQueryParam queryParam) {

        ApiVendor probe = new ApiVendor();
        if (queryParam.getName() != null) {
            probe.setName(queryParam.getName().getHas());
        }

        Example example = Example.of(probe, ExampleMatcher.matching());
        org.springframework.data.domain.Page<ApiVendor> page = apiVendorRepository.findAll(example,
                PageRequest.of(queryParam.getPageIndex() - 1,
                        queryParam.getPageSize(),
                        JpaUtils.getDefaultSort())
        );

        return Page.of(page.getTotalElements(),
                page.get().map(apiVendor -> apiVendorServiceBeanMapper.toApiVendorResult(apiVendor))
                        .collect(Collectors.toList())
        );


    }

    @Override
    public ApiVendorResult create(ApiVendorCreateParam createParam) {

        {
            validName(createParam.getName());
            validAppKeyAndSecretKey(createParam.getAppKey(), createParam.getSecretKey());
        }

        ApiVendor apiVendor = ApiVendor.builder()
                .name(createParam.getName())
                .appKey(createParam.getAppKey())
                .secretKey(createParam.getSecretKey())
                .build();
        apiVendorRepository.save(apiVendor);
        return apiVendorServiceBeanMapper.toApiVendorResult(apiVendor);
    }

    @Override
    public ApiVendorResult updateById(Long id, ApiVendorUpdateParam updateParam) {
        ApiVendor apiVendor = apiVendorRepository.findById(id).orElseThrow(() -> new ApiVendorException(ApiResultCode.API_RET_CODE_API_VENDOR_BIZ_ERR, I18nExceptionConstants.EX_API_VENDOR_NOT_EXIST, id));

        {
            validName(updateParam.getName());
            validAppKeyAndSecretKey(updateParam.getAppKey(), updateParam.getSecretKey());
        }

        BeanUtils.copyProperties(updateParam, apiVendor);
        apiVendorRepository.save(apiVendor);
        return apiVendorServiceBeanMapper.toApiVendorResult(apiVendor);
    }

    @Override
    public Boolean deleteById(Long id) {
        apiVendorRepository.findById(id).orElseThrow(() -> new ApiVendorException(ApiResultCode.API_RET_CODE_API_VENDOR_BIZ_ERR, I18nExceptionConstants.EX_API_VENDOR_NOT_EXIST, id));
        apiVendorRepository.deleteById(id);
        return true;
    }

    @Override
    public ApiVendorDetailResult findById(Long id) {
        ApiVendor apiVendor = apiVendorRepository.findById(id).orElseThrow(() -> new ApiVendorException(ApiResultCode.API_RET_CODE_API_VENDOR_BIZ_ERR, I18nExceptionConstants.EX_TEMPLATE_NOT_EXIST, id));
        return apiVendorServiceBeanMapper.toApiVendorDetailResult(apiVendor);
    }

    private void validName(String name) {
        Optional<ApiVendor> byNameAndDeletedFlag = apiVendorRepository.findByNameAndDeletedFlag(name, GlobalConstants.DATA_STATUS_NORMAL);
        byNameAndDeletedFlag.ifPresent(apiVendor -> {
            throw new ApiVendorException(ApiResultCode.API_RET_CODE_API_VENDOR_BIZ_ERR, I18nExceptionConstants.EX_API_VENDOR_NAME_ALREADY_EXIST, name);
        });
    }

    private void validAppKeyAndSecretKey(String appKey, String secretKey) {
        Optional<ApiVendor> byAppKeyAndSecretKeyAndDeletedFlag = apiVendorRepository.findByAppKeyAndSecretKeyAndDeletedFlag(appKey, secretKey, GlobalConstants.DATA_STATUS_NORMAL);
        byAppKeyAndSecretKeyAndDeletedFlag.ifPresent(apiVendor -> {
            throw new ApiVendorException(ApiResultCode.API_RET_CODE_API_VENDOR_BIZ_ERR, I18nExceptionConstants.EX_API_VENDOR_APPKEY_AND_SECRETKEY_ALREADY_EXIST, appKey, secretKey);
        });
    }
}

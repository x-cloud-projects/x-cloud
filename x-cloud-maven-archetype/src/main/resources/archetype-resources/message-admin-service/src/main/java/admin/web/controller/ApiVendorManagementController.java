#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.admin.web.controller;

/*-
 * ${symbol_pound}%L
 * Message Admin Service
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


import ${package}.admin.web.mapper.ApiVendorWebBeanMapper;
import ${package}.admin.web.vo.apivendor.ApiVendorCreateVO;
import ${package}.admin.web.vo.apivendor.ApiVendorPageListResultVO;
import ${package}.admin.web.vo.apivendor.ApiVendorUpdateVO;
import ${package}.service.ApiVendorService;
import ${package}.admin.web.vo.apivendor.ApiVendorListQueryVO;
import com.xfeel.support.common.domain.pagination.Page;
import com.xfeel.support.swagger.GlobalSwaggerConstants;
import com.xfeel.support.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Admin
 */
@Api(tags = {GlobalSwaggerConstants.API_TAG_ADMIN, "供应商管理"})
@RestController
@RequestMapping("management")
public class ApiVendorManagementController extends BaseController {

    private final ApiVendorService apiVendorService;
    private final ApiVendorWebBeanMapper apiVendorWebBeanMapper;

    public ApiVendorManagementController(ApiVendorService apiVendorService, ApiVendorWebBeanMapper apiVendorWebBeanMapper) {
        this.apiVendorService = apiVendorService;
        this.apiVendorWebBeanMapper = apiVendorWebBeanMapper;
    }

    @ApiOperation(value = "供应商列表", tags = {GlobalSwaggerConstants.API_TAG_ADMIN,GlobalSwaggerConstants.API_TAG_PAGE})
    @GetMapping(value = "apivendors")
    public ResponseEntity<?> index(ApiVendorListQueryVO queryParam) {

        Page<ApiVendorPageListResultVO> pageResult = Optional.ofNullable(apiVendorService.queryPage(apiVendorWebBeanMapper.toApiVendorListQueryParam(queryParam)))
                .map(page -> {
                    Page<ApiVendorPageListResultVO> pageVO = new Page<>();
                    pageVO.setTotal(page.getTotal());
                    pageVO.setList(page.getList().stream()
                            .map(result -> apiVendorWebBeanMapper.fromApiVendorPageListResultVO(result))
                            .collect(Collectors.toList()));
                    return pageVO;
        }).orElse(Page.emptyResult());
        return ok(pageResult);
    }

    @ApiOperation(value = "新增供应商", tags = {GlobalSwaggerConstants.API_TAG_ADMIN,GlobalSwaggerConstants.API_TAG_ADD})
    @PostMapping(value = "apivendors")
    public ResponseEntity<?> create(@RequestBody @Validated ApiVendorCreateVO createVO) {
        return ok(apiVendorService.create(apiVendorWebBeanMapper.toApiVendorCreateParam(createVO)));
    }

    @ApiOperation(value = "供应商详情", tags = {GlobalSwaggerConstants.API_TAG_ADMIN,GlobalSwaggerConstants.API_TAG_DETAIL})
    @GetMapping(value = "apivendors/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") Long id) {
        return ok(apiVendorWebBeanMapper.toApiVendorDetailVO(apiVendorService.findById(id)));
    }

    @ApiOperation(value = "修改供应商", tags = {GlobalSwaggerConstants.API_TAG_ADMIN,GlobalSwaggerConstants.API_TAG_UPDATE})
    @PutMapping(value = "apivendors/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ApiVendorUpdateVO updateVO) {
        return ok(apiVendorService.updateById(id, apiVendorWebBeanMapper.toApiVendorUpdateParam(updateVO)));
    }

    @ApiOperation(value = "删除供应商", tags = {GlobalSwaggerConstants.API_TAG_ADMIN,GlobalSwaggerConstants.API_TAG_DELETE})
    @DeleteMapping(value = "apivendors/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ok(apiVendorService.deleteById(id));
    }
}

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


import ${package}.admin.web.mapper.TemplateWebBeanMapper;
import ${package}.admin.web.vo.template.TemplateListQueryVO;
import ${package}.service.TemplateService;
import ${package}.admin.web.vo.template.TemplateCreateVO;
import ${package}.admin.web.vo.template.TemplateUpdateVO;
import com.xfeel.support.swagger.GlobalSwaggerConstants;
import com.xfeel.support.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Admin
 */
@Api(tags = {GlobalSwaggerConstants.API_TAG_ADMIN, "模板接口"})
@RestController
@RequestMapping("management")
public class TemplateManagementController extends BaseController {

    private final TemplateService templateService;
    private final TemplateWebBeanMapper templateWebBeanMapper;

    public TemplateManagementController(TemplateService templateService, TemplateWebBeanMapper templateWebBeanMapper) {
        this.templateService = templateService;
        this.templateWebBeanMapper = templateWebBeanMapper;
    }

    @ApiOperation(value = "模板列表", tags = {GlobalSwaggerConstants.API_TAG_ADMIN,GlobalSwaggerConstants.API_TAG_PAGE})
    @GetMapping(value = "templates")
    public ResponseEntity<?> index(TemplateListQueryVO queryParam) {
        return ResponseEntity.ok(templateService.queryPage(templateWebBeanMapper.toTemplateListQueryParam(queryParam)));
    }

    @ApiOperation(value = "新增模板", tags = {GlobalSwaggerConstants.API_TAG_ADMIN,GlobalSwaggerConstants.API_TAG_ADD})
    @PostMapping(value = "templates")
    public ResponseEntity<?> create(@RequestBody @Valid TemplateCreateVO createVO) {
        return ResponseEntity.ok(templateService.create(templateWebBeanMapper.toTemplateCreateParam(createVO)));
    }

    @ApiOperation(value = "模板详情", tags = {GlobalSwaggerConstants.API_TAG_ADMIN,GlobalSwaggerConstants.API_TAG_DETAIL})
    @GetMapping(value = "templates/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") Long id) {
        return ResponseEntity.ok(templateService.findById(id));
    }

    @ApiOperation(value = "修改模板", tags = {GlobalSwaggerConstants.API_TAG_ADMIN,GlobalSwaggerConstants.API_TAG_UPDATE})
    @PutMapping(value = "templates/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody TemplateUpdateVO updateVO) {
        return ResponseEntity.ok(templateService.updateById(id, templateWebBeanMapper.toTemplateUpdateParam(updateVO)));
    }

    @ApiOperation(value = "删除模板", tags = {GlobalSwaggerConstants.API_TAG_ADMIN,GlobalSwaggerConstants.API_TAG_DELETE})
    @DeleteMapping(value = "templates/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(templateService.deleteById(id));
    }
}

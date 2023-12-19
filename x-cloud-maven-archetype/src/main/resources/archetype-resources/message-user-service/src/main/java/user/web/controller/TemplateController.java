#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.user.web.controller;

/*-
 * ${symbol_pound}%L
 * Message User Service
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


import ${package}.service.TemplateService;
import ${package}.user.web.mapper.TemplateWebBeanMapper;
import com.xfeel.support.swagger.GlobalSwaggerConstants;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Admin
 */
@Api(tags = {GlobalSwaggerConstants.API_TAG_USER, "模板接口"})
@RestController
@RequestMapping("user")
public class TemplateController {

    private final TemplateService templateService;
    private final TemplateWebBeanMapper templateWebBeanMapper;

    public TemplateController(TemplateService templateService, TemplateWebBeanMapper templateWebBeanMapper) {
        this.templateService = templateService;
        this.templateWebBeanMapper = templateWebBeanMapper;
    }
}

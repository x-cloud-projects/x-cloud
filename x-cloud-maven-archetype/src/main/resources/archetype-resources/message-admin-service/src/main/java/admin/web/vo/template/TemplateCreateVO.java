#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.admin.web.vo.template;

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


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Admin
 */
@Data
public class TemplateCreateVO {

    @ApiModelProperty("模板名称")
    @NotBlank(message = "{valid.template.name.not.empty}")
    private String name;

    @ApiModelProperty("模板编号")
    @NotBlank(message = "{valid.template.code.not.empty}")
    private String code;

    @ApiModelProperty("模板")
    @NotBlank(message = "{valid.template.content.not.empty}")
    private String template;

}

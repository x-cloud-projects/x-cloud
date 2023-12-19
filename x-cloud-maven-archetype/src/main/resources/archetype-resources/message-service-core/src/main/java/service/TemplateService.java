#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

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
import com.xfeel.support.common.domain.pagination.Page;

/**
 * @author Admin
 */
public interface TemplateService {

    /**
     * 查询模板分页列表
     *
     * @param queryParam
     * @return
     */
    Page<TemplateResult> queryPage(TemplateListQueryParam queryParam);

    /**
     * 创建模板
     *
     * @param createParam
     * @return
     */
    TemplateResult create(TemplateCreateParam createParam);

    /**
     * 修改模板
     *
     * @param id
     * @param updateParam
     * @return
     */
    TemplateResult updateById(Long id, TemplateUpdateParam updateParam);

    /**
     * 删除模板
     *
     * @param id
     * @return
     */
    Boolean deleteById(Long id);

    /**
     * 模板详情
     *
     * @param id
     * @return
     */
    TemplateDetailResult findById(Long id);
}

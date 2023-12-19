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


import ${package}.constant.I18nExceptionConstants;

import ${package}.domain.*;
import ${package}.exception.TemplateException;
import ${package}.mapper.TemplateServiceBeanMapper;
import ${package}.dao.repository.TemplateRepository;
import ${package}.service.TemplateService;
import ${package}.constant.ApiResultCode;
import ${package}.constant.GlobalConstants;
import ${package}.dao.entity.Template;
import ${package}.enums.AuditStatusEnum;
import com.xfeel.support.jpa.utility.JpaUtils;
import com.xfeel.support.common.domain.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Admin
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateServiceBeanMapper templateServiceBeanMapper;

    public TemplateServiceImpl(TemplateRepository templateRepository, TemplateServiceBeanMapper templateServiceBeanMapper) {
        this.templateRepository = templateRepository;
        this.templateServiceBeanMapper = templateServiceBeanMapper;
    }

    @Override
    public Page<TemplateResult> queryPage(TemplateListQueryParam queryParam) {
        Template probe = new Template();
        if (queryParam.getCode() != null) {
            probe.setCode(queryParam.getCode().getEq());
        }

        if (queryParam.getTemplate() != null) {
            probe.setTemplate(queryParam.getTemplate().getHas());
        }
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT);
        Example<Template> example = Example.of(probe, matcher);
        org.springframework.data.domain.Page<Template> page = templateRepository.findAll(example,
                PageRequest.of(queryParam.getPageIndex() - 1,
                        queryParam.getPageSize(),
                        JpaUtils.getDefaultSort())
        );
        return Page.of(
                page.getTotalElements(),
                page.get().map(template -> templateServiceBeanMapper.fromTemplate(template)).collect(Collectors.toList()));
    }

    @Override
    public TemplateResult create(TemplateCreateParam createVO) {
        validCode(createVO.getCode());
        Template template = Template.builder()
                .code(createVO.getCode())
                .template(createVO.getTemplate())
                .build();
        templateRepository.save(template);
        return templateServiceBeanMapper.fromTemplate(template);
    }

    @Override
    public TemplateResult updateById(Long id, TemplateUpdateParam updateVO) {

        /**
         * 修改模板逻辑： 1. 模板修改后需要进行重新审核 2. 模板修改后原引用消息如何处理
         */

        Template template = templateRepository.findById(id).orElseThrow(() -> new TemplateException(ApiResultCode.API_RET_CODE_TEMPLATE_BIZ_ERR, I18nExceptionConstants.EX_TEMPLATE_NOT_EXIST, id));

        if (!Objects.isNull(updateVO.getCode())) {
            validCode(updateVO.getCode());
        }

        if (updateVO.getAuditStatus() != null) {
            template.setAuditStatus(updateVO.getAuditStatus());
            if (AuditStatusEnum.REJECT.value() == updateVO.getAuditStatus() && !StringUtils.hasText(updateVO.getAuditReason())) {
                throw new TemplateException(ApiResultCode.API_RET_CODE_TEMPLATE_BIZ_ERR, I18nExceptionConstants.EX_TEMPLATE_AUDIT_REASON_NOT_EMPTY);
            }
            template.setAuditReason(updateVO.getAuditReason());
        } else {
            BeanUtils.copyProperties(updateVO, template);
            // 设置为待审核
            template.setAuditStatus(AuditStatusEnum.AUDITING.value());
        }
        templateRepository.save(template);

        return templateServiceBeanMapper.fromTemplate(template);
    }

    @Override
    public Boolean deleteById(Long id) {
        templateRepository.deleteById(id);
        return true;
    }

    @Override
    public TemplateDetailResult findById(Long id) {
        Template template = templateRepository.findById(id)
                .orElseThrow(() -> new TemplateException(ApiResultCode.API_RET_CODE_TEMPLATE_BIZ_ERR, I18nExceptionConstants.EX_TEMPLATE_NOT_EXIST, id));
        return templateServiceBeanMapper.fromTemplateDetail(template);
    }

    private void validId(Long id){
        templateRepository.findById(id)
                .orElseThrow(() -> new TemplateException(ApiResultCode.API_RET_CODE_TEMPLATE_BIZ_ERR, I18nExceptionConstants.EX_TEMPLATE_NOT_EXIST, id));
    }

    private void validCode(String code) {
        if (!StringUtils.hasText(code)) {
            throw new TemplateException(ApiResultCode.API_RET_CODE_TEMPLATE_BIZ_ERR, I18nExceptionConstants.EX_TEMPLATE_CODE_NOT_EMPTY);
        }
        Optional<Template> byCodeAndDeletedFlag = templateRepository.findByCodeAndDeletedFlag(code, GlobalConstants.DATA_STATUS_NORMAL);
        if (byCodeAndDeletedFlag.isPresent()) {
            throw new TemplateException(ApiResultCode.API_RET_CODE_TEMPLATE_BIZ_ERR, I18nExceptionConstants.EX_TEMPLATE_CODE_ALREADY_EXIST, code);
        }
    }
}

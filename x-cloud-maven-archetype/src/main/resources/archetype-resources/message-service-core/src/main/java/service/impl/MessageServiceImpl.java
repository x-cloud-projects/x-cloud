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


import ${groupId}.common.util.JsonUtils;
import ${package}.config.CommonConfig;

import ${package}.constant.I18nExceptionConstants;
import ${package}.domain.*;
import ${package}.exception.MessageException;
import ${package}.dao.repository.ApiVendorRepository;
import ${package}.dao.repository.MessageRepository;
import ${package}.dao.repository.TemplateRepository;
import ${package}.mapper.MessageServiceBeanMapper;
import ${package}.service.MessageService;
import ${package}.constant.ApiResultCode;
import ${package}.constant.GlobalConstants;
import ${package}.dao.entity.Message;
import ${package}.dao.entity.Template;
import ${package}.enums.AuditStatusEnum;
import ${package}.mongo.entity.MessageRecord;
import ${package}.mongo.repository.MessageRecordRepository;
import ${package}.utility.TemplateUtils;
import com.xfeel.support.jpa.utility.JpaUtils;
import com.xfeel.support.common.domain.pagination.Page;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Admin
 */
@Service
public class MessageServiceImpl implements MessageService {

    /**
     * 默认群发人数限制
     */
    private static final int DEFAULT_GROUP_SEND_LIMIT = 10;

    private final TemplateRepository templateRepository;
    private final MessageRepository messageRepository;
    private final ApiVendorRepository apiVendorRepository;
    private final MessageRecordRepository messageRecordRepository;

    //private final AliSmsSender aliSmsSender;

    private final MessageServiceBeanMapper messageServiceBeanMapper;

    private final CommonConfig.BizProperties bizProperties;

    public MessageServiceImpl(TemplateRepository templateRepository, MessageRepository messageRepository, ApiVendorRepository apiVendorRepository, MessageRecordRepository messageRecordRepository, MessageServiceBeanMapper messageServiceBeanMapper, CommonConfig.BizProperties bizProperties) {
        this.templateRepository = templateRepository;
        this.messageRepository = messageRepository;
        this.apiVendorRepository = apiVendorRepository;
        this.messageRecordRepository = messageRecordRepository;
        this.messageServiceBeanMapper = messageServiceBeanMapper;
        this.bizProperties = bizProperties;
    }

    @Override
    public Page<MessagePageListResult> queryPageList(MessageListQueryParam listQueryParam) {
        org.springframework.data.domain.Page<Message> page = messageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    if (!Objects.isNull(listQueryParam.getTarget())) {
                        predicates.add(criteriaBuilder.like(root.get("target"), JpaUtils.buildContaining(listQueryParam.getTarget().getHas())));
                    }

                    if (!Objects.isNull(listQueryParam.getStartTime())) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(GlobalConstants.ENTITY_PROPERTY_GMT_CREATE), listQueryParam.getStartTime().getGte()));
                    }

                    if (!Objects.isNull(listQueryParam.getEndTime())) {
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(GlobalConstants.ENTITY_PROPERTY_GMT_CREATE), listQueryParam.getEndTime().getLte()));
                    }
                    return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
                },
                PageRequest.of(listQueryParam.getPageIndex(), listQueryParam.getPageSize(), JpaUtils.getDefaultSort()));
        return Page.of(page.getTotalElements(), page.get().map(message -> messageServiceBeanMapper.fromMessage(message)).collect(Collectors.toList()));
    }

    @Override
    public MessageDetailResult queryDetail(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new MessageException(ApiResultCode.API_RET_CODE_MESSAGE_BIZ_ERR, I18nExceptionConstants.EX_MESSAGE_NOT_EXIST));
        return messageServiceBeanMapper.fromMessageDetail(message);
    }

    @Override
    public void sendMessage(final MessageSendParam sendParam) {
        /**
         * 发消息逻辑： 1. 群发限制，大于10人走审核(TODO)
         */
        Template template = templateRepository.findByCodeAndAuditStatusAndDeletedFlag(sendParam.getTemplateCode(), AuditStatusEnum.PASS.value(), GlobalConstants.DATA_STATUS_NORMAL).orElseThrow(() -> {
            throw new MessageException(ApiResultCode.API_RET_CODE_MESSAGE_BIZ_ERR, I18nExceptionConstants.EX_TEMPLATE_NOT_READY);
        });
        Message message = Message.builder()
                .templateId(template.getId())
                .target(String.join(",", sendParam.getTargetList()))
                .jsonData(JsonUtils.toJsonString(sendParam.getMessageDataList()))
                .build();
        Message savedMessage = messageRepository.save(message);
        if (savedMessage.getId() != null) {
            //准备数据
            Map<String, String> targetDataMap = sendParam.getMessageDataList().stream().collect(Collectors.toMap(MessageSendParam.MessageData::getTarget, MessageSendParam.MessageData::getJsonData));
            //发送消息
            sendParam.getTargetList().forEach(target -> {
                String jsonData = targetDataMap.get(target);
                // 处理模板内容
                String content = TemplateUtils.render(template.getTemplate(), jsonData);
                //aliSmsSender.send(template.getCode(),jsonData);
                MessageRecord messageRecord = MessageRecord.builder()
                        .message(message)
                        .template(template)
                        .target(target)
                        .content(content)
                        .build();
                messageRecordRepository.save(messageRecord);
            });
        }

        if (bizProperties.getGroupSendLimit() <= DEFAULT_GROUP_SEND_LIMIT){
            //send
        }
    }

    @Override
    public Page<MessageRecordPageListResult> queryMessageDetailPageList(Long id, MessageDetailQueryParam queryParam) {
        validMessage(id);
        MessageRecord probe = new MessageRecord();
        probe.setMessage(Message.builder().build());
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<MessageRecord> example = Example.of(probe, matcher);
        org.springframework.data.domain.Page<MessageRecord> page = messageRecordRepository.findAll(example, PageRequest.of(queryParam.getPageIndex(), queryParam.getPageSize(), Sort.by(Sort.Direction.DESC, "gmtModified,gmtCraete")));
        return Page.of(page.getTotalElements(), page.get().map(messageRecord -> messageServiceBeanMapper.fromMessageRecord(messageRecord)).collect(Collectors.toList()));
    }

    @Override
    public MessageRecordDetailResult queryMessageDetail(Long id, String detailId) {
        validMessage(id);
        MessageRecord messageRecord = messageRecordRepository.findById(detailId).orElseThrow(() -> new MessageException(ApiResultCode.API_RET_CODE_MESSAGE_BIZ_ERR, I18nExceptionConstants.EX_MESSAGE_NOT_EXIST));
        return messageServiceBeanMapper.fromMessageRecordDetail(messageRecord);
    }

    private void validMessage(Long id) {
        messageRepository.findById(id).orElseThrow(() -> new MessageException(ApiResultCode.API_RET_CODE_MESSAGE_BIZ_ERR, I18nExceptionConstants.EX_MESSAGE_NOT_EXIST));
    }
}

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


import ${package}.admin.web.mapper.MessageWebBeanMapper;
import ${package}.admin.web.vo.message.MessagePageQueryVO;
import ${package}.admin.web.vo.message.MessageRecordPageQueryVO;
import ${package}.service.MessageService;
import com.xfeel.support.swagger.GlobalSwaggerConstants;
import com.xfeel.support.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Admin
 */
@Api(tags = {GlobalSwaggerConstants.API_TAG_ADMIN, "消息接口"})
@RestController
@RequestMapping("management")
public class MessageManagementController extends BaseController {

    private final MessageService messageService;
    private final MessageWebBeanMapper messageWebBeanMapper;

    public MessageManagementController(MessageService messageService, MessageWebBeanMapper messageWebBeanMapper) {
        this.messageService = messageService;
        this.messageWebBeanMapper = messageWebBeanMapper;
    }

    @ApiOperation(value = "消息列表", tags ={GlobalSwaggerConstants.API_TAG_ADMIN,GlobalSwaggerConstants.API_TAG_PAGE})
    @GetMapping(value = "messages")
    public ResponseEntity<?> index(MessagePageQueryVO listQueryParam) {
        return ResponseEntity.ok(messageService.queryPageList(messageWebBeanMapper.toMessageListQueryParam(listQueryParam)));
    }

    @ApiOperation(value = "消息详情", tags = {GlobalSwaggerConstants.API_TAG_ADMIN,GlobalSwaggerConstants.API_TAG_DETAIL})
    @GetMapping(value = "messages/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") Long id) {
        return ResponseEntity.ok(messageService.queryDetail(id));
    }

    @ApiOperation(value = "消息明细列表（分页）", tags = {GlobalSwaggerConstants.API_TAG_ADMIN,GlobalSwaggerConstants.API_TAG_PAGE})
    @GetMapping(value = "messages/{id}/details")
    public ResponseEntity<?> messageDetails(@PathVariable("id") Long id, MessageRecordPageQueryVO queryParam) {
        return ResponseEntity.ok(messageService.queryMessageDetailPageList(id, messageWebBeanMapper.toMessageDetailParam(queryParam)));
    }

    @ApiOperation(value = "消息明细", tags = {GlobalSwaggerConstants.API_TAG_ADMIN,GlobalSwaggerConstants.API_TAG_DETAIL})
    @GetMapping(value = "messages/{id}/details/{detailId}")
    public ResponseEntity<?> messageDetail(@PathVariable("id") Long id,@PathVariable("detailId") String detailId) {
        return ResponseEntity.ok(messageService.queryMessageDetail(id,detailId));
    }

}

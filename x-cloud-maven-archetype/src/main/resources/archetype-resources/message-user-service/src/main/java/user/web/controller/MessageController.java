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


import ${package}.api.MessageApi;
import ${package}.api.vo.MessageSendVO;
import ${package}.service.MessageService;
import ${package}.user.web.mapper.MessageWebBeanMapper;
import com.xfeel.support.swagger.GlobalSwaggerConstants;
import com.xfeel.support.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Admin
 */
@Api(tags = {GlobalSwaggerConstants.API_TAG_USER, "消息接口"})
@RestController
@RequestMapping("user")
public class MessageController extends BaseController implements MessageApi {

    private final MessageService messageService;
    private final MessageWebBeanMapper messageWebBeanMapper;
    public MessageController(MessageService messageService, MessageWebBeanMapper messageWebBeanMapper) {
        this.messageService = messageService;
        this.messageWebBeanMapper = messageWebBeanMapper;
    }

    @Override
    @ApiOperation(value = "发送消息", notes = "用户端")
    @RequestMapping(value = "message/send", method = RequestMethod.POST)
    public ResponseEntity<?> send(@RequestBody @Valid MessageSendVO sendParam) {
        messageService.sendMessage(messageWebBeanMapper.toMessageSendParam(sendParam));
        return ok(true);
    }
}

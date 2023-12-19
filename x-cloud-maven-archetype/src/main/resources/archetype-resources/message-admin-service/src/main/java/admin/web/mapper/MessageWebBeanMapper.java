#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.admin.web.mapper;

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


import ${package}.admin.web.vo.message.MessagePageQueryVO;
import ${package}.admin.web.vo.message.MessageRecordPageQueryVO;
import ${package}.domain.MessageDetailQueryParam;
import ${package}.domain.MessageListQueryParam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageWebBeanMapper {

    /**
     * MessageListQueryVO -> MessageListQueryParam
     * @param listQueryParam
     * @return
     */
    MessageListQueryParam toMessageListQueryParam(MessagePageQueryVO listQueryParam);

    /**
     * MessageDetailQueryVO -> MessageDetailQueryParam
     * @param queryParam
     * @return
     */
    MessageDetailQueryParam toMessageDetailParam(MessageRecordPageQueryVO queryParam);
}

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
public interface MessageService {
    /**
     * 分页列表
     *
     * @param queryParam
     * @return
     */
    Page queryPageList(MessageListQueryParam queryParam);

    /**
     * 查询消息详情
     *
     * @param id 消息ID
     * @return
     */
     MessageDetailResult queryDetail(Long id);

    /**
     * 发送消息
     *
     * @param sendParam    消息发送
     */
    void sendMessage(MessageSendParam sendParam);

    /**
     * 查询消息明细列表
     *
     * @param id
     * @param queryParam
     * @return
     */
     Page<MessageRecordPageListResult> queryMessageDetailPageList(Long id, MessageDetailQueryParam queryParam);

    /**
     * 查询消息明细
     *
     * @param id
     * @param detailId
     * @return
     */
     MessageRecordDetailResult queryMessageDetail(Long id, String detailId);
}

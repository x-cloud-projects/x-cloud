#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.user.web.handler;

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


import com.xfeel.boot.autoconfigure.i18n.BaseLocaleException;
import com.xfeel.boot.autoconfigure.web.BaseGlobalExceptionHandler;
import ${package}.constant.ApiResultCode;
import ${package}.exception.ApiVendorException;
import ${package}.exception.MessageException;
import ${package}.exception.TemplateException;
import com.xfeel.support.common.domain.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * MMP异常处理器
 *
 * @author Admin
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel,AbbreviationAsWordInName")
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends BaseGlobalExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ApiVendorException.class, MessageException.class, TemplateException.class})
    public ApiResult businessException(BaseLocaleException e) {
        log.error("business error", e);
        if (e instanceof ApiVendorException) {
            return ApiResult.of(ApiResultCode.API_RET_CODE_API_VENDOR_BIZ_ERR, e.getMessage());
        } else if (e instanceof TemplateException) {
            return ApiResult.of(ApiResultCode.API_RET_CODE_TEMPLATE_BIZ_ERR, e.getMessage());
        } else if (e instanceof MessageException) {
            return ApiResult.of(ApiResultCode.API_RET_CODE_MESSAGE_BIZ_ERR, e.getMessage());
        }
        return ApiResult.of(ApiResultCode.API_RET_CODE_GLOBAL_EXCEPTION,e.getMessage());
    }

}

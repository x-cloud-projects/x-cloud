#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exception;

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


import com.xfeel.boot.autoconfigure.i18n.BaseLocaleException;

/**
 * @author Admin
 */
public class TemplateException extends BaseLocaleException {

    public TemplateException(Throwable throwable) {
        super(throwable);
    }

    public TemplateException(String message) {
        super(message);
    }

    public TemplateException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public TemplateException(String code, String msgKey) {
        super(code, msgKey);
    }

    public TemplateException(String code, String msgKey, Object... args) {
        super(code, msgKey, args);
    }

    public TemplateException(String code, String msgKey, Throwable throwable) {
        super(code, msgKey, throwable);
    }

    public TemplateException(String code, String msgKey, Throwable throwable, Object... args) {
        super(code, msgKey, throwable, args);
    }
}

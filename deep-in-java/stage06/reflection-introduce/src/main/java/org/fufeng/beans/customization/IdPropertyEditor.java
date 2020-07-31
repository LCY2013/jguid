/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-30
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.beans.customization;

import java.beans.PropertyEditorSupport;

import static org.fufeng.beans.properties.Person.isNumeric;

/**
 * @program: jguid
 * @description: {@link PropertyEditorSupport} 属性编辑器
 *  Person id 属性编辑器
 *  String -> Long
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-30
 * @see java.beans.PropertyEditor
 * @see PropertyEditorSupport
 * @see java.beans.PropertyEditorManager
 */
public class IdPropertyEditor extends PropertyEditorSupport{

    @Override
    public Long getValue() {
        return (Long) super.getValue();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (isNumeric(text)) {
            super.setValue(Long.parseLong(text));
        }
    }
}

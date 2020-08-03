/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-08-03
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.java.reflection;

import java.io.Serializable;

/**
 * @program: jguid
 * @description: 用户仓储具体实现
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-03
 */
@Repository
public class UserRepository implements Comparable<UserRepository>, // getGenericInterfaces[0] = ParameterizedType ->
                                                                    // ParameterizedType.rawType = Comparable
                                                                    // ParameterizedType.getActualTypeArguments()[0] = UserRepository
                            CurdRepository<User>,   // getGenericInterfaces[1] = ParameterizedType -> ParameterizedType.rawType = CrudRepository
                                                // ParameterizedType.rawType = CrudRepository
                                                // ParameterizedType.getActualTypeArguments()[0] = User
                            Serializable {   // getGenericInterfaces[2] = Class -> isInterface() == true

    @Override
    public int compareTo(UserRepository o) {
        return 0;
    }
}

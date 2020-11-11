/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-11
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.pool.v9;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.RecursiveTask;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 模拟MapReduce 统计单词数量
 * @create 2020-11-11
 */
public class WordCount extends RecursiveTask<Map<String, Integer>> {

    private static final long serialVersionUID = 106734581318142905L;

    private String[] fc;

    private int start, end;

    public WordCount(String[] fc, int start, int end) {
        this.fc = fc;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Map<String, Integer> compute() {
        // 分治边界
        if (end - start <= 1) {
            return calc(fc[start]);
        } else {
            // 进行任务缩小切分
            int mid = (this.start + this.end) / 2;
            // 新建缩小后的第一个任务
            final WordCount wordCount1 = new WordCount(fc, start, mid);
            wordCount1.fork();
            // 新建缩小后的第二个任务
            final WordCount wordCount2 = new WordCount(fc, mid, end);
            wordCount2.fork();
            return merge(wordCount2.join(), wordCount1.join());
        }
    }

    /**
     * 合并单词统计量
     *
     * @param wd1 统计1
     * @param wd2 统计2
     * @return 统计合并
     */
    private Map<String, Integer> merge(Map<String, Integer> wd1,
                                       Map<String, Integer> wd2) {
        // 新建结果集
        // 先将wd1全部导入到result中
        final Map<String, Integer> result = new HashMap<>(wd1);

        // 将wd2中统计的数据合并到result中
        wd2.forEach((k, v) -> {
            final Integer wc = result.get(k);
            if (Objects.isNull(wc)) {
                result.put(k, v);
            } else {
                result.put(k,wc+v);
            }
        });

        return result;
    }

    /**
     * 计算文本中的单词数
     *
     * @param text 文本
     * @return 单词 {@code key}  数量 {@code value}
     */
    private Map<String, Integer> calc(String text) {
        // 定义返回结果字典
        final Map<String, Integer> resultMap = new HashMap<>();
        if (Objects.isNull(text)) {
            return resultMap;
        }
        // 分割文本
        final String[] words = text.split("\\s+");
        // 进行单词统计
        for (String word : words) {
            final Integer count = resultMap.get(word);
            if (Objects.isNull(count)) {
                resultMap.put(word, 1);
            } else {
                resultMap.put(word, count + 1);
            }
        }
        // 返回单词统计结果
        return resultMap;
    }

}

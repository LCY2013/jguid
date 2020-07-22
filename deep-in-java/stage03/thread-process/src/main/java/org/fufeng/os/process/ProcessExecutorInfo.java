/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-22
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.os.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @program: jguid
 * @description: 进程执行
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-22
 */
public class ProcessExecutorInfo {

    // 等待其他进程执行的时间
    private static final long waitForTimeInSecond =
            Long.getLong("process.executor.wait.for",1);
    // 执行指令
    private final String command;
    // 指令参数
    private final String arguments;
    // 运行时
    private final Runtime runtime = Runtime.getRuntime();
    // 完成状态
    private boolean finished;

    /**
     *  构造进程执行器
     * @param command 执行指令
     * @param arguments 指令参数
     */
    public ProcessExecutorInfo(String command, String... arguments) {
        // 组装命令参数
        StringBuilder sb = new StringBuilder();
        if(Objects.nonNull(arguments)){
            for (String arg : arguments) {
                sb.append(" ").append(arg);
            }
        }
        // 参数赋值
        this.arguments = sb.toString();
        this.command = command + this.arguments;
    }

    /**
     *  执行进程命令
     * @param outputStream 输出流
     * @throws IOException IO异常
     */
    public void execute(OutputStream outputStream) throws IOException {
        try {
            this.execute(outputStream,Long.MAX_VALUE);
        } catch (TimeoutException e) {

        }
    }

    /**
     *  具体执行进程实现
     * @param outputStream 输出流定义
     * @param timeOutInMilliSecond 超时时间设置(毫秒)
     * @throws IOException IO流异常
     * @throws TimeoutException 超时异常
     */
    private void execute(OutputStream outputStream, long timeOutInMilliSecond) throws IOException, TimeoutException {
        final Process process = this.runtime.exec(command);
        final long startTime = System.currentTimeMillis();
        long endTime = -1L;
        // 获取进程输入流
        final InputStream processInputStream = process.getInputStream();
        // 获取进程输出流
        final InputStream processErrorStream = process.getErrorStream();
        //final OutputStream processOutputStream = process.getOutputStream();
        int exitValue = -1;
        while (!isFinished()){
            long costTime = endTime - startTime;
            // 如果结束时间以及大于了超时时间
            if (costTime > timeOutInMilliSecond){
                finished = true;
                // 销毁进程
                process.destroy();
                // 定义返回消息
                String message = String.format("Execution is timeout [%d ms]!",timeOutInMilliSecond);
                throw new TimeoutException(message);
            }
            try {
                while (processInputStream.available() > 0){
                    outputStream.write(processInputStream.read());
                }
                while (processErrorStream.available() > 0){
                    outputStream.write(processErrorStream.read());
                }
                // 获取进程执行后的返回状态值
                exitValue = process.exitValue();
                // 进程非正常退出
                if (exitValue != 0){
                    // 抛出一个IO异常
                    throw new IOException();
                }
                // 设置完成状态
                finished = true;
            }catch (IllegalThreadStateException exception){
                // Process is not finished yet
                // Sleep a little to save on CPU cycles
                waitFor(waitForTimeInSecond);
                endTime = System.currentTimeMillis();
            }
        }
    }

    /**
     *  做一个sleep的等待休眠
     * @param waitForTimeInSecond 休眠时间(秒)
     */
    private void waitFor(long waitForTimeInSecond) {
        try {
            TimeUnit.SECONDS.sleep(waitForTimeInSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *  返回进程是否标示
     * @return 是否以及完成
     */
    public boolean isFinished() {
        return finished;
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        // 定义执行指令操作
        ProcessExecutorInfo processExecutorInfo =
                new ProcessExecutorInfo("java","-version");
        processExecutorInfo.execute(System.out,2000);
    }

}

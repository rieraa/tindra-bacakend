package com.tindra.judgeservice.judge.sandbox;


import com.tindra.judgeservice.judge.sandbox.impl.RemoteSandBox;
import com.tindra.judgeservice.judge.sandbox.impl.TestSandBox;

/**
 * 代码沙箱工厂（根据字符串参数创建指定的代码沙箱实例）
 */
public class SandboxFactory {

    /**
     * 创建代码沙箱示例
     *
     * @param type 沙箱类型
     * @return SandBox
     */
    public static SandBox sandBoxInstance(String type) {
        switch (type) {
            case "remote":
                return new RemoteSandBox();
            case "test":
            default:
                return new TestSandBox();
        }
    }

}

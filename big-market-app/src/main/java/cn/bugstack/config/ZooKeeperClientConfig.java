package cn.bugstack.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ZookeeperClientConfigProperties.class)
public class ZooKeeperClientConfig {

    /**
     * 多参数构建ZooKeeper客户端连接
     *
     * @return client
     */
    @Bean(name = "zookeeperClient") // 将该方法返回的对象注册为一个 Spring Bean，并命名为 "zookeeperClient"
    public CuratorFramework createWithOptions(ZookeeperClientConfigProperties properties) {
        // 创建一个指数退避重试策略，用于处理 Zookeeper 连接失败时的重试逻辑
        // properties.getBaseSleepTimeMs()：初始重试等待时间（毫秒）
        // properties.getMaxRetries()：最大重试次数
        ExponentialBackoffRetry backoffRetry = new ExponentialBackoffRetry(
                properties.getBaseSleepTimeMs(),
                properties.getMaxRetries()
        );

        // 使用 CuratorFrameworkFactory 构建一个 Zookeeper 客户端实例
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(properties.getConnectString()) // 设置 Zookeeper 服务器的连接地址
                .retryPolicy(backoffRetry) // 设置重试策略
                .sessionTimeoutMs(properties.getSessionTimeoutMs()) // 设置会话超时时间（毫秒）
                .connectionTimeoutMs(properties.getConnectionTimeoutMs()) // 设置连接超时时间（毫秒）
                .build(); // 构建 CuratorFramework 实例

        client.start(); // 启动 Zookeeper 客户端
        return client; // 返回客户端实例，Spring 会将其注册为 Bean
    }

}

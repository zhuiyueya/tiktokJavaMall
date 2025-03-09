package com.chasemoon.gomall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: chasemoon
 * @CreateTime: 2025-02-24
 * @Description:让我解释一下CORS（跨域资源共享）的工作机制：
 *
 * 1. **源（Origin）的概念**：
 *    - 源是由协议（http/https）、域名（或IP）和端口组成的
 *    - 比如 `http://8.148.21.215:3000` 就是一个完整的源
 *
 * 2. **CORS的工作流程**：
 *    - 当浏览器发送跨域请求时，会自动在请求头中添加 `Origin` 字段，表明请求来自哪个源
 *    - 比如，当用户在浏览器访问 `http://8.148.21.215:3000` 的网页，这个网页向 `http://8.148.21.215:8080` 发送请求时：
 *      ```
 *      Origin: http://8.148.21.215:3000
 *      ```
 *    - 服务器会检查这个 `Origin` 是否在允许列表中
 *    - 如果允许，则在响应头中添加 `Access-Control-Allow-Origin` 字段
 *
 * 3. **为什么配置前端服务器的IP而不是浏览器IP**：
 *    - CORS的本质是控制**哪些网页源**可以访问你的API，而不是控制哪些IP地址可以访问
 *    - 浏览器的IP是不相关的，重要的是网页的来源
 *    - 举个例子：
 *      - 用户A在北京（IP: 1.1.1.1）访问你的前端网页 `http://8.148.21.215:3000`
 *      - 用户B在上海（IP: 2.2.2.2）访问同样的前端网页
 *      - 这两个用户发起的API请求，`Origin` 都是 `http://8.148.21.215:3000`
 *      - 所以我们在后端配置允许 `http://8.148.21.215:3000` 这个源
 *
 * 4. **安全性考虑**：
 *    - CORS是一个安全机制，目的是防止恶意网站通过用户的浏览器访问其他网站的敏感数据
 *    - 通过限制允许的源，我们可以确保只有我们信任的前端应用才能访问API
 *    - 如果我们配置浏览器IP，不仅技术上不可行，而且会完全破坏CORS的安全机制
 *
 * 简单来说：
 * ```
 * 浏览器（任意IP）--> 前端服务器（8.148.21.215:3000）--> 后端API（8.148.21.215:8080）
 * ```
 * CORS控制的是"前端服务器"到"后端API"这一段，而不是"浏览器"到"后端API"这一段。
 *
 * 这就是为什么我们在CORS配置中指定前端服务器的地址（`http://8.148.21.215:3000`），而不是用户浏览器的IP地址。
 *
 *
 * 这是一个很好的安全相关问题！让我详细解释：
 *
 * 1. **关于修改Origin**：
 * - 浏览器强制执行CORS策略，Origin请求头是由浏览器自动添加的，而且是不可由JavaScript代码修改的
 * - 即使使用自定义的HTTP客户端（如Postman、curl等）可以伪造Origin，但这已经跳出了CORS的范畴
 * - CORS的目的是防止恶意网站通过**用户的浏览器**访问其他网站的敏感数据，而不是防止直接的服务器访问
 *
 * 2. **为什么这很重要**：
 * ```
 * 场景举例：
 * 用户已登录银行网站(bank.com)，浏览器中存有bank.com的Cookie
 * 恶意网站(evil.com)尝试通过用户的浏览器发送请求到bank.com
 *
 * 如果没有CORS：
 * evil.com的JavaScript -> 用户浏览器 -> bank.com（带着用户的Cookie）
 * 这样恶意网站就可以利用用户的身份信息进行操作
 *
 * 有了CORS：
 * 1. evil.com的JavaScript发起请求
 * 2. 浏览器自动添加Origin: evil.com
 * 3. bank.com看到未授权的Origin，拒绝请求
 * 4. 浏览器强制阻止evil.com获取响应
 * ```
 *
 * 3. **关于直接访问后端API**：
 * - 是的，攻击者确实可以：
 *   1. 爬取前端页面
 *   2. 分析API接口
 *   3. 直接调用后端API
 * - 但这就是为什么我们需要其他的安全措施：
 *   ```java
 *   // 例如在SecurityConfig中的配置
 *   .authorizeHttpRequests(auth -> auth
 *       .requestMatchers("/user/login", "/user/register","/product/list").permitAll()  // 公开接口
 *       .anyRequest().authenticated()  // 其他接口需要认证
 *   )
 *   ```
 * - 常见的API保护措施包括：
 *   1. 身份认证（JWT、Session等）
 *   2. 请求频率限制（Rate Limiting）
 *   3. API密钥
 *   4. OAuth2授权
 *   5. 防火墙规则
 *
 * 4. **安全的多层防御**：
 * ```
 * 前端安全：
 * - CORS（防止跨站请求）
 * - CSP（内容安全策略）
 * - SameSite Cookie属性
 *
 * API安全：
 * - 身份认证
 * - 授权控制
 * - 输入验证
 * - 请求限制
 *
 * 传输安全：
 * - HTTPS
 * - 证书验证
 * - 数据加密
 *
 * 服务器安全：
 * - 防火墙
 * - 漏洞修复
 * - 安全监控
 * ```
 *
 * 5. **最佳实践建议**：
 * ```java
 * // 1. 使用环境变量配置允许的源
 * @Value("${cors.allowed-origins}")
 * private String[] allowedOrigins;
 *
 * // 2. 实施严格的CORS策略
 * @Override
 * public void addCorsMappings(CorsRegistry registry) {
 *     registry.addMapping("/api/**")
 *            .allowedOrigins(allowedOrigins)
 *            .allowedMethods("GET", "POST")  // 明确指定允许的方法
 *            .allowedHeaders("Authorization", "Content-Type")  // 明确指定允许的头
 *            .exposedHeaders("Authorization")
 *            .allowCredentials(true)
 *            .maxAge(3600);  // 缓存预检请求结果
 * }
 *
 * // 3. 对敏感操作使用CSRF令牌
 * .csrf(csrf -> csrf
 *     .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
 *     .ignoringRequestMatchers("/public/**")
 * )
 * ```
 *
 * 总结：
 * - CORS是浏览器的一个安全特性，它只能防止恶意网站通过用户的浏览器发起未经授权的跨域请求
 * - CORS不能，也不打算防止直接的服务器到服务器的请求
 * - 完整的API安全需要多层防御机制
 * - CORS应该是整体安全策略中的一部分，而不是唯一的安全措施
 *
 * 因此，在实际应用中，我们应该：
 * 1. 正确配置CORS以防止XSS攻击
 * 2. 实施完善的认证和授权机制
 * 3. 使用HTTPS确保传输安全
 * 4. 实施API访问控制和速率限制
 * 5. 定期进行安全审计和更新
 *
 * 让我详细解释浏览器如何处理Origin：
 *
 * 1. **Origin的获取来源**：
 * ```
 * Origin = protocol + host + port
 *
 * 例如：当你在浏览器访问 http://example.com:8080/page.html 时
 * - protocol = "http"
 * - host = "example.com"
 * - port = "8080"
 * 最终 Origin = "http://example.com:8080"
 * ```
 *
 * 2. **浏览器如何确定Origin**：
 * - Origin是基于**发起请求的文档**的URL来确定的
 * - 具体来说：
 * ```javascript
 * // 假设你在 http://example.com:8080/page.html 这个页面上
 * // 当执行以下代码时：
 * fetch('http://api.other-domain.com/data')
 *
 * // 浏览器会：
 * // 1. 检查当前文档的URL（document.location）
 * // 2. 提取出协议、主机名和端口
 * // 3. 自动在请求头中添加：
 * // Origin: http://example.com:8080
 * ```
 *
 * 3. **不同场景下的Origin处理**：
 * ```javascript
 * // 场景1：普通页面中的AJAX请求
 * // 页面URL: http://example.com/page.html
 * fetch('http://api.com/data')
 * // Origin: http://example.com
 *
 * // 场景2：iframe中的请求
 * // iframe的src="http://sub.example.com/frame.html"
 * fetch('http://api.com/data')
 * // Origin: http://sub.example.com
 *
 * // 场景3：通过file://协议打开的本地HTML文件
 * fetch('http://api.com/data')
 * // Origin: null
 *
 * // 场景4：浏览器扩展（Chrome Extension）
 * // Origin: chrome-extension://[扩展ID]
 * ```
 *
 * 4. **Origin的不可伪造性**：
 * ```javascript
 * // 尝试在JavaScript中修改Origin（这是不可能的）
 * let headers = new Headers();
 * headers.append('Origin', 'http://fake-origin.com');  // 这个设置会被浏览器忽略
 *
 * // 即使使用自定义请求头也无法修改
 * fetch('http://api.com/data', {
 *     headers: {
 *         'Origin': 'http://fake-origin.com'  // 浏览器会忽略这个，使用真实的Origin
 *     }
 * });
 * ```
 *
 * 5. **浏览器的具体实现**：
 * ```cpp
 * // 这是一个简化的伪代码示例，展示浏览器如何处理Origin
 * class Browser {
 *     void addOriginHeader(Request& request) {
 *         // 获取当前文档URL
 *         Document* document = getCurrentDocument();
 *         URL documentUrl = document->getURL();
 *
 *         // 构建Origin
 *         String origin = documentUrl.protocol + "://" +
 *                        documentUrl.host +
 *                        (documentUrl.port ? ":" + documentUrl.port : "");
 *
 *         // 添加到请求头（这个操作发生在浏览器底层，JavaScript无法干预）
 *         request.headers.set("Origin", origin);
 *     }
 *
 *     void sendRequest(Request& request) {
 *         // 在发送请求前添加Origin
 *         addOriginHeader(request);
 *
 *         // 发送请求
 *         network.send(request);
 *     }
 * }
 * ```
 *
 * 6. **特殊情况**：
 * ```javascript
 * // 1. 同源请求
 * // 虽然也会带Origin，但服务器通常不会检查
 *
 * // 2. 预检请求（OPTIONS）
 * // 复杂请求会先发送OPTIONS请求
 * fetch('http://api.com/data', {
 *     method: 'PUT',
 *     headers: {
 *         'Content-Type': 'application/json'
 *     }
 * });
 * // 浏览器会自动发送OPTIONS请求，带上：
 * // Origin: [当前页面的origin]
 * // Access-Control-Request-Method: PUT
 * // Access-Control-Request-Headers: content-type
 *
 * // 3. 重定向
 * // 浏览器会保持原始请求的Origin
 * ```
 *
 * 7. **为什么这样设计是安全的**：
 * - Origin是在浏览器内核（如Chromium、WebKit）级别实现的
 * - 这部分代码运行在浏览器的特权级别
 * - JavaScript运行在沙箱环境中，无法修改这些底层行为
 * - 即使是浏览器扩展，也只能在特定的权限范围内操作
 *
 * 这就是为什么CORS是一个有效的安全机制：
 * 1. Origin的值由浏览器控制，不受网页JavaScript的影响
 * 2. 浏览器强制执行CORS策略，不允许未经授权的跨域访问
 * 3. 这个机制是在浏览器底层实现的，不能被绕过（除非不使用浏览器）
 *
 *
 * @Version:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}

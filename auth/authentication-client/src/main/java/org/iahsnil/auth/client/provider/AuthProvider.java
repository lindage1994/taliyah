package org.iahsnil.auth.client.provider;

import org.iahsnil.common.response.ResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "authentication-server", fallback = AuthProvider.AuthProviderFallback.class)
public interface AuthProvider {
    /**
     * 调用签权服务，判断用户是否有权限
     *
     * @param authentication header token
     * @param url uri
     * @param method method
     * @return <pre>
     * Result:
     * {
     *   code:"000000"
     *   message:"请求成功"
     *   data: true/false
     * }
     * </pre>
     */
    @PostMapping(value = "/auth/permission")
    ResponseBean<?> auth(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication, @RequestParam("url") String url, @RequestParam("method") String method);

    @Component
    class AuthProviderFallback implements AuthProvider {
        /**
         * 降级统一返回无权限
         *
         * @param authentication header token
         * @param url uri
         * @param method method
         * @return <pre>
         * Result:
         * {
         *   code:"-1"
         *   mesg:"系统异常"
         * }
         * </pre>
         */
        @Override
        public ResponseBean<?> auth(String authentication, String url, String method) {
            return ResponseBean.fail();
        }
    }
}

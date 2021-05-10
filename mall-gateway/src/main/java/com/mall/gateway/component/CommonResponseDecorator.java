package com.mall.gateway.component;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.mall.common.advice.response.UnityResult;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * @author 谢成伟
 * Date:2021/4/15
 * Time:14:37
 * @ action  标准化返回结果处理
 */
public class CommonResponseDecorator extends ServerHttpResponseDecorator {

    private DataBufferFactory bufferFactory ;

    public CommonResponseDecorator(ServerHttpResponse serverHttpResponse) {
        super(serverHttpResponse);
        this.bufferFactory = serverHttpResponse.bufferFactory();
    }

    @Override
    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
        Flux<? extends DataBuffer> fluxBody = Flux.from(body);
        if (body instanceof Flux) {
            return super.writeWith(fluxBody.buffer().map(dataBuffer -> {
                DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                DataBuffer join = dataBufferFactory.join(dataBuffer);
                byte[] content = new byte[join.readableByteCount()];

                join.read(content);
                DataBufferUtils.release(join);
                String responseData = new String(content, Charset.forName("UTF-8"));
                UnityResult unityResult =null;
                try {
                    unityResult= UnityResult.success(JSONObject.parseObject(responseData));
                }catch (Exception e){
                    unityResult= UnityResult.success(JSONObject.parseArray(responseData));
                }

                byte[] newRs =JSONObject.toJSONString(unityResult).getBytes(Charset.forName("UTF-8"));
                //如果不重新设置长度则收不到消息。
                this.getDelegate().getHeaders().setContentLength(newRs.length);
                return bufferFactory.wrap(newRs);
            }));
        }
        return super.writeWith(body);

    }
}


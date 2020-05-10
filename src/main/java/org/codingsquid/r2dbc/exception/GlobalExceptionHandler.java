package org.codingsquid.r2dbc.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codingsquid.r2dbc.exception.model.ErrorModel;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author hubert.squid
 * @since 2020.05.10
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable throwable) {
        log.error(throwable.getMessage(), throwable);
        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
        try {
            if (throwable instanceof NotFoundEntityException) {
                exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
                DataBuffer dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(ErrorModel.from((NotFoundEntityException) throwable)));
                exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
                return exchange.getResponse().writeWith(Mono.just(dataBuffer));
            }

            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            DataBuffer dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(ErrorModel.unknown()));
            return exchange.getResponse().writeWith(Mono.just(dataBuffer));
        } catch (JsonProcessingException exception) {
            return exchange.getResponse().writeWith(Mono.just(bufferFactory.wrap("".getBytes())));
        }
    }
}

package com.guciowons.yummify.common.ws.infrstructure.framework;

import com.guciowons.yummify.common.security.domain.AccessDeniedException;
import com.guciowons.yummify.common.security.domain.UnauthorizedException;
import com.guciowons.yummify.common.security.infractructure.framework.UserPrincipalJwtConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketAuthInterceptor implements ChannelInterceptor {
    private final JwtDecoder jwtDecoder;
    private final UserPrincipalJwtConverter jwtConverter;
    private final List<WebSocketSubscriptionAuthorizer> subscriptionAuthorizers;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            authenticate(accessor);
        } else if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
            authorizeSubscription(accessor);
        }

        log.info(
                "command={}, sessionId={}, user={}",
                accessor.getCommand(),
                accessor.getSessionId(),
                accessor.getUser()
        );

        return message;
    }

    private void authenticate(StompHeaderAccessor accessor) {
        String authorization = accessor.getFirstNativeHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new UnauthorizedException();
        }

        String token = authorization.substring(7);
        Jwt jwt = jwtDecoder.decode(token);
        Authentication authentication = jwtConverter.convert(jwt);

        accessor.setUser(authentication);
        accessor.setUserChangeCallback(user -> {});
    }

    private void authorizeSubscription(StompHeaderAccessor accessor) {
        String destination = accessor.getDestination();

        Authentication authentication = (Authentication) accessor.getUser();

        WebSocketSubscriptionAuthorizer authorizer = subscriptionAuthorizers.stream()
                .filter(a -> a.supports(destination))
                .findFirst()
                .orElseThrow(AccessDeniedException::new);

        if (!authorizer.isAllowed(destination, authentication)) {
            throw new AccessDeniedException();
        }
    }
}

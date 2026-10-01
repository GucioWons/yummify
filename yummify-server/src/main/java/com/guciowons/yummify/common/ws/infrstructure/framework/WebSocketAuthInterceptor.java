package com.guciowons.yummify.common.ws.infrstructure.framework;

import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.infractructure.framework.UserPrincipalJwtConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketAuthInterceptor implements ChannelInterceptor {
    private final JwtDecoder jwtDecoder;
    private final UserPrincipalJwtConverter jwtConverter;
    private final WebSocketSessionRegistry sessionRegistry;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            authenticate(accessor);
        } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
            unregister(accessor);
        }

        return message;
    }

    private void authenticate(StompHeaderAccessor accessor) {
        String authorization = accessor.getFirstNativeHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new BadCredentialsException("Missing JWT");
        }

        String token = authorization.substring(7);
        Jwt jwt = jwtDecoder.decode(token);
        Authentication authentication = jwtConverter.convert(jwt);

        accessor.setUser(authentication);

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        sessionRegistry.register(
                principal.restaurantId(),
                accessor.getSessionId()
        );
    }

    private void unregister(StompHeaderAccessor accessor) {
        Authentication authentication = (Authentication) accessor.getUser();

        if (authentication == null) {
            return;
        }

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        sessionRegistry.unregister(principal.restaurantId(), accessor.getSessionId());
    }
}

package com.guciowons.yummify.common.request;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.security.logic.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class RequestContextInterceptor implements HandlerInterceptor {
    private static final String LANGUAGE_HEADER = "X-Language";
    private static final String DEFAULT_LANGUAGE_HEADER = "X-Default-Language";

    private final TokenService tokenService;

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) {
        UserDTO user = tokenService.getUser();
        Language language = getLanguage(request, LANGUAGE_HEADER);
        Language defaultLanguage = getLanguage(request, DEFAULT_LANGUAGE_HEADER);

        RequestContext.set(new RequestContext(user, language, defaultLanguage));
        return true;
    }

    @Override
    public void afterCompletion(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            Exception ex
    ) {
        RequestContext.clear();
    }

    private Language getLanguage(HttpServletRequest request, String header) {
        String language = request.getHeader(header);
        return Language.valueOf(language);
    }
}

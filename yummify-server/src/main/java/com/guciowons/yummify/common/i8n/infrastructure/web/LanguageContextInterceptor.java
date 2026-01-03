package com.guciowons.yummify.common.i8n.infrastructure.web;

import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class LanguageContextInterceptor implements HandlerInterceptor {
    private static final String LANGUAGE_HEADER = "X-Language";
    private static final String DEFAULT_LANGUAGE_HEADER = "X-Default-Language";

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) {
        Language language = getLanguage(request, LANGUAGE_HEADER);
        Language defaultLanguage = getLanguage(request, DEFAULT_LANGUAGE_HEADER);

        LanguageContext.set(new LanguageContext(language, defaultLanguage));
        return true;
    }

    @Override
    public void afterCompletion(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            Exception ex
    ) {
        LanguageContext.clear();
    }

    private Language getLanguage(HttpServletRequest request, String header) {
        String language = request.getHeader(header);
        return Language.valueOf(language);
    }
}

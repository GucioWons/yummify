package com.guciowons.yummify.common.request;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.i8n.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestContext {
    private static final ThreadLocal<RequestContext> current = new ThreadLocal<>();

    private final UserDTO user;
    private final Language language;

    public static void set(RequestContext context) {
        current.set(context);
    }

    public static RequestContext get() {
        return current.get();
    }

    public static void clear() {
        current.remove();
    }
}

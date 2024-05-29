package com.kc.te.util;

import jakarta.servlet.http.*;
import org.springframework.security.web.util.*;
import org.springframework.web.util.*;

/**
 * @author Krishna Chaitanya
 */
public class RequestUtil {

    private RequestUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getContextPath(HttpServletRequest request) {
        return UriComponentsBuilder.fromHttpUrl(UrlUtils.buildFullRequestUrl(request))
                .replacePath(request.getContextPath())
                .replaceQuery(null)
                .fragment(null)
                .build()
                .toUriString();
    }

}

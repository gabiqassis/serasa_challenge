package dev.gabiqassis.serasa.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PathConstants {
    private static final String API = "/api";

    public static final String BASE_API_ORDER = "/api";
    public static final String ORDER_V1 = BASE_API_ORDER + "/v1/order";
    public static final String ORDER_BY_ID = ORDER_V1 + "/{id}";
    public static final String ORDER_BY_USER_ID = ORDER_V1 + "/user/{userId}";


}


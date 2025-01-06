package dev.gabiqassis.serasa.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PathConstants {
    private static final String API = "/api";

    public static final String USER_V1 = API + "/v1/user";
    public static final String USER_ID = USER_V1 + "/{id}";

}


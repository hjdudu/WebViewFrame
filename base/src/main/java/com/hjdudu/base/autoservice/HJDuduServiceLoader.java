package com.hjdudu.base.autoservice;

import java.util.ServiceLoader;

public class HJDuduServiceLoader {
    private HJDuduServiceLoader() {
    }

    public static <S> S load(Class<S> services) {
        try {

            ServiceLoader<S> load = ServiceLoader.load(services);
            return load.iterator().next();
        } catch (Exception e) {
            return null;
        }
    }
}

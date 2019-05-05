package com.ql.ddd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

public class IdCard extends Entity<IdCard.Context> {
    private IdCard(Context context) {
        super(context);
    }

    public void write() {
        ctx().demoService.demo();
    }

    @Lazy(false)
    @Component
    public static class Factory implements Entity.Factory {
        private static Context context = new Context();

        public static IdCard make() {
            return new IdCard(context);
        }

        @Autowired
        private void setCertService(DemoService demoService) {
            context.demoService = demoService;
        }
    }

    static class Context implements Entity.Context {

        private DemoService demoService;
    }

}

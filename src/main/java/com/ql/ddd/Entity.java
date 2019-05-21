package com.ql.ddd;

public abstract class Entity<C extends Entity.Context> {
    private transient C _ctx; // 上下文

    protected Entity(C context) {
        this._ctx = context;
    }

    protected C ctx() {
        return _ctx;
    }

    public interface Factory {
    }

    public interface Context {
    }
}

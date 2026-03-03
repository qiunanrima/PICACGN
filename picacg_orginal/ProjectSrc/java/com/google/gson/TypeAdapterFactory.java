package com.google.gson;

import com.google.gson.reflect.TypeToken;
/* loaded from: picacg_1.jar:com/google/gson/TypeAdapterFactory.class */
public interface TypeAdapterFactory {
    <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken);
}

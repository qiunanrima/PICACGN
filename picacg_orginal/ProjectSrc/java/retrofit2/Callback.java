package retrofit2;
/* loaded from: picacg_1.jar:retrofit2/Callback.class */
public interface Callback<T> {
    void onFailure(Call<T> call, Throwable th);

    void onResponse(Call<T> call, Response<T> response);
}

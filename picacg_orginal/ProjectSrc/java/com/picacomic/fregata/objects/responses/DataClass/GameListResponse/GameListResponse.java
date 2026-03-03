package com.picacomic.fregata.objects.responses.DataClass.GameListResponse;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/DataClass/GameListResponse/GameListResponse.class */
public class GameListResponse {
    GameListData games;

    public GameListResponse(GameListData gameListData) {
        this.games = gameListData;
    }

    public GameListData getGames() {
        return this.games;
    }

    public void setGames(GameListData gameListData) {
        this.games = gameListData;
    }

    public String toString() {
        return "GameListResponse{games=" + this.games + '}';
    }
}

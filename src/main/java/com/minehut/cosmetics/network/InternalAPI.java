package com.minehut.cosmetics.network;

import com.google.gson.Gson;
import com.minehut.cosmetics.config.Config;
import com.minehut.cosmetics.cosmetics.CosmeticCategory;
import com.minehut.cosmetics.model.PackInfo;
import com.minehut.cosmetics.model.profile.ConsumeResponse;
import com.minehut.cosmetics.model.profile.CosmeticProfileResponse;
import com.minehut.cosmetics.model.profile.SimpleResponse;
import com.minehut.cosmetics.model.rank.PlayerRank;
import kong.unirest.GenericType;
import kong.unirest.HttpMethod;
import kong.unirest.HttpResponse;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class InternalAPI extends CosmeticsAPI {

    public InternalAPI(Config config, Gson gson) {
        super(config, gson);
    }

    @Override
    public CompletableFuture<HttpResponse<PackInfo>> getPackInfo() {
        return requestType(HttpMethod.GET, "/v1/resourcepacks/info", PackInfo.class);
    }

    @Override
    public CompletableFuture<HttpResponse<CosmeticProfileResponse>> getProfile(UUID uuid) {
        return requestType(HttpMethod.GET, "/v1/cosmetics/profile/{uuid}", CosmeticProfileResponse.class, (req) ->
                req.routeParam("uuid", uuid.toString())
        );
    }

    @Override
    public CompletableFuture<HttpResponse<SimpleResponse>> equipCosmetic(UUID uuid, String category, String id) {
        return requestType(HttpMethod.POST, "/v1/cosmetics/equip/{uuid}/{category}/{id}", SimpleResponse.class, (req) ->
                req.routeParam("uuid", uuid.toString())
                        .routeParam("category", category)
                        .routeParam("id", id)
        );
    }

    @Override
    public CompletableFuture<HttpResponse<PlayerRank[]>> getRanks() {
        return requestType(HttpMethod.GET, "/v1/ranks", new GenericType<>() {});
    }

    @Override
    public CompletableFuture<HttpResponse<ConsumeResponse>> consumeCosmetic(UUID uuid, CosmeticCategory category, String id, int quantity) {
        return requestType(HttpMethod.POST, "/v1/cosmetics/consume/{uuid}/{category}/{id}", ConsumeResponse.class, req ->
                req.routeParam("uuid", uuid.toString())
                        .routeParam("category", category.name())
                        .routeParam("id", id)
                        .queryString("qty", quantity)
        );
    }
}

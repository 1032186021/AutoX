package com.saltfish.assistant.domain.entity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\bg\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u00f5\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0001\u0012\b\b\u0002\u0010\u0011\u001a\u00020\n\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0001\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010&J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\t\u0010R\u001a\u00020\nH\u00c6\u0003J\t\u0010S\u001a\u00020\u0007H\u00c6\u0003J\u0010\u0010T\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u0010\u0010U\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u0010\u0010V\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u000b\u0010W\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010X\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u0010\u0010Z\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\t\u0010[\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\\\u001a\u00020\u0007H\u00c6\u0003J\t\u0010]\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\t\u0010e\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010j\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0002\u0010@J\t\u0010k\u001a\u00020\nH\u00c6\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010n\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u00fe\u0002\u0010o\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0011\u001a\u00020\n2\b\b\u0002\u0010\u0012\u001a\u00020\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00072\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u00072\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010pJ\u0013\u0010q\u001a\u00020r2\b\u0010s\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010t\u001a\u00020\u0007H\u00d6\u0001J\t\u0010u\u001a\u00020\u0003H\u00d6\u0001R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010)\u001a\u0004\b\'\u0010(R\u0013\u0010$\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010)\u001a\u0004\b.\u0010(R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010-R\u0011\u0010\u0012\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010-R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010+R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010-R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010+R\u0011\u0010\u0011\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010-R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010+R\u0013\u0010%\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010-R\u0013\u0010!\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010-R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010-R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010-R\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010)\u001a\u0004\b>\u0010(R\u0015\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\n\n\u0002\u0010A\u001a\u0004\b?\u0010@R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010-R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u0010-R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u00107R\u0011\u0010\u0019\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\bE\u00101R\u0013\u0010#\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010+R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u00101R\u0013\u0010\"\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u0010+R\u0011\u0010\u0017\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\bI\u00101R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bJ\u0010-R\u0013\u0010 \u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\bK\u0010+R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010)\u001a\u0004\bL\u0010(R\u0011\u0010\u001a\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\bM\u00101R\u0011\u0010\u001b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\bN\u00101\u00a8\u0006v"}, d2 = {"Lcom/saltfish/assistant/domain/entity/SaltfishGoodsEntity;", "", "id", "", "categoryId", "", "status", "", "categoryName", "purchasePrice", "", "sellingPrice", "name", "description", "brandName", "images", "deliveryMethod", "freight", "coinDeduction", "userId", "accountId", "productId", "platform", "type", "displayAttributes", "showCount", "viewCount", "wantCount", "remark", "pushTime", "createTime", "updateTime", "updatedColumn", "modelName", "tags", "skus", "attributes", "md5Name", "(Ljava/lang/String;Ljava/lang/Long;ILjava/lang/String;Ljava/lang/Double;DLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;DILjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;ILjava/lang/Object;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;)V", "getAccountId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getAttributes", "()Ljava/lang/Object;", "getBrandName", "()Ljava/lang/String;", "getCategoryId", "getCategoryName", "getCoinDeduction", "()I", "getCreateTime", "getDeliveryMethod", "getDescription", "getDisplayAttributes", "getFreight", "()D", "getId", "getImages", "getMd5Name", "getModelName", "getName", "getPlatform", "getProductId", "getPurchasePrice", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getPushTime", "getRemark", "getSellingPrice", "getShowCount", "getSkus", "getStatus", "getTags", "getType", "getUpdateTime", "getUpdatedColumn", "getUserId", "getViewCount", "getWantCount", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/Long;ILjava/lang/String;Ljava/lang/Double;DLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;DILjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;ILjava/lang/Object;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;)Lcom/saltfish/assistant/domain/entity/SaltfishGoodsEntity;", "equals", "", "other", "hashCode", "toString", "saltfish_debug"})
public final class SaltfishGoodsEntity {
    @org.jetbrains.annotations.Nullable
    private final java.lang.String id = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long categoryId = null;
    private final int status = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String categoryName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Double purchasePrice = null;
    private final double sellingPrice = 0.0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String name = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String description = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String brandName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object images = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object deliveryMethod = null;
    private final double freight = 0.0;
    private final int coinDeduction = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long userId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long accountId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long productId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String platform = null;
    private final int type = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object displayAttributes = null;
    private final int showCount = 0;
    private final int viewCount = 0;
    private final int wantCount = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String remark = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String pushTime = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String createTime = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String updateTime = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object updatedColumn = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String modelName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object tags = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object skus = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object attributes = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String md5Name = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.domain.entity.SaltfishGoodsEntity copy(@org.jetbrains.annotations.Nullable
    java.lang.String id, @org.jetbrains.annotations.Nullable
    java.lang.Long categoryId, int status, @org.jetbrains.annotations.Nullable
    java.lang.String categoryName, @org.jetbrains.annotations.Nullable
    java.lang.Double purchasePrice, double sellingPrice, @org.jetbrains.annotations.Nullable
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.Nullable
    java.lang.String brandName, @org.jetbrains.annotations.Nullable
    java.lang.Object images, @org.jetbrains.annotations.Nullable
    java.lang.Object deliveryMethod, double freight, int coinDeduction, @org.jetbrains.annotations.Nullable
    java.lang.Long userId, @org.jetbrains.annotations.Nullable
    java.lang.Long accountId, @org.jetbrains.annotations.Nullable
    java.lang.Long productId, @org.jetbrains.annotations.Nullable
    java.lang.String platform, int type, @org.jetbrains.annotations.Nullable
    java.lang.Object displayAttributes, int showCount, int viewCount, int wantCount, @org.jetbrains.annotations.Nullable
    java.lang.String remark, @org.jetbrains.annotations.Nullable
    java.lang.String pushTime, @org.jetbrains.annotations.Nullable
    java.lang.String createTime, @org.jetbrains.annotations.Nullable
    java.lang.String updateTime, @org.jetbrains.annotations.Nullable
    java.lang.Object updatedColumn, @org.jetbrains.annotations.Nullable
    java.lang.String modelName, @org.jetbrains.annotations.Nullable
    java.lang.Object tags, @org.jetbrains.annotations.Nullable
    java.lang.Object skus, @org.jetbrains.annotations.Nullable
    java.lang.Object attributes, @org.jetbrains.annotations.Nullable
    java.lang.String md5Name) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public SaltfishGoodsEntity() {
        super();
    }
    
    public SaltfishGoodsEntity(@org.jetbrains.annotations.Nullable
    java.lang.String id, @org.jetbrains.annotations.Nullable
    java.lang.Long categoryId, int status, @org.jetbrains.annotations.Nullable
    java.lang.String categoryName, @org.jetbrains.annotations.Nullable
    java.lang.Double purchasePrice, double sellingPrice, @org.jetbrains.annotations.Nullable
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.Nullable
    java.lang.String brandName, @org.jetbrains.annotations.Nullable
    java.lang.Object images, @org.jetbrains.annotations.Nullable
    java.lang.Object deliveryMethod, double freight, int coinDeduction, @org.jetbrains.annotations.Nullable
    java.lang.Long userId, @org.jetbrains.annotations.Nullable
    java.lang.Long accountId, @org.jetbrains.annotations.Nullable
    java.lang.Long productId, @org.jetbrains.annotations.Nullable
    java.lang.String platform, int type, @org.jetbrains.annotations.Nullable
    java.lang.Object displayAttributes, int showCount, int viewCount, int wantCount, @org.jetbrains.annotations.Nullable
    java.lang.String remark, @org.jetbrains.annotations.Nullable
    java.lang.String pushTime, @org.jetbrains.annotations.Nullable
    java.lang.String createTime, @org.jetbrains.annotations.Nullable
    java.lang.String updateTime, @org.jetbrains.annotations.Nullable
    java.lang.Object updatedColumn, @org.jetbrains.annotations.Nullable
    java.lang.String modelName, @org.jetbrains.annotations.Nullable
    java.lang.Object tags, @org.jetbrains.annotations.Nullable
    java.lang.Object skus, @org.jetbrains.annotations.Nullable
    java.lang.Object attributes, @org.jetbrains.annotations.Nullable
    java.lang.String md5Name) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getCategoryId() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int getStatus() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCategoryName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getPurchasePrice() {
        return null;
    }
    
    public final double component6() {
        return 0.0;
    }
    
    public final double getSellingPrice() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBrandName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getImages() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getDeliveryMethod() {
        return null;
    }
    
    public final double component12() {
        return 0.0;
    }
    
    public final double getFreight() {
        return 0.0;
    }
    
    public final int component13() {
        return 0;
    }
    
    public final int getCoinDeduction() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getUserId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getAccountId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component16() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getProductId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPlatform() {
        return null;
    }
    
    public final int component18() {
        return 0;
    }
    
    public final int getType() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component19() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getDisplayAttributes() {
        return null;
    }
    
    public final int component20() {
        return 0;
    }
    
    public final int getShowCount() {
        return 0;
    }
    
    public final int component21() {
        return 0;
    }
    
    public final int getViewCount() {
        return 0;
    }
    
    public final int component22() {
        return 0;
    }
    
    public final int getWantCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component23() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getRemark() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component24() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPushTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component25() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCreateTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component26() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUpdateTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component27() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getUpdatedColumn() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component28() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getModelName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component29() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTags() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component30() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getSkus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component31() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getAttributes() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component32() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getMd5Name() {
        return null;
    }
}
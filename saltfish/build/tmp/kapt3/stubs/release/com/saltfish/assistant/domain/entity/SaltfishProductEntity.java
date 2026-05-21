package com.saltfish.assistant.domain.entity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\bh\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u00a7\u0003\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0015\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010#\u001a\u00020\u0015\u0012\b\b\u0002\u0010$\u001a\u00020\u0015\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\'\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\u0002\u0010*J\t\u0010W\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u0010\u0010[\u001a\u0004\u0018\u00010\u0011H\u00c6\u0003\u00a2\u0006\u0002\u0010DJ\t\u0010\\\u001a\u00020\u0011H\u00c6\u0003J\t\u0010]\u001a\u00020\u0011H\u00c6\u0003J\t\u0010^\u001a\u00020\u0015H\u00c6\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010a\u001a\u00020\u0015H\u00c6\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010c\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u00100J\u000b\u0010d\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u0010\u0010g\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u00100J\u000b\u0010h\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010n\u001a\u00020\u0015H\u00c6\u0003J\t\u0010o\u001a\u00020\u0015H\u00c6\u0003J\u000b\u0010p\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010q\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010r\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010s\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010t\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010u\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010v\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010w\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010x\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010y\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010z\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u00b0\u0003\u0010{\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00152\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010#\u001a\u00020\u00152\b\b\u0002\u0010$\u001a\u00020\u00152\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\'\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0001H\u00c6\u0001\u00a2\u0006\u0002\u0010|J\u0013\u0010}\u001a\u00020~2\b\u0010\u007f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\n\u0010\u0080\u0001\u001a\u00020\u0015H\u00d6\u0001J\n\u0010\u0081\u0001\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010)\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0015\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u00101\u001a\u0004\b/\u00100R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010,R\u0011\u0010\u0013\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010.R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010,R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010,R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010,R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010,R\u0011\u0010#\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010<R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010,R\u0013\u0010\'\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010.R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010.R\u0011\u0010\u0018\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010<R\u0013\u0010&\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010.R\u0013\u0010%\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010.R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\n\n\u0002\u0010E\u001a\u0004\bC\u0010DR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010,R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u0010.R\u0011\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u00104R\u0013\u0010(\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\bI\u0010,R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bJ\u0010.R\u0013\u0010!\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bK\u0010.R\u0013\u0010\"\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bL\u0010.R\u0011\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\bM\u0010<R\u0013\u0010 \u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bN\u0010.R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bO\u0010.R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bP\u0010.R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\bQ\u0010,R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bR\u0010.R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bS\u0010.R\u0015\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u00101\u001a\u0004\bT\u00100R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\bU\u0010,R\u0011\u0010$\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\bV\u0010<\u00a8\u0006\u0082\u0001"}, d2 = {"Lcom/saltfish/assistant/domain/entity/SaltfishProductEntity;", "", "id", "", "createTime", "", "updateTime", "name", "description", "images", "videos", "realshotImages", "mainImages", "detailImages", "comments", "tags", "purchasePrice", "", "sellingPrice", "commission", "stock", "", "url", "remark", "newLevel", "categoryId", "brandName", "sourceCategoryName", "displayAttributes", "userId", "supplierId", "supplierName", "supplierAvatar", "sourceId", "sourcePlatform", "isShare", "wantCount", "originalName", "originalDescription", "modelName", "skus", "attributes", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Double;DDILjava/lang/String;Ljava/lang/String;ILjava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V", "getAttributes", "()Ljava/lang/Object;", "getBrandName", "()Ljava/lang/String;", "getCategoryId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getComments", "getCommission", "()D", "getCreateTime", "getDescription", "getDetailImages", "getDisplayAttributes", "getId", "()J", "getImages", "()I", "getMainImages", "getModelName", "getName", "getNewLevel", "getOriginalDescription", "getOriginalName", "getPurchasePrice", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getRealshotImages", "getRemark", "getSellingPrice", "getSkus", "getSourceCategoryName", "getSourceId", "getSourcePlatform", "getStock", "getSupplierAvatar", "getSupplierId", "getSupplierName", "getTags", "getUpdateTime", "getUrl", "getUserId", "getVideos", "getWantCount", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Double;DDILjava/lang/String;Ljava/lang/String;ILjava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)Lcom/saltfish/assistant/domain/entity/SaltfishProductEntity;", "equals", "", "other", "hashCode", "toString", "saltfish_release"})
public final class SaltfishProductEntity {
    private final long id = 0L;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String createTime = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String updateTime = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String name = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object description = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object images = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object videos = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object realshotImages = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object mainImages = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object detailImages = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object comments = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object tags = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Double purchasePrice = null;
    private final double sellingPrice = 0.0;
    private final double commission = 0.0;
    private final int stock = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String url = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String remark = null;
    private final int newLevel = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long categoryId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String brandName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String sourceCategoryName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object displayAttributes = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long userId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String supplierId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String supplierName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String supplierAvatar = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String sourceId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String sourcePlatform = null;
    private final int isShare = 0;
    private final int wantCount = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String originalName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String originalDescription = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String modelName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object skus = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object attributes = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.domain.entity.SaltfishProductEntity copy(long id, @org.jetbrains.annotations.Nullable
    java.lang.String createTime, @org.jetbrains.annotations.Nullable
    java.lang.String updateTime, @org.jetbrains.annotations.Nullable
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.Object description, @org.jetbrains.annotations.Nullable
    java.lang.Object images, @org.jetbrains.annotations.Nullable
    java.lang.Object videos, @org.jetbrains.annotations.Nullable
    java.lang.Object realshotImages, @org.jetbrains.annotations.Nullable
    java.lang.Object mainImages, @org.jetbrains.annotations.Nullable
    java.lang.Object detailImages, @org.jetbrains.annotations.Nullable
    java.lang.Object comments, @org.jetbrains.annotations.Nullable
    java.lang.Object tags, @org.jetbrains.annotations.Nullable
    java.lang.Double purchasePrice, double sellingPrice, double commission, int stock, @org.jetbrains.annotations.Nullable
    java.lang.String url, @org.jetbrains.annotations.Nullable
    java.lang.String remark, int newLevel, @org.jetbrains.annotations.Nullable
    java.lang.Long categoryId, @org.jetbrains.annotations.Nullable
    java.lang.String brandName, @org.jetbrains.annotations.Nullable
    java.lang.String sourceCategoryName, @org.jetbrains.annotations.Nullable
    java.lang.Object displayAttributes, @org.jetbrains.annotations.Nullable
    java.lang.Long userId, @org.jetbrains.annotations.Nullable
    java.lang.String supplierId, @org.jetbrains.annotations.Nullable
    java.lang.String supplierName, @org.jetbrains.annotations.Nullable
    java.lang.String supplierAvatar, @org.jetbrains.annotations.Nullable
    java.lang.String sourceId, @org.jetbrains.annotations.Nullable
    java.lang.String sourcePlatform, int isShare, int wantCount, @org.jetbrains.annotations.Nullable
    java.lang.String originalName, @org.jetbrains.annotations.Nullable
    java.lang.String originalDescription, @org.jetbrains.annotations.Nullable
    java.lang.String modelName, @org.jetbrains.annotations.Nullable
    java.lang.Object skus, @org.jetbrains.annotations.Nullable
    java.lang.Object attributes) {
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
    
    public SaltfishProductEntity() {
        super();
    }
    
    public SaltfishProductEntity(long id, @org.jetbrains.annotations.Nullable
    java.lang.String createTime, @org.jetbrains.annotations.Nullable
    java.lang.String updateTime, @org.jetbrains.annotations.Nullable
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.Object description, @org.jetbrains.annotations.Nullable
    java.lang.Object images, @org.jetbrains.annotations.Nullable
    java.lang.Object videos, @org.jetbrains.annotations.Nullable
    java.lang.Object realshotImages, @org.jetbrains.annotations.Nullable
    java.lang.Object mainImages, @org.jetbrains.annotations.Nullable
    java.lang.Object detailImages, @org.jetbrains.annotations.Nullable
    java.lang.Object comments, @org.jetbrains.annotations.Nullable
    java.lang.Object tags, @org.jetbrains.annotations.Nullable
    java.lang.Double purchasePrice, double sellingPrice, double commission, int stock, @org.jetbrains.annotations.Nullable
    java.lang.String url, @org.jetbrains.annotations.Nullable
    java.lang.String remark, int newLevel, @org.jetbrains.annotations.Nullable
    java.lang.Long categoryId, @org.jetbrains.annotations.Nullable
    java.lang.String brandName, @org.jetbrains.annotations.Nullable
    java.lang.String sourceCategoryName, @org.jetbrains.annotations.Nullable
    java.lang.Object displayAttributes, @org.jetbrains.annotations.Nullable
    java.lang.Long userId, @org.jetbrains.annotations.Nullable
    java.lang.String supplierId, @org.jetbrains.annotations.Nullable
    java.lang.String supplierName, @org.jetbrains.annotations.Nullable
    java.lang.String supplierAvatar, @org.jetbrains.annotations.Nullable
    java.lang.String sourceId, @org.jetbrains.annotations.Nullable
    java.lang.String sourcePlatform, int isShare, int wantCount, @org.jetbrains.annotations.Nullable
    java.lang.String originalName, @org.jetbrains.annotations.Nullable
    java.lang.String originalDescription, @org.jetbrains.annotations.Nullable
    java.lang.String modelName, @org.jetbrains.annotations.Nullable
    java.lang.Object skus, @org.jetbrains.annotations.Nullable
    java.lang.Object attributes) {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCreateTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUpdateTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getImages() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getVideos() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getRealshotImages() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getMainImages() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getDetailImages() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getComments() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTags() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getPurchasePrice() {
        return null;
    }
    
    public final double component14() {
        return 0.0;
    }
    
    public final double getSellingPrice() {
        return 0.0;
    }
    
    public final double component15() {
        return 0.0;
    }
    
    public final double getCommission() {
        return 0.0;
    }
    
    public final int component16() {
        return 0;
    }
    
    public final int getStock() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getRemark() {
        return null;
    }
    
    public final int component19() {
        return 0;
    }
    
    public final int getNewLevel() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component20() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getCategoryId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component21() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBrandName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component22() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSourceCategoryName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component23() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getDisplayAttributes() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component24() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getUserId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component25() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSupplierId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component26() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSupplierName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component27() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSupplierAvatar() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component28() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSourceId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component29() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSourcePlatform() {
        return null;
    }
    
    public final int component30() {
        return 0;
    }
    
    public final int isShare() {
        return 0;
    }
    
    public final int component31() {
        return 0;
    }
    
    public final int getWantCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component32() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getOriginalName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component33() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getOriginalDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component34() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getModelName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component35() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getSkus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component36() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getAttributes() {
        return null;
    }
}
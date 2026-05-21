package com.saltfish.assistant.domain.entity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\bM\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u00e3\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010$J\u000b\u0010J\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010S\u001a\u0004\u0018\u00010\u0016H\u00c6\u0003\u00a2\u0006\u0002\u0010&J\u0010\u0010T\u001a\u0004\u0018\u00010\u0016H\u00c6\u0003\u00a2\u0006\u0002\u0010&J\u000b\u0010U\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010W\u001a\u0004\u0018\u00010\u0016H\u00c6\u0003\u00a2\u0006\u0002\u0010&J\u000b\u0010X\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u0010\u0010Z\u001a\u0004\u0018\u00010\u001dH\u00c6\u0003\u00a2\u0006\u0002\u0010DJ\u000b\u0010[\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010b\u001a\u00020\u0007H\u00c6\u0003J\t\u0010c\u001a\u00020\u0007H\u00c6\u0003J\t\u0010d\u001a\u00020\u0007H\u00c6\u0003J\t\u0010e\u001a\u00020\u0007H\u00c6\u0003J\t\u0010f\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u00ec\u0002\u0010h\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010iJ\u0013\u0010j\u001a\u00020k2\b\u0010l\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010m\u001a\u00020\u001dH\u00d6\u0001J\t\u0010n\u001a\u00020\u0003H\u00d6\u0001R\u0015\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u00a2\u0006\n\n\u0002\u0010\'\u001a\u0004\b%\u0010&R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010)R\u0013\u0010 \u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010)R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010)R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010)R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010)R\u0013\u0010\"\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010)R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u0010\n\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010.R\u0011\u0010\u000b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010.R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010)R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u00103R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010)R\u0011\u0010\t\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010.R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010)R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010)R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010)R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010)R\u0015\u0010\u0019\u001a\u0004\u0018\u00010\u0016\u00a2\u0006\n\n\u0002\u0010\'\u001a\u0004\b>\u0010&R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010)R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010)R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010.R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010)R\u0015\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u00a2\u0006\n\n\u0002\u0010E\u001a\u0004\bC\u0010DR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010)R\u0013\u0010!\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u0010)R\u0013\u0010#\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u0010)R\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u00a2\u0006\n\n\u0002\u0010\'\u001a\u0004\bI\u0010&\u00a8\u0006o"}, d2 = {"Lcom/saltfish/assistant/domain/entity/SaltfishOrderEntity;", "", "id", "", "payId", "platform", "sellingPrice", "", "discountAmount", "originalPrice", "finalPrice", "freight", "shopName", "buyer", "recipient", "phone", "address", "remark", "tipoff", "expressNo", "expressCompany", "userId", "", "accountId", "goodsId", "productId", "goodsSku", "extend", "status", "", "payTime", "deliveryTime", "createTime", "updateTime", "expressStatus", "urgeTime", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDDDDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccountId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getAddress", "()Ljava/lang/String;", "getBuyer", "getCreateTime", "getDeliveryTime", "getDiscountAmount", "()D", "getExpressCompany", "getExpressNo", "getExpressStatus", "getExtend", "()Ljava/lang/Object;", "getFinalPrice", "getFreight", "getGoodsId", "getGoodsSku", "getId", "getOriginalPrice", "getPayId", "getPayTime", "getPhone", "getPlatform", "getProductId", "getRecipient", "getRemark", "getSellingPrice", "getShopName", "getStatus", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTipoff", "getUpdateTime", "getUrgeTime", "getUserId", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDDDDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/saltfish/assistant/domain/entity/SaltfishOrderEntity;", "equals", "", "other", "hashCode", "toString", "saltfish_release"})
public final class SaltfishOrderEntity {
    @org.jetbrains.annotations.Nullable
    private final java.lang.String id = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String payId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String platform = null;
    private final double sellingPrice = 0.0;
    private final double discountAmount = 0.0;
    private final double originalPrice = 0.0;
    private final double finalPrice = 0.0;
    private final double freight = 0.0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String shopName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String buyer = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String recipient = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String phone = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String address = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String remark = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String tipoff = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String expressNo = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String expressCompany = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long userId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long accountId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String goodsId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long productId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object goodsSku = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object extend = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer status = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String payTime = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String deliveryTime = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String createTime = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String updateTime = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String expressStatus = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String urgeTime = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.domain.entity.SaltfishOrderEntity copy(@org.jetbrains.annotations.Nullable
    java.lang.String id, @org.jetbrains.annotations.Nullable
    java.lang.String payId, @org.jetbrains.annotations.Nullable
    java.lang.String platform, double sellingPrice, double discountAmount, double originalPrice, double finalPrice, double freight, @org.jetbrains.annotations.Nullable
    java.lang.String shopName, @org.jetbrains.annotations.Nullable
    java.lang.String buyer, @org.jetbrains.annotations.Nullable
    java.lang.String recipient, @org.jetbrains.annotations.Nullable
    java.lang.String phone, @org.jetbrains.annotations.Nullable
    java.lang.String address, @org.jetbrains.annotations.Nullable
    java.lang.String remark, @org.jetbrains.annotations.Nullable
    java.lang.String tipoff, @org.jetbrains.annotations.Nullable
    java.lang.String expressNo, @org.jetbrains.annotations.Nullable
    java.lang.String expressCompany, @org.jetbrains.annotations.Nullable
    java.lang.Long userId, @org.jetbrains.annotations.Nullable
    java.lang.Long accountId, @org.jetbrains.annotations.Nullable
    java.lang.String goodsId, @org.jetbrains.annotations.Nullable
    java.lang.Long productId, @org.jetbrains.annotations.Nullable
    java.lang.Object goodsSku, @org.jetbrains.annotations.Nullable
    java.lang.Object extend, @org.jetbrains.annotations.Nullable
    java.lang.Integer status, @org.jetbrains.annotations.Nullable
    java.lang.String payTime, @org.jetbrains.annotations.Nullable
    java.lang.String deliveryTime, @org.jetbrains.annotations.Nullable
    java.lang.String createTime, @org.jetbrains.annotations.Nullable
    java.lang.String updateTime, @org.jetbrains.annotations.Nullable
    java.lang.String expressStatus, @org.jetbrains.annotations.Nullable
    java.lang.String urgeTime) {
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
    
    public SaltfishOrderEntity() {
        super();
    }
    
    public SaltfishOrderEntity(@org.jetbrains.annotations.Nullable
    java.lang.String id, @org.jetbrains.annotations.Nullable
    java.lang.String payId, @org.jetbrains.annotations.Nullable
    java.lang.String platform, double sellingPrice, double discountAmount, double originalPrice, double finalPrice, double freight, @org.jetbrains.annotations.Nullable
    java.lang.String shopName, @org.jetbrains.annotations.Nullable
    java.lang.String buyer, @org.jetbrains.annotations.Nullable
    java.lang.String recipient, @org.jetbrains.annotations.Nullable
    java.lang.String phone, @org.jetbrains.annotations.Nullable
    java.lang.String address, @org.jetbrains.annotations.Nullable
    java.lang.String remark, @org.jetbrains.annotations.Nullable
    java.lang.String tipoff, @org.jetbrains.annotations.Nullable
    java.lang.String expressNo, @org.jetbrains.annotations.Nullable
    java.lang.String expressCompany, @org.jetbrains.annotations.Nullable
    java.lang.Long userId, @org.jetbrains.annotations.Nullable
    java.lang.Long accountId, @org.jetbrains.annotations.Nullable
    java.lang.String goodsId, @org.jetbrains.annotations.Nullable
    java.lang.Long productId, @org.jetbrains.annotations.Nullable
    java.lang.Object goodsSku, @org.jetbrains.annotations.Nullable
    java.lang.Object extend, @org.jetbrains.annotations.Nullable
    java.lang.Integer status, @org.jetbrains.annotations.Nullable
    java.lang.String payTime, @org.jetbrains.annotations.Nullable
    java.lang.String deliveryTime, @org.jetbrains.annotations.Nullable
    java.lang.String createTime, @org.jetbrains.annotations.Nullable
    java.lang.String updateTime, @org.jetbrains.annotations.Nullable
    java.lang.String expressStatus, @org.jetbrains.annotations.Nullable
    java.lang.String urgeTime) {
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
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPayId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPlatform() {
        return null;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    public final double getSellingPrice() {
        return 0.0;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    public final double getDiscountAmount() {
        return 0.0;
    }
    
    public final double component6() {
        return 0.0;
    }
    
    public final double getOriginalPrice() {
        return 0.0;
    }
    
    public final double component7() {
        return 0.0;
    }
    
    public final double getFinalPrice() {
        return 0.0;
    }
    
    public final double component8() {
        return 0.0;
    }
    
    public final double getFreight() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getShopName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBuyer() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getRecipient() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPhone() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getRemark() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTipoff() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getExpressNo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getExpressCompany() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component18() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getUserId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component19() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getAccountId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getGoodsId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component21() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getProductId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component22() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getGoodsSku() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component23() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getExtend() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component24() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component25() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPayTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component26() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDeliveryTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component27() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCreateTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component28() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUpdateTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component29() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getExpressStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component30() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUrgeTime() {
        return null;
    }
}
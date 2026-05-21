package com.saltfish.assistant.domain.entity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b=\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u00f9\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\u0002\u0010\u001aJ\u000b\u00105\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u00107\u001a\u00020\u000fH\u00c6\u0003J\t\u00108\u001a\u00020\u000fH\u00c6\u0003J\t\u00109\u001a\u00020\u000fH\u00c6\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010>\u001a\u00020\u000fH\u00c6\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010@\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001cJ\u000b\u0010A\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u0010\u0010H\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001cJ\u0010\u0010I\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001cJ\u0082\u0002\u0010J\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u000f2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00c6\u0001\u00a2\u0006\u0002\u0010KJ\u0013\u0010L\u001a\u00020M2\b\u0010N\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010O\u001a\u00020\u000fH\u00d6\u0001J\t\u0010P\u001a\u00020\u0003H\u00d6\u0001R\u0015\u0010\f\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001e\u0010\u001cR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0016\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010 R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0011\u0010\u0011\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\"R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010 R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010 R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010 R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010 R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010 R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\"R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\'R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010 R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010\'R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b2\u0010\u001cR\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010 R\u0011\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010\"\u00a8\u0006Q"}, d2 = {"Lcom/saltfish/assistant/domain/entity/SaltfishPostEntity;", "", "id", "", "categoryId", "", "categoryName", "tags", "md5Name", "description", "images", "userId", "accountId", "platform", "showCount", "", "viewCount", "likeCount", "remark", "pushTime", "createTime", "updateTime", "collectCount", "name", "video", "updatedColumn", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "getAccountId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCategoryId", "getCategoryName", "()Ljava/lang/String;", "getCollectCount", "()I", "getCreateTime", "getDescription", "getId", "getImages", "()Ljava/lang/Object;", "getLikeCount", "getMd5Name", "getName", "getPlatform", "getPushTime", "getRemark", "getShowCount", "getTags", "getUpdateTime", "getUpdatedColumn", "getUserId", "getVideo", "getViewCount", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Lcom/saltfish/assistant/domain/entity/SaltfishPostEntity;", "equals", "", "other", "hashCode", "toString", "saltfish_debug"})
public final class SaltfishPostEntity {
    @org.jetbrains.annotations.Nullable
    private final java.lang.String id = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long categoryId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String categoryName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object tags = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String md5Name = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String description = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object images = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long userId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long accountId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String platform = null;
    private final int showCount = 0;
    private final int viewCount = 0;
    private final int likeCount = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String remark = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String pushTime = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String createTime = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String updateTime = null;
    private final int collectCount = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String name = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String video = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object updatedColumn = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.domain.entity.SaltfishPostEntity copy(@org.jetbrains.annotations.Nullable
    java.lang.String id, @org.jetbrains.annotations.Nullable
    java.lang.Long categoryId, @org.jetbrains.annotations.Nullable
    java.lang.String categoryName, @org.jetbrains.annotations.Nullable
    java.lang.Object tags, @org.jetbrains.annotations.Nullable
    java.lang.String md5Name, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.Nullable
    java.lang.Object images, @org.jetbrains.annotations.Nullable
    java.lang.Long userId, @org.jetbrains.annotations.Nullable
    java.lang.Long accountId, @org.jetbrains.annotations.Nullable
    java.lang.String platform, int showCount, int viewCount, int likeCount, @org.jetbrains.annotations.Nullable
    java.lang.String remark, @org.jetbrains.annotations.Nullable
    java.lang.String pushTime, @org.jetbrains.annotations.Nullable
    java.lang.String createTime, @org.jetbrains.annotations.Nullable
    java.lang.String updateTime, int collectCount, @org.jetbrains.annotations.Nullable
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.String video, @org.jetbrains.annotations.Nullable
    java.lang.Object updatedColumn) {
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
    
    public SaltfishPostEntity() {
        super();
    }
    
    public SaltfishPostEntity(@org.jetbrains.annotations.Nullable
    java.lang.String id, @org.jetbrains.annotations.Nullable
    java.lang.Long categoryId, @org.jetbrains.annotations.Nullable
    java.lang.String categoryName, @org.jetbrains.annotations.Nullable
    java.lang.Object tags, @org.jetbrains.annotations.Nullable
    java.lang.String md5Name, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.Nullable
    java.lang.Object images, @org.jetbrains.annotations.Nullable
    java.lang.Long userId, @org.jetbrains.annotations.Nullable
    java.lang.Long accountId, @org.jetbrains.annotations.Nullable
    java.lang.String platform, int showCount, int viewCount, int likeCount, @org.jetbrains.annotations.Nullable
    java.lang.String remark, @org.jetbrains.annotations.Nullable
    java.lang.String pushTime, @org.jetbrains.annotations.Nullable
    java.lang.String createTime, @org.jetbrains.annotations.Nullable
    java.lang.String updateTime, int collectCount, @org.jetbrains.annotations.Nullable
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.String video, @org.jetbrains.annotations.Nullable
    java.lang.Object updatedColumn) {
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
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCategoryName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTags() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getMd5Name() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getImages() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getUserId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getAccountId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPlatform() {
        return null;
    }
    
    public final int component11() {
        return 0;
    }
    
    public final int getShowCount() {
        return 0;
    }
    
    public final int component12() {
        return 0;
    }
    
    public final int getViewCount() {
        return 0;
    }
    
    public final int component13() {
        return 0;
    }
    
    public final int getLikeCount() {
        return 0;
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
    public final java.lang.String getPushTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCreateTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUpdateTime() {
        return null;
    }
    
    public final int component18() {
        return 0;
    }
    
    public final int getCollectCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getVideo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component21() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getUpdatedColumn() {
        return null;
    }
}
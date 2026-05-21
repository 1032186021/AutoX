package com.saltfish.assistant.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.saltfish.assistant.domain.model.TaskEntity;
import com.saltfish.assistant.domain.model.TaskStatus;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TaskDao_Impl implements TaskDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TaskEntity> __insertionAdapterOfTaskEntity;

  private final EntityDeletionOrUpdateAdapter<TaskEntity> __updateAdapterOfTaskEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateStatus;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldCompleted;

  public TaskDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTaskEntity = new EntityInsertionAdapter<TaskEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `tasks` (`id`,`platform`,`taskType`,`params`,`priority`,`status`,`createdAt`,`startedAt`,`completedAt`,`errorMessage`,`retryCount`,`maxRetries`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TaskEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getPlatform() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPlatform());
        }
        if (value.getTaskType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTaskType());
        }
        if (value.getParams() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getParams());
        }
        stmt.bindLong(5, value.getPriority());
        if (value.getStatus() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, __TaskStatus_enumToString(value.getStatus()));
        }
        stmt.bindLong(7, value.getCreatedAt());
        if (value.getStartedAt() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getStartedAt());
        }
        if (value.getCompletedAt() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.getCompletedAt());
        }
        if (value.getErrorMessage() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getErrorMessage());
        }
        stmt.bindLong(11, value.getRetryCount());
        stmt.bindLong(12, value.getMaxRetries());
      }
    };
    this.__updateAdapterOfTaskEntity = new EntityDeletionOrUpdateAdapter<TaskEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `tasks` SET `id` = ?,`platform` = ?,`taskType` = ?,`params` = ?,`priority` = ?,`status` = ?,`createdAt` = ?,`startedAt` = ?,`completedAt` = ?,`errorMessage` = ?,`retryCount` = ?,`maxRetries` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TaskEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getPlatform() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPlatform());
        }
        if (value.getTaskType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTaskType());
        }
        if (value.getParams() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getParams());
        }
        stmt.bindLong(5, value.getPriority());
        if (value.getStatus() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, __TaskStatus_enumToString(value.getStatus()));
        }
        stmt.bindLong(7, value.getCreatedAt());
        if (value.getStartedAt() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getStartedAt());
        }
        if (value.getCompletedAt() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.getCompletedAt());
        }
        if (value.getErrorMessage() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getErrorMessage());
        }
        stmt.bindLong(11, value.getRetryCount());
        stmt.bindLong(12, value.getMaxRetries());
        stmt.bindLong(13, value.getId());
      }
    };
    this.__preparedStmtOfUpdateStatus = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE tasks SET status = ?, errorMessage = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteOldCompleted = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM tasks WHERE status IN ('COMPLETED', 'FAILED', 'CANCELLED') AND completedAt < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final TaskEntity task, final Continuation<? super Long> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfTaskEntity.insertAndReturnId(task);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object update(final TaskEntity task, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTaskEntity.handle(task);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateStatus(final long id, final TaskStatus status, final String error,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateStatus.acquire();
        int _argIndex = 1;
        if (status == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, __TaskStatus_enumToString(status));
        }
        _argIndex = 2;
        if (error == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, error);
        }
        _argIndex = 3;
        _stmt.bindLong(_argIndex, id);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdateStatus.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteOldCompleted(final long before,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldCompleted.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, before);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteOldCompleted.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<TaskEntity>> observeAll() {
    final String _sql = "SELECT * FROM tasks ORDER BY priority DESC, createdAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"tasks"}, new Callable<List<TaskEntity>>() {
      @Override
      public List<TaskEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPlatform = CursorUtil.getColumnIndexOrThrow(_cursor, "platform");
          final int _cursorIndexOfTaskType = CursorUtil.getColumnIndexOrThrow(_cursor, "taskType");
          final int _cursorIndexOfParams = CursorUtil.getColumnIndexOrThrow(_cursor, "params");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "startedAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfErrorMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "errorMessage");
          final int _cursorIndexOfRetryCount = CursorUtil.getColumnIndexOrThrow(_cursor, "retryCount");
          final int _cursorIndexOfMaxRetries = CursorUtil.getColumnIndexOrThrow(_cursor, "maxRetries");
          final List<TaskEntity> _result = new ArrayList<TaskEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TaskEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPlatform;
            if (_cursor.isNull(_cursorIndexOfPlatform)) {
              _tmpPlatform = null;
            } else {
              _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
            }
            final String _tmpTaskType;
            if (_cursor.isNull(_cursorIndexOfTaskType)) {
              _tmpTaskType = null;
            } else {
              _tmpTaskType = _cursor.getString(_cursorIndexOfTaskType);
            }
            final String _tmpParams;
            if (_cursor.isNull(_cursorIndexOfParams)) {
              _tmpParams = null;
            } else {
              _tmpParams = _cursor.getString(_cursorIndexOfParams);
            }
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final TaskStatus _tmpStatus;
            _tmpStatus = __TaskStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpStartedAt;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmpStartedAt = null;
            } else {
              _tmpStartedAt = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final String _tmpErrorMessage;
            if (_cursor.isNull(_cursorIndexOfErrorMessage)) {
              _tmpErrorMessage = null;
            } else {
              _tmpErrorMessage = _cursor.getString(_cursorIndexOfErrorMessage);
            }
            final int _tmpRetryCount;
            _tmpRetryCount = _cursor.getInt(_cursorIndexOfRetryCount);
            final int _tmpMaxRetries;
            _tmpMaxRetries = _cursor.getInt(_cursorIndexOfMaxRetries);
            _item = new TaskEntity(_tmpId,_tmpPlatform,_tmpTaskType,_tmpParams,_tmpPriority,_tmpStatus,_tmpCreatedAt,_tmpStartedAt,_tmpCompletedAt,_tmpErrorMessage,_tmpRetryCount,_tmpMaxRetries);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getNextPending(final Continuation<? super TaskEntity> continuation) {
    final String _sql = "SELECT * FROM tasks WHERE status = 'PENDING' ORDER BY priority DESC, createdAt ASC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TaskEntity>() {
      @Override
      public TaskEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPlatform = CursorUtil.getColumnIndexOrThrow(_cursor, "platform");
          final int _cursorIndexOfTaskType = CursorUtil.getColumnIndexOrThrow(_cursor, "taskType");
          final int _cursorIndexOfParams = CursorUtil.getColumnIndexOrThrow(_cursor, "params");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "startedAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfErrorMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "errorMessage");
          final int _cursorIndexOfRetryCount = CursorUtil.getColumnIndexOrThrow(_cursor, "retryCount");
          final int _cursorIndexOfMaxRetries = CursorUtil.getColumnIndexOrThrow(_cursor, "maxRetries");
          final TaskEntity _result;
          if(_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPlatform;
            if (_cursor.isNull(_cursorIndexOfPlatform)) {
              _tmpPlatform = null;
            } else {
              _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
            }
            final String _tmpTaskType;
            if (_cursor.isNull(_cursorIndexOfTaskType)) {
              _tmpTaskType = null;
            } else {
              _tmpTaskType = _cursor.getString(_cursorIndexOfTaskType);
            }
            final String _tmpParams;
            if (_cursor.isNull(_cursorIndexOfParams)) {
              _tmpParams = null;
            } else {
              _tmpParams = _cursor.getString(_cursorIndexOfParams);
            }
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final TaskStatus _tmpStatus;
            _tmpStatus = __TaskStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpStartedAt;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmpStartedAt = null;
            } else {
              _tmpStartedAt = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final String _tmpErrorMessage;
            if (_cursor.isNull(_cursorIndexOfErrorMessage)) {
              _tmpErrorMessage = null;
            } else {
              _tmpErrorMessage = _cursor.getString(_cursorIndexOfErrorMessage);
            }
            final int _tmpRetryCount;
            _tmpRetryCount = _cursor.getInt(_cursorIndexOfRetryCount);
            final int _tmpMaxRetries;
            _tmpMaxRetries = _cursor.getInt(_cursorIndexOfMaxRetries);
            _result = new TaskEntity(_tmpId,_tmpPlatform,_tmpTaskType,_tmpParams,_tmpPriority,_tmpStatus,_tmpCreatedAt,_tmpStartedAt,_tmpCompletedAt,_tmpErrorMessage,_tmpRetryCount,_tmpMaxRetries);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object getById(final long id, final Continuation<? super TaskEntity> continuation) {
    final String _sql = "SELECT * FROM tasks WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TaskEntity>() {
      @Override
      public TaskEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPlatform = CursorUtil.getColumnIndexOrThrow(_cursor, "platform");
          final int _cursorIndexOfTaskType = CursorUtil.getColumnIndexOrThrow(_cursor, "taskType");
          final int _cursorIndexOfParams = CursorUtil.getColumnIndexOrThrow(_cursor, "params");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "startedAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfErrorMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "errorMessage");
          final int _cursorIndexOfRetryCount = CursorUtil.getColumnIndexOrThrow(_cursor, "retryCount");
          final int _cursorIndexOfMaxRetries = CursorUtil.getColumnIndexOrThrow(_cursor, "maxRetries");
          final TaskEntity _result;
          if(_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPlatform;
            if (_cursor.isNull(_cursorIndexOfPlatform)) {
              _tmpPlatform = null;
            } else {
              _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
            }
            final String _tmpTaskType;
            if (_cursor.isNull(_cursorIndexOfTaskType)) {
              _tmpTaskType = null;
            } else {
              _tmpTaskType = _cursor.getString(_cursorIndexOfTaskType);
            }
            final String _tmpParams;
            if (_cursor.isNull(_cursorIndexOfParams)) {
              _tmpParams = null;
            } else {
              _tmpParams = _cursor.getString(_cursorIndexOfParams);
            }
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final TaskStatus _tmpStatus;
            _tmpStatus = __TaskStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpStartedAt;
            if (_cursor.isNull(_cursorIndexOfStartedAt)) {
              _tmpStartedAt = null;
            } else {
              _tmpStartedAt = _cursor.getLong(_cursorIndexOfStartedAt);
            }
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final String _tmpErrorMessage;
            if (_cursor.isNull(_cursorIndexOfErrorMessage)) {
              _tmpErrorMessage = null;
            } else {
              _tmpErrorMessage = _cursor.getString(_cursorIndexOfErrorMessage);
            }
            final int _tmpRetryCount;
            _tmpRetryCount = _cursor.getInt(_cursorIndexOfRetryCount);
            final int _tmpMaxRetries;
            _tmpMaxRetries = _cursor.getInt(_cursorIndexOfMaxRetries);
            _result = new TaskEntity(_tmpId,_tmpPlatform,_tmpTaskType,_tmpParams,_tmpPriority,_tmpStatus,_tmpCreatedAt,_tmpStartedAt,_tmpCompletedAt,_tmpErrorMessage,_tmpRetryCount,_tmpMaxRetries);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __TaskStatus_enumToString(final TaskStatus _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case PENDING: return "PENDING";
      case RUNNING: return "RUNNING";
      case COMPLETED: return "COMPLETED";
      case FAILED: return "FAILED";
      case CANCELLED: return "CANCELLED";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private TaskStatus __TaskStatus_stringToEnum(final String _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case "PENDING": return TaskStatus.PENDING;
      case "RUNNING": return TaskStatus.RUNNING;
      case "COMPLETED": return TaskStatus.COMPLETED;
      case "FAILED": return TaskStatus.FAILED;
      case "CANCELLED": return TaskStatus.CANCELLED;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}

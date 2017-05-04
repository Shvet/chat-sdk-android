package tk.wanderingdevelopment.chatsdkcore.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.braunster.chatsdk.dao.BUser;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BUSER".
*/
public class BUserDao extends AbstractDao<BUser, Long> {

    public static final String TABLENAME = "BUSER";

    /**
     * Properties of entity BUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property EntityID = new Property(1, String.class, "entityID", false, "ENTITY_ID");
        public final static Property AuthenticationType = new Property(2, Integer.class, "authenticationType", false, "AUTHENTICATION_TYPE");
        public final static Property MessageColor = new Property(3, String.class, "messageColor", false, "MESSAGE_COLOR");
        public final static Property LastOnline = new Property(4, java.util.Date.class, "lastOnline", false, "LAST_ONLINE");
        public final static Property LastUpdated = new Property(5, java.util.Date.class, "lastUpdated", false, "LAST_UPDATED");
        public final static Property Online = new Property(6, Boolean.class, "online", false, "ONLINE");
        public final static Property Metadata = new Property(7, String.class, "metadata", false, "METADATA");
    }

    private DaoSession daoSession;


    public BUserDao(DaoConfig config) {
        super(config);
    }
    
    public BUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BUSER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ENTITY_ID\" TEXT," + // 1: entityID
                "\"AUTHENTICATION_TYPE\" INTEGER," + // 2: authenticationType
                "\"MESSAGE_COLOR\" TEXT," + // 3: messageColor
                "\"LAST_ONLINE\" INTEGER," + // 4: lastOnline
                "\"LAST_UPDATED\" INTEGER," + // 5: lastUpdated
                "\"ONLINE\" INTEGER," + // 6: online
                "\"METADATA\" TEXT);"); // 7: metadata
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BUSER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String entityID = entity.getEntityID();
        if (entityID != null) {
            stmt.bindString(2, entityID);
        }
 
        Integer authenticationType = entity.getAuthenticationType();
        if (authenticationType != null) {
            stmt.bindLong(3, authenticationType);
        }
 
        String messageColor = entity.getMessageColor();
        if (messageColor != null) {
            stmt.bindString(4, messageColor);
        }
 
        java.util.Date lastOnline = entity.getLastOnline();
        if (lastOnline != null) {
            stmt.bindLong(5, lastOnline.getTime());
        }
 
        java.util.Date lastUpdated = entity.getLastUpdated();
        if (lastUpdated != null) {
            stmt.bindLong(6, lastUpdated.getTime());
        }
 
        Boolean online = entity.getOnline();
        if (online != null) {
            stmt.bindLong(7, online ? 1L: 0L);
        }
 
        String metadata = entity.getMetadata();
        if (metadata != null) {
            stmt.bindString(8, metadata);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String entityID = entity.getEntityID();
        if (entityID != null) {
            stmt.bindString(2, entityID);
        }
 
        Integer authenticationType = entity.getAuthenticationType();
        if (authenticationType != null) {
            stmt.bindLong(3, authenticationType);
        }
 
        String messageColor = entity.getMessageColor();
        if (messageColor != null) {
            stmt.bindString(4, messageColor);
        }
 
        java.util.Date lastOnline = entity.getLastOnline();
        if (lastOnline != null) {
            stmt.bindLong(5, lastOnline.getTime());
        }
 
        java.util.Date lastUpdated = entity.getLastUpdated();
        if (lastUpdated != null) {
            stmt.bindLong(6, lastUpdated.getTime());
        }
 
        Boolean online = entity.getOnline();
        if (online != null) {
            stmt.bindLong(7, online ? 1L: 0L);
        }
 
        String metadata = entity.getMetadata();
        if (metadata != null) {
            stmt.bindString(8, metadata);
        }
    }

    @Override
    protected final void attachEntity(BUser entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public BUser readEntity(Cursor cursor, int offset) {
        BUser entity = new BUser( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // entityID
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // authenticationType
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // messageColor
            cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)), // lastOnline
            cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)), // lastUpdated
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0, // online
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // metadata
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BUser entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setEntityID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAuthenticationType(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setMessageColor(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setLastOnline(cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)));
        entity.setLastUpdated(cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)));
        entity.setOnline(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0);
        entity.setMetadata(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BUser entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BUser entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BUser entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}

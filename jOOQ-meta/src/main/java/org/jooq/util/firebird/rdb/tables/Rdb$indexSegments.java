/**
 * This class is generated by jOOQ
 */
package org.jooq.util.firebird.rdb.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.5.0"},
                            comments = "This class is generated by jOOQ")
public class Rdb$indexSegments extends org.jooq.impl.TableImpl<org.jooq.Record> {

	private static final long serialVersionUID = -627728362;

	/**
	 * The singleton instance of RDB$INDEX_SEGMENTS
	 */
	public static final org.jooq.util.firebird.rdb.tables.Rdb$indexSegments RDB$INDEX_SEGMENTS = new org.jooq.util.firebird.rdb.tables.Rdb$indexSegments();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.Record> getRecordType() {
		return org.jooq.Record.class;
	}

	/**
	 * The table column <code>RDB$INDEX_SEGMENTS.RDB$INDEX_NAME</code>
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.String> RDB$INDEX_NAME = createField("RDB$INDEX_NAME", org.jooq.impl.SQLDataType.CHAR, this);

	/**
	 * The table column <code>RDB$INDEX_SEGMENTS.RDB$FIELD_NAME</code>
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.String> RDB$FIELD_NAME = createField("RDB$FIELD_NAME", org.jooq.impl.SQLDataType.CHAR, this);

	/**
	 * The table column <code>RDB$INDEX_SEGMENTS.RDB$FIELD_POSITION</code>
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.Short> RDB$FIELD_POSITION = createField("RDB$FIELD_POSITION", org.jooq.impl.SQLDataType.SMALLINT, this);

	/**
	 * The table column <code>RDB$INDEX_SEGMENTS.RDB$STATISTICS</code>
	 * <p>
	 * The SQL type of this item (DOUBLE) could not be mapped.<br/>
	 * Deserialising this field might not work!
	 */
	public final org.jooq.TableField<org.jooq.Record, java.lang.Object> RDB$STATISTICS = createField("RDB$STATISTICS", org.jooq.util.firebird.FirebirdDataType.getDefaultDataType("DOUBLE"), this);

	public Rdb$indexSegments() {
		super("RDB$INDEX_SEGMENTS");
	}

	public Rdb$indexSegments(java.lang.String alias) {
		super(alias, null, org.jooq.util.firebird.rdb.tables.Rdb$indexSegments.RDB$INDEX_SEGMENTS);
	}

	@Override
	public org.jooq.util.firebird.rdb.tables.Rdb$indexSegments as(java.lang.String alias) {
		return new org.jooq.util.firebird.rdb.tables.Rdb$indexSegments(alias);
	}
}
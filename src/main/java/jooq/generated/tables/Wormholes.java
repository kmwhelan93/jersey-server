/**
 * This class is generated by jOOQ
 */
package jooq.generated.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Wormholes extends org.jooq.impl.TableImpl<jooq.generated.tables.records.WormholesRecord> {

	private static final long serialVersionUID = 1967792823;

	/**
	 * The reference instance of <code>maimon.wormholes</code>
	 */
	public static final jooq.generated.tables.Wormholes WORMHOLES = new jooq.generated.tables.Wormholes();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<jooq.generated.tables.records.WormholesRecord> getRecordType() {
		return jooq.generated.tables.records.WormholesRecord.class;
	}

	/**
	 * The column <code>maimon.wormholes.wormhole_id</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.WormholesRecord, java.lang.Integer> WORMHOLE_ID = createField("wormhole_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>maimon.wormholes.base_id</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.WormholesRecord, java.lang.Integer> BASE_ID = createField("base_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>maimon.wormholes.relative_coord_x</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.WormholesRecord, java.lang.Integer> RELATIVE_COORD_X = createField("relative_coord_x", org.jooq.impl.SQLDataType.INTEGER.defaulted(true), this, "");

	/**
	 * The column <code>maimon.wormholes.relative_coord_y</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.WormholesRecord, java.lang.Integer> RELATIVE_COORD_Y = createField("relative_coord_y", org.jooq.impl.SQLDataType.INTEGER.defaulted(true), this, "");

	/**
	 * The column <code>maimon.wormholes.connected_wormhole_id</code>.
	 */
	public final org.jooq.TableField<jooq.generated.tables.records.WormholesRecord, java.lang.Integer> CONNECTED_WORMHOLE_ID = createField("connected_wormhole_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>maimon.wormholes</code> table reference
	 */
	public Wormholes() {
		this("wormholes", null);
	}

	/**
	 * Create an aliased <code>maimon.wormholes</code> table reference
	 */
	public Wormholes(java.lang.String alias) {
		this(alias, jooq.generated.tables.Wormholes.WORMHOLES);
	}

	private Wormholes(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.WormholesRecord> aliased) {
		this(alias, aliased, null);
	}

	private Wormholes(java.lang.String alias, org.jooq.Table<jooq.generated.tables.records.WormholesRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, jooq.generated.Maimon.MAIMON, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<jooq.generated.tables.records.WormholesRecord, java.lang.Integer> getIdentity() {
		return jooq.generated.Keys.IDENTITY_WORMHOLES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<jooq.generated.tables.records.WormholesRecord> getPrimaryKey() {
		return jooq.generated.Keys.KEY_WORMHOLES_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<jooq.generated.tables.records.WormholesRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<jooq.generated.tables.records.WormholesRecord>>asList(jooq.generated.Keys.KEY_WORMHOLES_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<jooq.generated.tables.records.WormholesRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<jooq.generated.tables.records.WormholesRecord, ?>>asList(jooq.generated.Keys.WORMHOLES_IBFK_1, jooq.generated.Keys.WORMHOLES_IBFK_2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public jooq.generated.tables.Wormholes as(java.lang.String alias) {
		return new jooq.generated.tables.Wormholes(alias, this);
	}

	/**
	 * Rename this table
	 */
	public jooq.generated.tables.Wormholes rename(java.lang.String name) {
		return new jooq.generated.tables.Wormholes(name, null);
	}
}
